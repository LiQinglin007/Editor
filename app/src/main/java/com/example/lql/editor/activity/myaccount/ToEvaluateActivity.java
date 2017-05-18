package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;

/**
 * 类描述：去评价页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class ToEvaluateActivity extends Activity implements View.OnClickListener {


    private TextView title;
    private ImageView leftback;
    private android.widget.EditText evaluatecontented;
    private ImageView evaluategoodimg;
    private android.widget.LinearLayout evaluategoodly;
    private ImageView evaluatebadimg;
    private android.widget.LinearLayout evaluatebadly;
    private android.widget.Button evaluatesubmitbut;
    int evaluate=1;//1   好评   0：差评
    String ServiceId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_to_evaluate);
        ServiceId=getIntent().getStringExtra("ServiceId");
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("评价服务");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);



        this.evaluatesubmitbut = (Button) findViewById(R.id.evaluate_submit_but);
        this.evaluatebadly = (LinearLayout) findViewById(R.id.evaluate_bad_ly);
        this.evaluatebadimg = (ImageView) findViewById(R.id.evaluate_bad_img);
        this.evaluategoodly = (LinearLayout) findViewById(R.id.evaluate_good_ly);
        this.evaluategoodimg = (ImageView) findViewById(R.id.evaluate_good_img);
        this.evaluatecontented = (EditText) findViewById(R.id.evaluate_content_ed);



        leftback.setOnClickListener(this);
        evaluategoodly.setOnClickListener(this);
        evaluatebadly.setOnClickListener(this);
        evaluatesubmitbut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.evaluate_submit_but://保存
                if(TextUtils.isEmpty(evaluatecontented.getText().toString().trim())){
                    T.shortToast(getApplicationContext(),"评论不能为空");
                }else{
                    String Userid="";
                    if(PreferenceUtils.getBoolean("IsLogin",false)){
                        Userid=PreferenceUtils.getString("UserId","");
                    }else{
                        Userid="0";
                    }

                    SendRequest.addcomment(Userid,evaluatecontented.getText().toString().trim(), evaluate, ServiceId, new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if(response.contains("<html>")){
                                T.shortToast(getApplicationContext(),"服务器异常");
                                return;
                            }
                            MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                            T.shortToast(getApplicationContext(),myBasebean.getMsg());
                            if(myBasebean.getResultCode()==0){
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Throwable e) {
                            T.shortToast(getApplicationContext(),"亲，请检查网络");
                        }
                    });
                }
                break;
            case R.id.evaluate_good_ly://好评
                evaluate=1;
                evaluategoodimg.setImageResource(R.drawable.btn_choose_sel);
                evaluatebadimg.setImageResource(R.drawable.btn_choose_nor);
                break;
            case R.id.evaluate_bad_ly://差评
                evaluate=0;
                evaluatebadimg.setImageResource(R.drawable.btn_choose_sel);
                evaluategoodimg.setImageResource(R.drawable.btn_choose_nor);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
