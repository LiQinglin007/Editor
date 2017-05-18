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
import com.example.lql.editor.activity.service.ServiceDetailsActivity;
import com.example.lql.editor.adapter.me.MyEvaluateAdapter;
import com.example.lql.editor.bean.MyEvaluateBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.DividerItemDecoration;
import com.example.lql.editor.widget.RecyclerView.MyDecoration;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

import static com.example.lql.editor.widget.RecyclerView.IsBottom.isSlideToBottom;

/**
 * 类描述：我的评价
 * 作  者：李清林
 * 时  间：2016/11/27
 * 修改备注：
 */
public class MyEvaluateActivity extends Activity {

    private android.support.v7.widget.RecyclerView myevaluatere;
    private TextView title;
    private ImageView leftback;
    MyEvaluateAdapter mMyEvaluateAdapter;//我的评论适配器
    ArrayList<MyEvaluateBean.DataBean> mList=new ArrayList<>();

    int Page=1;//				页数
    int Rows=20;//				行数


    LinearLayout loading;
    int Count=1;
    SwipeRefreshLayout mySwipeRefresh;

    String Userid="";

    ProgressDialog  pDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_my_evaluate);
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }

        pDialog=new ProgressDialog(MyEvaluateActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();
        initView();
    }

    private void initView(){
        this.myevaluatere = (RecyclerView) findViewById(R.id.myevaluate_re);
        title = (TextView) findViewById(R.id.title_title);
        title.setText("我的评价");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mMyEvaluateAdapter=new MyEvaluateAdapter(mList,MyEvaluateActivity.this);
        myevaluatere.setLayoutManager(new LinearLayoutManager(MyEvaluateActivity.this));
        myevaluatere.addItemDecoration(new DividerItemDecoration(MyEvaluateActivity.this, MyDecoration.VERTICAL_LIST));
        myevaluatere.setAdapter(mMyEvaluateAdapter);

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
                        getData(true,0,1);
                    }
                }, 2000);
            }
        });

        myevaluatere.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        getData(false,1,Page);
                    }else{
                        Toast.makeText(MyEvaluateActivity.this, "没有更多内容了", Toast.LENGTH_SHORT).show();
                    }
                }}
            }
        });
        mMyEvaluateAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                    PublicStaticData.ServiceId=mList.get(position).getServiceId()+"";

                //1：速审  2：查重   3：降重
//           评价的服务类型，1：查重2:降重3：速审
                if(mList.get(position).getType()==1){
                    PublicStaticData.PopNumberTitle=2;
                }else if(mList.get(position).getType()==2){
                    PublicStaticData.PopNumberTitle=3;
                }else if(mList.get(position).getType()==3){
                    PublicStaticData.PopNumberTitle=1;
                }
                startActivity(new Intent(MyEvaluateActivity.this, ServiceDetailsActivity.class));
            }
        });



        getData(true,1,1);
    }



    private void getData(final boolean IsClearn,final int index,int Page){
        SendRequest.CommentList(Userid,Page,20,new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                MyEvaluateBean myEvaluateBean= JSON.parseObject(response,MyEvaluateBean.class);
                if(myEvaluateBean.getResultCode()==0){
                    if(IsClearn){
                        mList.clear();
                    }
                    if(myEvaluateBean.getData().size()==0){
                        T.shortToast(getApplicationContext(),"暂无评价");
                        return;
                    }
                    mList.addAll(myEvaluateBean.getData());
                    mMyEvaluateAdapter.notifyDataSetChanged();
                }else{
                    T.shortToast(getApplicationContext(),myEvaluateBean.getMsg());
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
                loading.setVisibility(View.GONE);
                if(index==0){
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }
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
