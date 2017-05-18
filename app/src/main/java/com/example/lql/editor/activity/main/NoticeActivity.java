package com.example.lql.editor.activity.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.adapter.main.NoticeAdapter;
import com.example.lql.editor.bean.NoticeBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.DividerItemDecoration;
import com.example.lql.editor.widget.RecyclerView.MyDecoration;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

/**
 * 类描述：公告
 * 作  者：李清林
 * 时  间：2016/11/29
 * 修改备注：
 */
public class NoticeActivity extends Activity {
    private android.support.v7.widget.RecyclerView noticere;
    private TextView title;
    private ImageView leftback;
    NoticeAdapter mNoticeAdapter;//适配器
    ArrayList<NoticeBean.DataBean> mList=new ArrayList<>();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_notice);
        pDialog=new ProgressDialog(NoticeActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();

        initView();
    }

    private void initView(){
        this.noticere = (RecyclerView) findViewById(R.id.notice_re);
        title = (TextView) findViewById(R.id.title_title);
        title.setText("公告");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mNoticeAdapter=new NoticeAdapter(mList,NoticeActivity.this);
        noticere.setLayoutManager(new LinearLayoutManager(this));
        noticere.addItemDecoration(new DividerItemDecoration(this, MyDecoration.VERTICAL_LIST));
        noticere.setAdapter(mNoticeAdapter);


        mNoticeAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PublicStaticData.myNotice=mList.get(position);
                startActivity(new Intent(NoticeActivity.this,NoticeDetailsActivity.class));
            }
        });
        getData();
    }


    private void getData(){
        SendRequest.Notice(new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.dismiss();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                try{
                    NoticeBean mBean= JSON.parseObject(response,NoticeBean.class);
                    if(mBean.getResultCode()==0){
                        mList.addAll(mBean.getData());
                        mNoticeAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.dismiss();
                T.shortToast(getApplicationContext(),"亲，请检查网络");
            }
        });
    }


    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
