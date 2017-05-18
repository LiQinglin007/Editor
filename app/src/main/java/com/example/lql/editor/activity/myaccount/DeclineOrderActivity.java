package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.adapter.me.DeclineOrderAdapter;
import com.example.lql.editor.bean.DeclineOrderBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

import static com.example.lql.editor.widget.RecyclerView.IsBottom.isSlideToBottom;

/**
 * 类描述：降重订单页面
 * 作  者：李清林
 * 时  间：2016/11/27
 * 修改备注：
 */
public class DeclineOrderActivity extends Activity implements View.OnClickListener{
    private TextView title;
    private ImageView leftback;
    private TextView checkordertv1;
    private View checkorderv1;
    private android.widget.LinearLayout checkorderlay1;
    private TextView checkordertv2;
    private View checkorderv2;
    private android.widget.LinearLayout checkorderlay2;
    private TextView checkordertv3;
    private View checkorderv3;
    private android.widget.LinearLayout checkorderlay3;
    private android.support.v7.widget.RecyclerView declineorderre;
    DeclineOrderAdapter mDeclineOrderAdapter;//适配器
    ArrayList<DeclineOrderBean.DataBean> mList=new ArrayList<>();


   public static  int type=1;


    int Page=1;//				页数
    int Rows=10;//				行数


    LinearLayout loading;
    int Count=1;
    SwipeRefreshLayout mySwipeRefresh;
    ProgressDialog  pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_decline_order);
        pDialog=new ProgressDialog(DeclineOrderActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();

        initView();
    }

    @Override
    protected void onResume() {
        getData(true,1);
        super.onResume();
    }
    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("降重订单");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);
        this.declineorderre = (RecyclerView) findViewById(R.id.declineorder_re);
        this.checkorderlay3 = (LinearLayout) findViewById(R.id.checkorder_lay3);
        this.checkorderv3 = (View) findViewById(R.id.checkorder_v3);
        this.checkordertv3 = (TextView) findViewById(R.id.checkorder_tv3);
        this.checkorderlay2 = (LinearLayout) findViewById(R.id.checkorder_lay2);
        this.checkorderv2 = (View) findViewById(R.id.checkorder_v2);
        this.checkordertv2 = (TextView) findViewById(R.id.checkorder_tv2);
        this.checkorderlay1 = (LinearLayout) findViewById(R.id.checkorder_lay1);
        this.checkorderv1 = (View) findViewById(R.id.checkorder_v1);
        this.checkordertv1 = (TextView) findViewById(R.id.checkorder_tv1);


        checkorderlay1.setOnClickListener(this);
        checkorderlay2.setOnClickListener(this);
        checkorderlay3.setOnClickListener(this);



        mDeclineOrderAdapter=new DeclineOrderAdapter(mList,DeclineOrderActivity.this);
        declineorderre.setLayoutManager(new LinearLayoutManager(this));
//        declineorderre.addItemDecoration(new DividerItemDecoration(this, MyDecoration.VERTICAL_LIST));
        declineorderre.setAdapter(mDeclineOrderAdapter);
        mDeclineOrderAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.Ordertype="1";
                PublicStaticData.OrderId=mList.get(position).getMyServiceId_()+"";
                startActivity(new Intent(DeclineOrderActivity.this,OrderDetailsActivity.class));
            }
        });




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

        declineorderre.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        Toast.makeText(DeclineOrderActivity.this, "没有更多内容了", Toast.LENGTH_SHORT).show();
                    }
                }
            }}
        });




        getData(true,1);
    }




    private void getData(final boolean IsNeedClean,final int index){
        String Userid="";
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }

//       	1:进行中2：待确认3：已完成
        SendRequest.ReduceRepeat(Userid,type, Page, Rows, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                DeclineOrderBean mDeclineOrderBean= JSON.parseObject(response,DeclineOrderBean.class);
                if(mDeclineOrderBean.getResultCode()==0){
                    Count=mDeclineOrderBean.getCount();
                    if(IsNeedClean){
                        mList.clear();
                    }
                    mList.addAll(mDeclineOrderBean.getData());
                    mDeclineOrderAdapter.notifyDataSetChanged();
                }else{
                    T.shortToast(getApplicationContext(),mDeclineOrderBean.getMsg());
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
        }
    }


    private void setTitleView(){
        checkorderv1.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkorderv2.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkorderv3.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkordertv1.setTextColor(getResources().getColor(R.color.color_333));
        checkordertv2.setTextColor(getResources().getColor(R.color.color_333));
        checkordertv3.setTextColor(getResources().getColor(R.color.color_333));
    }





    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        type=1;

        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
