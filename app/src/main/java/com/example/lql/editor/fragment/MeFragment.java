package com.example.lql.editor.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.main.LoginActivity;
import com.example.lql.editor.activity.myaccount.AboutUsActivity;
import com.example.lql.editor.activity.myaccount.AuthenticationActivity;
import com.example.lql.editor.activity.myaccount.CheckOrderActivity;
import com.example.lql.editor.activity.myaccount.DeclineOrderActivity;
import com.example.lql.editor.activity.myaccount.FeedbackActivity;
import com.example.lql.editor.activity.myaccount.MyAccountSettingActivity;
import com.example.lql.editor.activity.myaccount.MyCollectionActivity;
import com.example.lql.editor.activity.myaccount.MyEvaluateActivity;
import com.example.lql.editor.activity.myaccount.OtherAccountBoundActivity;
import com.example.lql.editor.activity.myaccount.StudioApply;
import com.example.lql.editor.activity.myaccount.SummaryOrderActivity;
import com.example.lql.editor.activity.myaccount.UpdatePwdActivity;
import com.example.lql.editor.activity.myaccount.WalletActivity;
import com.example.lql.editor.bean.GetNameBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.CircleImageView;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.UpdateAppUtils;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import zhangphil.iosdialog.widget.AlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    private CircleImageView myaccountheatimg;
    private android.widget.TextView myaccountphonetv;
    private android.widget.TextView myaccountmoneytv;
    private android.widget.ImageView myaccountnextimg;
    private android.widget.LinearLayout myaccountmyservicely;
    private android.widget.LinearLayout myaccountcheckly;
    private android.widget.LinearLayout myaccountsummaryly;
    private android.widget.LinearLayout myaccountfallsly;
    private android.widget.LinearLayout myaccountcollectionly;
    private android.widget.LinearLayout myaccountwalletly;
    private android.widget.LinearLayout myaccountevaluately;
    private android.widget.LinearLayout myaccountauthenticationly;
    private android.widget.LinearLayout myaccountchangepwdly;
    private android.widget.LinearLayout myaccountboundly;
    private android.widget.LinearLayout myaccountfeedbackly;
    private android.widget.LinearLayout myaccountaboutly;
    private android.widget.LinearLayout myaccounttoupdately;
    private android.widget.LinearLayout myaccountStudioApplicationly;
    Button singout_save_but;//退出
    View view;
    boolean IsLogin;
    TextView real_tv;
    TextView state_tv;
    String real;
    String state;
    ScrollView myScroll;
    SwipeRefreshLayout me_swipe;


    private TextView title;
    private ImageView left;

    private LinearLayout title_me;
//    DecimalFormat    df   = new DecimalFormat("######0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
        registBroadcastReceiver();
        initView();
        setView();

        return view;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //判断该Fragment时候已经正在前台显示  通过这两个判断，就可以知道什么时候去加载数据了
        if (isVisibleToUser && isVisible()) {
            IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
            if(IsLogin){
                getData();
            }else{
                setView();
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void registBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lql.toMain");
        getActivity().registerReceiver(mFreshReceiver, filter);
    }

    private BroadcastReceiver mFreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.lql.toMain".equals(action)) {
                myScroll.smoothScrollTo(0,0);
                IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
                setView();
            }
        }
    };



    @Override
    public void onResume() {
        IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
        Log.e("ISLOGIN==",IsLogin+"AAA");
        if(IsLogin){
            getData();
        }else{
            setView();
        }
        super.onResume();
    }

    /**
     * 查询余额，头像，昵称
     */
    private void getData(){
        PreferenceUtils.setBoolean("Click",false);
        sendBrocast1();
        SendRequest.UserDetail(PreferenceUtils.getString("UserId", ""), new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                PreferenceUtils.setBoolean("Click",true);
                sendBrocast1();
                GetNameBean mGetNameBean= JSON.parseObject(response,GetNameBean.class);
                if(mGetNameBean.getResultCode()==0){
                    PreferenceUtils.setFloat("Balance",Float.parseFloat(mGetNameBean.getData().getBalance()+""));
                    PreferenceUtils.setString("Img",mGetNameBean.getData().getImg());//头像
                    PreferenceUtils.setString("UserName",mGetNameBean.getData().getUserName()+"");//昵称
                    PreferenceUtils.setString("Real",mGetNameBean.getData().getReal()+"");//认证状态
                }
                setView();
            }

            @Override
            public void onFailure(Throwable e) {
                PreferenceUtils.setBoolean("Click",true);
                sendBrocast1();
                setView();
            }
        });
    }

    /**
     * 设置图片  文字
     */
    private void setView() {
        me_swipe.setRefreshing(false);
        if (IsLogin) {
            String nickname=PreferenceUtils.getString("UserName","").trim();
            String phone=PreferenceUtils.getString("Telphone","").trim();
            myaccountphonetv.setText(TextUtils.isEmpty(nickname)? phone:nickname);
            float mfloat=PreferenceUtils.getFloat("Balance",0);

            double doublem=Double.parseDouble(mfloat+"");
            DecimalFormat df   = new DecimalFormat("######0.00");
            myaccountmoneytv.setText("账户余额：￥" + df.format(doublem));
            Picasso.with(getActivity()).load(MyUrl.Pic+PreferenceUtils.getString("Img","")).into(myaccountheatimg);
            this.singout_save_but.setVisibility(View.VISIBLE);

            real = PreferenceUtils.getString("Real", "2");

            if (real.equals("2")) {
                real_tv.setText("未认证");
            } else if (real.equals("1")) {
                real_tv.setText("已实名认证");
            } else {
                real_tv.setText("正在审核");
            }

        } else {
            myaccountphonetv.setText("未登录");
            myaccountmoneytv.setText("账户余额：0");
            real_tv.setText("");
            myaccountheatimg.setImageResource(R.drawable.icon_head_nor1);
            this.singout_save_but.setVisibility(View.GONE);
        }
    }

    private void initView() {
        title_me= (LinearLayout) view.findViewById(R.id.title_me);
        myScroll= (ScrollView) view.findViewById(R.id.myScroll);
        real_tv = (TextView) view.findViewById(R.id.real_tv);
        state_tv = (TextView) view.findViewById(R.id.state_tv);
        me_swipe= (SwipeRefreshLayout) view.findViewById(R.id.me_swipe);
//        "real": 0							0:正在审核1：已实名认证2：未认证


        state = PreferenceUtils.getString("state", "0");
        if (state.equals("0")) {
            state_tv.setText("未申请");
        } else {
            state_tv.setText("已申请");
        }


        this.myaccounttoupdately = (LinearLayout) view.findViewById(R.id.myaccount_toupdate_ly);
        this.myaccountaboutly = (LinearLayout) view.findViewById(R.id.myaccount_about_ly);
        this.myaccountfeedbackly = (LinearLayout) view.findViewById(R.id.myaccount_feedback_ly);
        this.myaccountboundly = (LinearLayout) view.findViewById(R.id.myaccount_bound_ly);
        this.myaccountStudioApplicationly = (LinearLayout) view.findViewById(R.id.myaccount_StudioApplication_ly);
        this.singout_save_but = (Button) view.findViewById(R.id.singout_save_but);
        myaccounttoupdately.setOnClickListener(this);
        myaccountaboutly.setOnClickListener(this);
        myaccountfeedbackly.setOnClickListener(this);
        myaccountboundly.setOnClickListener(this);
        myaccountStudioApplicationly.setOnClickListener(this);
        this.singout_save_but.setOnClickListener(this);

        this.myaccountchangepwdly = (LinearLayout) view.findViewById(R.id.myaccount_changepwd_ly);
        this.myaccountauthenticationly = (LinearLayout) view.findViewById(R.id.myaccount_authentication_ly);
        this.myaccountevaluately = (LinearLayout) view.findViewById(R.id.myaccount_evaluate_ly);
        myaccountchangepwdly.setOnClickListener(this);
        myaccountauthenticationly.setOnClickListener(this);
        myaccountevaluately.setOnClickListener(this);

        this.myaccountwalletly = (LinearLayout) view.findViewById(R.id.myaccount_wallet_ly);
        this.myaccountcollectionly = (LinearLayout) view.findViewById(R.id.myaccount_collection_ly);
        this.myaccountfallsly = (LinearLayout) view.findViewById(R.id.myaccount_falls_ly);
        myaccountwalletly.setOnClickListener(this);
        myaccountcollectionly.setOnClickListener(this);
        myaccountfallsly.setOnClickListener(this);

        this.myaccountsummaryly = (LinearLayout) view.findViewById(R.id.myaccount_summary_ly);
        this.myaccountcheckly = (LinearLayout) view.findViewById(R.id.myaccount_check_ly);
        this.myaccountmyservicely = (LinearLayout) view.findViewById(R.id.myaccount_myservice_ly);
        myaccountsummaryly.setOnClickListener(this);
        myaccountcheckly.setOnClickListener(this);
        myaccountmyservicely.setOnClickListener(this);

        this.myaccountnextimg = (ImageView) view.findViewById(R.id.myaccount_next_img);
        myaccountnextimg.setOnClickListener(this);
        title_me.setOnClickListener(this);

        this.myaccountmoneytv = (TextView) view.findViewById(R.id.myaccount_money_tv);
        this.myaccountphonetv = (TextView) view.findViewById(R.id.myaccount_phone_tv);
        this.myaccountheatimg = (CircleImageView) view.findViewById(R.id.myaccount_heat_img);

        title = (TextView) view.findViewById(R.id.title_title);
        title.setText("我的");
        left = (ImageView) view.findViewById(R.id.titleBar_left_img);
        left.setVisibility(View.INVISIBLE);

        me_swipe.setColorSchemeResources(android.R.color.holo_red_light);
        me_swipe.setEnabled(false);
        //设置下拉刷新监听
        me_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
                        if(IsLogin){
                            getData();
                        }else{

//                            me_swipe.setRefreshing(false);
                            setView();
                        }
                    }
                }, 0);
            }
        });


        if (myScroll != null) {
            myScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (me_swipe != null) {
                        IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
                        if(IsLogin){
                            me_swipe.setEnabled(myScroll.getScrollY() == 0);
                        }else{
                            me_swipe.setEnabled(false);
                        }
                    }
                }
            });
        }

        myScroll.smoothScrollTo(0,0);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myaccount_next_img://编辑个人资料
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), MyAccountSettingActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;

            case R.id.title_me:
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), MyAccountSettingActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_myservice_ly://我要服务
//                startActivity(new Intent(getActivity(), DeclineOrderActivity.class));
                break;
            case R.id.myaccount_check_ly://查重订单
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), CheckOrderActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_summary_ly://速审订单
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), SummaryOrderActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_falls_ly://降重订单
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), DeclineOrderActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_collection_ly://我的收藏
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), MyCollectionActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_wallet_ly://钱包账户
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), WalletActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_evaluate_ly://我的评价
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), MyEvaluateActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

                break;
            case R.id.myaccount_authentication_ly://实名认证
//                startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                if (IsLogin) {
                    if (real.equals("2")) {
                        startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                    } else if (real.equals("1")) {
                        T.shortToast(getActivity(), "已实名认证");
                    } else {
                        T.shortToast(getActivity(), "正在审核");
                    }
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_changepwd_ly://修改密码
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), UpdatePwdActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_bound_ly://账号绑定
                if (IsLogin) {
                    startActivity(new Intent(getActivity(), OtherAccountBoundActivity.class));
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_StudioApplication_ly://工作室申请
                if (IsLogin) {
//                    if (state.equals("0")) {
                        startActivity(new Intent(getActivity(), StudioApply.class));
//                    } else {
//                        T.shortToast(getActivity(), "您已经申请工作室");
//                    }
                } else {
                    T.shortToast(getActivity(), "尚未登录，请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.myaccount_feedback_ly://意见反馈
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.myaccount_about_ly://关于我们
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.myaccount_toupdate_ly://检测更新
                PublicStaticData.UpdataApp = true;
                UpdateAppUtils.UpdateAppUtils(getActivity(),true);
                break;
            case R.id.singout_save_but://退出登录
                new AlertDialog(getActivity())
                        .builder().setMsg("是否退出登录？")
                        .setTitle("提示")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PreferenceUtils.setBoolean("IsLogin", false);
                                    PreferenceUtils.setBoolean("Login", false);
                                if (!TextUtils.isEmpty(PublicStaticData.Name)) {
                                    Platform mPlatform = ShareSDK.getPlatform(PublicStaticData.Name);
                                    mPlatform.removeAccount(true);
                                }
                                PublicStaticData.MainFragmentNmuber = 0;
                                sendBrocast();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
                break;
        }
    }

    public void sendBrocast() {
        Intent intent = new Intent();
        intent.setAction("com.lql.toMain");
        getActivity().sendBroadcast(intent);
    }

    public void sendBrocast1(){
        Intent intent = new Intent();
        intent.setAction("com.lql.MainActivity.setClick");
        getActivity().sendBroadcast(intent);
    }

}
