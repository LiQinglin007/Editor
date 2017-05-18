package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;

import zhangphil.iosdialog.widget.AlertDialog;

/**
 * 类描述：充值页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */


public class RechargeActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    String address = "http://e22a.com/h.YsyDvf?cv=AAaYtYDZ&sm=ba143c";
    private android.widget.EditText rechargeaccounted;
    private android.widget.EditText rechargepwded;
    private android.widget.Button rechargesubmitbut;
    private TextView rechargeaddresstv;
    private TextView rechargecopyaddresstv;

    String Userid="";

    ProgressDialog  pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_recharge);
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }

        pDialog=new ProgressDialog(RechargeActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();


        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("充值");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);

        this.rechargecopyaddresstv = (TextView) findViewById(R.id.recharge_copyaddress_tv);
        this.rechargeaddresstv = (TextView) findViewById(R.id.recharge_address_tv);
        this.rechargesubmitbut = (Button) findViewById(R.id.recharge_submit_but);
        this.rechargepwded = (EditText) findViewById(R.id.recharge_pwd_ed);
        this.rechargeaccounted = (EditText) findViewById(R.id.recharge_account_ed);

        rechargeaddresstv.setText(address);
        rechargesubmitbut.setOnClickListener(this);
        rechargecopyaddresstv.setOnClickListener(this);

        getData();
    }


    private void getData(){
        SendRequest.shopaddress(new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器错误");
                    return;
                }
                MyBasebean myBasebean=JSON.parseObject(response,MyBasebean.class);
                if(myBasebean.getResultCode()==0){
                    address=myBasebean.getData();
                    rechargeaddresstv.setText(address);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.recharge_copyaddress_tv://复制地址
                ClipboardManager myClipboard= (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData  myClip = ClipData.newPlainText("text", address);
                myClipboard.setPrimaryClip(myClip);
                T.shortToast(getApplicationContext(),"复制成功");
                break;
            case R.id.recharge_submit_but://确认充值
                final String account=rechargeaccounted.getText().toString().trim();
                final  String pwd=rechargepwded.getText().toString().trim();
                if(TextUtils.isEmpty(account)){
                    T.shortToast(getApplicationContext(),"账号不能为空");
                }else if(TextUtils.isEmpty(pwd)){
                    T.shortToast(getApplicationContext(),"密码不能为空");
                }else{

                    new AlertDialog(this)
                            .builder().setMsg("是否确认要充值？")
                            .setTitle("提示")
                            .setPositiveButton("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SendRequest.Recharge(Userid,account, pwd, new mOkCallBack() {
                                        @Override
                                        public void onSuccess(String response) {
                                            if(response.contains("<html>")){
                                                T.shortToast(getApplicationContext(),"服务器异常");
                                                return;
                                            }
                                            MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                                            T.shortToast(getApplicationContext(),myBasebean.getMsg());
                                            if(myBasebean.getResultCode()==0){
                                                finish();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Throwable e) {
                                            T.shortToast(getApplicationContext(),"亲，请检查网络");
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            })
                            .show();
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
