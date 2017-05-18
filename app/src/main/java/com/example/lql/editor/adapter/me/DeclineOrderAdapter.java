package com.example.lql.editor.adapter.me;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.myaccount.DeclineOrderActivity;
import com.example.lql.editor.activity.myaccount.ToEvaluateActivity;
import com.example.lql.editor.activity.studio.StudioDetailsActivity;
import com.example.lql.editor.bean.DeclineOrderBean;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.ToDouble;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import zhangphil.iosdialog.widget.AlertDialog;

/**
 * 降重订单  adapter
 */

public class DeclineOrderAdapter extends  RecyclerView.Adapter<DeclineOrderAdapter.MyViewHolder>{
   private ArrayList<DeclineOrderBean.DataBean> mList;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;



    public DeclineOrderAdapter(ArrayList<DeclineOrderBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    public void setOnItemClickLitener( OnItemClickLitener  OnItemClickLitener){
        this.mOnItemClickLitener=OnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_decline_order,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if(mOnItemClickLitener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }

        holder.item_decline_studioname_tv.setText(mList.get(position).getShopName());
        holder.item_declineorder_state_tv.setText(mList.get(position).getServerState());
        holder.item_declineorder_name_tv.setText(mList.get(position).getServiceName());
        holder.item_declineorder_price_tv.setText("￥"+ ToDouble.toDpuble(mList.get(position).getMoney()));
        holder.item_declineorder_img.setImageURI(Uri.parse(MyUrl.Pic+mList.get(position).getServiceImg()));
//       	1:进行中2：待确认3：已完成
        if(DeclineOrderActivity.type==1){
            holder.item_checkorder_confirmorder_tv.setVisibility(View.GONE);
            holder.item_checkorder_evaluate_tv.setVisibility(View.GONE);
            holder.item_declineorder_confirm_tv.setVisibility(View.VISIBLE);
            holder.item_declineorder_finished_tv.setVisibility(View.GONE);
        }else if(DeclineOrderActivity.type==2){
            holder.item_checkorder_confirmorder_tv.setVisibility(View.VISIBLE);
            holder.item_checkorder_confirmorder_tv.setOnClickListener(new ConfirmClick(position));
            holder.item_checkorder_evaluate_tv.setVisibility(View.GONE);
            holder.item_declineorder_confirm_tv.setVisibility(View.GONE);
            holder.item_declineorder_finished_tv.setVisibility(View.GONE);

        }else{
            String state= mList.get(position).getServerState();
            if(state.equals("待评价")){
                holder.item_checkorder_confirmorder_tv.setVisibility(View.GONE);
                holder.item_checkorder_evaluate_tv.setVisibility(View.VISIBLE);
                holder.item_declineorder_confirm_tv.setVisibility(View.GONE);
                holder.item_declineorder_finished_tv.setVisibility(View.GONE);
                holder.item_checkorder_evaluate_tv.setOnClickListener(new EvaluateClick(position));
            }else{
                holder.item_checkorder_confirmorder_tv.setVisibility(View.GONE);
                holder.item_checkorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_declineorder_confirm_tv.setVisibility(View.GONE);
                holder.item_declineorder_finished_tv.setVisibility(View.VISIBLE);
            }
        }



        holder.item_decline_shop.setOnClickListener(new ToStudioClick(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }





    class ToStudioClick implements View.OnClickListener{
        private int index;

        public ToStudioClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            PublicStaticData.StudioId=mList.get(index).getShopId()+"";
            mContext.startActivity(new Intent(mContext, StudioDetailsActivity.class));
        }
    }


    /**
     * 服务评论
     */


    class EvaluateClick implements View.OnClickListener{
        private int index;

        public EvaluateClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(mContext, ToEvaluateActivity.class);
            intent.putExtra("ServiceId", mList.get(index).getMyServiceId_()+"");
            mContext.startActivity(intent);
        }
    }
    /**
     * 订单确认
     */
    class ConfirmClick implements View.OnClickListener{
        private int index;
        public ConfirmClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {


            new AlertDialog(mContext)
                    .builder().setMsg("是否要订单确认？")
                    .setTitle("提示")
                    .setPositiveButton("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            SendRequest.Confirm(mList.get(index).getMyServiceId_() + "", new mOkCallBack() {
                                @Override
                                public void onSuccess(String response) {
                                    if(response.contains("<html>")){
                                        T.shortToast(mContext,"服务器异常");
                                        return;
                                    }
                                    MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                                    if(myBasebean.getResultCode()==0){
                                        mList.remove(index);
                                        notifyDataSetChanged();
                                    }else{
                                        T.shortToast(mContext,myBasebean.getMsg());
                                    }
                                }
                                @Override
                                public void onFailure(Throwable e) {
                                    T.shortToast(mContext,"亲，请检查网络");
                                }
                            });
                        }
                    })
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    })
                    .show();






        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_decline_studioname_tv;//工作室名称
        TextView item_declineorder_state_tv;//订单状态（待检测之类的）
        TextView item_declineorder_name_tv;//服务名称
        TextView item_declineorder_price_tv;//服务价格
        SimpleDraweeView item_declineorder_img;//工作室头像



        TextView item_checkorder_confirmorder_tv;//订单确认（可点击）
        TextView item_checkorder_evaluate_tv;//服务评价（可点击）
        TextView item_declineorder_confirm_tv;//等待小编确认（不可点击）
        TextView item_declineorder_finished_tv;//订单完成（不可点击）
        LinearLayout item_decline_shop;


        public MyViewHolder(View itemView) {

            super(itemView);

            item_decline_studioname_tv= (TextView) itemView.findViewById(R.id.item_decline_studioname_tv);
            item_declineorder_state_tv= (TextView) itemView.findViewById(R.id.item_declineorder_state_tv);
            item_declineorder_name_tv= (TextView) itemView.findViewById(R.id.item_declineorder_name_tv);

            item_declineorder_price_tv= (TextView) itemView.findViewById(R.id.item_declineorder_price_tv);
            item_checkorder_confirmorder_tv= (TextView) itemView.findViewById(R.id.item_checkorder_confirmorder_tv);
            item_checkorder_evaluate_tv= (TextView) itemView.findViewById(R.id.item_checkorder_evaluate_tv);

            item_declineorder_confirm_tv= (TextView) itemView.findViewById(R.id.item_declineorder_confirm_tv);
            item_declineorder_finished_tv= (TextView) itemView.findViewById(R.id.item_declineorder_finished_tv);

            item_declineorder_img= (SimpleDraweeView) itemView.findViewById(R.id.item_declineorder_img);
            item_decline_shop= (LinearLayout) itemView.findViewById(R.id.item_decline_shop);
        }
    }

}
