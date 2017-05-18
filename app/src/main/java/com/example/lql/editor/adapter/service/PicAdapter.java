package com.example.lql.editor.adapter.service;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.ServiceDetailsBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/12/13.
 */

public class PicAdapter extends BaseAdapter {
    ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean> imgs;
    Context mContext;

    public PicAdapter(ArrayList<ServiceDetailsBean.DataBean.ServiceimgBean> imgs, Context context) {
        this.imgs = imgs;
        mContext = context;


    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return imgs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(mContext).inflate(R.layout.item_picadapter,null);
        SimpleDraweeView pic_item_picadapter= (SimpleDraweeView) view.findViewById(R.id.pic_item_picadapter);

        if(TextUtils.isEmpty(imgs.get(i).getWidth())||imgs.get(i).getWidth().equals("null")){
            return view;
        }

        String a=imgs.get(i).getWidth()+"f";
        pic_item_picadapter.setAspectRatio(Float.parseFloat(a));
        pic_item_picadapter.setImageURI(Uri.parse(MyUrl.Pic+imgs.get(i).getImg()));




        return view;
    }
}
