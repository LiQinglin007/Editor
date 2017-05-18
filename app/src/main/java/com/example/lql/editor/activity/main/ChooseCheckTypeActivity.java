package com.example.lql.editor.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.utils.PublicStaticData;

import static com.example.lql.editor.R.id.titleBar_left_img;
/**
 * 类描述：选择查重类型页面
 * 作  者：李清林
 * 时  间：2016/11/24
 * 修改备注：
 */
public class ChooseCheckTypeActivity extends Activity implements View.OnClickListener{

    private android.widget.ImageView chooseperiodicalimg;
    private android.widget.ImageView choosedegreeimg;
    private android.widget.ImageView chooseundergraduateimg;
    private android.widget.ImageView choosemasterimg;
    private TextView title;
    private ImageView leftback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_choose_check_type);
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("选择查重类型");
        leftback = (ImageView) findViewById(titleBar_left_img);
        this.choosemasterimg = (ImageView) findViewById(R.id.choose_master_img);
        this.chooseundergraduateimg = (ImageView) findViewById(R.id.choose_undergraduate_img);
        this.choosedegreeimg = (ImageView) findViewById(R.id.choose_degree_img);
        this.chooseperiodicalimg = (ImageView) findViewById(R.id.choose_periodical_img);
        leftback.setOnClickListener(this);
        choosemasterimg.setOnClickListener(this);
        chooseundergraduateimg.setOnClickListener(this);
        choosedegreeimg.setOnClickListener(this);
        chooseperiodicalimg.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.titleBar_left_img:
                finish();
                break;
            case R.id.choose_periodical_img://img1
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=1;
                finish();
                break;
            case R.id.choose_degree_img://img2
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=2;
                finish();
                break;
            case R.id.choose_undergraduate_img://img3
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=3;
                finish();
                break;
            case R.id.choose_master_img://img4
                PublicStaticData.MainFragmentNmuber = 1;
                PublicStaticData.PopNumberTitle=2;
                PublicStaticData.PopNumber=4;
                finish();
                break;
        }
    }

    public void sendBrocast(){
        Intent intent = new Intent();
        intent.setAction("com.lql.setview");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
