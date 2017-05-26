package com.example.lql.editor.activity.studio;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.main.LoginActivity;
import com.example.lql.editor.activity.service.ServiceDetailsActivity;
import com.example.lql.editor.adapter.studio.StudioDetailsAdapter;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.bean.StudioDetailsBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.CircleImageView;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.ToNotNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.lql.editor.R.id.studio_collection_ly;

/**
 * 类描述：工作室详情况页面
 * 作  者：李清林
 * 时  间：2016/11/27
 * 修改备注：
 */

public class StudioDetailsActivity extends Activity implements View.OnClickListener {
    final public int REQUEST_CODE_CALLPHONE_PERMISSIONS = 10001;//拨打电话

    //    private android.support.v7.widget.RecyclerView studiore;
    private ListView studiore;
    private android.widget.ImageView studiocollectionimg;
    private android.widget.LinearLayout studiocollectionly;
    private android.widget.LinearLayout studioqqly;
    private android.widget.TextView studiophonetv;
    private android.widget.LinearLayout studiophonely;
    private TextView title;
    private ImageView leftback;
    TextView studioName;//店铺名称
    TextView studioName1;//诚信保证金
    TextView studioName2;//月销量
    TextView studioName3;//信誉值
    TextView introduce;//店铺简介
    TextView number;//评论数量
    TextView username;//用户名称
    TextView usercontent;//评论内容
    TextView viewall;//查看全部
    TextView studio_collection_tv;//查看全部

    SimpleDraweeView StudioPic;//工作室头像
//    SimpleDraweeView UserPic;//评论的用户的头像
    LinearLayout heat_studiodetails_user_lv;


    CircleImageView heat_studiodetails_user_img1;

    View HeadView;//头布局

    StudioDetailsAdapter mStudioDetailsAdapter;//适配器

    ArrayList<StudioDetailsBean.DataBean.ServiceBean> mList = new ArrayList<>();


    String studioId = "";
    StudioDetailsBean mBean;

    String Userid = "";
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        studioId = PublicStaticData.StudioId;
        setContentView(R.layout.activity_studio_details);
        if (PreferenceUtils.getBoolean("IsLogin", false)) {
            Userid = PreferenceUtils.getString("UserId", "");
        } else {
            Userid = "0";
        }

        pDialog = new ProgressDialog(StudioDetailsActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();


        initView();
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }


    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("工作室");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);

        this.studiophonely = (LinearLayout) findViewById(R.id.studio_phone_ly);
        this.studiophonetv = (TextView) findViewById(R.id.studio_phone_tv);
        this.studioqqly = (LinearLayout) findViewById(R.id.studio_qq_ly);
        this.studiocollectionly = (LinearLayout) findViewById(R.id.studio_collection_ly);
        this.studiocollectionimg = (ImageView) findViewById(R.id.studio_collection_img);
        this.studiore = (ListView) findViewById(R.id.studio_re);
        this.studio_collection_tv = (TextView) findViewById(R.id.studio_collection_tv);


        HeadView = getLayoutInflater().inflate(R.layout.head_studio_details, null);
        studiore.addHeaderView(HeadView);
        mStudioDetailsAdapter = new StudioDetailsAdapter(mList, StudioDetailsActivity.this);
        studiore.setAdapter(mStudioDetailsAdapter);
        studioName = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv);
        studioName1 = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv1);
        studioName2 = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv2);
        studioName3 = (TextView) HeadView.findViewById(R.id.heat_studiodetails_name_tv3);
        introduce = (TextView) HeadView.findViewById(R.id.heat_studiodetails_introduce_tv);
        number = (TextView) HeadView.findViewById(R.id.heat_studiodetails_number_tv);
        username = (TextView) HeadView.findViewById(R.id.heat_studiodetails_user_tv);
        usercontent = (TextView) HeadView.findViewById(R.id.heat_studiodetails_usercontent_tv);
        viewall = (TextView) HeadView.findViewById(R.id.heat_studiodetails_viewall_tv);//查看全部
        heat_studiodetails_user_lv = (LinearLayout) HeadView.findViewById(R.id.heat_studiodetails_user_lv);//查看全部

        StudioPic = (SimpleDraweeView) HeadView.findViewById(R.id.heat_studiodetails_img);
//        UserPic = (SimpleDraweeView) HeadView.findViewById(R.id.heat_studiodetails_user_img);
        heat_studiodetails_user_img1 = (CircleImageView) HeadView.findViewById(R.id.heat_studiodetails_user_img1);

        viewall.setOnClickListener(this);
        studiocollectionly.setOnClickListener(this);
        studioqqly.setOnClickListener(this);
        studiophonely.setOnClickListener(this);


        studiore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    return;
                }
                PublicStaticData.ServiceId = mList.get(i - 1).getProductId() + "";
//                "Type": 1								1：检测查重2：修改降重3：编辑速审
                if (mList.get(i - 1).getType() == 1) {
                    PublicStaticData.PopNumberTitle = 2;
                } else if (mList.get(i - 1).getType() == 2) {
                    PublicStaticData.PopNumberTitle = 3;
                } else if (mList.get(i - 1).getType() == 3) {
                    PublicStaticData.PopNumberTitle = 1;
                }
                startActivity(new Intent(StudioDetailsActivity.this, ServiceDetailsActivity.class));
            }
        });
        getData();
    }


    private void getData() {
        if (TextUtils.isEmpty(studioId)) {
            T.shortToast(getApplicationContext(), "没有取到id");
            return;
        }
        SendRequest.ShopDetail(Userid, studioId, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if (response.contains("<html>")) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    return;
                }
                mBean = JSON.parseObject(response, StudioDetailsBean.class);
                if (mBean.getResultCode() == 0) {
                    if (!TextUtils.isEmpty(mBean.getData().getHeadImg())) {
                        StudioPic.setImageURI(Uri.parse(MyUrl.Pic + mBean.getData().getHeadImg()));//图片
//                        StudioPic.setImageURI(Uri.parse(MyUrl.Pic+"/Upload/img/20161210/20161210021225.jpg"));//图片
                    }
                    studioName.setText(ToNotNull.StringUtils(mBean.getData().getWorkName() + ""));
                    studioName1.setText("诚信保证金：" + ToNotNull.StringUtils(mBean.getData().getDeposit() + ""));
                    studioName2.setText("月销量：" + ToNotNull.StringUtils(mBean.getData().getCount() + ""));
                    studioName3.setText("信誉值：" + ToNotNull.StringUtils(mBean.getData().getCredit() + ""));
                    number.setText("用户评论(" + ToNotNull.StringUtils(mBean.getData().getComment() + "") + ")");
                    if (mBean.getData().getComment() == 0) {
                        heat_studiodetails_user_lv.setVisibility(View.GONE);
                        usercontent.setVisibility(View.GONE);
                    }
//                    introduce.setText(mBean.getData().getDis()+"");
                    introduce.setText(ToNotNull.StringUtils(mBean.getData().getDis() + ""));

                    if (mBean.getData().getCommentDetail() != null) {
                        username.setText(ToNotNull.StringUtils(mBean.getData().getCommentDetail().getName() + ""));
                        usercontent.setText(ToNotNull.StringUtils(mBean.getData().getCommentDetail().getComContent() + ""));
//                        UserPic.setImageURI(Uri.parse(MyUrl.Pic+mBean.getData().getCommentDetail().getImg()));//图片
//                        Picasso.with(StudioDetailsActivity.this).
                        Picasso.with(StudioDetailsActivity.this).load(MyUrl.Pic + mBean.getData().getCommentDetail().getImg()).into(heat_studiodetails_user_img1);
//                        heat_studiodetails_user_img1
                    }
                    mList.clear();
                    mList.addAll(mBean.getData().getService());
                    mStudioDetailsAdapter.notifyDataSetChanged();


//                    "collect": 0,								是否收藏1：收藏0：未收藏
                    if (mBean.getData().getCollect() == 0) {
                        studiocollectionimg.setImageResource(R.drawable.icon_collection_nor);
                    } else {
                        studiocollectionimg.setImageResource(R.drawable.icon_collection_sel);
                        studio_collection_tv.setText("已收藏");
                    }
                    studiophonetv.setText(" " + ToNotNull.StringUtils(mBean.getData().getTelphone() + ""));//电话

                } else {
                    T.shortToast(getApplicationContext(), mBean.getMsg());
                }

            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
                T.shortToast(getApplicationContext(), "亲，请检查网络");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.studio_phone_ly://打电话   6.0记得申请权限
                String TelphoneStr = mBean.getData().getTelphone();
                if (TextUtils.isEmpty(TelphoneStr)) {
                    T.shortToast(getApplicationContext(), "该店铺未设置联系方式");
                    return;
                }

                new zhangphil.iosdialog.widget.AlertDialog(this)
                        .builder().setMsg("确认拨打" + TelphoneStr + "?")
                        .setTitle("提示")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (Build.VERSION.SDK_INT >= 23) {
                                    int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);
                                    if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                                        if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                            showMessageOKCancel("You need to allow access to Contacts",
                                                    new DialogInterface.OnClickListener() {
                                                        @RequiresApi(api = Build.VERSION_CODES.M)
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                                                    REQUEST_CODE_CALLPHONE_PERMISSIONS);
                                                        }
                                                    });
                                            return;
                                        }
                                    }
                                    ToCallPhone();
                                } else {
                                    ToCallPhone();
                                }
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();

                break;
            case studio_collection_ly://收藏
                    boolean IsLogin = PreferenceUtils.getBoolean("IsLogin", false);
                    if (IsLogin) {
                    SendRequest.UserFavorite(Userid, studioId, 2, new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if (response.contains("<html>")) {
                                T.shortToast(getApplicationContext(), "服务器异常");
                                return;
                            }
                            MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                            if (myBasebean.getResultCode() == 0) {
                                if (mBean.getData().getCollect() == 0) {
                                    mBean.getData().setCollect(1);
                                    studiocollectionimg.setImageResource(R.drawable.icon_collection_sel);
                                    studio_collection_tv.setText("已收藏");
                                } else {
                                    mBean.getData().setCollect(0);
                                    studiocollectionimg.setImageResource(R.drawable.icon_collection_nor);
                                    studio_collection_tv.setText("收藏工作室");
                                }
                            }
                                T.shortToast(getApplicationContext(), myBasebean.getMsg());
                        }

                        @Override
                        public void onFailure(Throwable e) {
                            T.shortToast(getApplicationContext(), "亲，请检查网络");
                        }
                    });
                } else {
                    T.shortToast(StudioDetailsActivity.this, "请先登录");
                    startActivity(new Intent(StudioDetailsActivity.this, LoginActivity.class));
                }
                break;
            case R.id.studio_qq_ly://QQ
                if(!isSpecialApplInstalled(this,"com.tencent.mobileqq")){
                    T.shortToast(getApplicationContext(),"您没有安装QQ");
                    return;
                }

                String QQStr = mBean.getData().getQq();
                if (TextUtils.isEmpty(QQStr)) {
                    T.shortToast(getApplicationContext(), "该店铺未设置QQ");
                } else {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqq://im/chat?chat_type=crm&uin=" + QQStr)));
                }
                break;
            case R.id.heat_studiodetails_viewall_tv://查看全部评论
                startActivity(new Intent(this, StudioCommentActivity.class));
                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(StudioDetailsActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CALLPHONE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ToCallPhone();
                } else {
                    Toast.makeText(StudioDetailsActivity.this, "请先开启拨打电话权限", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void ToCallPhone() {
        //用intent启动拨打电话
        String TelphoneStr = mBean.getData().getTelphone();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + TelphoneStr));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
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
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
