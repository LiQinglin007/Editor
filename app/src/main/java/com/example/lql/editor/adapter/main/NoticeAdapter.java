package com.example.lql.editor.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.NoticeBean;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/29.
 */

public class NoticeAdapter extends  RecyclerView.Adapter<NoticeAdapter.MyViewHolder>{
    private ArrayList<NoticeBean.DataBean> mList;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public NoticeAdapter(ArrayList<NoticeBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NoticeAdapter.MyViewHolder myViewHolder=new NoticeAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_notice,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final NoticeAdapter.MyViewHolder holder, int position) {
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

        holder.item_notice_title.setText(mList.get(position).getNotice()+"");
        holder.item_notice_content.setText(mList.get(position).getContent()+"");
        holder.item_notice_time.setText(mList.get(position).getCreateTime()+"");
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_notice_title;//名称
        TextView item_notice_content;//内容
        TextView item_notice_time;//时间


        public MyViewHolder(View itemView) {

            super(itemView);
            item_notice_title= (TextView) itemView.findViewById(R.id.item_notice_title);
            item_notice_content= (TextView) itemView.findViewById(R.id.item_notice_content);
            item_notice_time= (TextView) itemView.findViewById(R.id.item_notice_time);
        }
    }
}