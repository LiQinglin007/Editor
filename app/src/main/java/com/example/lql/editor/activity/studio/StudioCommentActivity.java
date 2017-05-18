package com.example.lql.editor.activity.studio;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.adapter.studio.StudioCommentAdapter;
import com.example.lql.editor.bean.StudioCommentBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.DividerItemDecoration;
import com.example.lql.editor.widget.RecyclerView.MyDecoration;

import java.util.ArrayList;

import static com.example.lql.editor.widget.RecyclerView.IsBottom.isSlideToBottom;

/**
 * 类描述：工作室评论
 * 作  者：李清林
 * 时  间：2016/11/27
 * 修改备注：
 */
public class StudioCommentActivity extends Activity {

    private android.support.v7.widget.RecyclerView studiocommentre;
    private TextView title;
    private ImageView leftback;
    ArrayList<StudioCommentBean.DataBean> strList = new ArrayList<>();
    StudioCommentAdapter mStudioCommentAdapter;//评论适配器

    int Page = 1;//				页数
    int Rows = 20;//				行数


    LinearLayout loading;
    int Count = 1;
    SwipeRefreshLayout mySwipeRefresh;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublicStaticData.mActivityList.add(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_studio_comment);

        pDialog = new ProgressDialog(StudioCommentActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();


        initView();
    }

    private void initView() {
        this.studiocommentre = (RecyclerView) findViewById(R.id.studio_comment_re);
        title = (TextView) findViewById(R.id.title_title);
        title.setText("工作室评论");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mStudioCommentAdapter = new StudioCommentAdapter(StudioCommentActivity.this, strList);
        studiocommentre.setLayoutManager(new LinearLayoutManager(StudioCommentActivity.this));
        studiocommentre.addItemDecoration(new DividerItemDecoration(StudioCommentActivity.this, MyDecoration.VERTICAL_LIST));
        studiocommentre.setAdapter(mStudioCommentAdapter);
        final String myid = PublicStaticData.StudioId + "";

        loading = (LinearLayout) findViewById(R.id.loading);
        mySwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.mySwipeRefresh);
        mySwipeRefresh.setColorSchemeResources(android.R.color.holo_red_light);
        //设置下拉刷新监听
        mySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Page = 1;
                        getData(myid, true, 0);
                    }
                }, 0);
            }
        });

        studiocommentre.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy != 0) {
                    if (isSlideToBottom(recyclerView)) {
                        if (Count > 0) {
                            Page++;
                            loading.setVisibility(View.VISIBLE);
                            getData(myid, false, 1);
                        } else {
                            Toast.makeText(StudioCommentActivity.this, "没有更多内容了", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        getData(myid, false, 1);
    }

    public void getData(String myid, final boolean IsClearn, final int index) {
        SendRequest.ProductCommentList(myid, Page + "", Rows + "", new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.e("onSuccess",response);
                pDialog.hide();
                if (response.contains("<html>")) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    return;
                }
                StudioCommentBean mBean = JSON.parseObject(response, StudioCommentBean.class);
                if (mBean.getResultCode() == 0) {
                    if (IsClearn) {
                        strList.clear();
                    }
                    Count = mBean.getCount();
                    strList.addAll(mBean.getData());
                    mStudioCommentAdapter.notifyDataSetChanged();
                } else {
                    T.shortToast(getApplicationContext(), mBean.getMsg());
                }


                loading.setVisibility(View.GONE);
                if (index == 0) {
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                loading.setVisibility(View.GONE);
                if (index == 0) {
                    //停止刷新动画
                    mySwipeRefresh.setRefreshing(false);
                }
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
