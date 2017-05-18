package com.example.lql.editor.activity.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.adapter.service.ChooseSubjectAdapter;
import com.example.lql.editor.bean.ChooseSubjectBean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.DividerItemDecoration;
import com.example.lql.editor.widget.RecyclerView.MyDecoration;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

/**
 * 类描述：选择学科   学历   职称
 * 作  者：李清林
 * 时  间：2016/11/29
 * 修改备注：
 */
public class ChooseSubjectActivity extends Activity {
    private TextView title;
    private ImageView leftback;
    private android.support.v7.widget.RecyclerView choosesubjectre;
    ChooseSubjectAdapter mChooseSubjectAdapter;
    ChooseSubjectBean mBean=null;
    ArrayList<ChooseSubjectBean.DataBean> mList=new ArrayList<>();
    public int Click=0;
    ProgressDialog  pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_choose_subject);
        pDialog=new ProgressDialog(ChooseSubjectActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();

        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText(PublicStaticData.ChooseType);
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        this.choosesubjectre = (RecyclerView) findViewById(R.id.choose_subject_re);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(Click==0){
//                    T.shortToast(getApplicationContext(),"请选择一项");
//                }else{
                    finish();
//                }
            }
        });

        choosesubjectre.setLayoutManager(new LinearLayoutManager(ChooseSubjectActivity.this));
        choosesubjectre.addItemDecoration(new DividerItemDecoration(ChooseSubjectActivity.this, MyDecoration.VERTICAL_LIST));
        mChooseSubjectAdapter = new ChooseSubjectAdapter(mList,ChooseSubjectActivity. this);
        choosesubjectre.setAdapter(mChooseSubjectAdapter);

        mChooseSubjectAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Click++;
                PublicStaticData.chooseSubjectId=mList.get(position);
                finish();
            }
        });
        getData();
    }

    private void getData(){
        String titlestr=title.getText().toString().trim();
//        0:学科   1：学历   2：职称
        int type=0;
        if(titlestr.equals("选择学科方向")){
            type=0;
        }else if(titlestr.equals("选择职称")){
            type=2;
        }else if(titlestr.equals("选择学历")){
            type=1;
        }
        SendRequest.ProjectList(new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                mBean= JSON.parseObject(response,ChooseSubjectBean.class);
                if(mBean.getResultCode()==0){
                    mList.addAll(mBean.getData());
                    mChooseSubjectAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                T.shortToast(getApplicationContext(),"亲，请检查网络");
            }
        },type);
    }




    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
