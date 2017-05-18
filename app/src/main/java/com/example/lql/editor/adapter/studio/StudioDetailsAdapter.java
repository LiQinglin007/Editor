package com.example.lql.editor.adapter.studio;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.StudioDetailsBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.utils.ToDouble;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/29.
 */

public class StudioDetailsAdapter extends BaseAdapter {
    private ArrayList<StudioDetailsBean.DataBean.ServiceBean> mList;
    private Context mContext;

    public StudioDetailsAdapter(ArrayList<StudioDetailsBean.DataBean.ServiceBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder mMyViewHolder;
        if (view == null) {
            mMyViewHolder = new MyViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_studio_details, null);
            mMyViewHolder.item_studio_name_tv = (TextView) view.findViewById(R.id.item_studio_name_tv);
            mMyViewHolder.item_studio_type_tv = (TextView) view.findViewById(R.id.item_studio_type_tv);
            mMyViewHolder.item_studio_price_tv = (TextView) view.findViewById(R.id.item_studio_price_tv);
            mMyViewHolder.item_studio_priceold_tv = (TextView) view.findViewById(R.id.item_studio_priceold_tv);
            mMyViewHolder.item_studio_number_tv = (TextView) view.findViewById(R.id.item_studio_number_tv);
            mMyViewHolder.item_studio_img = (SimpleDraweeView) view.findViewById(R.id.item_studio_img);
            mMyViewHolder.item_studio_img1 = (ImageView) view.findViewById(R.id.item_studio_img1);
            view.setTag(mMyViewHolder);
        } else {
            mMyViewHolder = (MyViewHolder) view.getTag();
        }
        mMyViewHolder.item_studio_priceold_tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        // 给 ImageView 设置一个 tag
        mMyViewHolder.item_studio_img.setTag(mList.get(i).getPicture());
        // 通过 tag 来防止图片错位
        if (mMyViewHolder.item_studio_img.getTag() != null && mMyViewHolder.item_studio_img.getTag().equals(mList.get(i).getPicture())) {
            mMyViewHolder.item_studio_img.setImageURI(Uri.parse(MyUrl.Pic + mList.get(i).getPicture()));//图
//            Picasso.with(mContext).invalidate(MyUrl.Pic + mList.get(i).getPicture());
//            Picasso.with(mContext).load(MyUrl.Pic+mList.get(i).getPicture()).into(mMyViewHolder.item_studio_img1);
        }

        mMyViewHolder.item_studio_name_tv.setText(mList.get(i).getServiceName());
//        "Type": 1								1：检测查重2：修改降重3：编辑速审
        int type = mList.get(i).getType();
        if (type == 1) {
            mMyViewHolder.item_studio_type_tv.setText("检测查重");
            if(mList.get(i).getServicePrice().contains("-")||mList.get(i).getServicePrice().contains("~")){

            }else{
            if(Double.parseDouble(mList.get(i).getServicePrice())==mList.get(i).getOriginalCost()||mList.get(i).getOriginalCost()==0){
                mMyViewHolder.item_studio_priceold_tv.setVisibility(View.GONE);
            }else{
                mMyViewHolder.item_studio_priceold_tv.setVisibility(View.VISIBLE);
            }
            }
        } else if (type == 2) {
            mMyViewHolder.item_studio_type_tv.setText("修改降重");
            if(mList.get(i).getServicePrice().contains("-")||mList.get(i).getServicePrice().contains("~")){

            }else {
                if (Double.parseDouble(mList.get(i).getServicePrice()) == mList.get(i).getOriginalCost() || mList.get(i).getOriginalCost() == 0) {
                    mMyViewHolder.item_studio_priceold_tv.setVisibility(View.GONE);
                } else {
                    mMyViewHolder.item_studio_priceold_tv.setVisibility(View.VISIBLE);
                }
            }
        } else {
            mMyViewHolder.item_studio_type_tv.setText("编辑速审");
            mMyViewHolder.item_studio_priceold_tv.setVisibility(View.GONE);
        }
        mMyViewHolder.item_studio_price_tv.setText("￥"+mList.get(i).getServicePrice() + "");


        mMyViewHolder.item_studio_number_tv.setText("月销量:" + mList.get(i).getCount());
        mMyViewHolder.item_studio_priceold_tv.setText("￥"+ ToDouble.toDpuble(mList.get(i).getOriginalCost()));
        return view;
    }


    class MyViewHolder {
        TextView item_studio_name_tv;//名称
        TextView item_studio_type_tv;//服务类型检测查重
        TextView item_studio_price_tv;//现在的价格
        TextView item_studio_priceold_tv;//原来的价格
        TextView item_studio_number_tv;//月销量

        SimpleDraweeView item_studio_img;
        ImageView item_studio_img1;
    }
}
