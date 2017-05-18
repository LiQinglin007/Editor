package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.main.LoginActivity;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.RegularUtil;
import com.example.lql.editor.utils.T;




/**
 * 类描述：修改密码页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */


public class UpdatePwdActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private android.widget.EditText updateoldpwded;
    private android.widget.EditText updatenewpwded;
    private android.widget.EditText updatenewpwdagained;
    private android.widget.Button updatePwdsubmitbut;

    String Userid="";

    ProgressDialog  pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_update_pwd);
        pDialog = new ProgressDialog(UpdatePwdActivity.this);
        pDialog.setMessage("加载中...");
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("修改登录密码");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        this.updatePwdsubmitbut = (Button) findViewById(R.id.updatePwd_submit_but);
        this.updatenewpwdagained = (EditText) findViewById(R.id.update_newpwdagain_ed);
        this.updatenewpwded = (EditText) findViewById(R.id.update_newpwd_ed);
        this.updateoldpwded = (EditText) findViewById(R.id.update_oldpwd_ed);


        leftback.setOnClickListener(this);
        updatePwdsubmitbut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();

                break;
            case R.id.updatePwd_submit_but:
                String oldPwd=updateoldpwded.getText().toString().trim();
                String newPwd=updatenewpwded.getText().toString().trim();
                String newPwdAgain=updatenewpwdagained.getText().toString().trim();

                if(!RegularUtil.isPassword(oldPwd)){
                    T.shortToast(getApplicationContext(),"原密码格式不正确");
                }else if(!RegularUtil.isPassword(newPwd)){
                    T.shortToast(getApplicationContext(),"新密码格式不正确");
                }else if(!newPwdAgain.equals(newPwd)){
                    T.shortToast(getApplicationContext(),"两次密码不一致");
                }else{
                    submit(oldPwd,newPwd);
                }
                break;
        }
    }


    /**
     * 保存按钮
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     */
    private void submit(String oldPwd,String newPwd){
        pDialog.show();
        SendRequest.UpdataPassWord(Userid,oldPwd, newPwd, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                if(myBasebean.getResultCode()==0){
                    T.shortToast(getApplicationContext(),"修改成功，请重新登录");
                    for (int i = 0; i < PublicStaticData.mActivityList.size()-1; i++) {
                        PublicStaticData.mActivityList.get(i).finish();
                    }
//                    PublicStaticData.IsLogin=false;
                    PreferenceUtils.setBoolean("IsLogin",false);
                    PublicStaticData.MainFragmentNmuber=0;
                    PublicStaticData.toMain=true;
                    startActivity(new Intent(UpdatePwdActivity.this, LoginActivity.class));
                    finish();
                }else{
                    T.shortToast(getApplicationContext(),myBasebean.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                T.shortToast(getApplicationContext(),"亲，请检查网络");
            }
        });

    }



    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }





}
