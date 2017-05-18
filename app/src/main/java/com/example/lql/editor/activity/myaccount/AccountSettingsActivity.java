package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
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

/**
 * 类描述：账户设置页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class AccountSettingsActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private android.widget.EditText accountsettingnameed;
    private android.widget.EditText accountsettingnumbered;
    private android.widget.Button accountsettingsubmitbut;
    String name="";
    String number="";
    String Userid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_account_settings);
        name=getIntent().getStringExtra("name");
        number=getIntent().getStringExtra("number");
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("账户设置");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);
        this.accountsettingsubmitbut = (Button) findViewById(R.id.accountsetting_submit_but);
        this.accountsettingnumbered = (EditText) findViewById(R.id.accountsetting_number_ed);
        this.accountsettingnameed = (EditText) findViewById(R.id.accountsetting_name_ed);

        accountsettingnameed.setText(name);
        accountsettingnumbered.setText(number);

        accountsettingsubmitbut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.accountsetting_submit_but:
                final String name=accountsettingnameed.getText().toString().trim();
               final String number=accountsettingnumbered.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    T.shortToast(getApplicationContext(),"账户名称不能为空");
                }else  if(TextUtils.isEmpty(number)){
                    T.shortToast(getApplicationContext(),"账户号不能为空");
                }else{
                    SendRequest.UpdateCard(Userid,number, name, new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if(response.contains("<html>")){
                                T.shortToast(getApplicationContext(),"服务器异常");
                                return;
                            }
                           MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                            if(myBasebean.getResultCode()==0){
                                finish();
                            }
                           T.shortToast(getApplicationContext(),myBasebean.getMsg());
                        }

                        @Override
                        public void onFailure(Throwable e) {

                            T.shortToast(getApplicationContext(),"亲，请检查网络");
                        }
                    });
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
