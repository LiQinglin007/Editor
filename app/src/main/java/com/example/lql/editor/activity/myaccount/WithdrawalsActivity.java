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
import com.example.lql.editor.utils.DialogUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;

import zhangphil.iosdialog.widget.AlertDialog;

/**
 * 类描述：提现页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class WithdrawalsActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private TextView withdrawalsmoneytv;
    private TextView withdrawalsaccounttv;
    private android.widget.EditText withdrawalsnumbered;
    private android.widget.Button withdrawalssubmitbut;
    String name="";
    String number="";
    String money="";
    String Userid="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_withdrawals);
        name=getIntent().getStringExtra("name");
        number=getIntent().getStringExtra("number");
        money=getIntent().getStringExtra("money");
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("提现");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);


        this.withdrawalssubmitbut = (Button) findViewById(R.id.withdrawals_submit_but);
        this.withdrawalsnumbered = (EditText) findViewById(R.id.withdrawals_number_ed);
        this.withdrawalsaccounttv = (TextView) findViewById(R.id.withdrawals_account_tv);
        this.withdrawalsmoneytv = (TextView) findViewById(R.id.withdrawals_money_tv);

        withdrawalsmoneytv.setText("￥"+money);
//                                      151             0084           6748
//        String pwdNumber=number.substring(0,3)+"****"+number.substring(7,11);
        withdrawalsaccounttv.setText(TextUtils.isEmpty(number)||number.equals("null")?"":name+"("+number+")");
        withdrawalssubmitbut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;

            case R.id.withdrawals_submit_but:


                if(TextUtils.isEmpty(number)||number.equals("null")){
                    T.shortToast(getApplicationContext(),"请先设置提现账号");
                    return;
                }

                final String myDepositMoney=withdrawalsnumbered.getText().toString().trim();
                if(TextUtils.isEmpty(myDepositMoney)){
                    T.shortToast(getApplicationContext(),"提现金额不能为空");
                }else {
                    if (money.contains("-") || money.contains("~")) {

                    } else {
                        Double Balance = Double.parseDouble(money);
                        final Double DepositMoney = Double.parseDouble(myDepositMoney);

                        if (Balance == 0) {
                            T.shortToast(getApplicationContext(), "余额不足");
                            return;
                        }
                        if (DepositMoney > Balance) {
                            T.shortToast(getApplicationContext(), "余额不足");
                            return;
                        }

                        if (DepositMoney <= 0) {
                            T.shortToast(getApplicationContext(), "提现金额不能为0");
                            return;
                        }


                        new AlertDialog(this)
                                .builder().setMsg("是否确认要提现？")
                                .setTitle("提示")
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        SendRequest.AddDeposit(Userid, DepositMoney + "", new mOkCallBack() {
                                            @Override
                                            public void onSuccess(String response) {
                                                if (response.contains("<html>")) {
                                                    T.shortToast(getApplicationContext(), "服务器异常");
                                                    return;
                                                }
                                                MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                                                if (myBasebean.getResultCode() == 0) {
                                                    DialogUtils.showMyDialogTX(WithdrawalsActivity.this, myDepositMoney + "", new DialogUtils.DialogCallBack() {
                                                        @Override
                                                        public void callback() {
                                                            finish();
                                                        }
                                                    });
                                                } else {
                                                    T.shortToast(getApplicationContext(), myBasebean.getMsg());
                                                    finish();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Throwable e) {
                                                T.shortToast(getApplicationContext(), "亲，请检查网络");
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
