package com.example.lql.editor.adapter.me;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.bean.CollectionStudioBean;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PreferenceUtils;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/29.    收藏工作室
 */

public class MyCollectionStudioAdapter extends RecyclerView.Adapter<MyCollectionStudioAdapter.MyViewHolder> {
    private ArrayList<CollectionStudioBean.DataBean> mList;
    private Context mContext;


    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public MyCollectionStudioAdapter(ArrayList<CollectionStudioBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public MyCollectionStudioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyCollectionStudioAdapter.MyViewHolder myViewHolder = new MyCollectionStudioAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_collection_studio, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }


        holder.item_collection_studio_name_tv.setText(mList.get(position).getName());
        holder.item_collection_studio_tv1.setText("诚信保证金:" + mList.get(position).getPrice());
        holder.item_collection_studio_tv2.setText("月销量:" + mList.get(position).getCount());
        holder.item_collection_studio_tv3.setText("信誉值:" + mList.get(position).getComment());

        holder.item_collection_studio_img.setImageURI(Uri.parse(MyUrl.Pic + mList.get(position).getImgurl()));
        holder.item_collection_studio_cancel_tv.setOnClickListener(new MyClick(position));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MyClick implements View.OnClickListener {

        private int index;

        public MyClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            String Userid = "";
            if (PreferenceUtils.getBoolean("IsLogin", false)) {
                Userid = PreferenceUtils.getString("UserId", "");
            } else {
                Userid = "0";
            }
            SendRequest.UserFavorite(Userid, mList.get(index).getId() + "", 2, new mOkCallBack() {
                @Override
                public void onSuccess(String response) {
                    if (response.contains("<html>")) {
                        T.shortToast(mContext, "服务器异常");
                        return;
                    }
                    MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                    if (myBasebean.getResultCode() == 0) {
                        mList.remove(index);
                        notifyDataSetChanged();
                    }
                    T.shortToast(mContext, myBasebean.getMsg());
                }

                @Override
                public void onFailure(Throwable e) {
                    T.shortToast(mContext, "亲，请检查网络");
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_collection_studio_name_tv;//工作室名称
        TextView item_collection_studio_tv1;//诚信保证金
        TextView item_collection_studio_tv2;//月销量
        TextView item_collection_studio_tv3;//信誉值
        TextView item_collection_studio_cancel_tv;//取消收藏（可点击）
        SimpleDraweeView item_collection_studio_img;//工作室头像


        public MyViewHolder(View itemView) {
            super(itemView);
            item_collection_studio_name_tv = (TextView) itemView.findViewById(R.id.item_collection_studio_name_tv);
            item_collection_studio_tv1 = (TextView) itemView.findViewById(R.id.item_collection_studio_tv1);
            item_collection_studio_tv2 = (TextView) itemView.findViewById(R.id.item_collection_studio_tv2);
            item_collection_studio_tv3 = (TextView) itemView.findViewById(R.id.item_collection_studio_tv3);
            item_collection_studio_cancel_tv = (TextView) itemView.findViewById(R.id.item_collection_studio_cancel_tv);


            item_collection_studio_img = (SimpleDraweeView) itemView.findViewById(R.id.item_collection_studio_img);

        }
    }
}