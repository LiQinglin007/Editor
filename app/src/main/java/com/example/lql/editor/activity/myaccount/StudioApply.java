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
import android.widget.EditText;
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
import com.example.lql.editor.utils.RegularUtil;
import com.example.lql.editor.utils.T;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import zhangphil.iosdialog.widget.ActionSheetDialog;

import static com.example.lql.editor.utils.ChoosePicUtil.MATCHING_CODE_CAMERA;
import static com.example.lql.editor.utils.ChoosePicUtil.MATCHING_CODE_GALLERY;

/**
 * Created by LQL on 2016/12/10.
 */

public class StudioApply extends Activity implements View.OnClickListener {

    private android.widget.ImageView authenticationimg1;
    private android.widget.ImageView authenticationimg2;
    private android.widget.ImageView authenticationimg3;
    private android.widget.ImageView authenticationimg4;
    private android.widget.ImageView authenticationimg5;
    private android.widget.Button studioapplysubmitbut;
    private android.widget.EditText studioapplyphonetv;


    int ButtonNumber = 1;
    File file1 = null, file2 = null, file3 = null, file4 = null, file5 = null;

    private TextView title;
    private ImageView leftback;
    ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_studioapply);
        pDialog = new ProgressDialog(StudioApply.this);
        pDialog.setMessage("加载中...");
        initView();
    }


    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("工作室申请");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);
        this.studioapplysubmitbut = (Button) findViewById(R.id.studioapply_submit_but);
        this.studioapplyphonetv = (EditText) findViewById(R.id.studioapply_phone_tv);
        this.authenticationimg5 = (ImageView) findViewById(R.id.authentication_img5);
        this.authenticationimg4 = (ImageView) findViewById(R.id.authentication_img4);
        this.authenticationimg3 = (ImageView) findViewById(R.id.authentication_img3);
        this.authenticationimg2 = (ImageView) findViewById(R.id.authentication_img2);
        this.authenticationimg1 = (ImageView) findViewById(R.id.authentication_img1);

        studioapplysubmitbut.setOnClickListener(this);
        authenticationimg1.setOnClickListener(this);
        authenticationimg2.setOnClickListener(this);
        authenticationimg3.setOnClickListener(this);
        authenticationimg4.setOnClickListener(this);
        authenticationimg5.setOnClickListener(this);

        String Telphone = PreferenceUtils.getString("Telphone", "");//电话
        this.studioapplyphonetv.setText(TextUtils.isEmpty(Telphone) || Telphone.equals("") ? "" : Telphone);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studioapply_submit_but:
                if (!RegularUtil.isMobile(studioapplyphonetv.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "请填写正确的手机号");
                } else {
                    int i = 0;
                    if ((file1 == null || file2 == null)) {
                        i++;
                    }
                    if (file3 == null || file4 == null || file5 == null) {
                        i++;
                    }


                    if (i == 2) {
                        T.shortToast(getApplicationContext(), "请添加完整图片");
                        return;
                    }


                    ArrayList<File> list = new ArrayList<>();

                    if (file1 != null) {
                        list.add(file1);
                        list.add(file2);
                    }
//                        if (file2 != null) {
//                            list.add(file2);
//                        }
                    if (file3 != null) {
                        list.add(file3);
                        list.add(file4);
                        list.add(file5);
                    }


                    pDialog.show();
                    studioapplysubmitbut.setClickable(false);
                    String Userid = "";
                    if (PreferenceUtils.getBoolean("IsLogin", false)) {
                        Userid = PreferenceUtils.getString("UserId", "");
                    } else {
                        Userid = "0";
                    }

                    SendRequest.offer(Userid, studioapplyphonetv.getText().toString().trim(), list, new OkHttpClientManager.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            studioapplysubmitbut.setClickable(true);
                            pDialog.hide();
                            T.shortToast(getApplicationContext(), "亲，请检查网络");
                        }

                        @Override
                        public void onResponse(String response) {
                            pDialog.hide();
                            studioapplysubmitbut.setClickable(true);
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
                }
                break;
            case R.id.authentication_img1:
                ButtonNumber = 1;
                showMyDialog();
                break;
            case R.id.authentication_img2:
                ButtonNumber = 2;
                showMyDialog();
                break;
            case R.id.authentication_img3:
                ButtonNumber = 3;
                showMyDialog();
                break;
            case R.id.authentication_img4:
                ButtonNumber = 4;
                showMyDialog();
                break;
            case R.id.authentication_img5:
                ButtonNumber = 5;
                showMyDialog();
                break;
            case R.id.titleBar_left_img:
                finish();
                break;
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
                                ChoosePicUtil.startActivityFor(MATCHING_CODE_GALLERY, StudioApply.this);
                            }
                        })
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ChoosePicUtil.startActivityFor(MATCHING_CODE_CAMERA, StudioApply.this);
                            }
                        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        final Bitmap bitmap = ChoosePicUtil.getBitmapFromResult(requestCode, resultCode, data, StudioApply.this, false, false);
        final String path = ChoosePicUtil.getPathFromResult(requestCode, resultCode, data, StudioApply.this);

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
                                Picasso.with(StudioApply.this).load(new File(String.valueOf(file))).into(authenticationimg1);
                            } else if (ButtonNumber == 2) {
                                file2 = file;
                                Picasso.with(StudioApply.this).load(new File(String.valueOf(file))).into(authenticationimg2);
                            } else if (ButtonNumber == 3) {
                                file3 = file;
                                Picasso.with(StudioApply.this).load(new File(String.valueOf(file))).into(authenticationimg3);
                            } else if (ButtonNumber == 4) {
                                file4 = file;
                                Picasso.with(StudioApply.this).load(new File(String.valueOf(file))).into(authenticationimg4);
                            } else if (ButtonNumber == 5) {
                                file5 = file;
                                Picasso.with(StudioApply.this).load(new File(String.valueOf(file))).into(authenticationimg5);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        pDialog.hide();
                        T.shortToast(getApplicationContext(), "请重新选择图片");
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
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
