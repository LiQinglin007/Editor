package com.example.lql.editor.activity.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.BoundPhoneBean;
import com.example.lql.editor.bean.GetSmsCode;
import com.example.lql.editor.bean.LoginBean;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.CountDownUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.RegularUtil;
import com.example.lql.editor.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * 类描述：登录注册页面
 * 作  者：李清林
 * 时  间：2016/11/23
 * 修改备注：
 */


public class LoginActivity extends Activity implements View.OnClickListener {

    //    private android.widget.RadioButton loginradio;
//    private android.widget.RadioButton registerradio;
    private android.widget.TextView loginradio;
    private android.widget.TextView registerradio;


    private android.widget.EditText loginphoneed;
    private android.widget.EditText loginpwded;
    private android.widget.TextView loginforgotpwdtv;
    private android.widget.Button loginbut;
    private android.widget.ImageView loginqqim;
    private android.widget.ImageView loginwechatim;
    private android.widget.ImageView loginsinaim;
    private android.widget.LinearLayout loginly;
    private android.widget.LinearLayout registerly;
    private android.widget.EditText registerphoneed;
    private android.widget.EditText registercodeed;
    private android.widget.TextView registergetcodetv;
    private android.widget.EditText registerpwded;
    private android.widget.EditText registerqpwded;
    private android.widget.Button registerbut;
    private ImageView login_close;
    private ProgressDialog pDialog;

    private int LoginType = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("加载中...");
        initView();
        PublicStaticData.mActivityList.add(this);
    }

    private void initView() {
        this.registerbut = (Button) findViewById(R.id.register_but);
        this.registerqpwded = (EditText) findViewById(R.id.register_qpwd_ed);
        this.registerpwded = (EditText) findViewById(R.id.register_pwd_ed);
        this.registergetcodetv = (TextView) findViewById(R.id.register_getcode_tv);
        this.registercodeed = (EditText) findViewById(R.id.register_code_ed);
        this.registerphoneed = (EditText) findViewById(R.id.register_phone_ed);
        this.loginly = (LinearLayout) findViewById(R.id.login_ly);
        this.registerly = (LinearLayout) findViewById(R.id.register_ly);
        this.loginsinaim = (ImageView) findViewById(R.id.login_sina_im);
        this.loginwechatim = (ImageView) findViewById(R.id.login_wechat_im);
        this.loginqqim = (ImageView) findViewById(R.id.login_qq_im);
        this.loginbut = (Button) findViewById(R.id.login_but);
        this.loginforgotpwdtv = (TextView) findViewById(R.id.login_forgotpwd_tv);
        this.loginpwded = (EditText) findViewById(R.id.login_pwd_ed);
        this.loginphoneed = (EditText) findViewById(R.id.login_phone_ed);
//        this.registerradio = (RadioButton) findViewById(R.id.register_radio);
//        this.loginradio = (RadioButton) findViewById(R.id.login_radio);
        this.registerradio = (TextView) findViewById(R.id.register_radio);
        this.loginradio = (TextView) findViewById(R.id.login_radio);
        login_close = (ImageView) findViewById(R.id.login_close);


        registerradio.setOnClickListener(this);
        loginradio.setOnClickListener(this);
        registergetcodetv.setOnClickListener(this);//获取验证码
        loginforgotpwdtv.setOnClickListener(this);//忘记密码
        loginbut.setOnClickListener(this);
        registerbut.setOnClickListener(this);
        loginqqim.setOnClickListener(this);
        loginwechatim.setOnClickListener(this);
        loginsinaim.setOnClickListener(this);
        login_close.setOnClickListener(this);

//        loginphoneed.setText("15076165538");
//        loginpwded.setText("123456");
        CountDownUtils.startTime(registergetcodetv, false);
    }


    @Override
    protected void onResume() {
        CountDownUtils.startTime(registergetcodetv, false);
        super.onResume();
    }


    @Override
    protected void onStop() {
        CountDownUtils.time = 60;
        CountDownUtils.run = false;
        super.onStop();
    }

    /*切换页面*/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void steView(boolean isLogin) {
        if (isLogin) {
            loginradio.setBackground(getResources().getDrawable(R.drawable.logintext_left_sle));
            loginradio.setTextColor(getResources().getColor(R.color.color_FFFFFF));
            registerradio.setBackground(getResources().getDrawable(R.drawable.logintext_right_no));
            registerradio.setTextColor(getResources().getColor(R.color.color_1AB394));
            loginly.setVisibility(View.VISIBLE);
            registerly.setVisibility(View.GONE);
        } else {
            loginradio.setBackground(getResources().getDrawable(R.drawable.logintext_left_no));
            loginradio.setTextColor(getResources().getColor(R.color.color_1AB394));
            registerradio.setBackground(getResources().getDrawable(R.drawable.logintext_right_sle));
            registerradio.setTextColor(getResources().getColor(R.color.color_FFFFFF));
            loginly.setVisibility(View.GONE);
            registerly.setVisibility(View.VISIBLE);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.register_radio://注册
                steView(false);
                break;
            case R.id.login_radio://登录
                steView(true);
                break;
            case R.id.register_getcode_tv://获取验证码
                if (!RegularUtil.isMobile(registerphoneed.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "手机号输入格式错误");
                } else {

                    SendRequest.getregistersms(registerphoneed.getText().toString().trim(), "1", new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if (response.contains("<html>")) {
                                T.shortToast(getApplicationContext(), "服务器异常");
                                return;
                            }

                            try{
                                //请求成功
                                MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                                if (myBasebean.getResultCode() == 1) {
                                    T.shortToast(getApplicationContext(), myBasebean.getMsg());
                                } else {
                                    CountDownUtils.startTime(registergetcodetv, true);
                                }
                            } catch (Exception e) {
                                T.shortToast(getApplicationContext(), "服务器异常");
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Throwable e) {
                            T.shortToast(getApplicationContext(), "亲，请检查网络");
                        }
                    });
                }
                break;
            case R.id.login_but://登录按钮
                loginVerification();//登录验证
                break;
            case R.id.register_but://注册按钮
                registerVerification();
                break;
            case R.id.login_qq_im://QQ登录
                PreferenceUtils.setInt("mLoginType",1);
                pDialog.show();
                LoginType = 1;
                PublicStaticData.Name = QQ.NAME;
                OtherLogin(QQ.NAME);
                break;
            case R.id.login_wechat_im://微信登录

                if(isAppInstalled("com.tencent.mm")){
                    PreferenceUtils.setInt("mLoginType",2);
                    pDialog.show();
                    LoginType = 2;
                    PublicStaticData.Name = Wechat.NAME;
                    OtherLogin(Wechat.NAME);
                }else{
                    T.shortToast(getApplicationContext(),"请先安装微信客户端");
                }
                break;
            case R.id.login_sina_im://微博登录
                PreferenceUtils.setInt("mLoginType",3);
                pDialog.show();
                LoginType = 3;
                PublicStaticData.Name = SinaWeibo.NAME;
                OtherLogin(SinaWeibo.NAME);
                break;
            case R.id.login_forgotpwd_tv://忘记密码
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                break;
            case R.id.login_close:
                finish();
                break;
        }
    }



    public boolean isAppInstalled(String packageName){
        PackageManager packageManager=LoginActivity.this.getPackageManager();
        List<PackageInfo> pinfo=packageManager.getInstalledPackages(0);
        List<String> pName=new ArrayList<>();
        if(pinfo!=null){
            for (int i = 0; i <pinfo.size() ; i++) {
                String PN=pinfo.get(i).packageName;
                pName.add(PN);
            }

        }
        return pName.contains(packageName);
    }

    /**
     * 登录验证
     */
    public void loginVerification() {
        final String phone = loginphoneed.getText().toString().trim();
        final String pwd = loginpwded.getText().toString().trim();
        if(TextUtils.isEmpty(phone)){
            T.shortToast(getApplicationContext(), "手机号不能为空");
        }else if (!RegularUtil.isMobile(phone)) {
            T.shortToast(getApplicationContext(), "手机号输入格式错误");
        } else if (TextUtils.isEmpty(pwd)) {
            T.shortToast(getApplicationContext(), "密码不能为空");
        }else if (!RegularUtil.isPassword(pwd)) {
            T.shortToast(getApplicationContext(), "请输入6~16位数字或字母");
        } else {
            pDialog.show();
            SendRequest.Login1(phone, pwd, new mOkCallBack() {
                @Override
                public void onSuccess(String response) {
                    pDialog.hide();
                    if (response.contains("<html>")) {
                        T.shortToast(getApplicationContext(), "服务器异常");
                        return;
                    }

                    try{
                        //请求成功
                        LoginBean bean = JSON.parseObject(response, LoginBean.class);
                        if (bean.getResultCode() == 0) {
                            PreferenceUtils.setBoolean("Login", true);
                            //登录方式    1：账号密码登录   2：第三方登录
                            PreferenceUtils.setInt("LoginType", 1);
                            PreferenceUtils.setString("name", phone);
                            PreferenceUtils.setString("pwd", pwd);
//                            PublicStaticData.mybean=bean.getData();

                            PreferenceUtils.setString("Telphone", bean.getData().getData().getTelphone() + "");//电话

                            PreferenceUtils.setString("UserId", bean.getData().getData().getUserId() + "");//id
                            PreferenceUtils.setFloat("Balance", Float.parseFloat(bean.getData().getData().getBalance() + ""));//余额
                            Float mfloat=PreferenceUtils.getFloat("Balance",0);


                            PreferenceUtils.setString("Img", bean.getData().getData().getImg());//头像
                            PreferenceUtils.setString("UserName", bean.getData().getData().getUserName() + "");//昵称
                            PreferenceUtils.setString("Sex", bean.getData().getData().getSex());//性别
                            PreferenceUtils.setString("Birthday", bean.getData().getData().getBirthday());//生日
                            PreferenceUtils.setString("School", bean.getData().getData().getSchool());//学校
                            PreferenceUtils.setString("Job", bean.getData().getData().getJob());//职位
                            PreferenceUtils.setString("Graduate", bean.getData().getData().getGraduate());//学历
                            PreferenceUtils.setString("Profession", bean.getData().getData().getProfession());//职称
                            PreferenceUtils.setString("Real", bean.getData().getData().getReal() + "");//实名认证状态   0:正在审核1：已实名认证2：未认证
                            PreferenceUtils.setString("state", bean.getData().getState() + "");//是否申请工作室  0:未申请工作室   1：已申请


//                            PublicStaticData.IsLogin=true;
                            PreferenceUtils.setBoolean("IsLogin", true);
                            if(PublicStaticData.toMain){
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                PublicStaticData.toMain=false;
                            }


                            finish();
                        } else {
                            pDialog.hide();
                            T.shortToast(getApplicationContext(), bean.getMsg());
                        }
                    } catch (Exception e) {
                        T.shortToast(getApplicationContext(), "服务器异常");
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Throwable e) {
                    //请求失败
                    pDialog.hide();
                    T.shortToast(getApplicationContext(), "亲，请检查网络");
                }
            });
        }

    }


    /**
     * 注册验证
     */
    public void registerVerification() {
        final String phone = registerphoneed.getText().toString().trim();
        String code = registercodeed.getText().toString().trim();
        final String pwd = registerpwded.getText().toString().trim();
        String qpwd = registerqpwded.getText().toString().trim();

        if (!RegularUtil.isMobile(phone)) {
            T.shortToast(getApplicationContext(), "手机号输入格式错误");
        } else if (TextUtils.isEmpty(code)) {
            T.shortToast(getApplicationContext(), "请输入短信验证码");
        } else if (!RegularUtil.isPassword(pwd)) {
            T.shortToast(getApplicationContext(), "请输入6~16位数字或字母");
        } else if (!qpwd.equals(pwd)) {
            T.shortToast(getApplicationContext(), "两次密码不一致");
        } else {


            pDialog.show();
            SendRequest.userregister(phone, pwd, code, "1", new mOkCallBack() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onSuccess(String response) {
                    pDialog.hide();
//                    {"ResultCode":0,"Msg":"注册成功！","Data":1030}
                    if (response.contains("<html>")) {
                        T.shortToast(getApplicationContext(), "服务器异常");
                        return;
                    }

                    try{
                        GetSmsCode bean = JSON.parseObject(response, GetSmsCode.class);
                        T.shortToast(getApplicationContext(), bean.getMsg());
                        if (bean.getResultCode() == 0) {
//                            loginVerification();
                            loginphoneed.setText(phone);
                            loginpwded.setText(pwd);
                            steView(true);
                        }
                    } catch (Exception e) {
                        T.shortToast(getApplicationContext(), "服务器异常");
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Throwable e) {
                    T.shortToast(getApplicationContext(), "亲，请检查网络");
                    pDialog.hide();
                }
            });
        }

    }


    /**
     * 第三方登录
     *
     * @param type   1:QQ 2：微信 3：微博
     * @param openId openid
     */
    private void OtherLogin(final int type, final String openId) {

        pDialog.show();
        SendRequest.OtherLogin(openId, type, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.e("onSuccess==",response);
                pDialog.hide();

                if (response.contains("<html>")) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    if (!TextUtils.isEmpty(PublicStaticData.Name)) {
                        Platform mPlatform = ShareSDK.getPlatform(PublicStaticData.Name);
                        mPlatform.removeAccount(true);
                    }
                    return;
                }

                try{
                    BoundPhoneBean bean = JSON.parseObject(response, BoundPhoneBean.class);
                    if (bean.getResultCode() == 0) {
                        T.shortToast(getApplicationContext(), bean.getMsg());
                        PublicStaticData.LoginType = type;
                        PublicStaticData.openId = openId;
                        startActivity(new Intent(LoginActivity.this, BoundPhoneActivity.class));
                        finish();
                    } else if (bean.getResultCode() == 1){

                        PreferenceUtils.setBoolean("Login", true);
                        PreferenceUtils.setBoolean("IsLogin", true);
                        //登录方式    1：账号密码登录   2：第三方登录
                        PreferenceUtils.setInt("LoginType", 2);

                        PreferenceUtils.setString("Telphone", bean.getData().getData().getTelphone() + "");//电话
                        PreferenceUtils.setString("UserName", bean.getData().getData().getUserName() + "");//昵称
                        PreferenceUtils.setString("UserId", bean.getData().getData().getUserId() + "");//id
                        PreferenceUtils.setFloat("Balance", Float.parseFloat(bean.getData().getData().getBalance() + ""));//余额
                        PreferenceUtils.setString("Img", bean.getData().getData().getImg());//头像
                        PreferenceUtils.setString("Sex", bean.getData().getData().getSex());//性别
                        PreferenceUtils.setString("Birthday", bean.getData().getData().getBirthday());//生日
                        PreferenceUtils.setString("School", bean.getData().getData().getSchool());//学校
                        PreferenceUtils.setString("Job", bean.getData().getData().getJob());//职位
                        PreferenceUtils.setString("Graduate", bean.getData().getData().getGraduate());//学历
                        PreferenceUtils.setString("Profession", bean.getData().getData().getProfession());//职称
                        PreferenceUtils.setString("Real", bean.getData().getData().getReal() + "");//实名认证状态   0:正在审核1：已实名认证2：未认证
                        PreferenceUtils.setString("state", bean.getData().getState() + "");//是否申请工作室  0:未申请工作室   1：已申请

                        finish();
                    }else{
                        if (!TextUtils.isEmpty(PublicStaticData.Name)) {
                            Platform mPlatform = ShareSDK.getPlatform(PublicStaticData.Name);
                            mPlatform.removeAccount(true);
                        }
                        T.shortToast(getApplicationContext(),bean.getMsg());
                    }
                } catch (Exception e) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable e) {
                //请求失败
                if (!TextUtils.isEmpty(PublicStaticData.Name)) {
                    Platform mPlatform = ShareSDK.getPlatform(PublicStaticData.Name);
                    mPlatform.removeAccount(true);
                }
                pDialog.hide();
                T.shortToast(getApplicationContext(), "亲，请检查网络");
            }
        });
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            pDialog.show();
            Bundle b = msg.getData();
            String openid = b.getString("openid");
            OtherLogin(LoginType, openid);
        }
    };


    /**
     * 登录
     */
    public void OtherLogin(String name) {
        Platform mPlatform = ShareSDK.getPlatform(name);
        mPlatform.setPlatformActionListener(mPlatformActionListener);
//        mPlatform.authorize();//单独授权,OnComplete返回的hashmap是空的
        mPlatform.showUser(null);//授权并获取用户信息
    }

    public PlatformActionListener mPlatformActionListener = new PlatformActionListener() {
        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            message.what = 0;
            bundle.putString("openid", platform.getDb().getUserId() + "");
            PreferenceUtils.setString("openid",platform.getDb().getUserId()+"");
            message.setData(bundle);
            handler.sendMessage(message);
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
        }

        @Override
        public void onCancel(Platform platform, int i) {

        }
    };


    @Override
    protected void onDestroy() {
        CountDownUtils.time = 60;
        CountDownUtils.run = false;
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }


}
