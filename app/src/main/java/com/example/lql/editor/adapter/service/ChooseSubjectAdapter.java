package com.example.lql.editor.adapter.service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.ChooseSubjectBean;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/30.
 */

public class ChooseSubjectAdapter extends  RecyclerView.Adapter<ChooseSubjectAdapter.MyViewHolder>{

    private ArrayList<ChooseSubjectBean.DataBean> mList;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    public ChooseSubjectAdapter(ArrayList<ChooseSubjectBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_choosesubject,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final  MyViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position).getProjectName());
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
        TextView mTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.item_choosesubject_textview);
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
