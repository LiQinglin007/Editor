package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.OkHttpClientManager;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.utils.ChoosePicUtil;
import com.example.lql.editor.utils.CircleImageView;
import com.example.lql.editor.utils.ClearEditText;
import com.example.lql.editor.utils.OnoptionsUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.RegularUtil;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.pickerview.TimePopupWindow;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import zhangphil.iosdialog.widget.ActionSheetDialog;

import static com.example.lql.editor.R.id.myaccount_heat_img;
import static com.example.lql.editor.utils.ChoosePicUtil.MATCHING_CODE_CAMERA;
import static com.example.lql.editor.utils.ChoosePicUtil.MATCHING_CODE_GALLERY;

/**
 * 类描述：个人中心编辑资料页面
 * 作  者：李清林
 * 时  间：2016/11/24
 * 修改备注：
 */
public class MyAccountSettingActivity extends Activity implements View.OnClickListener {

    private CircleImageView myaccountheatimg;
    private android.widget.LinearLayout settingchoosepic;
    private com.example.lql.editor.utils.ClearEditText settingnamecet;
    private android.widget.TextView settingsextv;
    private android.widget.LinearLayout settingchoosesex;
    private android.widget.TextView settingbirthdaytv;
    private android.widget.LinearLayout settingchoosebirthday;
    private com.example.lql.editor.utils.ClearEditText settingschoolcet;
    private com.example.lql.editor.utils.ClearEditText settingoccupationcet;
    private com.example.lql.editor.utils.ClearEditText settingeducationcet;
    private com.example.lql.editor.utils.ClearEditText settingacademictitlecet;
    private android.widget.Button settingsavebut;



    private TextView title;
    private ImageView leftback;
    String name="";
    String sex="";
    String Birthday="";//生日
    String School="";//学校
    String Job="";//职称
    String Graduate="";// 学历
    String Profession="";//职位
    String path="";

    File mfile=null;
    String Userid="";

    String dialog_sex="男";

    ProgressDialog  pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_my_account_setting);
        boolean IsLogin=PreferenceUtils.getBoolean("IsLogin",false);
        if(IsLogin){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }

        pDialog=new ProgressDialog(MyAccountSettingActivity.this);
        pDialog.setMessage("加载中...");
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("个人资料");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);


        this.settingsavebut = (Button) findViewById(R.id.setting_save_but);
        this.settingacademictitlecet = (ClearEditText) findViewById(R.id.setting_academictitle_cet);
        this.settingeducationcet = (ClearEditText) findViewById(R.id.setting_education_cet);
        this.settingoccupationcet = (ClearEditText) findViewById(R.id.setting_occupation_cet);
        this.settingschoolcet = (ClearEditText) findViewById(R.id.setting_school_cet);
        this.settingchoosebirthday = (LinearLayout) findViewById(R.id.setting_choose_birthday);
        this.settingbirthdaytv = (TextView) findViewById(R.id.setting_birthday_tv);
        this.settingchoosesex = (LinearLayout) findViewById(R.id.setting_choose_sex);
        this.settingsextv = (TextView) findViewById(R.id.setting_sex_tv);
        this.settingnamecet = (ClearEditText) findViewById(R.id.setting_name_cet);
        this.settingchoosepic = (LinearLayout) findViewById(R.id.setting_choose_pic);
        this.myaccountheatimg = (CircleImageView) findViewById(myaccount_heat_img);

        leftback.setOnClickListener(this);
        settingchoosepic.setOnClickListener(this);//选择头像
        settingchoosebirthday.setOnClickListener(this);//选择生日
        settingchoosesex.setOnClickListener(this);//选择性别
        settingsavebut.setOnClickListener(this);//选择性别
        setView();
    }






    private void setView(){
        Picasso.with(MyAccountSettingActivity.this).load(MyUrl.Pic+PreferenceUtils.getString("Img","")).into(myaccountheatimg);
//        Glide.with(this).load(MyUrl.Pic+PublicStaticData.mybean.getData().getImg()).into(myaccountheatimg);
        settingnamecet.setText(PreferenceUtils.getString("UserName",""));
        settingsextv.setText(PreferenceUtils.getString("Sex",""));
        dialog_sex=PreferenceUtils.getString("Sex","");
        settingbirthdaytv.setText(PreferenceUtils.getString("Birthday",""));
        settingschoolcet.setText(PreferenceUtils.getString("School",""));
        settingoccupationcet.setText(PreferenceUtils.getString("Job",""));
        settingeducationcet.setText(PreferenceUtils.getString("Graduate",""));
        settingacademictitlecet.setText(PreferenceUtils.getString("Profession",""));
        Birthday=settingbirthdaytv.getText().toString().trim();
        sex=settingsextv.getText().toString().trim();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_choose_pic://选择头像
                showMyDialog();
                break;
            case R.id.setting_choose_birthday://选择生日
                InputMethodManager imm =  (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if(imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                            0);
                }
                OnoptionsUtils.showDateSelect(this, settingchoosebirthday, new TimePopupWindow.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Birthday = sdf.format(date);
                        settingbirthdaytv.setText(Birthday);
                    }
                });
                break;
            case R.id.setting_choose_sex://选择性别
                chooseSex();
                break;
            case R.id.setting_save_but://保存

                name=settingnamecet.getText().toString().trim();
                School=settingschoolcet.getText().toString().trim();
                Job=settingoccupationcet.getText().toString().trim();
                Graduate=settingeducationcet.getText().toString().trim();
                Profession=settingacademictitlecet.getText().toString().trim();




                if(!TextUtils.isEmpty(name)&&!RegularUtil.isChineseOrEngOrNumber(name)){
                    T.shortToast(getApplicationContext(),"昵称请输入数字、字母或汉字");
                    return;
                }

                if(!TextUtils.isEmpty(School)&&!RegularUtil.isChineseOrEngOrNumber(School)){
                    T.shortToast(getApplicationContext(),"学校请输入数字、字母或汉字");
                    return;
                }

                if(!TextUtils.isEmpty(Job)&&!RegularUtil.isChineseOrEngOrNumber(Job)){
                    T.shortToast(getApplicationContext(),"职业请输入数字、字母或汉字");
                    return;
                }

                if(!TextUtils.isEmpty(Graduate)&&!RegularUtil.isChineseOrEngOrNumber(Graduate)){
                    T.shortToast(getApplicationContext(),"学历请输入数字、字母或汉字");
                    return;
                }

                if(!TextUtils.isEmpty(Profession)&&!RegularUtil.isChineseOrEngOrNumber(Profession)){
                    T.shortToast(getApplicationContext(),"职称请输入数字、字母或汉字");
                    return;
                }







                pDialog.show();

                SendRequest.UpdateInfo(Userid,name, sex, Birthday, School, Job, Graduate, Profession, mfile, new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        pDialog.hide();
                        T.shortToast(getApplicationContext(),"亲，请检查网络");
                    }

                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        if(response.contains("<html>")){
                            T.shortToast(getApplicationContext(),"服务器异常");
                            return;
                        }
                        MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                        if(myBasebean.getResultCode()==0){
                            PreferenceUtils.setString("UserName",name);//昵称
                            PreferenceUtils.setString("Img",myBasebean.getData());//头像
                            PreferenceUtils.setString("Sex",sex);//性别
                            PreferenceUtils.setString("Birthday",Birthday);//生日
                            PreferenceUtils.setString("School",School);//学校
                            PreferenceUtils.setString("Job",Job);//职位
                            PreferenceUtils.setString("Graduate",Graduate);//学历
                            PreferenceUtils.setString("Profession",Profession);//职称

                            finish();
                        }
                        T.shortToast(getApplicationContext(),myBasebean.getMsg());
                    }
                });
                break;

            case R.id.titleBar_left_img:
                finish();
                break;
        }
    }

    /**
     * 选择性别
     */
    public void chooseSex() {
        final Dialog dialog = new Dialog(MyAccountSettingActivity.this, R.style.myTheme_dialog);
        final View view = LayoutInflater.from(MyAccountSettingActivity.this).inflate(
                R.layout.choosesex_layout, null);
        dialog.setContentView(view);
        LinearLayout man = (LinearLayout) view.findViewById(R.id.choose_sex_man_ly);
        LinearLayout woman = (LinearLayout) view.findViewById(R.id.choose_sex_woman_ly);
        final ImageView man_img = (ImageView) view.findViewById(R.id.choose_sex_man_img);
        final ImageView woman_img = (ImageView) view.findViewById(R.id.choose_sex_woman_img);


        if(dialog_sex.equals("女")){
            woman_img.setImageResource(R.drawable.btn_choose_sel);
            man_img.setImageResource(R.drawable.btn_choose_nor);
        }else{
            man_img.setImageResource(R.drawable.btn_choose_sel);
            woman_img.setImageResource(R.drawable.btn_choose_nor);
        }


        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                man_img.setImageResource(R.drawable.btn_choose_sel);
                woman_img.setImageResource(R.drawable.btn_choose_nor);
                settingsextv.setText("男");
                sex="男";
                dialog_sex="男";
                dialog.dismiss();
            }
        });


        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                woman_img.setImageResource(R.drawable.btn_choose_sel);
                man_img.setImageResource(R.drawable.btn_choose_nor);
                settingsextv.setText("女");
                sex="女";
                dialog_sex="女";
                dialog.dismiss();
            }
        });

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.5
        p.alpha = 0.9f; // 透明度
        dialogWindow.setAttributes(p);
        dialog.show();
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
                                ChoosePicUtil.startActivityFor(MATCHING_CODE_GALLERY, MyAccountSettingActivity.this);
                            }
                        })
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                ChoosePicUtil.startActivityFor(MATCHING_CODE_CAMERA, MyAccountSettingActivity.this);
                            }
                        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Bitmap bitmap = ChoosePicUtil.getBitmapFromResult(requestCode, resultCode, data, MyAccountSettingActivity.this, false, false);
         path = ChoosePicUtil.getPathFromResult(requestCode, resultCode, data, MyAccountSettingActivity.this);
        if (bitmap != null) {
            try{
                bitmap.getWidth();
            }catch(Exception e) {
                T.shortToast(getApplicationContext(),"图片出错了");
                return;
            }

            myaccountheatimg.setImageBitmap(bitmap);
            Luban.get(this)
                    .load(new File(path))                     //传入要压缩的图片
                    .putGear(Luban.THIRD_GEAR)      //设定压缩档次，默认三挡
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                            //TODO 压缩开始前调用，可以在方法内启动 loading UI
                        }

                        @Override
                        public void onSuccess(File file) {
                            mfile=file;
                        }

                        @Override
                        public void onError(Throwable e) {
                            T.shortToast(getApplicationContext(), "请重新选择图片");
                        }
                    }).launch();    //启动压缩
        }
    }





    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
