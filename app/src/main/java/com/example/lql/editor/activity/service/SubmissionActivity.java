package com.example.lql.editor.activity.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.ServiceSubmission;
import com.example.lql.editor.bean.UploadBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.OkHttpClientManager;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.ClearEditText;
import com.example.lql.editor.utils.DialogUtils;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.ToDouble;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;

import java.io.File;

import static com.example.lql.editor.utils.PublicStaticData.ServiceFee;
import static com.example.lql.editor.utils.PublicStaticData.chooseFileBean;
import static com.example.lql.editor.utils.PublicStaticData.chooseSubjectId;

/**
 * 类描述：我要投稿
 * 作  者：李清林
 * 时  间：2016/11/30
 * 修改备注：
 */
public class SubmissionActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private com.facebook.drawee.view.SimpleDraweeView submissionimg;
    private TextView submissionnametv;
    private TextView submissionnumbertv;
    private TextView submissionpricetv;
    private TextView submissionpriceoldtv;
    private com.example.lql.editor.utils.ClearEditText submissionnamecet;
    private TextView submissionsubjecttv;
    private android.widget.LinearLayout submissionsubjectlv;
    private com.example.lql.editor.utils.ClearEditText submissionauthorcet;
    private TextView submissionsafftv;
    private android.widget.LinearLayout submissionchoosesaff;
    private TextView submissioneducationtv;
    private android.widget.LinearLayout submissionchooseeducation;
    private com.example.lql.editor.utils.ClearEditText submissiontimecet;
    private TextView submissionchoosefiletv;
    private android.widget.LinearLayout submissionchoosefile;
    private android.widget.Button submissionsubmitbut;
    private LinearLayout submissionly1;
    private View submissionv1;
    private View submissionv2;
    private LinearLayout submissionly3;
    private View submissionv3;
    private View submissionv4;
    private View submissionv5;
    private LinearLayout submissionly6;
    private View submissionv6;
    private View submissionv7;
    private ImageView ly3minusimg;
    private android.widget.EditText ly3numbered;
    private ImageView ly3addimg;
    private LinearLayout submissionly8;
    private View submissionv8;
    private int number = 1;


    private String subjectId = "";//学科id
    private String saffId = "";//职称id
    private String educationId = "";//学历id
    private String fileId = "";//文件id


    String Userid = "";
    ProgressDialog pDialog;


    public int FILE_SELECT_CODE = 10010;
//    String path="";


    String FileName = "";
    String FilePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_submission);
        registBroadcastReceiver();
        pDialog = new ProgressDialog(SubmissionActivity.this);
        pDialog.setMessage("加载中...");

        if (PreferenceUtils.getBoolean("IsLogin", false)) {
            Userid = PreferenceUtils.getString("UserId", "");
        } else {
            Userid = "0";
        }
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText(PublicStaticData.serviceType);
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(this);


        this.submissionsubmitbut = (Button) findViewById(R.id.submission_submit_but);
        this.submissionv8 = (View) findViewById(R.id.submission_v8);
        this.submissionly8 = (LinearLayout) findViewById(R.id.submission_ly8);
        this.ly3addimg = (ImageView) findViewById(R.id.ly3_add_img);
        this.ly3numbered = (EditText) findViewById(R.id.ly3_number_ed);
        this.ly3minusimg = (ImageView) findViewById(R.id.ly3_minus_img);
        this.submissionv7 = (View) findViewById(R.id.submission_v7);
        this.submissionchoosefile = (LinearLayout) findViewById(R.id.submission_choose_file);
        this.submissionchoosefiletv = (TextView) findViewById(R.id.submission_choosefile_tv);
        this.submissionv6 = (View) findViewById(R.id.submission_v6);
        this.submissionly6 = (LinearLayout) findViewById(R.id.submission_ly6);
        this.submissiontimecet = (ClearEditText) findViewById(R.id.submission_time_cet);
        this.submissionv5 = (View) findViewById(R.id.submission_v5);
        this.submissionchooseeducation = (LinearLayout) findViewById(R.id.submission_choose_education);
        this.submissioneducationtv = (TextView) findViewById(R.id.submission_education_tv);
        this.submissionv4 = (View) findViewById(R.id.submission_v4);
        this.submissionchoosesaff = (LinearLayout) findViewById(R.id.submission_choose_saff);
        this.submissionsafftv = (TextView) findViewById(R.id.submission_saff_tv);
        this.submissionv3 = (View) findViewById(R.id.submission_v3);
        this.submissionly3 = (LinearLayout) findViewById(R.id.submission_ly3);
        this.submissionauthorcet = (ClearEditText) findViewById(R.id.submission_author_cet);
        this.submissionv2 = (View) findViewById(R.id.submission_v2);
        this.submissionsubjectlv = (LinearLayout) findViewById(R.id.submission_subject_lv);
        this.submissionsubjecttv = (TextView) findViewById(R.id.submission_subject_tv);
        this.submissionv1 = (View) findViewById(R.id.submission_v1);
        this.submissionly1 = (LinearLayout) findViewById(R.id.submission_ly1);
        this.submissionnamecet = (ClearEditText) findViewById(R.id.submission_name_cet);
        this.submissionpriceoldtv = (TextView) findViewById(R.id.submission_priceold_tv);//原价
        this.submissionpricetv = (TextView) findViewById(R.id.submission_price_tv);//现价
        this.submissionnumbertv = (TextView) findViewById(R.id.submission_number_tv);
        this.submissionnametv = (TextView) findViewById(R.id.submission_name_tv);
        this.submissionimg = (SimpleDraweeView) findViewById(R.id.submission_img);


        this.submissionsubjectlv.setOnClickListener(this);
        this.submissionchoosesaff.setOnClickListener(this);
        this.submissionchooseeducation.setOnClickListener(this);
        this.submissionchoosefile.setOnClickListener(this);
        this.submissionsubmitbut.setOnClickListener(this);
        this.ly3minusimg.setOnClickListener(this);
        this.ly3addimg.setOnClickListener(this);

        //设置图片
        submissionimg.setImageURI(Uri.parse(MyUrl.Pic + getIntent().getStringExtra("Picture")));
        //服务名称
        submissionnametv.setText(TextUtils.isEmpty(getIntent().getStringExtra("ServiceName")) || getIntent().getStringExtra("ServiceName").equals("null") ? "" : getIntent().getStringExtra("ServiceName"));
        //现在
//        submissionpricetv.setText(TextUtils.isEmpty(getIntent().getStringExtra("OriginalCost")) || getIntent().getStringExtra("OriginalCost").equals("null") ? "" : "￥" + getIntent().getStringExtra("OriginalCost"));
        submissionpricetv.setText(TextUtils.isEmpty(getIntent().getStringExtra("ServicePrice")) || getIntent().getStringExtra("ServicePrice").equals("null") ? "" : "￥" + getIntent().getStringExtra("ServicePrice") + "");
        //原价  OriginalCost
//        submissionpriceoldtv.setText(TextUtils.isEmpty(getIntent().getStringExtra("ServicePrice")) || getIntent().getStringExtra("ServicePrice").equals("null") ? "" : "￥" + getIntent().getStringExtra("ServicePrice") + "");
        submissionpriceoldtv.setText(TextUtils.isEmpty(getIntent().getStringExtra("OriginalCost")) || getIntent().getStringExtra("OriginalCost").equals("null") ? "" : "￥" + getIntent().getStringExtra("OriginalCost"));
        submissionpriceoldtv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        //服务月销量
        submissionnumbertv.setText("月销量：" + getIntent().getStringExtra("Count"));


        if (title.getText().toString().equals("我要查重")) {

            this.submissionv8.setVisibility(View.GONE);
            this.submissionly8.setVisibility(View.GONE);
            submissionchoosesaff.setVisibility(View.GONE);//职称
            submissionv4.setVisibility(View.GONE);
            submissionchooseeducation.setVisibility(View.GONE);//作者学历
            submissionv5.setVisibility(View.GONE);
            this.submissionv6.setVisibility(View.GONE);//见刊时间
            this.submissionly6.setVisibility(View.GONE);
            submissionpriceoldtv.setVisibility(View.VISIBLE);
            if (Double.parseDouble(getIntent().getStringExtra("ServicePrice")) == Double.parseDouble(getIntent().getStringExtra("OriginalCost"))) {
                submissionpriceoldtv.setVisibility(View.GONE);
            }
        } else if (title.getText().toString().equals("我要投稿")) {
            this.submissionv8.setVisibility(View.GONE);
            this.submissionly8.setVisibility(View.GONE);
            submissionpriceoldtv.setVisibility(View.GONE);
        } else if (title.getText().toString().equals("我要降重")) {

            this.submissionv1.setVisibility(View.GONE);//见刊时间
            this.submissionly1.setVisibility(View.GONE);

            this.submissionv2.setVisibility(View.GONE);//见刊时间
            this.submissionsubjectlv.setVisibility(View.GONE);

            this.submissionv3.setVisibility(View.GONE);//见刊时间
            this.submissionly3.setVisibility(View.GONE);


            this.submissionv4.setVisibility(View.GONE);//见刊时间
            this.submissionchoosesaff.setVisibility(View.GONE);

            this.submissionv5.setVisibility(View.GONE);//见刊时间
            this.submissionchooseeducation.setVisibility(View.GONE);

            this.submissionv6.setVisibility(View.GONE);//见刊时间
            this.submissionly6.setVisibility(View.GONE);

            this.submissionv7.setVisibility(View.GONE);//选择文件
            this.submissionchoosefile.setVisibility(View.GONE);


            ly3numbered.setSelection(ly3numbered.getText().toString().trim().length());//将光标追踪到内容的最后
            submissionpriceoldtv.setVisibility(View.VISIBLE);
            if (Double.parseDouble(getIntent().getStringExtra("ServicePrice")) == Double.parseDouble(getIntent().getStringExtra("OriginalCost"))) {
                submissionpriceoldtv.setVisibility(View.GONE);
            }
        }


        ly3numbered.addTextChangedListener(watcher);
    }


    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub
            if (TextUtils.isEmpty(ly3numbered.getText().toString().trim())) {

            } else {
                number = Integer.parseInt(ly3numbered.getText().toString().trim());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub

        }
    };


    private void registBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lql.submission.finish");
        registerReceiver(mFreshReceiver, filter);
    }

    private BroadcastReceiver mFreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.lql.submission.finish".equals(action)) {
                SubmissionActivity.this.finish();
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();

        number = Integer.parseInt(ly3numbered.getText().toString().trim());
        if (PublicStaticData.ChooseType.equals("选择学科方向")) {
            if (chooseSubjectId != null) {
                subjectId = chooseSubjectId.getId() + "";
                submissionsubjecttv.setText(chooseSubjectId.getProjectName());
            }
        } else if (PublicStaticData.ChooseType.equals("选择职称")) {
            if (chooseSubjectId != null) {
                saffId = chooseSubjectId.getId() + "";
                submissionsafftv.setText(chooseSubjectId.getProjectName());
            }
        } else if (PublicStaticData.ChooseType.equals("选择学历")) {
            if (chooseSubjectId != null) {
                educationId = chooseSubjectId.getId() + "";
                submissioneducationtv.setText(chooseSubjectId.getProjectName());
            }
        }

        //如果是选择的是文件  回来就设置文件的名称
        if (PublicStaticData.IsFile) {
            if (chooseFileBean != null) {
                submissionchoosefiletv.setText(chooseFileBean.getName());
                fileId = chooseFileBean.getId() + "";
                PublicStaticData.IsFile = false;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;

            case R.id.submission_subject_lv:
                //选择学科方向
                PublicStaticData.ChooseType = "选择学科方向";
                startActivity(new Intent(this, ChooseSubjectActivity.class));
                break;
            case R.id.submission_choose_saff:
                //作者职称
                PublicStaticData.ChooseType = "选择职称";
                startActivity(new Intent(this, ChooseSubjectActivity.class));
                break;
            case R.id.submission_choose_education:
                //学历
                PublicStaticData.ChooseType = "选择学历";
                startActivity(new Intent(this, ChooseSubjectActivity.class));
                break;
            case R.id.submission_choose_file:
                //选择文件
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, "请选择文件"), FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "请安装一个文件管理器", Toast.LENGTH_SHORT).show();
                }
//                startActivity(new Intent(this, ChooseFileActivity.class));
//                Upload
                break;
            case R.id.submission_submit_but:
                //提交
                if (title.getText().toString().equals("我要查重")) {
                    /**
                     * 我要查重：
                     * @param Shopid   工作室编号
                     * @param Productid  服务编号
                     * @param Title   标题
                     * @param Authorname   作者姓名
                     * @param Direction   学科方向
                     * @param ServiceFee   此次服务总价钱
                     * @param Url   文件地址
                     * @param UrlName   文件名称
                     * @param mOkCallBack
                     */
                    String name = submissionnamecet.getText().toString().trim();//文件名称
                    String author = submissionauthorcet.getText().toString().trim();//作者姓名

                    if (TextUtils.isEmpty(name)) {
                        T.shortToast(getApplicationContext(), "文章标题不能为空");
                    } else if (TextUtils.isEmpty(subjectId)) {
                        T.shortToast(getApplicationContext(), "学科方向不能为空");
                    } else if (TextUtils.isEmpty(author)) {
                        T.shortToast(getApplicationContext(), "作者姓名不能为空");
                    }

//                    else if (TextUtils.isEmpty(fileId)) {
//                        T.shortToast(getApplicationContext(), "文件不能为空");
//                    }

                    else if (TextUtils.isEmpty(FilePath)) {
                        T.shortToast(getApplicationContext(), "文件不能为空");
                    } else {

                        /**
                         * 我要查重：
                         * @param Shopid   工作室编号
                         * @param Productid  服务编号
                         * @param Title   标题
                         * @param Authorname   作者姓名
                         * @param Direction   学科方向
                         * @param ServiceFee   此次服务总价钱
                         * @param Url   文件地址
                         * @param UrlName   文件名称
                         * @param mOkCallBack
                         */
                        PublicStaticData.ServiceFeeSum = ServiceFee;

                        pDialog.show();
//                        SendRequest.AddMyService1(Userid, PublicStaticData.Shopid, PublicStaticData.Productid, name, author, submissionsubjecttv.getText().toString().trim(), ServiceFee, chooseFileBean.getPath(), chooseFileBean.getName(), new mOkCallBack() {
                        SendRequest.AddMyService1(Userid, PublicStaticData.Shopid, PublicStaticData.Productid, name, author, submissionsubjecttv.getText().toString().trim(), ServiceFee, FilePath, FileName, new mOkCallBack() {
                            @Override
                            public void onSuccess(String response) {
                                Log.e("onSuccess11",response);
                                pDialog.hide();
                                if (response.contains("<html>")) {
                                    T.shortToast(getApplicationContext(), "服务器异常");
                                    return;
                                }
                                ServiceSubmission mBean = JSON.parseObject(response, ServiceSubmission.class);
                                if (mBean.getResultCode() == 0) {
                                    PublicStaticData.serviceId = mBean.getData() + "";
                                    //去支付页面
                                    startActivity(new Intent(SubmissionActivity.this, PayActivity.class));
//                                    finish();
                                } else {
                                    T.shortToast(getApplicationContext(), mBean.getMsg());
                                }
                            }

                            @Override
                            public void onFailure(Throwable e) {
                                pDialog.hide();
                            }
                        });


                    }

                } else if (title.getText().toString().equals("我要投稿")) {


                    String name = submissionnamecet.getText().toString().trim();//文件名称
                    String author = submissionauthorcet.getText().toString().trim();//作者姓名
                    String time = submissiontimecet.getText().toString().trim();//见刊时间

                    if (TextUtils.isEmpty(name)) {
                        T.shortToast(getApplicationContext(), "文章标题不能为空");
                    } else if (TextUtils.isEmpty(subjectId)) {
                        T.shortToast(getApplicationContext(), "学科方向不能为空");
                    } else if (TextUtils.isEmpty(author)) {
                        T.shortToast(getApplicationContext(), "作者姓名不能为空");
                    } else if (TextUtils.isEmpty(saffId)) {
                        T.shortToast(getApplicationContext(), "作者职称不能为空");
                    } else if (TextUtils.isEmpty(educationId)) {
                        T.shortToast(getApplicationContext(), "作者学历不能为空");
                    } else if (TextUtils.isEmpty(time)) {
                        T.shortToast(getApplicationContext(), "见刊时间不能为空");
                    }


//                    else if (TextUtils.isEmpty(fileId)) {
//                        T.shortToast(getApplicationContext(), "文件不能为空");
//                    }

                    else if (TextUtils.isEmpty(FilePath)) {
                        T.shortToast(getApplicationContext(), "文件不能为空");
                    } else {
                        /**
                         *速审参数：  Type:int						1:查重2：降重3：速审
                         * @param Shopid   工作室编号
                         * @param Productid  服务编号
                         * @param Title   标题
                         * @param Authorname   作者姓名
                         * @param Direction   学科方向
                         * @param ServiceFee   此次服务总价钱
                         * @param Url   文件地址
                         * @param UrlName   文件名称
                         * @param Schooling  学历
                         * @param Professional  职称
                         * @param Publicationtime  见刊时间
                         * @param mOkCallBack
                         */
                        pDialog.show();
//                        SendRequest.AddMyService3(Userid, PublicStaticData.Shopid, PublicStaticData.Productid, name, author, submissionsubjecttv.getText().toString().trim(), ServiceFee, PublicStaticData.chooseFileBean.getPath(), PublicStaticData.chooseFileBean.getName(), submissioneducationtv.getText().toString().trim(), submissionsafftv.getText().toString().trim(), time, new mOkCallBack() {
                        SendRequest.AddMyService3(Userid, PublicStaticData.Shopid, PublicStaticData.Productid, name, author, submissionsubjecttv.getText().toString().trim(), PublicStaticData.ServiceFee, FilePath, FileName, submissioneducationtv.getText().toString().trim(), submissionsafftv.getText().toString().trim(), time, new mOkCallBack() {
                            @Override
                            public void onSuccess(String response) {
                                Log.e("onSuccess22",response);
                                pDialog.hide();
                                if (response.contains("<html>")) {
                                    T.shortToast(getApplicationContext(), "服务器异常");
                                    return;
                                }
                                ServiceSubmission mBean = JSON.parseObject(response, ServiceSubmission.class);
                                if (mBean.getResultCode() == 0) {
                                    DialogUtils.showMyDialog(SubmissionActivity.this, new DialogUtils.DialogCallBack() {
                                        @Override
                                        public void callback() {
                                            //弹框消失后的回调
                                            SubmissionActivity.this.finish();
                                        }
                                    }, 2);
                                } else {
                                    T.shortToast(getApplicationContext(), mBean.getMsg());
                                }
                            }

                            @Override
                            public void onFailure(Throwable e) {
                                pDialog.hide();
                                T.shortToast(getApplicationContext(), e.toString());
                            }
                        });
                    }

                } else if (title.getText().toString().equals("我要降重")) {
                    /**
                     *我要降重：
                     * @param Shopid   工作室编号
                     * @param Productid  服务编号
                     * @param ServiceFee   此次服务总价钱
                     * @param Count    份数
                     * @param mOkCallBack
                     */
//                    if (TextUtils.isEmpty(number + "") || (number + "").equals("") || number == 0) {
//                        T.shortToast(getApplicationContext(), "请选择降重数量");
//                        return;
//                    }

                    if (TextUtils.isEmpty(ly3numbered.getText().toString().trim()) || (ly3numbered.getText().toString().trim()).equals("") || number == 0) {
                        T.shortToast(getApplicationContext(), "请选择降重数量");
                        return;
                    }
                    PublicStaticData.ServiceFeeSum = Double.parseDouble(ServiceFee) * Integer.parseInt(ly3numbered.getText().toString().trim()) + "";
                    PublicStaticData.ServiceFeeSum = ToDouble.toDpuble(Double.parseDouble(PublicStaticData.ServiceFeeSum));
                    pDialog.show();
                    SendRequest.AddMyService2(Userid, PublicStaticData.Shopid, PublicStaticData.Productid, ServiceFee, ly3numbered.getText().toString().trim(), new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            Log.e("onSuccess33",response);
                            pDialog.hide();
                            if (response.contains("<html>")) {
                                T.shortToast(getApplicationContext(), "服务器异常");
                                return;
                            }
                            ServiceSubmission mBean = JSON.parseObject(response, ServiceSubmission.class);
                            if (mBean.getResultCode() == 0) {
                                PublicStaticData.serviceId = mBean.getData() + "";
                                //去支付页面
                                startActivity(new Intent(SubmissionActivity.this, PayActivity.class));
//                              finish();
                            } else {
                                T.shortToast(getApplicationContext(), mBean.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Throwable e) {
                            pDialog.hide();
                        }
                    });
                }
                break;
            case R.id.ly3_add_img:
                if (number < 999) {
                    number++;
                    ly3numbered.setText(number + "");
                }

                break;
            case R.id.ly3_minus_img:
                if (number > 1) {
                    number--;
                    ly3numbered.setText(number + "");
                }
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String path1 = getPath(this, uri);
            pDialog.show();
            if (path1.contains(".docx") || path1.contains(".doc")) {
                SendRequest.Upload(Userid, new File(path1), new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e("==onError", e.toString());
                        pDialog.hide();
                        T.shortToast(getApplicationContext(), "亲，请检查网络");
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("==onResponse", response);
                        pDialog.hide();
                        if (response.contains("<html>")) {
                            T.shortToast(getApplicationContext(), "服务器异常");
                            return;
                        }
                        UploadBean mBean = JSON.parseObject(response, UploadBean.class);
                        if (mBean.getResultCode() == 0) {
                            FileName = mBean.getData().getFileName();
                            submissionchoosefiletv.setText(FileName);
                            FilePath = mBean.getData().getUrl();
                        } else {
                            T.shortToast(getApplicationContext(), mBean.getMsg());
                        }
                    }
                });
            } else {
                T.shortToast(getApplicationContext(), "请选择Word文档");
                pDialog.hide();
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public String getPath(Context context, Uri uri) {

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    @Override
    protected void onDestroy() {
        PublicStaticData.ChooseType = "";
        PublicStaticData.chooseSubjectId = null;
        chooseFileBean = null;

        unregisterReceiver(mFreshReceiver);
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }

}