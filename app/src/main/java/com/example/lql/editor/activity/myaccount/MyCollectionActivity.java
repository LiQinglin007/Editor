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
import com.example.lql.editor.activity.studio.StudioDetailsActivity;
import com.example.lql.editor.adapter.me.MyCollectionServiceAdapter;
import com.example.lql.editor.adapter.me.MyCollectionStudioAdapter;
import com.example.lql.editor.bean.CollectionServiceBean;
import com.example.lql.editor.bean.CollectionStudioBean;
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
 * 类描述：我的收藏页面
 * 作  者：李清林
 * 时  间：2016/11/27
 * 修改备注：
 */
public class MyCollectionActivity extends Activity implements View.OnClickListener{

    private TextView title;
    private ImageView leftback;
    private TextView checkordertv1;
    private View checkorderv1;
    private android.widget.LinearLayout checkorderlay1;
    private TextView checkordertv2;
    private View checkorderv2;
    private android.widget.LinearLayout checkorderlay2;
    private android.support.v7.widget.RecyclerView servicere;
    private android.support.v7.widget.RecyclerView studiore;

    MyCollectionStudioAdapter mMyCollectionStudioAdapter;//收藏工作室
    MyCollectionServiceAdapter mMyCollectionServiceAdapter;//收藏服务

    ArrayList<CollectionServiceBean.DataBean> mListService=new ArrayList<>();//服务的
    ArrayList<CollectionStudioBean.DataBean> mListStudio=new ArrayList<>();//工作室的


    int Type=1;  // 1：服务2：工作室
    int Page=1;//				页数
    int Rows=20;//				行数
    LinearLayout loading;
    int Count=1;
    SwipeRefreshLayout mySwipeRefresh;
    ProgressDialog   pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_my_collection);
        pDialog=new ProgressDialog(MyCollectionActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();
        initView();
        getData(1,true,0,1);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("我的收藏");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);
        this.studiore = (RecyclerView) findViewById(R.id.studio_re);
        this.servicere = (RecyclerView) findViewById(R.id.service_re);
        this.checkorderlay2 = (LinearLayout) findViewById(R.id.checkorder_lay2);
        this.checkorderv2 = (View) findViewById(R.id.checkorder_v2);
        this.checkordertv2 = (TextView) findViewById(R.id.checkorder_tv2);
        this.checkorderlay1 = (LinearLayout) findViewById(R.id.checkorder_lay1);
        this.checkorderv1 = (View) findViewById(R.id.checkorder_v1);
        this.checkordertv1 = (TextView) findViewById(R.id.checkorder_tv1);



        mMyCollectionStudioAdapter=new MyCollectionStudioAdapter(mListStudio,MyCollectionActivity.this);
        studiore.setLayoutManager(new LinearLayoutManager(this));
        studiore.addItemDecoration(new DividerItemDecoration(MyCollectionActivity.this, MyDecoration.VERTICAL_LIST));
        studiore.setAdapter(mMyCollectionStudioAdapter);


        mMyCollectionServiceAdapter=new MyCollectionServiceAdapter(mListService,MyCollectionActivity.this);
        servicere.setLayoutManager(new LinearLayoutManager(this));
        servicere.addItemDecoration(new DividerItemDecoration(MyCollectionActivity.this, MyDecoration.VERTICAL_LIST));
        servicere.setAdapter(mMyCollectionServiceAdapter);


        checkorderlay1.setOnClickListener(this);
        checkorderlay2.setOnClickListener(this);


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
                        Page++;
                        getData(Type,true,0,1);
                    }
                }, 2000);
            }
        });

        servicere.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        getData(1,false,1,Page);
                    }else{
                        Toast.makeText(MyCollectionActivity.this, "没有更多内容了", Toast.LENGTH_SHORT).show();
                    }
                }}
            }

        });

        studiore.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy!=0) {
                    if (isSlideToBottom(recyclerView)) {
                        if (Count > 0) {
                            Page++;
                            loading.setVisibility(View.VISIBLE);
                            getData(2, false, 1,Page);
                        } else {
                            Toast.makeText(MyCollectionActivity.this, "没有更多内容了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        mMyCollectionStudioAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.StudioId=mListStudio.get(position).getId()+"";
                startActivity(new Intent(MyCollectionActivity.this, StudioDetailsActivity.class));
            }
        });


        mMyCollectionServiceAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.ServiceId=mListService.get(position).getId()+"";
//                "Type": 1								1：检测查重2：修改降重3：编辑速审
                if(mListService.get(position).getType()==1){
                    PublicStaticData.PopNumberTitle=2;
                }else if(mListService.get(position).getType()==2){
                    PublicStaticData.PopNumberTitle=3;
                }else if(mListService.get(position).getType()==3){
                    PublicStaticData.PopNumberTitle=1;
                }
                startActivity(new Intent(MyCollectionActivity.this, ServiceDetailsActivity.class));
            }
        });


    }



    /**
     * @param type    1：服务2：工作室
     * @param IsClrean   是否需要清除原来的数据
     * @param index
     */
    private void getData(final int type,final boolean IsClrean,final int index,int Page){
        String Userid="";
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }

        SendRequest.UserFavoriteList(Userid,type,Page,20, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                if(type==1){
                    CollectionServiceBean mCollectionServiceBean=JSON.parseObject(response,CollectionServiceBean.class);
                    if(mCollectionServiceBean.getResultCode()==0){
                        Count=mCollectionServiceBean.getCount();
                        if(IsClrean){
                            mListService.clear();
                        }
                        mListService.addAll(mCollectionServiceBean.getData());
                        mMyCollectionServiceAdapter.notifyDataSetChanged();
                    }else{
                        T.shortToast(getApplicationContext(),mCollectionServiceBean.getMsg());
                    }
                }else{
                    CollectionStudioBean mCollectionStudioBean= JSON.parseObject(response,CollectionStudioBean.class);
                    Count=mCollectionStudioBean.getCount();
                    if(mCollectionStudioBean.getResultCode()==0){
                        if(IsClrean){
                            mListStudio.clear();
                        }
                        mListStudio.addAll(mCollectionStudioBean.getData());
                        mMyCollectionStudioAdapter.notifyDataSetChanged();
                    }else{
                        T.shortToast(getApplicationContext(),mCollectionStudioBean.getMsg());
                    }
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
            case R.id.checkorder_lay1://服务
                servicere.setVisibility(View.VISIBLE);
                setTitleView();
                checkordertv1.setTextColor(getResources().getColor(R.color.color_1AB394));
                checkorderv1.setBackgroundColor(getResources().getColor(R.color.color_1AB394));
                Type=1;
                getData(1,true,0,1);
                break;
            case R.id.checkorder_lay2://工作室
                servicere.setVisibility(View.GONE);
                setTitleView();
                checkordertv2.setTextColor(getResources().getColor(R.color.color_1AB394));
                checkorderv2.setBackgroundColor(getResources().getColor(R.color.color_1AB394));
                Type=2;
                getData(2,true,0,1);
                break;
        }
    }


    private void setTitleView(){
        checkorderv1.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkorderv2.setBackgroundColor(getResources().getColor(R.color.color_white));
        checkordertv1.setTextColor(getResources().getColor(R.color.black));
        checkordertv2.setTextColor(getResources().getColor(R.color.black));
    }


    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
