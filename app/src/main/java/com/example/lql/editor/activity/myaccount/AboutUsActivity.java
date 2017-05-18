package com.example.lql.editor.activity.myaccount;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.utils.PublicStaticData;

/**
 * 类描述： 关于我们页面
 * 作  者：李清林
 * 时  间：2016/11/25
 * 修改备注：
 */
public class AboutUsActivity extends Activity {
    private TextView title;
    private ImageView leftback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_about_us);
        title = (TextView) findViewById(R.id.title_title);
        title.setText("关于我们");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        leftback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }





    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
