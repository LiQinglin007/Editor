package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.adapter.me.SummaryOrderAdapter;
import com.example.lql.editor.bean.SummaryOrderBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

import static com.example.lql.editor.widget.RecyclerView.IsBottom.isSlideToBottom;

/**
 * 类描述：速审订单页面
 * 作  者：李清林
 * 时  间：2016/11/27
 * 修改备注：
 */
public class SummaryOrderActivity extends Activity implements View.OnClickListener{
    private TextView title;
    private ImageView leftback;
    private TextView checkordertv1;
    private android.view.View checkorderv1;
    private android.widget.LinearLayout checkorderlay1;
    private TextView checkordertv2;
    private android.view.View checkorderv2;
    private android.widget.LinearLayout checkorderlay2;
    private TextView checkordertv3;
    private android.view.View checkorderv3;
    private android.widget.LinearLayout checkorderlay3;
    private TextView checkordertv4;
    private android.view.View checkorderv4;
    private android.widget.LinearLayout checkorderlay4;
    private TextView checkordertv5;
    private android.view.View checkorderv5;
    private android.widget.LinearLayout checkorderlay5;
    private RecyclerView summaryorder_re;

    SummaryOrderAdapter mSummaryOrderAdapter;//速审订单适配器


    public static int type=1;


    int Page=1;//				页数
    int Rows=10;//				行数

    ArrayList<SummaryOrderBean.DataBean> mList=new ArrayList<>();



    LinearLayout loading;
    int Count=1;
    SwipeRefreshLayout mySwipeRefresh;

    ProgressDialog  pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        pDialog=new ProgressDialog(SummaryOrderActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();
        registBroadcastReceiver();
       initView();
    }


    @Override
    protected void onResume() {
        getData(true,1);
        super.onResume();
    }


    private void initView() {
        this.checkorderlay5 = (LinearLayout) findViewById(R.id.checkorder_lay5);
        this.checkorderv5 = (View) findViewById(R.id.checkorder_v5);
        this.checkordertv5 = (TextView) findViewById(R.id.checkorder_tv5);
        this.checkorderlay4 = (LinearLayout) findViewById(R.id.checkorder_lay4);
        this.checkorderv4 = (View) findViewById(R.id.checkorder_v4);
        this.checkordertv4 = (TextView) findViewById(R.id.checkorder_tv4);
        this.checkorderlay3 = (LinearLayout) findViewById(R.id.checkorder_lay3);
        this.checkorderv3 = (View) findViewById(R.id.checkorder_v3);
        this.checkordertv3 = (TextView) findViewById(R.id.checkorder_tv3);
        this.checkorderlay2 = (LinearLayout) findViewById(R.id.checkorder_lay2);
        this.checkorderv2 = (View) findViewById(R.id.checkorder_v2);
        this.checkordertv2 = (TextView) findViewById(R.id.checkorder_tv2);
        this.checkorderlay1 = (LinearLayout) findViewById(R.id.checkorder_lay1);
        this.checkorderv1 = (View) findViewById(R.id.checkorder_v1);
        this.checkordertv1 = (TextView) findViewById(R.id.checkorder_tv1);
        summaryorder_re= (RecyclerView) findViewById(R.id.summaryorder_re);


        title = (TextView) findViewById(R.id.title_title);
        title.setText("速审订单");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);



        mSummaryOrderAdapter=new SummaryOrderAdapter(mList,SummaryOrderActivity.this);
        summaryorder_re.setLayoutManager(new LinearLayoutManager(this));
//        summaryorder_re.addItemDecoration(new DividerItemDecoration(this, MyDecoration.VERTICAL_LIST));
        summaryorder_re.setAdapter(mSummaryOrderAdapter);
        mSummaryOrderAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.Ordertype="2";
                PublicStaticData.OrderId=mList.get(position).getMyServiceId_()+"";
                startActivity(new Intent(SummaryOrderActivity.this,OrderDetailsActivity.class));
            }
        });

        checkorderlay1.setOnClickListener(this);
        checkorderlay2.setOnClickListener(this);
        checkorderlay3.setOnClickListener(this);
        checkorderlay4.setOnClickListener(this);
        checkorderlay5.setOnClickListener(this);


        loading= (LinearLayout) findViewById(R.id.loading);
        mySwipeRefresh= (SwipeRefreshLayout) findViewById(R.id.mySwipeRefresh);
        mySwipeRefresh.setColorSchemeResources(android.R.color.holo_red_light);
        //设置下拉刷新监听
        mySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Page=1;
                        getData(true,0);
                    }
                }, 2000);
            }
        });

        summaryorder_re.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy!=0){
                    if (isSlideToBottom(recyclerView)) {
                        if(Count>0){
                            Page++;
                            loading.setVisibility(View.VISIBLE);
                            getData(false,1);
                        }else{
                            Toast.makeText(SummaryOrderActivity.this, "没有更多内容了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });




        getData(true,1);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.checkorder_lay1:
                setTitleView();
                checkordertv1.setTextColor(getResources().getColor(R.color.color_1AB394));
                checkorderv1.setBackgroundColor(getResources().getColor(R.color.color_1AB394));
                type=1;
                pDialog.show();
                getData(true,1);
                break;
            case R.id.checkorder_lay2:
                setTitleView();
                checkordertv2.setTextColor(getResources().getColor(R.color.color_1AB394));
                checkorderv2.setBackgroundColor(getResources().getColor(R.color.color_1AB394));
                type=2;
                pDialog.show();
                getData(true,1);
                break;
            case R.id.checkorder_lay3:
                setTitleView();
                checkordertv3.setTextColor(getResources().getColor(R.color.color_1AB394));
                checkorderv3.setBackgroundColor(getResources().getColor(R.color.color_1AB394));
                type=3;
                pDialog.show();
                getData(true,1);
                break;
            case R.id.checkorder_lay4:
                setTitleView();
                checkordertv4.setTextColor(getResources().getColor(R.color.color_1AB394));
                checkorderv4.setBackgroundColor(getResources().getColor(R.color.color_1AB394));
                type=4;
                pDialog.show();
                getData(true,1);
                break;
            case R.id.checkorder_lay5:
                setTitleView();
                checkordertv5.setTextColor(getResources().getColor(R.color.color_1AB394));
                checkorderv5.setBackgroundColor(getResources().getColor(R.color.color_1AB394));
                type=5;
                pDialog.show();
                getData(true,1);
                break;
        }
    }





    private void getData(final boolean IsNeedClean,final int index){
        String Userid="";
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }

//        1:初审中2：复审中3：查稿中4：样刊跟踪5：已完成
        SendRequest.SummaryList(Userid,type, Page, Rows, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                SummaryOrderBean mSummaryOrderBean= JSON.parseObject(response,SummaryOrderBean.class);
                if(mSummaryOrderBean.getResultCode()==0){
                    Count=mSummaryOrderBean.getCount();
                    if(IsNeedClean){
                        mList.clear();
                    }
                    mList.addAll(mSummaryOrderBean.getData());
                    mSummaryOrderAdapter.notifyDataSetChanged();
                }else{
                    T.shortToast(getApplicationContext(),mSummaryOrderBean.getMsg());
                }

                loading.setVisibility(View.GONE);
                if(index==0){
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                T.shortToast(getApplicationContext(),"亲，请检查网络");
                loading.setVisibility(View.GONE);
                if(index==0){
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }

            }
        });

    }






    private void setTitleView(){
        checkorderv1.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkorderv2.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkorderv3.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkorderv4.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkorderv5.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkordertv1.setTextColor(getResources().getColor(R.color.color_333));
        checkordertv2.setTextColor(getResources().getColor(R.color.color_333));
        checkordertv3.setTextColor(getResources().getColor(R.color.color_333));
        checkordertv4.setTextColor(getResources().getColor(R.color.color_333));
        checkordertv5.setTextColor(getResources().getColor(R.color.color_333));
    }


    private void registBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lql.getDataSummaryOrder");
        registerReceiver(mFreshReceiver, filter);
    }

    private BroadcastReceiver mFreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.lql.getDataSummaryOrder".equals(action)) {
                getData(true,1);
            }
        }
    };





    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        type=1;
        unregisterReceiver(mFreshReceiver);
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
