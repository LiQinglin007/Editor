package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.OkHttpClientManager;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.utils.ChoosePicUtil;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import zhangphil.iosdialog.widget.ActionSheetDialog;

import static com.example.lql.editor.utils.ChoosePicUtil.MATCHING_CODE_CAMERA;
import static com.example.lql.editor.utils.ChoosePicUtil.MATCHING_CODE_GALLERY;


/**
 * 类描述：实名认证页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class AuthenticationActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private TextView authenticationcontenttv;
    private ImageView authenticationimg1;
    private ImageView authenticationimg2;
    private ImageView authenticationimg3;
    private android.widget.Button authenticationsubmitbut;
    private TextView studioapply_name_tv;


    int ButtonNumber = -1;//记录第几个按钮
    File file1 = null, file2 = null, file3 = null;//
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_authentication);
        pDialog = new ProgressDialog(AuthenticationActivity.this);
        pDialog.setMessage("加载中...");


        initView();
    }

    private void initView() {

        title = (TextView) findViewById(R.id.title_title);
        title.setText("实名认证");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        this.authenticationsubmitbut = (Button) findViewById(R.id.authentication_submit_but);
        this.authenticationimg3 = (ImageView) findViewById(R.id.authentication_img3);
        this.authenticationimg2 = (ImageView) findViewById(R.id.authentication_img2);
        this.authenticationimg1 = (ImageView) findViewById(R.id.authentication_img1);
        this.authenticationcontenttv = (TextView) findViewById(R.id.authentication_content_tv);
        this.studioapply_name_tv = (TextView) findViewById(R.id.studioapply_name_tv);

        leftback.setOnClickListener(this);


        //        "real": 0							0:正在审核1：已实名认证2：未认证
        authenticationcontenttv.setText("提示：请上传清晰有效的二代身份证照");
        this.authenticationsubmitbut.setOnClickListener(this);
        this.authenticationimg3.setOnClickListener(this);
        this.authenticationimg2.setOnClickListener(this);
        this.authenticationimg1.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.authentication_img3:
                ButtonNumber = 3;
                showMyDialog();
                break;
            case R.id.authentication_img2:
                ButtonNumber = 2;
                showMyDialog();
                break;
            case R.id.authentication_img1:
                ButtonNumber = 1;
                showMyDialog();
                break;
            case R.id.authentication_submit_but:
                String Userid = "";
                if (PreferenceUtils.getBoolean("IsLogin", false)) {
                    Userid = PreferenceUtils.getString("UserId", "");
                } else {
                    Userid = "0";
                }
                String name=studioapply_name_tv.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    T.shortToast(getApplicationContext(),"请填写真实姓名");
                    return;
                }
                pDialog.show();
                authenticationsubmitbut.setClickable(false);
                if (file1 != null && file2 != null && file3 != null) {
                    SendRequest.UserImgurlUpload(Userid,name, file1, file2, file3, new OkHttpClientManager.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            pDialog.hide();
                            authenticationsubmitbut.setClickable(true);
                            T.shortToast(getApplicationContext(), "亲，请检查网络");
                        }

                        @Override
                        public void onResponse(String response) {
                            authenticationsubmitbut.setClickable(true);
                            pDialog.hide();
                            if (response.contains("<html>")) {
                                T.shortToast(getApplicationContext(), "服务器异常");
                                return;
                            }
                            MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                            if (myBasebean.getResultCode() == 0) {
                                finish();
                            }
                            T.shortToast(getApplicationContext(), myBasebean.getMsg());
                        }
                    });

                } else {
                    authenticationsubmitbut.setClickable(true);
                    pDialog.hide();
                    T.shortToast(getApplicationContext(), "请上传完整图片");
                }
        }
    }


    /**
     * 显示底部弹出对话框
     */
    public void showMyDialog() {
        new ActionSheetDialog(this)
                .builder()
                .setTitle("选择图片")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ChoosePicUtil.startActivityFor(MATCHING_CODE_GALLERY, AuthenticationActivity.this);
                            }
                        })
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ChoosePicUtil.startActivityFor(MATCHING_CODE_CAMERA, AuthenticationActivity.this);
                            }
                        }).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Bitmap bitmap = ChoosePicUtil.getBitmapFromResult(requestCode, resultCode, data, AuthenticationActivity.this, false, false);
        ChoosePicUtil.BitmapRecycle();
        final String path = ChoosePicUtil.getPathFromResult(requestCode, resultCode, data, AuthenticationActivity.this);
        if (path == null) {
            return;
        }


        try{
            readBitmapFromFilePath(path).getWidth();
        }catch(Exception e) {
            T.shortToast(getApplicationContext(),"图片出错了");
            return;
        }

        Luban.get(this)
                .load(new File(path))                     //传人要压缩的图片
                .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                .setCompressListener(new OnCompressListener() { //设置回调

                    @Override
                    public void onStart() {
                        pDialog.show();
                    }

                    @Override
                    public void onSuccess(File file) {
                        pDialog.hide();
                        if (path != null) {
                            if (ButtonNumber == 1) {
                                file1 = file;
                                Picasso.with(AuthenticationActivity.this).load(new File(String.valueOf(file))).into(authenticationimg1);
                            } else if (ButtonNumber == 2) {
                                file2 = file;
                                Picasso.with(AuthenticationActivity.this).load(new File(String.valueOf(file))).into(authenticationimg2);
                            } else if (ButtonNumber == 3) {
                                file3 = file;
                                Picasso.with(AuthenticationActivity.this).load(new File(String.valueOf(file))).into(authenticationimg3);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        T.shortToast(getApplicationContext(), "请重新选择图片");
                        pDialog.hide();
                    }
                }).launch();    //启动压缩
    }



    /**
     * 获取缩放后的本地图片
     *
     * @param filePath 文件路径
     * @return 缩放后的bitmap对象
     */
    private  Bitmap readBitmapFromFilePath(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);

            int inSampleSize = 1;

            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;

            return BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
