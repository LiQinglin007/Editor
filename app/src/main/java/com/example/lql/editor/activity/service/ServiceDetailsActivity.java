package com.example.lql.editor.activity.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
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
import com.example.lql.editor.activity.main.LoginActivity;
import com.example.lql.editor.activity.studio.StudioDetailsActivity;
import com.example.lql.editor.adapter.service.PicAdapter;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.bean.ServiceDetailsBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.ToDouble;
import com.example.lql.editor.view.MyListView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import static com.example.lql.editor.R.id.explain_ly1_tv4;
import static com.example.lql.editor.R.id.explain_ly2_tv;

/**
 * 类描述：服务详情
 * 作  者：李清林
 * 时  间：2016/11/29
 * 修改备注：
 */
public class ServiceDetailsActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private com.facebook.drawee.view.SimpleDraweeView servicedetailsimg;
    private TextView servicedetailsnametv;
    private TextView servicedetailsnumbertv;
    private TextView servicedetailspricetv;
    private TextView servicedetailspriceoldtv;
    private com.facebook.drawee.view.SimpleDraweeView heatservicedetailsimg;
    private TextView heatservicedetailsnametv;
    private TextView heatservicedetailsnametv1;
    private TextView heatservicedetailsnametv2;
    private TextView heatservicedetailsnametv3;
    private TextView explainly1tv1;
    private TextView explainly1tv2;
    private TextView explainly1tv3;
    private TextView explainly1tv4;
    private TextView explainly1tv5;
    private TextView explainly1tv6;
    private TextView explainly1tv7;
    private android.widget.LinearLayout explainly1;
    private TextView explainly2tv;
    private android.widget.LinearLayout explainly2;
    private TextView explainly3tv;
    private android.widget.LinearLayout explainly3;
    private ImageView servicebootomcollectionimg;
    private android.widget.LinearLayout servicebootomcollectionly;
    private android.widget.LinearLayout servicebootomqqly;
    private TextView servicebootomtypetv;
    private android.widget.LinearLayout studiophonely;
    private MyListView service_listview;
    private LinearLayout service_bootom_type_ly;
    private LinearLayout studio_ly;
    private TextView service_bootom_collection_tv;


    PicAdapter mPicAdapter;

    private int number = -1;//1：速审  2：查重   3：降重
    String serviceId = "";
    ServiceDetailsBean mBean ;
    private ProgressDialog pDialog;
    ScrollView ServiceDetails_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_service_details);
        pDialog=new ProgressDialog(ServiceDetailsActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();
        serviceId = PublicStaticData.ServiceId;
        number=PublicStaticData.PopNumberTitle;
        initView();

    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("服务详情");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);
        ServiceDetails_scroll= (ScrollView) findViewById(R.id.ServiceDetails_scroll);
        studio_ly= (LinearLayout) findViewById(R.id.studio_ly);
        this.studiophonely = (LinearLayout) findViewById(R.id.studio_phone_ly);
        this.servicebootomtypetv = (TextView) findViewById(R.id.service_bootom_type_tv);
        this.servicebootomqqly = (LinearLayout) findViewById(R.id.service_bootom_qq_ly);
        this.servicebootomcollectionly = (LinearLayout) findViewById(R.id.service_bootom_collection_ly);
        this.servicebootomcollectionimg = (ImageView) findViewById(R.id.service_bootom_collection_img);
        this.explainly3 = (LinearLayout) findViewById(R.id.explain_ly3);
        this.explainly3tv = (TextView) findViewById(R.id.explain_ly3_tv);
        this.explainly2 = (LinearLayout) findViewById(R.id.explain_ly2);
        this.explainly2tv = (TextView) findViewById(explain_ly2_tv);
        this.explainly1 = (LinearLayout) findViewById(R.id.explain_ly1);
        this.explainly1tv7 = (TextView) findViewById(R.id.explain_ly1_tv7);
        this.explainly1tv6 = (TextView) findViewById(R.id.explain_ly1_tv6);
        this.explainly1tv5 = (TextView) findViewById(R.id.explain_ly1_tv5);
        this.explainly1tv4 = (TextView) findViewById(explain_ly1_tv4);
        this.explainly1tv3 = (TextView) findViewById(R.id.explain_ly1_tv3);
        this.explainly1tv2 = (TextView) findViewById(R.id.explain_ly1_tv2);
        this.explainly1tv1 = (TextView) findViewById(R.id.explain_ly1_tv1);
        this.heatservicedetailsnametv3 = (TextView) findViewById(R.id.heat_service_details_name_tv3);
        this.heatservicedetailsnametv2 = (TextView) findViewById(R.id.heat_service_details_name_tv2);
        this.heatservicedetailsnametv1 = (TextView) findViewById(R.id.heat_service_details_name_tv1);
        this.heatservicedetailsnametv = (TextView) findViewById(R.id.heat_service_details_name_tv);
        this.heatservicedetailsimg = (SimpleDraweeView) findViewById(R.id.heat_service_details_img);
        this.servicedetailspriceoldtv = (TextView) findViewById(R.id.service_details_priceold_tv);
        this.servicedetailspricetv = (TextView) findViewById(R.id.service_details_price_tv);
        this.servicedetailsnumbertv = (TextView) findViewById(R.id.service_details_number_tv);
        this.servicedetailsnametv = (TextView) findViewById(R.id.service_details_name_tv);
        this.servicedetailsimg = (SimpleDraweeView) findViewById(R.id.service_details_img);
        service_listview = (MyListView) findViewById(R.id.service_listview);
        service_bootom_type_ly = (LinearLayout) findViewById(R.id.service_bootom_type_ly);
        service_bootom_collection_tv = (TextView) findViewById(R.id.service_bootom_collection_tv);
//        service_myview= (ImageView) findViewById(service_myview);

//        service_gridview.setVisibility(View.GONE);



        service_bootom_type_ly.setOnClickListener(this);
        servicebootomcollectionly.setOnClickListener(this);
        servicebootomqqly.setOnClickListener(this);
        studio_ly.setOnClickListener(this);

        getData();
        setView();
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    private void getData() {

        if (serviceId.equals("")) {
            T.shortToast(getApplicationContext(), "没有取到id");
            return;
        }
        String userid;
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            userid =PreferenceUtils.getString("UserId","");
        }else{
            userid="0";
        }
        SendRequest.ServiceDetail(serviceId,userid, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if(response.contains("<html>")){
                    T.shortToast(getApplicationContext(),"服务器异常");
                    return;
                }
                mBean = JSON.parseObject(response, ServiceDetailsBean.class);
                if (mBean.getResultCode() == 0) {
//                    servicedetailsimg//服务图片
                    //设置图片
                    servicedetailsimg.setImageURI(Uri.parse(MyUrl.Pic + mBean.getData().getDetail().getPicture()));
                    //服务名称
                    servicedetailsnametv.setText(mBean.getData().getDetail().getServiceName());
                    //原价  OriginalCost
                    double numbers=mBean.getData().getDetail().getOriginalCost();
                    if(numbers==0){
                        servicedetailspriceoldtv.setVisibility(View.GONE);
                    }else{
                        servicedetailspriceoldtv.setText("￥"+ ToDouble.toDpuble(numbers));
                        servicedetailspriceoldtv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    //现在
                    servicedetailspricetv.setText("￥"+mBean.getData().getDetail().getServicePrice() + "");
                    //服务月销量
                    servicedetailsnumbertv.setText("月销量：" + mBean.getData().getDetail().getCount());
//                    工作室名称
                    heatservicedetailsnametv.setText(mBean.getData().getDetail().getName() + "");
                    PublicStaticData.ShopName=mBean.getData().getDetail().getName();
//                    "deposit": "100",											工作室保证金
                    heatservicedetailsnametv1.setText("诚信保证金：" + mBean.getData().getDetail().getDeposit());
//                    "credit": 0,													工作室信誉
                    heatservicedetailsnametv3.setText("信誉值:" + mBean.getData().getDetail().getCredit() + "");
//                    "totalCount": 0,											工作室月销量
                    heatservicedetailsnametv2.setText("月销量：" + mBean.getData().getDetail().getTotalCount());
//                    "img": null,													工作室头像
                    heatservicedetailsimg.setImageURI(Uri.parse(MyUrl.Pic + mBean.getData().getDetail().getImg()));
//                    刊物级别：北大核心期刊
                    explainly1tv1.setText("刊物级别：" + mBean.getData().getDetail().getPublicationLevel());
//                    复合影响因子：0.165
                    explainly1tv2.setText("复合影响因子：" + mBean.getData().getDetail().getInfluenceFactor());
                    //综合影响因子
                    explainly1tv3.setText("综合影响因子：" + mBean.getData().getDetail().getTotalInfluenceFactor());
                    //录用学科范围
                    explainly1tv4.setText("录用学科范围：" + mBean.getData().getDetail().getCheckType().substring(0,mBean.getData().getDetail().getCheckType().length()-1));
                    //初审周期：
                    explainly1tv5.setText("初审周期：" + mBean.getData().getDetail().getPrePeriod());
                    //复审周期
                    explainly1tv6.setText("复审周期：" + mBean.getData().getDetail().getReviewCycle());
                    //终审周期
                    explainly1tv7.setText("终审周期：" + mBean.getData().getDetail().getReviewCycle());
                    // 检测类型
                    explainly2tv.setText("检测类型:" +mBean.getData().getDetail().getCheckType().substring(0,mBean.getData().getDetail().getCheckType().length()-1));
                    //  学科范围
                    explainly3tv.setText("学科范围:" + mBean.getData().getDetail().getCheckType().substring(0,mBean.getData().getDetail().getCheckType().length()-1));

                    if (number == 2||number==3) {
                        if(Double.parseDouble(mBean.getData().getDetail().getServicePrice())==mBean.getData().getDetail().getOriginalCost()||mBean.getData().getDetail().getOriginalCost()==0){
                            servicedetailspriceoldtv.setVisibility(View.GONE);
                        }else{
                            servicedetailspriceoldtv.setVisibility(View.VISIBLE);
                        }
                    }

//                    "collect": 0,								是否收藏1：收藏0：未收藏
                    if (mBean.getData().getDetail().getCollect() == 1) {
                        servicebootomcollectionimg.setImageResource(R.drawable.icon_collection_sel);
                        service_bootom_collection_tv.setText("已收藏");
                    }

                   ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean> img=new ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean>();
                    img.addAll( mBean.getData().getServiceimg());

                    mPicAdapter=new PicAdapter(img,ServiceDetailsActivity.this);
                    service_listview.setAdapter(mPicAdapter);

//                    ServiceDetails_scroll.smoothScrollTo(0,0);
                }

            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                T.shortToast(getApplicationContext(),"亲，请检查网络");
            }
        });
    }

    /**
     * 显示数据
     */
    private void setView() {
//        private int number=-1;//1：速审  2：查重   3：降重
        ServiceDetails_scroll.smoothScrollTo(0,0);
        if (number == 1) {
            servicedetailspriceoldtv.setVisibility(View.GONE);
            explainly1.setVisibility(View.VISIBLE);
            explainly2.setVisibility(View.GONE);
            explainly3.setVisibility(View.GONE);
            servicebootomtypetv.setText("我要投稿");
        } else if (number == 2) {
            explainly1.setVisibility(View.GONE);
            explainly2.setVisibility(View.VISIBLE);
            explainly3.setVisibility(View.GONE);
            servicebootomtypetv.setText("我要查重");
        } else if (number == 3) {
            explainly1.setVisibility(View.GONE);
            explainly2.setVisibility(View.GONE);
            explainly3.setVisibility(View.VISIBLE);
            servicebootomtypetv.setText("我要降重");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.studio_ly:
                PublicStaticData.StudioId=mBean.getData().getDetail().getShopId()+"";
//                Log.e("PublicStaticDId",PublicStaticData.StudioId+"");
                startActivity(new Intent(ServiceDetailsActivity.this, StudioDetailsActivity.class));
                break;
            case R.id.titleBar_left_img:
                finish();
                break;

            case R.id.service_bootom_collection_ly://收藏
                boolean IsLogin= PreferenceUtils.getBoolean("IsLogin",false);
                if(IsLogin){
                    SendRequest.UserFavorite(PreferenceUtils.getString("UserId",""),serviceId, 1, new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if(response.contains("<html>")){
                                T.shortToast(getApplicationContext(),"服务器异常");
                                return;
                            }
                            MyBasebean myBasebean=JSON.parseObject(response,MyBasebean.class);
                            if(myBasebean.getResultCode()==0){
                                if (mBean.getData().getDetail().getCollect() == 1) {
                                    servicebootomcollectionimg.setImageResource(R.drawable.icon_collection_nor);
                                    mBean.getData().getDetail().setCollect(0);
                                    service_bootom_collection_tv.setText("收藏服务");
                                } else {
                                    mBean.getData().getDetail().setCollect(1);
                                    servicebootomcollectionimg.setImageResource(R.drawable.icon_collection_sel);
                                    service_bootom_collection_tv.setText("已收藏");
                                }
                            }
                             T.shortToast(getApplicationContext(),myBasebean.getMsg());

                        }

                        @Override
                        public void onFailure(Throwable e) {
                            T.shortToast(getApplicationContext(),"亲，请检查网络");
                        }
                    });
                }else{
                    T.shortToast(ServiceDetailsActivity.this,"请先登录");
                    startActivity(new Intent(ServiceDetailsActivity.this, LoginActivity.class));
                }
                break;

            case R.id.service_bootom_qq_ly://QQ
               if(!isSpecialApplInstalled(this,"com.tencent.mobileqq")){
                   T.shortToast(getApplicationContext(),"您没有安装QQ");
                   return;
               }

                String QQStr=mBean.getData().getDetail().getQQ();
                if(TextUtils.isEmpty(QQStr)){
                    T.shortToast(getApplicationContext(),"该店铺未设置QQ");
                }else{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqq://im/chat?chat_type=crm&uin="+QQStr)));
                }
                break;

            case R.id.service_bootom_type_ly://提交

                boolean IsLogin1= PreferenceUtils.getBoolean("IsLogin",false);
                if(IsLogin1){

                    Intent intent =new Intent(this, SubmissionActivity.class);
                    if (number == 1) {
                        PublicStaticData.serviceType = "我要投稿";
                        servicebootomtypetv.setText("我要投稿");

                        String real=PreferenceUtils.getString("Real","");
                        if(!real.equals("1")){
                            T.shortToast(getApplicationContext(),"请先通过实名认证");
                            return;
                        }

                    } else if (number == 2) {
                        PublicStaticData.serviceType = "我要查重";
                        servicebootomtypetv.setText("我要查重");
                    } else if (number == 3) {
                        PublicStaticData.serviceType = "我要降重";
                        servicebootomtypetv.setText("我要降重");
                    }
                    PublicStaticData.Shopid=mBean.getData().getDetail().getShopId()+"";//工作室编号    投稿时候
                    PublicStaticData.Productid=serviceId;//服务编号   投稿时候
                    PublicStaticData.ServiceFee=mBean.getData().getDetail().getServicePrice() + "";//服务价格  投稿时候

                    intent.putExtra("ServiceName",mBean.getData().getDetail().getServiceName());
                    intent.putExtra("Picture",mBean.getData().getDetail().getPicture());
                    intent.putExtra("OriginalCost",ToDouble.toDpuble(mBean.getData().getDetail().getOriginalCost())+"");
                    intent.putExtra("ServicePrice",mBean.getData().getDetail().getServicePrice()+"");
                    intent.putExtra("Count",mBean.getData().getDetail().getCount()+"");

                    startActivity(intent);
//                    finish();


                }else{
                    T.shortToast(ServiceDetailsActivity.this,"请先登录");
                    startActivity(new Intent(ServiceDetailsActivity.this, LoginActivity.class));
                }

                break;
        }
    }


    /**
     * 判断手机设备是否安装指定包名的apk应用程序
     * @param context
     * @param packageName
     * @return
     */
    public  boolean isSpecialApplInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
