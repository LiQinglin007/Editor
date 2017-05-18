package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
 * 类描述：意见反馈页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class FeedbackActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private android.widget.EditText feedbackcontented;
    private android.widget.Button feedbacksubmitbut;

    String Userid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_feedback);
        if(PreferenceUtils.getBoolean("IsLogin",false)){
            Userid=PreferenceUtils.getString("UserId","");
        }else{
            Userid="0";
        }
        initView();
    }


    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("意见建议");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        this.feedbacksubmitbut = (Button) findViewById(R.id.feedback_submit_but);
        this.feedbackcontented = (EditText) findViewById(R.id.feedback_content_ed);

        leftback.setOnClickListener(this);
        feedbacksubmitbut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.feedback_submit_but:
                String feedbackStr=feedbackcontented.getText().toString().trim();
                if(!TextUtils.isEmpty(feedbackStr)){
                    SendRequest.AddSuggestion(Userid,feedbackStr, new mOkCallBack() {
                        @Override
                        public void onSuccess(String response) {
                            if(response.contains("<html>")){
                                T.shortToast(getApplicationContext(),"服务器异常");
                                return;
                            }
                            MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                            if(myBasebean.getResultCode()==0){
                                finish();
                            }
                            T.shortToast(getApplicationContext(),myBasebean.getMsg());
                        }
                        @Override
                        public void onFailure(Throwable e) {
                            T.shortToast(getApplicationContext(),"亲，请检查网络");
                        }
                    });
                }else{
                    T.shortToast(getApplicationContext(),"反馈内容不能为空");
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
