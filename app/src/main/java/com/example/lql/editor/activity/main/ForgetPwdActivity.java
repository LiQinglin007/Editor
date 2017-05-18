package com.example.lql.editor.activity.main;

import android.app.Activity;
import android.content.Intent;
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
import com.example.lql.editor.bean.GetSmsCode;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.CountDownUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.RegularUtil;
import com.example.lql.editor.utils.T;


/**
 * 类描述：忘记密码页面
 * 作  者：李清林
 * 时  间：2016/11/23 13:47
 * 修改备注：
 */
public class ForgetPwdActivity extends Activity implements View.OnClickListener {

    private android.widget.EditText forgetphoneed;
    private android.widget.EditText forgetcodeed;
    private android.widget.TextView forgetgetcodetv;
    private android.widget.EditText forgetpwded;
    private android.widget.EditText forgetqpwded;
    private android.widget.Button forgetsubmitbut;
    private TextView title;
    private ImageView leftback;
    private boolean IsNeedFinish=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_forget_pwd);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("忘记密码");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setImageResource(R.drawable.btn_back_lv);
        this.forgetsubmitbut = (Button) findViewById(R.id.forget_submit_but);
        this.forgetqpwded = (EditText) findViewById(R.id.forget_qpwd_ed);
        this.forgetpwded = (EditText) findViewById(R.id.forget_pwd_ed);
        this.forgetgetcodetv = (TextView) findViewById(R.id.forget_getcode_tv);
        this.forgetcodeed = (EditText) findViewById(R.id.forget_code_ed);
        this.forgetphoneed = (EditText) findViewById(R.id.forget_phone_ed);

        leftback.setOnClickListener(this);
        forgetgetcodetv.setOnClickListener(this);
        forgetsubmitbut.setOnClickListener(this);
        CountDownUtils.startTime(forgetgetcodetv,false);
    }

    @Override
    protected void onResume() {
        CountDownUtils.startTime(forgetgetcodetv,false);
        super.onResume();
    }


    @Override
    protected void onStop() {
        CountDownUtils.time=60;
        CountDownUtils.run=false;
        super.onStop();
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.forget_getcode_tv:
                if (!RegularUtil.isMobile(forgetphoneed.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "手机号格式不正确");
                } else {

//                    1:注册  2：忘记密码 3：绑定手机号
                    SendRequest.getregistersms(forgetphoneed.getText().toString().trim(), "2", new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if(response.contains("<html>")){
                                T.shortToast(getApplicationContext(),"服务器异常");
                                return;
                            }
                            //请求成功
                            try{
                                MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                                if (myBasebean.getResultCode() == 1) {
                                    T.shortToast(getApplicationContext(), myBasebean.getMsg());
                                }else{
                                    CountDownUtils.startTime(forgetgetcodetv,true);
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

            case R.id.forget_submit_but:
                registerVerification();
                break;

            case R.id.titleBar_left_img:
                finish();
                break;
        }
    }

    /**
     * 找回密码验证
     */
    public void registerVerification() {
        String phone = forgetphoneed.getText().toString().trim();
        String code = forgetcodeed.getText().toString().trim();
        String pwd = forgetpwded.getText().toString().trim();
        String qpwd = forgetqpwded.getText().toString().trim();

        if (!RegularUtil.isMobile(phone)) {
            T.shortToast(getApplicationContext(), "手机号格式不正确");
        } else if (TextUtils.isEmpty(code)) {
            T.shortToast(getApplicationContext(), "请输入短信验证码");
        } else if (!RegularUtil.isPassword(pwd)) {
            T.shortToast(getApplicationContext(), "请输入6~16位数字或字母");
        } else if (!qpwd.equals(pwd)) {
            T.shortToast(getApplicationContext(), "两次密码不一致");
        } else {
            SendRequest.userregister(phone, pwd, code, "2", new mOkCallBack() {
                @Override
                public void onSuccess(String response) {
                    if(response.contains("<html>")){
                        T.shortToast(getApplicationContext(),"服务器异常");
                        return;
                    }
                    try{
                        GetSmsCode bean = JSON.parseObject(response, GetSmsCode.class);
                        T.shortToast(getApplicationContext(), bean.getMsg());
                        if (bean.getResultCode() == 0) {
                            IsNeedFinish=false;
                            PreferenceUtils.setBoolean("IsLogin",false);
                            for (int i = 0; i < PublicStaticData.mActivityList.size() - 1; i++) {
                                PublicStaticData.mActivityList.get(i).finish();
                            }
                            PublicStaticData.MainFragmentNmuber = 0;
                            PublicStaticData.toMain=true;
                            startActivity(new Intent(ForgetPwdActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            T.shortToast(getApplicationContext(), bean.getMsg());
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
    }


    @Override
    protected void onDestroy() {
        CountDownUtils.time=60;
        CountDownUtils.run=false;

        if(IsNeedFinish){
            PublicStaticData.mActivityList.remove(this);
        }
        super.onDestroy();
    }
}
