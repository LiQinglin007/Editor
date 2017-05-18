package com.example.lql.editor.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.BoundPhoneBean;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.CountDownUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.RegularUtil;
import com.example.lql.editor.utils.T;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;

import static com.example.lql.editor.utils.PublicStaticData.openId;


/**
 * 类描述：绑定手机号页面
 * 作  者：李清林
 * 时  间：2016/11/23
 * 修改备注：
 */

public class BoundPhoneActivity extends Activity implements View.OnClickListener {

    private android.widget.EditText boundphoneed;
    private android.widget.EditText boundcodeed;
    private android.widget.TextView boundgetcodetv;
    private android.widget.Button boundbut;
    private TextView title;
    private ImageView leftback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_bound_phone);
        initView();


    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("绑定手机号");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setImageResource(R.drawable.btn_back_lv);
        this.boundbut = (Button) findViewById(R.id.bound_but);
        this.boundgetcodetv = (TextView) findViewById(R.id.bound_getcode_tv);
        this.boundcodeed = (EditText) findViewById(R.id.bound_code_ed);
        this.boundphoneed = (EditText) findViewById(R.id.bound_phone_ed);

        boundbut.setOnClickListener(this);
        boundgetcodetv.setOnClickListener(this);
        leftback.setOnClickListener(this);

        CountDownUtils.startTime(boundgetcodetv, false);
    }


    @Override
    protected void onResume() {
        CountDownUtils.startTime(boundgetcodetv, false);
        super.onResume();
    }


    @Override
    protected void onStop() {
        CountDownUtils.time = 60;
        CountDownUtils.run = false;
        super.onStop();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bound_but:
                if (!RegularUtil.isMobile(boundphoneed.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "手机号格式不正确");
                } else if (TextUtils.isEmpty(boundcodeed.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "验证码不能为空");
                } else {
                    /**
                     *
                     * @param openId
                     * @param Telphone   电话号码
                     * @param Registercode   验证码
                     * @param type    1:QQ 2:微信 3：微博
                     */
                    SendRequest.Bind(openId, boundphoneed.getText().toString().trim(), boundcodeed.getText().toString().trim(), PublicStaticData.LoginType, new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if (response.contains("<html>")) {
                                T.shortToast(getApplicationContext(), "服务器异常");
                                return;
                            }


                            try{
                                BoundPhoneBean bean = JSON.parseObject(response, BoundPhoneBean.class);
                                if (bean.getResultCode() == 0) {
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
                                } else {
                                    PreferenceUtils.getBoolean("IsLogin", false);
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
                break;
            case R.id.bound_getcode_tv:
                if (!RegularUtil.isMobile(boundphoneed.getText().toString().trim())) {
                    T.shortToast(getApplicationContext(), "手机号格式不正确");
                } else {
                    SendRequest.getregistersms(boundphoneed.getText().toString().trim(), "3", new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            Log.e("onSuccess++",response);
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
                                    CountDownUtils.startTime(boundgetcodetv, true);
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
            case R.id.titleBar_left_img:
                if (!TextUtils.isEmpty(PublicStaticData.Name)) {
                    Platform mPlatform = ShareSDK.getPlatform(PublicStaticData.Name);
                    mPlatform.removeAccount(true);
                }
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        if (!TextUtils.isEmpty(PublicStaticData.Name)) {
            Platform mPlatform = ShareSDK.getPlatform(PublicStaticData.Name);
            mPlatform.removeAccount(true);
        }
        CountDownUtils.time = 60;
        CountDownUtils.run = false;
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }


}
