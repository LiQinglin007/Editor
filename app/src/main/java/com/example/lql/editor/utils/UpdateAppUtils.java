package com.example.lql.editor.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.bean.VersionCodeBean;
import com.example.lql.editor.myhttp.HttpUtils;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SaveFileCallBack;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类描述：工具类   版本更新
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */

public class UpdateAppUtils {
    private static ProgressDialog pDialog;
    private static String nowVersion;
    private static ProgressDialog progressDialog;
    public static int versionCode = 0;//当前版本号
    public static String versionName = "";//当前版本名称
    public static Context mContext;
    public static String AppPath = Environment.getExternalStorageDirectory().getPath() + "/Editor/apk";

    /**
     * 返回当前程序版本名、版本号
     */
    public static void UpdateAppUtils(Context mContext,boolean IsToast) {
        UpdateAppUtils.mContext = mContext;
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                T.shortToast(mContext, "获取当前版本的错误");
            } else {
                //去检查更新（调接口）
                getVersionCode(IsToast);
            }
        } catch (Exception e) {
            T.shortToast(mContext, "获取当前版本的错误");
        }
    }


    /**
     * 去获取服务器存储的数据
     */

    /**
     //                     * 1、判断是不是主动更新   主动更新----》弹框
     //                     * 2、不是主动更新     判断是不是需要强制更新    强制更新----》弹框
     //                     * 3、不是强制更新    判断上次存储的版本号   如果上次存储的版本号比这次的版本号低-----》弹框
     //                     */

    private static void getVersionCode(final boolean IsToast) {
        SendRequest.UpdateVersion(new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.e("response",response);
                if(response.contains("<html>")){
                    T.shortToast(mContext,"服务器异常");
                    return;
                }
                VersionCodeBean mVersionCodeBean= JSON.parseObject(response,VersionCodeBean.class);
                if(mVersionCodeBean.getResultCode()==0){
//                    1：强制更新   0：不是

                    String Version_name = mVersionCodeBean.getData().getVersionNum();//版本名称
                    String Url = mVersionCodeBean.getData().getVersionUrl();//下载地址
                    String info = mVersionCodeBean.getData().getVersionReason();  //更新说明
                    int Forced = mVersionCodeBean.getData().getType();// 1：强制更新   0：不是
                    int Version_no = mVersionCodeBean.getData().getVersionId();//版本号


                        /**
                         * 1、判断是不是主动更新   主动更新----》弹框
                         * 2、不是主动更新     判断是不是需要强制更新    强制更新----》弹框
                         * 3、不是强制更新    判断上次存储的版本号   如果上次存储的版本号比这次的版本号低-----》弹框
                         */
                        if (Version_no > versionCode) {
                            //判断是主动更新还是被动提示更新
                            if(PublicStaticData.UpdataApp){//需要
                                setUpDialog(Version_name, Url, info, Forced,Version_no);
                            }else{   //被动更新
                                //不需要强制更新
                                if(Forced==0){
                                    //检查上次提示的版本号，如果和这次的版本号相同，就不提示了，如果比上次的版本号大，就提示
                                    int LastVersion_no=PreferenceUtils.getInt("LastVersion_no",1);
                                    if(Version_no>LastVersion_no){
                                        setUpDialog(Version_name, Url, info, Forced,Version_no);
                                    }
                                }else{
                                    //需要强制更新的
                                    setUpDialog(Version_name, Url, info, Forced,Version_no);
                                }
                            }
                        } else {
                            if(IsToast){
                                T.shortToast(mContext, "当前是最新版本");
                            }
                        }
                }else{
                    T.shortToast(mContext,mVersionCodeBean.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                T.shortToast(mContext,"亲，请检查网络");
            }
        });
    }

    /**
     * @param versionname 地址中版本的名字
     * @param downloadurl 下载包的地址
     * @param desc        版本的描述
     * @param forced      是否强制更新
     */
    protected static void setUpDialog(String versionname, final String downloadurl,
                                      String desc, final int forced, final int versionCode) {
        AlertDialog dialog = new AlertDialog.Builder(mContext).setCancelable(false)
                .setTitle("更新到 " + versionname ).setMessage(desc)
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        DownLoad(downloadurl);
                    }
                })
                .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //是不是强制更新   强制更新，点取消按钮，退出程序
                        // 1：强制更新   0：不是
                        if (forced != 0) {
                            T.shortToast(mContext, "此版本需要强制更新，程序即将退出");
                            mHandler.sendEmptyMessageDelayed(0, 2500);
                        } else {
                            PreferenceUtils.setInt("LastVersion_no",versionCode);
                            dialog.dismiss();
                        }
                    }
                })
                .create();
        dialog.show();
    }
    private static File file=null;

    private static void DownLoad(String downloadurl) {
        proDialogShow(mContext, "下载中...");
        //判断外部存储是否可以读写
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            mhandler.sendEmptyMessage(2);
            return;
        }
        Request request = new Request.Builder()
                .url( MyUrl.Pic+downloadurl)
                .tag(mContext)
                .build();
        Call call = HttpUtils.getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                T.shortToast(mContext,"亲，请检查网络");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 file=SendRequest.saveFile(2,response,"学术圈.apk", new SaveFileCallBack() {
                    @Override
                    public void myFileCallBack(int code) {
                        if(code==0){
                            mhandler.sendEmptyMessage(0);
                        }else{
                            mhandler.sendEmptyMessage(1);
                        }
                    }
                });
            }
        });
    }




    private static void ToApk(File file){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(new File(String.valueOf(file))),
                "application/vnd.android.package-archive");//打开的文件类型
        mContext.startActivity(intent);
    }




    private static Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                //保存成功
                PDialogHide();
                ToApk(file);
            }else if(msg.what==1){
                //保存失败
                PDialogHide();
                showDialog("保存失败");
            }else if(msg.what==2){
                //当前存储卡不可用
                PDialogHide();
                showDialog("当前存储卡不可用");
            }
        }
    };


    private static  void showDialog(String message){
        new zhangphil.iosdialog.widget.AlertDialog(mContext)
                .builder().setMsg(message)
                .setTitle("提示")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }

    private static void proDialogShow(Context context, String msg) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(msg);
        pDialog.show();
    }

    private static void PDialogHide() {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            for (int i = 0; i < PublicStaticData.mActivityList.size(); i++) {
                PublicStaticData.mActivityList.get(i).finish();
            }
            super.handleMessage(msg);
        }
    };
}
