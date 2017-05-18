package com.example.lql.editor.adapter.me;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.MyEvaluateBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/29.    我的评论
 */

public class MyEvaluateAdapter extends  RecyclerView.Adapter<MyEvaluateAdapter.MyViewHolder>{
    private ArrayList<MyEvaluateBean.DataBean> mList;
    private Context mContext;


    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



    public MyEvaluateAdapter(ArrayList<MyEvaluateBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public MyEvaluateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyEvaluateAdapter.MyViewHolder myViewHolder=new MyEvaluateAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_myevaluate,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        "Grades": 1,       1:好评0：差评
//        "CreateTime": "2016/9/14 15:09:10",    评论时间
//        "ComContent": "5xinghaoping",    评论内容
//        "ServiceId": 140,        服务编号
//        "ServiceName": "新加坡进口 爱儿适Lucky baby 纸尿裤NB/S86片"
//        服务名称
        if(mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }



        holder.item_myevaluate_name_tv.setText(mList.get(position).getServiceName());
        holder.item_myevaluate_tv.setText(mList.get(position).getComContent());
        holder.item_myevaluate_time_tv.setText(mList.get(position).getCreateTime());

        int grades=mList.get(position).getGrades();
        if(grades==1){
            holder.item_myevaluate_state_img.setImageResource(R.drawable.icon_good);
        }else{
            holder.item_myevaluate_state_img.setImageResource(R.drawable.icon_bad);
        }

        holder.item_myevaluate_img.setImageURI(Uri.parse(MyUrl.Pic+mList.get(position).getServicePic()));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_myevaluate_name_tv;//名称
        TextView item_myevaluate_tv;//内容
        TextView item_myevaluate_time_tv;//时间
        ImageView item_myevaluate_state_img;//小花

        SimpleDraweeView item_myevaluate_img;//用户头像



        public MyViewHolder(View itemView) {
            super(itemView);
            item_myevaluate_name_tv= (TextView) itemView.findViewById(R.id.item_myevaluate_name_tv);
            item_myevaluate_tv= (TextView) itemView.findViewById(R.id.item_myevaluate_tv);
            item_myevaluate_time_tv= (TextView) itemView.findViewById(R.id.item_myevaluate_time_tv);
            item_myevaluate_state_img= (ImageView) itemView.findViewById(R.id.item_myevaluate_state_img);
            item_myevaluate_img= (SimpleDraweeView) itemView.findViewById(R.id.item_myevaluate_img);

        }
    }
}