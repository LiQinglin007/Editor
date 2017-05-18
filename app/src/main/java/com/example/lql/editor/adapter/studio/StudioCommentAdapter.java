package com.example.lql.editor.adapter.studio;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.StudioCommentBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.utils.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/27.  工作室评论
 */

public class StudioCommentAdapter extends RecyclerView.Adapter<StudioCommentAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<StudioCommentBean.DataBean> mList;

    public StudioCommentAdapter(Context context, ArrayList<StudioCommentBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_studioevaluate, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.item_studioevaluate_user_tv.setText(mList.get(position).getPerson());
        holder.item_studioevaluate_user_img.setTag(mList.get(position).getHeadImg());
        if (holder.item_studioevaluate_user_img.getTag() != null && holder.item_studioevaluate_user_img.getTag().equals(mList.get(position).getHeadImg())) {
            Picasso.with(mContext)
                    .load(MyUrl.Pic + mList.get(position).getHeadImg())
                    .config(Bitmap.Config.RGB_565)
                    .resize(200, 200)
                    .into(holder.item_studioevaluate_user_img);
        }

        holder.item_studioevaluate_usercontent_tv.setText(mList.get(position).getComContent());
        holder.item_studioevaluate_time_tv.setText(mList.get(position).getCreateTime());
//        "Grades": 1,							1：好评0：差评
        if (mList.get(position).getGrades() == 1) {
            holder.item_studioevaluate_state_img.setImageResource(R.drawable.icon_good);
        } else {
            holder.item_studioevaluate_state_img.setImageResource(R.drawable.icon_bad);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView item_studioevaluate_user_img;//用户头像
        TextView item_studioevaluate_user_tv;//用户名称
        TextView item_studioevaluate_usercontent_tv;//评论内容
        TextView item_studioevaluate_time_tv;//时间
        ImageView item_studioevaluate_state_img;//小花

        public MyViewHolder(View itemView) {
            super(itemView);
            item_studioevaluate_state_img = (ImageView) itemView.findViewById(R.id.item_studioevaluate_state_img);
            item_studioevaluate_user_img = (CircleImageView) itemView.findViewById(R.id.item_studioevaluate_user_img);
            item_studioevaluate_user_tv = (TextView) itemView.findViewById(R.id.item_studioevaluate_user_tv);
            item_studioevaluate_usercontent_tv = (TextView) itemView.findViewById(R.id.item_studioevaluate_usercontent_tv);
            item_studioevaluate_time_tv = (TextView) itemView.findViewById(R.id.item_studioevaluate_time_tv);
        }
    }
}
