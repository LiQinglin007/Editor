package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.bean.OtherAccountBoundBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;

import zhangphil.iosdialog.widget.AlertDialog;


/**
 * 类描述：第三方账号页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */


public class OtherAccountBoundActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private ImageView otheraccountQQimg;
    private TextView otheraccountQQtv;
    private android.widget.LinearLayout otheraccountQQly;
    private ImageView otheraccountwachatimg;
    private TextView otheraccountwachattv;
    private android.widget.LinearLayout otheraccountwachatly;
    private ImageView otheraccountsinaimg;
    private TextView otheraccountsinatv;
    private android.widget.LinearLayout otheraccountsinaly;

    boolean IsQQ = false;
    boolean IsSina = false;
    boolean IsWaChat = false;

    String Userid = "";

    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_other_account_bound);
        if (PreferenceUtils.getBoolean("IsLogin", false)) {
            Userid = PreferenceUtils.getString("UserId", "");
        } else {
            Userid = "0";
        }
        pDialog = new ProgressDialog(OtherAccountBoundActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();
        initView();
    }


    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("解除账号与绑定");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);

        this.otheraccountsinaly = (LinearLayout) findViewById(R.id.other_account_sina_ly);
        this.otheraccountsinatv = (TextView) findViewById(R.id.other_account_sina_tv);
        this.otheraccountsinaimg = (ImageView) findViewById(R.id.other_account_sina_img);
        this.otheraccountwachatly = (LinearLayout) findViewById(R.id.other_account_wachat_ly);
        this.otheraccountwachattv = (TextView) findViewById(R.id.other_account_wachat_tv);
        this.otheraccountwachatimg = (ImageView) findViewById(R.id.other_account_wachat_img);
        this.otheraccountQQly = (LinearLayout) findViewById(R.id.other_account_QQ_ly);
        this.otheraccountQQtv = (TextView) findViewById(R.id.other_account_QQ_tv);
        this.otheraccountQQimg = (ImageView) findViewById(R.id.other_account_QQ_img);


        getData();
    }


    private void getData() {
        SendRequest.RelieveView(Userid, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if (response.contains("<html>")) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    return;
                }
                OtherAccountBoundBean mBean = JSON.parseObject(response, OtherAccountBoundBean.class);
                if (mBean.getResultCode() == 0) {
                    if (mBean.getData().get(0).getQQSatate().equals("已绑定")) {
                        IsQQ = true;
                    } else {
                        IsQQ = false;
                    }
                    if (mBean.getData().get(0).getWXSatate().equals("已绑定")) {
                        IsWaChat = true;
                    } else {
                        IsWaChat = false;
                    }
                    if (mBean.getData().get(0).getWBSatate().equals("已绑定")) {
                        IsSina = true;
                    } else {
                        IsSina = false;
                    }
                    setView();
                } else {
                    T.shortToast(getApplicationContext(), mBean.getMsg());
                }

            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                T.shortToast(getApplicationContext(), "亲，请检查网络");
            }
        });
    }


    private void setView() {
        if (IsQQ) {
            otheraccountQQtv.setText("已绑定");
            otheraccountQQimg.setImageResource(R.drawable.qq_sel);
        } else {
            otheraccountQQtv.setText("未绑定");
            otheraccountQQimg.setImageResource(R.drawable.qq_nor);
        }
        otheraccountQQly.setOnClickListener(this);

        if (IsWaChat) {
            otheraccountwachattv.setText("已绑定");
            otheraccountwachatimg.setImageResource(R.drawable.weixin_sel);
        } else {
            otheraccountwachattv.setText("未绑定");
            otheraccountwachatimg.setImageResource(R.drawable.weixin_nor);
        }
        otheraccountwachatly.setOnClickListener(this);

        if (IsSina) {
            otheraccountsinatv.setText("已绑定");
            otheraccountsinaimg.setImageResource(R.drawable.sina);
        } else {
            otheraccountsinatv.setText("未绑定");
            otheraccountsinaimg.setImageResource(R.drawable.weibo_nor);
        }
        otheraccountsinaly.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.other_account_sina_ly:
                if (IsSina) {
                    showMyDialog(3);
                } else {
                    T.shortToast(getApplicationContext(), "请使用第三方登录进行绑定");
                }
                break;
            case R.id.other_account_wachat_ly:
                if (IsWaChat) {
                    showMyDialog(2);
                } else {
                    T.shortToast(getApplicationContext(), "请使用第三方登录进行绑定");
                }
                break;
            case R.id.other_account_QQ_ly:
                if (IsQQ) {
                    showMyDialog(1);
                } else {
                    T.shortToast(getApplicationContext(), "请使用第三方登录进行绑定");
                }
                break;
        }
    }


    /**
     * 取消绑定账号
     *
     * @param number 取消账号的id  1：QQ  2：wachet 3:sina
     */
    private void showMyDialog(final int number) {

        new AlertDialog(this)
                .builder().setMsg("确认解除绑定？解除后需重新使用第三方进行登录绑定")
                .setTitle("提示")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Removebinding(number);
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        T.shortToast(getApplicationContext(),"取消");
                    }
                })
                .show();
    }


    //    1解绑QQ    2解绑微信   3解绑微博
    private void Removebinding(final int number) {
        SendRequest.Relieve(Userid, number, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                if (response.contains("<html>")) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    return;
                }
                MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                if (myBasebean.getResultCode() == 0) {
//                    finish();
                    if(number==1){
                        IsQQ=false;
                    }else if(number==2){
                        IsWaChat=false;
                    }else{
                        IsSina=false;
                    }
                    setView();
                }
                T.shortToast(getApplicationContext(), myBasebean.getMsg());
            }

            @Override
            public void onFailure(Throwable e) {
                T.shortToast(getApplicationContext(), "亲，请检查网络");
            }
        });
    }


    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
