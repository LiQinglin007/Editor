package com.example.lql.editor.adapter.service;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.ServiceBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.ToDouble;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


/**
 * Created by LQL on 2016/12/1.
 */

public class ServiceAdapter extends  RecyclerView.Adapter<ServiceAdapter.MyViewHolder>{
    private ArrayList<ServiceBean.DataBean> mList;
    private Context mContext;
    int Type=1;

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public ServiceAdapter(ArrayList<ServiceBean.DataBean> list, Context context, int type) {
        mList = list;
        mContext = context;
        Type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_fragment,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder mMyViewHolder, int i) {

        mMyViewHolder.item_main_name_tv.setText(mList.get(i).getServiceName()+"");
//        Type1:检测查重 2：修改降重3：编辑速审
//        1、只有速审没有原价
        Type=PublicStaticData.ServiceType;
        if(PublicStaticData.ServiceType==1){
            mMyViewHolder.item_main_type_tv.setText("检测查重");
            //原价
            mMyViewHolder.item_main_priceold_tv.setText("￥"+ ToDouble.toDpuble(mList.get(i).getOriginalCost()));
            mMyViewHolder.item_main_priceold_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            if(mList.get(i).getServicePrice().contains("-")||mList.get(i).getServicePrice().contains("~")){

            }else {
                if (Double.parseDouble(mList.get(i).getServicePrice()) == mList.get(i).getOriginalCost() || mList.get(i).getOriginalCost() == 0) {
                    mMyViewHolder.item_main_priceold_tv.setVisibility(View.GONE);
                } else {
                    mMyViewHolder.item_main_priceold_tv.setVisibility(View.VISIBLE);
                }
            }
        }else if(PublicStaticData.ServiceType==2){
            mMyViewHolder.item_main_type_tv.setText("修改降重");
            //原价
            mMyViewHolder.item_main_priceold_tv.setText("￥"+ToDouble.toDpuble(mList.get(i).getOriginalCost()));
            mMyViewHolder.item_main_priceold_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            if(mList.get(i).getServicePrice().contains("-")||mList.get(i).getServicePrice().contains("~")){

            }else{
                 if(Double.parseDouble(mList.get(i).getServicePrice())==mList.get(i).getOriginalCost()||mList.get(i).getOriginalCost()==0){
                     mMyViewHolder.item_main_priceold_tv.setVisibility(View.GONE);
                 }else{
                     mMyViewHolder.item_main_priceold_tv.setVisibility(View.VISIBLE);
                 }
            }
        }else if(PublicStaticData.ServiceType==3){
            mMyViewHolder.item_main_type_tv.setText("编辑速审");
            mMyViewHolder.item_main_priceold_tv.setVisibility(View.INVISIBLE);
        }


        if(PublicStaticData.PopNumberTitle==1){
            mMyViewHolder.item_main_priceold_tv.setVisibility(View.INVISIBLE);
        }

        if((mList.get(i).getOriginalCost()+"").equals("0")){
            mMyViewHolder.item_main_priceold_tv.setVisibility(View.INVISIBLE);
        }

        mMyViewHolder.item_main_type_tv.setVisibility(View.INVISIBLE);
        //现在的价格
        mMyViewHolder.item_main_price_tv.setText("￥"+mList.get(i).getServicePrice());
        //月销量
        mMyViewHolder.item_main_number_tv.setText("月销量："+mList.get(i).getCount());
        //设置图片
        Uri uri = Uri.parse(MyUrl.Pic+mList.get(i).getPicture());
        mMyViewHolder.item_main_img.setImageURI(uri);

        //点击事件
        if(mOnItemClickLitener!=null){
            mMyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = mMyViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(mMyViewHolder.itemView, pos);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_main_name_tv;//名称
        TextView item_main_type_tv;//服务类型检测查重
        TextView item_main_price_tv;//现在的价格
        TextView item_main_number_tv;//月销量
        TextView item_main_priceold_tv;//原来的价格
        SimpleDraweeView item_main_img;
        public MyViewHolder(View view) {
            super(view);
           item_main_name_tv= (TextView) view.findViewById(R.id.item_main_name_tv);
            item_main_type_tv= (TextView) view.findViewById(R.id.item_main_type_tv);
            item_main_price_tv= (TextView) view.findViewById(R.id.item_main_price_tv);
            item_main_priceold_tv= (TextView) view.findViewById(R.id.item_main_priceold_tv);
            item_main_number_tv= (TextView) view.findViewById(R.id.item_main_number_tv);
            item_main_img= (SimpleDraweeView) view.findViewById(R.id.item_main_img);
        }
    }
}
