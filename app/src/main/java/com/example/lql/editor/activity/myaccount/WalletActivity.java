package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.WalletBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.ToDouble;

/**
 * 类描述：我的账户页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class WalletActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private TextView title_right;
    private ImageView leftback;

    private TextView walletmoneytv;
    private android.widget.Button walletrechargebut;
    private android.widget.Button walletwithdrawalsbut;

    public String name="";
    public  String number="";
    String money="";
    String Userid="";

    ProgressDialog   pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_wallet);

        pDialog=new ProgressDialog(WalletActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();


        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }
        initView();
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title_right = (TextView) findViewById(R.id.right_title);
        title.setText("账户余额");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);
        title_right.setOnClickListener(this);


        this.walletwithdrawalsbut = (Button) findViewById(R.id.wallet_withdrawals_but);
        this.walletrechargebut = (Button) findViewById(R.id.wallet_recharge_but);
        this.walletmoneytv = (TextView) findViewById(R.id.wallet_money_tv);
        walletmoneytv.setText("0");
        walletwithdrawalsbut.setOnClickListener(this);
        walletrechargebut.setOnClickListener(this);
        getData();
    }


    private void getData(){
        SendRequest.DepositView(Userid,new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                WalletBean mBean= JSON.parseObject(response,WalletBean.class);
                if(mBean.getResultCode()==0){
                    name=mBean.getData().getAlipayName();
                    number=mBean.getData().getAlipayCard();
                    money= ToDouble.toDpuble(mBean.getData().getBalance());
                    walletmoneytv.setText(money);
                }else{
                    T.shortToast(getApplicationContext(),mBean.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                T.shortToast(getApplicationContext(),"亲，请检查网络");

            }
        });
    }

    Intent intent;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.right_title://账户设置
                 intent=new Intent(WalletActivity.this,AccountSettingsActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("number",number);
                startActivity(intent);
                break;
            case R.id.wallet_withdrawals_but://提现
                intent=new Intent(WalletActivity.this,WithdrawalsActivity.class);
                intent.putExtra("money",money);
                intent.putExtra("name",name);
                intent.putExtra("number",number);
                startActivity(intent);
                break;
            case R.id.wallet_recharge_but://充值
                startActivity(new Intent(WalletActivity.this,RechargeActivity.class));
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
