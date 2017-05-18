package com.example.lql.editor.activity.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.adapter.service.ChooseFileAdapter;
import com.example.lql.editor.bean.ChooseFileBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.view.MyListView;

import java.util.ArrayList;

/**
 * 类描述：选择文件
 * 作  者：李清林
 * 时  间：2016/11/29
 * 修改备注：
 */
public class ChooseFileActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftback;
    private com.example.lql.editor.view.MyListView choosefilemylist;
    private TextView choosefilewebsite;
    private TextView choosefilecopewebsite;
    private android.widget.Button choosefilesubmitbut;
    ChooseFileAdapter mChooseFileAdapter;
    ArrayList<ChooseFileBean.DataBean> mList = new ArrayList<>();

    public static int choose = 0;
    ProgressDialog pDialog;

    String address = MyUrl.Pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        PublicStaticData.mActivityList.add(this);
        setContentView(R.layout.activity_choose_file);
        pDialog = new ProgressDialog(ChooseFileActivity.this);
        pDialog.setMessage("加载中...");
        pDialog.show();
        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_title);
        title.setText("选择文件");
        leftback = (ImageView) findViewById(R.id.titleBar_left_img);
        this.choosefilesubmitbut = (Button) findViewById(R.id.choose_file_submit_but);
        this.choosefilecopewebsite = (TextView) findViewById(R.id.choose_file_copewebsite);
        this.choosefilewebsite = (TextView) findViewById(R.id.choose_file_website);
        this.choosefilemylist = (MyListView) findViewById(R.id.choose_file_mylist);

        leftback.setOnClickListener(this);
        choosefilesubmitbut.setOnClickListener(this);
        mChooseFileAdapter = new ChooseFileAdapter(mList, ChooseFileActivity.this);
        choosefilemylist.setAdapter(mChooseFileAdapter);
        choosefilewebsite.setText("提示：如需添加文件库文件，请使用电脑浏览器访问地址" + MyUrl.Pic + "。");
        getData();


        choosefilecopewebsite.setOnClickListener(this);
    }


    private void getData() {
        String Userid = "";
        if (PreferenceUtils.getBoolean("IsLogin", false)) {
            Userid = PreferenceUtils.getString("UserId", "");
        } else {
            Userid = "0";
        }

        SendRequest.DocList(Userid, new mOkCallBack() {
            @Override
            public void onSuccess(String response) {
                pDialog.hide();
                if (response.contains("<html>")) {
                    T.shortToast(getApplicationContext(), "服务器异常");
                    return;
                }
                ChooseFileBean mBean = JSON.parseObject(response, ChooseFileBean.class);
                if (mBean.getResultCode() == 0) {
                    mList.addAll(mBean.getData());
                    mChooseFileAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable e) {
                pDialog.hide();
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_file_submit_but:
                if (choose == 0) {
                    T.shortToast(getApplicationContext(), "请选择文件");
                } else {
                    finish();
                }
                break;
            case R.id.titleBar_left_img:

                PublicStaticData.IsFile = false;
                ChooseFileActivity.choose++;
                PublicStaticData.chooseFileBean = null;
                finish();

                break;
            case R.id.choose_file_copewebsite:
                ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData myClip = ClipData.newPlainText("text", address);
                myClipboard.setPrimaryClip(myClip);
                T.shortToast(getApplicationContext(), "复制成功");
                break;
        }
    }








    @Override
    protected void onDestroy() {
        pDialog.dismiss();
        PublicStaticData.mActivityList.remove(this);
        super.onDestroy();
    }
}
