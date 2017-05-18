package com.example.lql.editor.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.utils.PublicStaticData;

public class NoticeDetailsActivity extends Activity {
    private TextView title;
    private ImageView leftback;
    private TextView noticetitle;
    private TextView noticetime;
    private TextView noticecontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_notice_details);

        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("公告详情");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        this.noticecontent = (TextView) findViewById(R.id.notice_content);
        this.noticetime = (TextView) findViewById(R.id.notice_time);
        this.noticetitle = (TextView) findViewById(R.id.notice_title);

        this.noticetitle.setText(PublicStaticData.myNotice.getNotice()+"");
        this.noticetime.setText(PublicStaticData.myNotice.getCreateTime()+"");
        this.noticecontent.setText(PublicStaticData.myNotice.getContent()+"");
    }


    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
