package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.adapter.me.OrderdetailsAdapter;
import com.example.lql.editor.bean.CheckOrderDetailsBean;
import com.example.lql.editor.bean.OrderDetailsBean;
import com.example.lql.editor.bean.SummaryOrderDetailsBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.ToDouble;
import com.example.lql.editor.view.MyListView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import static com.example.lql.editor.R.id.orderdetails_ly2_tv4;

/**
 * 类描述：订单详情  个人中心
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */

public class OrderDetailsActivity extends Activity {
    private TextView title;
    private ImageView leftback;
    private com.facebook.drawee.view.SimpleDraweeView orderdetailsimg;
    private TextView orderdetailsnametv;
    private TextView orderdetailsmoneytv;
    private TextView orderdetailsly1nametv;
    private TextView orderdetailsly1typetv;
    private TextView orderdetailsly1contenttv1;
    private TextView orderdetailsly1contenttv2;
    private TextView orderdetailsly2nametv;
    private TextView orderdetailsly2tv1;
    private TextView orderdetailsly2tv2;
    private TextView orderdetailsly2tv3;
    private TextView orderdetailsly2tv4;
    private TextView orderdetailsly2tv5;
    private TextView orderdetailsly2tv6;
    private TextView orderdetailsly2tv7;
    private TextView orderdetailsly3nametv;
    private com.example.lql.editor.view.MyListView orderdetailsmylist;
    OrderdetailsAdapter mOrderdetailsAdapter;//下边时间的适配器
    ArrayList <OrderDetailsBean> mList=new ArrayList<>();


    int type=-1;//订单类型  0:订单详情（检查结果）查重     1：降重详情（降重进度）  2：投稿详情（投稿进度）速审
    SummaryOrderDetailsBean mBeanSummary;
    CheckOrderDetailsBean mBeanCheck;
    ProgressDialog  pDialog;

    private LinearLayout orderdetails_ly2_ly4,orderdetails_ly2_ly5,orderdetails_ly2_ly6;
    private View orderdetails_ly2_v4,orderdetails_ly2_v5,orderdetails_ly2_v6;




    private LinearLayout ly2,ly3;
    ScrollView order_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_order_details);
        type=Integer.parseInt(PublicStaticData.Ordertype);
        pDialog=new ProgressDialog(OrderDetailsActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();
        initView();
        getData();
    }


    private void getData(){
        if(type==0){//查重订单
            SendRequest.RepeatDetail(PublicStaticData.OrderId, new mOkCallBack() {
                @Override
                public void onSuccess(String response) {
                    pDialog.hide();
                    if(response.contains("<html>")){
                        T.shortToast(getApplicationContext(),"服务器异常");
                        return;
                    }
                    mBeanCheck=JSON.parseObject(response,CheckOrderDetailsBean.class);
                    if(mBeanCheck.getResultCode()==0){
                        setView();
                    }else{
                        T.shortToast(getApplicationContext(),mBeanCheck.getMsg());
                    }
                }
                @Override
                public void onFailure(Throwable e) {
                    pDialog.hide();
                    T.shortToast(getApplicationContext(),"亲，请检查网络");
                }
            });
        }else {
            SendRequest.SsDetail(PublicStaticData.OrderId, new mOkCallBack() {
                @Override
                public void onSuccess(String response) {
                    pDialog.hide();
                    if(response.contains("<html>")){
                        T.shortToast(getApplicationContext(),"服务器异常");
                        return;
                    }
                     mBeanSummary= JSON.parseObject(response,SummaryOrderDetailsBean.class);
                    if(mBeanSummary.getResultCode()==0){
                        setView();
                    }else{
                        T.shortToast(getApplicationContext(),mBeanSummary.getMsg());
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    pDialog.hide();
                    T.shortToast(getApplicationContext(),"亲，请检查网络");
                }
            });
        }
    }
    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("订单详情");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        order_scroll= (ScrollView) findViewById(R.id.order_scroll);
        this.orderdetailsmylist = (MyListView) findViewById(R.id.order_details_mylist);
        this.orderdetailsly3nametv = (TextView) findViewById(R.id.orderdetails_ly3_name_tv);
        this.orderdetailsly2tv7 = (TextView) findViewById(R.id.orderdetails_ly2_tv7);
        this.orderdetailsly2tv6 = (TextView) findViewById(R.id.orderdetails_ly2_tv6);
        this.orderdetailsly2tv5 = (TextView) findViewById(R.id.orderdetails_ly2_tv5);
        this.orderdetailsly2tv4 = (TextView) findViewById(orderdetails_ly2_tv4);
        this.orderdetailsly2tv3 = (TextView) findViewById(R.id.orderdetails_ly2_tv3);
        this.orderdetailsly2tv2 = (TextView) findViewById(R.id.orderdetails_ly2_tv2);
        this.orderdetailsly2tv1 = (TextView) findViewById(R.id.orderdetails_ly2_tv1);
        this.orderdetailsly2nametv = (TextView) findViewById(R.id.orderdetails_ly2_name_tv);
        this.orderdetailsly1contenttv2 = (TextView) findViewById(R.id.orderdetails_ly1_content_tv2);
        this.orderdetailsly1contenttv1 = (TextView) findViewById(R.id.orderdetails_ly1_content_tv1);
        this.orderdetailsly1typetv = (TextView) findViewById(R.id.orderdetails_ly1_type_tv);
        this.orderdetailsly1nametv = (TextView) findViewById(R.id.orderdetails_ly1_name_tv);
        this.orderdetailsmoneytv = (TextView) findViewById(R.id.orderdetails_money_tv);
        this.orderdetailsnametv = (TextView) findViewById(R.id.orderdetails_name_tv);
        this.orderdetailsimg = (SimpleDraweeView) findViewById(R.id.orderdetails_img);


        ly2= (LinearLayout) findViewById(R.id.orderdetails_ly2);
        ly3= (LinearLayout) findViewById(R.id.orderdetails_ly3);

        orderdetails_ly2_ly4= (LinearLayout) findViewById(R.id.orderdetails_ly2_ly4);
        orderdetails_ly2_ly5= (LinearLayout) findViewById(R.id.orderdetails_ly2_ly5);
        orderdetails_ly2_ly6= (LinearLayout) findViewById(R.id.orderdetails_ly2_ly6);

        orderdetails_ly2_v4=findViewById(R.id.orderdetails_ly2_v4);
        orderdetails_ly2_v5=findViewById(R.id.orderdetails_ly2_v5);
        orderdetails_ly2_v6=findViewById(R.id.orderdetails_ly2_v6);
//        private LinearLayout orderdetails_ly2_ly4,orderdetails_ly2_ly5,orderdetails_ly2_ly6;
//        private View orderdetails_ly2_v4,orderdetails_ly2_v5,orderdetails_ly2_v6;

        mOrderdetailsAdapter=new OrderdetailsAdapter(mList,OrderDetailsActivity.this);
        orderdetailsmylist.setAdapter(mOrderdetailsAdapter);
    }




    private void setView(){

        order_scroll.scrollTo(0,0);
//        int type=-1;//订单类型  0:订单详情（检查结果）  1：降重详情（降重进度）  2：投稿详情（投稿进度）

        //头部
           /*this.orderdetailsly2tv1//文章标题
            this.orderdetailsly2tv2//学科方向
            this.orderdetailsly2tv3//作者姓名
            this.orderdetailsly2tv7//文件名称*/


        if(type==0){

            orderdetails_ly2_ly4.setVisibility(View.GONE);
            orderdetails_ly2_ly5.setVisibility(View.GONE);
            orderdetails_ly2_ly6.setVisibility(View.GONE);
            orderdetails_ly2_v4.setVisibility(View.GONE);
            orderdetails_ly2_v5.setVisibility(View.GONE);
            orderdetails_ly2_v6.setVisibility(View.GONE);

            orderdetailsimg.setImageURI(Uri.parse(MyUrl.Pic+mBeanCheck.getData().get(0).getServicePicture()));
            orderdetailsnametv.setText(TextUtils.isEmpty(mBeanCheck.getData().get(0).getServiceTitle())||"null".equals(mBeanCheck.getData().get(0).getServiceTitle())?"":mBeanCheck.getData().get(0).getServiceTitle());
//            orderdetailsmoneytv.setText("￥"+mBeanCheck.getData().get(0).getMoney());
            orderdetailsmoneytv.setText(TextUtils.isEmpty(mBeanCheck.getData().get(0).getMoney()+"")||"null".equals(mBeanCheck.getData().get(0).getMoney()+"")?"￥0":"￥"+ToDouble.toDpuble(mBeanCheck.getData().get(0).getMoney()));
            this.orderdetailsly2tv1.setText(TextUtils.isEmpty(mBeanCheck.getData().get(0).getTitle())||"null".equals(mBeanCheck.getData().get(0).getTitle())?"":mBeanCheck.getData().get(0).getTitle());
            orderdetailsly2tv2.setText(TextUtils.isEmpty(mBeanCheck.getData().get(0).getDirection())||"null".equals(mBeanCheck.getData().get(0).getDirection())?"":mBeanCheck.getData().get(0).getDirection());
            orderdetailsly2tv3.setText(TextUtils.isEmpty(mBeanCheck.getData().get(0).getAuthorName())||"null".equals(mBeanCheck.getData().get(0).getAuthorName())?"":mBeanCheck.getData().get(0).getAuthorName());
            orderdetailsly2tv7.setText(TextUtils.isEmpty(mBeanCheck.getData().get(0).getUrlName())||"null".equals(mBeanCheck.getData().get(0).getUrlName())?"":mBeanCheck.getData().get(0).getUrlName());

            String state=mBeanCheck.getData().get(0).getServerState();
            //第一个layout
            orderdetailsly1nametv.setText("检查结果");
            orderdetailsly1typetv.setText(state);
            if(orderdetailsly1typetv.getText().toString().trim().equals("等待检测")){
                orderdetailsly1contenttv1.setText("正在排队检测中......");
                orderdetailsly1contenttv2.setVisibility(View.GONE);
            }else if(orderdetailsly1typetv.getText().toString().trim().equals("检测中")){
                orderdetailsly1contenttv1.setText("正在检测中......");
                orderdetailsly1contenttv2.setVisibility(View.VISIBLE);
                orderdetailsly1contenttv2.setText("同类文章检测平均时长"+mBeanCheck.getData().get(0).getAvg1()+"分钟");
            }else{
                orderdetailsly1contenttv1.setText(mBeanCheck.getData().get(0).getDetectionResult()+"");
                orderdetailsly1contenttv2.setVisibility(View.VISIBLE);
                orderdetailsly1contenttv2.setText("此次检测时长"+mBeanCheck.getData().get(0).getHowlong()+"分钟");
            }
            //第二个layout  隐藏第4（职称）5（学历）6（见刊时间）
            this.orderdetailsly2tv6.setVisibility(View.GONE);
            this.orderdetailsly2tv5.setVisibility(View.GONE);
            this.orderdetailsly2tv4.setVisibility(View.GONE);

            //第三个layout
            orderdetailsly3nametv.setText("检测进度");

            for (int i = 0; i < mBeanCheck.getData().get(0).getTime().size(); i++) {
                OrderDetailsBean mOrderDetail=new OrderDetailsBean();
                mOrderDetail.setServerName(mBeanCheck.getData().get(0).getTime().get(i).getServerName());
                mOrderDetail.setServiceTime(mBeanCheck.getData().get(0).getTime().get(i).getServiceTime());
                mList.add(mOrderDetail);
            }

            mOrderdetailsAdapter.notifyDataSetChanged();

        }else if(type==1){
            ly2.setVisibility(View.GONE);
//            ly3.setVisibility(View.GONE);

            orderdetailsimg.setImageURI(Uri.parse(MyUrl.Pic+mBeanSummary.getData().get(0).getServiceImg())+"");
            orderdetailsnametv.setText(mBeanSummary.getData().get(0).getServiceName()+"");
            orderdetailsmoneytv.setText("￥"+ ToDouble.toDpuble(mBeanSummary.getData().get(0).getTotalMoney()));

            this.orderdetailsly2tv1.setText(mBeanSummary.getData().get(0).getTitle()+"");//文章标题
            orderdetailsly2tv2.setText(mBeanSummary.getData().get(0).getCheckType()+"");//学科方向
            orderdetailsly2tv3.setText(mBeanSummary.getData().get(0).getAuthorName()+"");//作者名称
            orderdetailsly2tv4.setText(mBeanSummary.getData().get(0).getProfessional()+"");//作者职称
            orderdetailsly2tv5.setText(mBeanSummary.getData().get(0).getSchooling()+"");//学历
            orderdetailsly2tv6.setText(mBeanSummary.getData().get(0).getPublicationTime()+"");//见刊时间
            orderdetailsly2tv7.setText(mBeanSummary.getData().get(0).getUrlName()+"");

            String state=mBeanSummary.getData().get(0).getServerState();
            //第一个layout
            orderdetailsly1nametv.setText("降重进度");
            orderdetailsly1typetv.setText(state);
            if(orderdetailsly1typetv.getText().toString().trim().equals("进行中")){
                orderdetailsly1contenttv1.setText("正在等待小编确认......");
                orderdetailsly1contenttv2.setVisibility(View.GONE);
            }else if(orderdetailsly1typetv.getText().toString().trim().equals("待确认")){
                orderdetailsly1contenttv1.setText("待用户确认......");
                orderdetailsly1contenttv2.setVisibility(View.GONE);
            }else{
                if(mBeanSummary.getData().get(0).getServerState().contains("评价")){
                    orderdetailsly1contenttv1.setText("待评价");
                }else{
                    orderdetailsly1contenttv1.setText("订单完成");
                }
                orderdetailsly1contenttv2.setVisibility(View.GONE);
            }
            //第二个layout  隐藏第4（职称）5（学历）6（见刊时间）
            this.orderdetailsly2tv6.setVisibility(View.GONE);
            this.orderdetailsly2tv5.setVisibility(View.GONE);
            this.orderdetailsly2tv4.setVisibility(View.GONE);

            //第三个layout
            orderdetailsly3nametv.setText("检测进度");


            for (int i = 0; i < mBeanSummary.getData().get(0).getTime().size(); i++) {
                OrderDetailsBean mOrderDetail=new OrderDetailsBean();
                mOrderDetail.setServerName(mBeanSummary.getData().get(0).getTime().get(i).getServerName());
                mOrderDetail.setServiceTime(mBeanSummary.getData().get(0).getTime().get(i).getServiceTime());
                mList.add(mOrderDetail);
            }


            mOrderdetailsAdapter.notifyDataSetChanged();

        }else{
            orderdetailsimg.setImageURI(Uri.parse(MyUrl.Pic+mBeanSummary.getData().get(0).getServiceImg())+"");
            orderdetailsnametv.setText(mBeanSummary.getData().get(0).getServiceName()+"");
            orderdetailsmoneytv.setText("￥"+ToDouble.toDpuble(mBeanSummary.getData().get(0).getTotalMoney()));

            this.orderdetailsly2tv1.setText(mBeanSummary.getData().get(0).getTitle()+"");//文章标题
            orderdetailsly2tv2.setText(mBeanSummary.getData().get(0).getCheckType()+"");//学科方向
            orderdetailsly2tv3.setText(mBeanSummary.getData().get(0).getAuthorName()+"");//作者名称
            orderdetailsly2tv4.setText(mBeanSummary.getData().get(0).getProfessional()+"");//作者职称
            orderdetailsly2tv5.setText(mBeanSummary.getData().get(0).getSchooling()+"");//学历
            orderdetailsly2tv6.setText(mBeanSummary.getData().get(0).getPublicationTime()+"");//见刊时间
            orderdetailsly2tv7.setText(mBeanSummary.getData().get(0).getUrlName()+"");

            String state=mBeanSummary.getData().get(0).getServerState();

            //第一个layout
            orderdetailsly1nametv.setText("投稿进度");
            orderdetailsly1typetv.setVisibility(View.GONE);

            orderdetailsly1contenttv1.setText(state);

            orderdetailsly1contenttv2.setVisibility(View.GONE);

            //第二个layout  隐藏第4（职称）5（学历）6（见刊时间）
            this.orderdetailsly2tv6.setVisibility(View.VISIBLE);
            this.orderdetailsly2tv5.setVisibility(View.VISIBLE);
            this.orderdetailsly2tv4.setVisibility(View.VISIBLE);
            //第三个layout
            orderdetailsly3nametv.setText("速审进度");

            for (int i = 0; i < mBeanSummary.getData().get(0).getTime().size(); i++) {
                OrderDetailsBean mOrderDetail=new OrderDetailsBean();
                mOrderDetail.setServerName(mBeanSummary.getData().get(0).getTime().get(i).getServerName());
                mOrderDetail.setServiceTime(mBeanSummary.getData().get(0).getTime().get(i).getServiceTime());
                mList.add(mOrderDetail);
            }

            mOrderdetailsAdapter.notifyDataSetChanged();
        }
        order_scroll.smoothScrollTo(0,0);
    }


    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
