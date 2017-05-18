package com.example.lql.editor.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Window;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.BoundPhoneBean;
import com.example.lql.editor.bean.LoginBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.T;

public class SplashActivity extends Activity {

    boolean run=true;
    int time=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        boolean Login=PreferenceUtils.getBoolean("Login",false);
//        startActivity(new Intent(SplashActivity.this, DownLoadActivity.class));


        if(Login){
//            PreferenceUtils.setInt("LoginType",1);
            //登录方式    1：账号密码登录   2：第三方登录
            int LoginType=PreferenceUtils.getInt("LoginType",1);
            if(LoginType==2){

                String openid=PreferenceUtils.getString("openid","");
                int mLoginType=PreferenceUtils.getInt("mLoginType",0);
                if(TextUtils.isEmpty(openid)||mLoginType==0){
                    PreferenceUtils.setBoolean("IsLogin",false);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }else{
                    OtherLogin(openid,mLoginType);
                }
            }else{
                String name=PreferenceUtils.getString("name","");
                String pwd=PreferenceUtils.getString("pwd","");
                Login(name,pwd);
            }
        }else{
            new MyThread().start();
        }
    }



    private void OtherLogin(final String mopenid,final int mLoginType){

        SendRequest.OtherLogin(mopenid, mLoginType, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if (response.contains("<html>")) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    return;
                }

                try{
                    BoundPhoneBean bean = JSON.parseObject(response, BoundPhoneBean.class);
                    if (bean.getResultCode() == 0) {
                        PreferenceUtils.setBoolean("Login", false);
                        PreferenceUtils.setBoolean("IsLogin",false);
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
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

                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();

                    }else{
                        T.shortToast(getApplicationContext(),bean.getMsg());
                        PreferenceUtils.setBoolean("Login", false);
                        PreferenceUtils.setBoolean("IsLogin",false);
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }
                } catch (Exception e) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Throwable e) {
                //请求失败
                PreferenceUtils.setBoolean("IsLogin",false);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });
    }



    private void Login (final String name,final String pwd){
        SendRequest.Login1(name, pwd, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    PreferenceUtils.setBoolean("IsLogin",false);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    return;
                }

                try{
                    LoginBean bean= JSON.parseObject(response,LoginBean.class);
                    if(bean.getResultCode()==0){
                        PreferenceUtils.setBoolean("Login",true);
                        PreferenceUtils.setString("name",name);
                        PreferenceUtils.setString("pwd",pwd);

                        PreferenceUtils.setString("Telphone",bean.getData().getData().getTelphone()+"");//电话
                        PreferenceUtils.setString("UserName",bean.getData().getData().getUserName()+"");//昵称
                        PreferenceUtils.setString("UserId",bean.getData().getData().getUserId()+"");//id
                        PreferenceUtils.setFloat("Balance",Float.parseFloat(bean.getData().getData().getBalance()+""));//余额
                        PreferenceUtils.setString("Img",bean.getData().getData().getImg());//头像
                        PreferenceUtils.setString("Sex",bean.getData().getData().getSex());//性别
                        PreferenceUtils.setString("Birthday",bean.getData().getData().getBirthday());//生日
                        PreferenceUtils.setString("School",bean.getData().getData().getSchool());//学校
                        PreferenceUtils.setString("Job",bean.getData().getData().getJob());//职位
                        PreferenceUtils.setString("Graduate",bean.getData().getData().getGraduate());//学历
                        PreferenceUtils.setString("Profession",bean.getData().getData().getProfession());//职称
                        PreferenceUtils.setString("Real",bean.getData().getData().getReal()+"");//实名认证状态   0:正在审核1：已实名认证2：未认证
                        PreferenceUtils.setString("state",bean.getData().getState()+"");//是否申请工作室  0:未申请工作室   1：已申请

                        PreferenceUtils.setBoolean("IsLogin",true);
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }else{
                        T.shortToast(getApplicationContext(),bean.getMsg());
                        PreferenceUtils.setBoolean("Login", false);
                        PreferenceUtils.setBoolean("IsLogin", false);
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }
                } catch (Exception e) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Throwable e) {
                PreferenceUtils.setBoolean("IsLogin",false);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });

    }



    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time++;
            if(time>2){
                run=false;
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }
    };


    class MyThread  extends  Thread{

        @Override
        public void run() {
            while (run){
                try {
                    mHandler.sendEmptyMessage(0);
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }
}
