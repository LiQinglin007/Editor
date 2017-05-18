package com.example.lql.editor.adapter.studio;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.StudioListBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/29.  工作室适配器
 */

public class StudioAdapter extends  RecyclerView.Adapter<StudioAdapter.MyViewHolder>{
    private ArrayList<StudioListBean.DataBean> mList;
    private Context mContext;

    public StudioAdapter(ArrayList<StudioListBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }
    private OnItemClickLitener mOnItemClickLitener;


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public StudioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StudioAdapter.MyViewHolder myViewHolder=new StudioAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_collection_studio,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final StudioAdapter.MyViewHolder holder, int position) {
        holder.item_collection_studio_cancel_tv.setVisibility(View.GONE);//取消收藏按钮隐藏掉


        holder.item_collection_studio_name_tv.setText(mList.get(position).getWorkName());
        holder.item_collection_studio_tv1.setText("诚信保证金："+mList.get(position).getDeposit());
        holder.item_collection_studio_tv2.setText("月销量："+mList.get(position).getCount());
        holder.item_collection_studio_tv3.setText("信誉值："+mList.get(position).getCredit());
        holder.item_collection_studio_img.setImageURI(Uri.parse(MyUrl.Pic+mList.get(position).getHeadImg()));

        //点击事件
        if(mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_collection_studio_name_tv;//工作室名称
        TextView item_collection_studio_tv1;//诚信保证金
        TextView item_collection_studio_tv2;//月销量
        TextView item_collection_studio_tv3;//信誉值
        TextView item_collection_studio_cancel_tv;//取消收藏（可点击）
        SimpleDraweeView item_collection_studio_img;//工作室头像



        public MyViewHolder(View itemView) {
            super(itemView);
            item_collection_studio_name_tv= (TextView) itemView.findViewById(R.id.item_collection_studio_name_tv);
            item_collection_studio_tv1= (TextView) itemView.findViewById(R.id.item_collection_studio_tv1);
            item_collection_studio_tv2= (TextView) itemView.findViewById(R.id.item_collection_studio_tv2);
            item_collection_studio_tv3= (TextView) itemView.findViewById(R.id.item_collection_studio_tv3);
            item_collection_studio_cancel_tv= (TextView) itemView.findViewById(R.id.item_collection_studio_cancel_tv);


            item_collection_studio_img= (SimpleDraweeView) itemView.findViewById(R.id.item_collection_studio_img);

        }
    }
}