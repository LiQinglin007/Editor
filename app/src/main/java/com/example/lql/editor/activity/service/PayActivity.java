package com.example.lql.editor.activity.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.myaccount.RechargeActivity;
import com.example.lql.editor.bean.GetNameBean;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.DialogUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;

import zhangphil.iosdialog.widget.AlertDialog;

/**
 * 类描述：支付
 * 作  者：李清林
 * 时  间：2016/11/30
 * 修改备注：
 */
public class PayActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private TextView paystudionametv;
    private TextView paymoneytv;
    private TextView paybalancetv;
    private TextView payrechargetv;
    private android.widget.Button paysubmitbut;
    double Balance = 0;

    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_pay);
        pDialog = new ProgressDialog(PayActivity.this);
        pDialog.setMessage("加载中...");

        initView();
    }


    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    /**
     * 查询余额，头像，昵称
     */
    private void getData(){
        pDialog.show();
        SendRequest.UserDetail(PreferenceUtils.getString("UserId", ""), new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                GetNameBean mGetNameBean= JSON.parseObject(response,GetNameBean.class);
                if(mGetNameBean.getResultCode()==0){
                    PreferenceUtils.setFloat("Balance", Float.parseFloat(mGetNameBean.getData().getBalance()+""));
                    PreferenceUtils.setString("Img",mGetNameBean.getData().getImg());//头像
                    PreferenceUtils.setString("UserName",mGetNameBean.getData().getUserName()+"");//昵称
                    PreferenceUtils.setString("Real",mGetNameBean.getData().getReal()+"");//认证状态
                }
                setView();
            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                setView();
            }
        });
    }




    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("支付");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);
        this.paysubmitbut = (Button) findViewById(R.id.pay_submit_but);
        this.payrechargetv = (TextView) findViewById(R.id.pay_recharge_tv);
        this.paybalancetv = (TextView) findViewById(R.id.pay_balance_tv);
        this.paymoneytv = (TextView) findViewById(R.id.pay_money_tv);
        this.paystudionametv = (TextView) findViewById(R.id.pay_studioname_tv);
        setView();
        this.paysubmitbut.setOnClickListener(this);
        this.payrechargetv.setOnClickListener(this);
    }



    private void setView(){
        paystudionametv.setText(PublicStaticData.ShopName);
        paymoneytv.setText("￥" + PublicStaticData.ServiceFeeSum);
//        PreferenceUtils.setFloat("Balance",Float.parseFloat(yue+""));
        paybalancetv.setText("￥" +PreferenceUtils.getFloat("Balance",0));
//        Balance = PublicStaticData.mybean.getData().getBalance();
        Balance = Double.parseDouble(PreferenceUtils.getFloat("Balance",0)+"");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.pay_submit_but:
                //确认支付
                if (Balance > Double.parseDouble(PublicStaticData.ServiceFeeSum)) {

                    new AlertDialog(this)
                            .builder().setMsg("是否确认要支付？")
                            .setTitle("提示")
                            .setPositiveButton("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pay();
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            })
                            .show();
                } else {
                    T.shortToast(getApplicationContext(), "余额不足，请先充值");
                }
                break;
            case R.id.pay_recharge_tv:
                //去充值
                startActivity(new Intent(this, RechargeActivity.class));
                break;
        }
    }


    public void pay() {
        String serviceId= PublicStaticData.serviceId;
        String ServiceFee="";
        if (PublicStaticData.serviceType.equals("我要降重")) {
            ServiceFee=PublicStaticData.ServiceFee;
        }else{
            ServiceFee=PublicStaticData.ServiceFeeSum;
        }
        String Userid="";
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }
        SendRequest.waterpay(Userid,serviceId, ServiceFee,PublicStaticData.PayType, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
//                PublicStaticData.mybean.getData().setBalance(Balance-Double.parseDouble(PublicStaticData.ServiceFee));
                MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                if(myBasebean.getResultCode()==0){
                    if(PublicStaticData.Sum!=-1){
                        if(PublicStaticData.Sum==0){
                            DialogUtils.showMyDialog(PayActivity.this, new DialogUtils.DialogCallBack() {
                                @Override
                                public void callback() {
                                    //弹框消失后的回调
                                    sendBrocast();
                                    PayActivity.this.finish();

//                                startActivity(new Intent(PayActivity.this,ServiceDetailsActivity.class));
                                }
                            }, 5);
                        }else{
                            DialogUtils.showMyDialog(PayActivity.this, new DialogUtils.DialogCallBack() {
                                @Override
                                public void callback() {
                                    //弹框消失后的回调
                                    sendBrocast();
                                    PayActivity.this.finish();
//                                startActivity(new Intent(PayActivity.this,ServiceDetailsActivity.class));
                                }
                            }, 6);
                        }
                        return;
                    }



                    if (PublicStaticData.serviceType.equals("我要降重")) {
                        DialogUtils.showMyDialog(PayActivity.this, new DialogUtils.DialogCallBack() {
                            @Override
                            public void callback() {
                                //弹框消失后的回调
                                sendBrocast();
                                PayActivity.this.finish();

//                                startActivity(new Intent(PayActivity.this,ServiceDetailsActivity.class));
                            }
                        }, 4);
                    } else {
                        DialogUtils.showMyDialog(PayActivity.this, new DialogUtils.DialogCallBack() {
                            @Override
                            public void callback() {
                                //弹框消失后的回调
                                sendBrocast();
                                PayActivity.this.finish();
//                                startActivity(new Intent(PayActivity.this,ServiceDetailsActivity.class));
                            }
                        }, 3);
                    }
                }else{
                    T.shortToast(getApplicationContext(),myBasebean.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                T.shortToast(getApplicationContext(),"亲，请检查网络");

            }
        });


    }

    @Override
    protected void onDestroy() {
        PublicStaticData.PayType="1";
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }



    public void sendBrocast(){
        Intent intent = new Intent();
        intent.setAction("com.lql.submission.finish");
        sendBroadcast(intent);
    }


}
