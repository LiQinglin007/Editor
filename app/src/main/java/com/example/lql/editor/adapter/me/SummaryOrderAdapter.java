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
import com.example.lql.editor.activity.myaccount.SummaryOrderActivity;
import com.example.lql.editor.activity.myaccount.ToEvaluateActivity;
import com.example.lql.editor.activity.service.PayActivity;
import com.example.lql.editor.activity.studio.StudioDetailsActivity;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.bean.SummaryOrderBean;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import zhangphil.iosdialog.widget.AlertDialog;

/**
 * Created by LQL on 2016/11/29. 速审订单适配器
 */

public class SummaryOrderAdapter extends RecyclerView.Adapter<SummaryOrderAdapter.MyViewHolder> {
    private ArrayList<SummaryOrderBean.DataBean> mList;
    private Context mContext;

    private OnItemClickLitener mOnItemClickLitener;

    public SummaryOrderAdapter(ArrayList<SummaryOrderBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;

    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    @Override
    public SummaryOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SummaryOrderAdapter.MyViewHolder myViewHolder = new SummaryOrderAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_summary_order, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

        holder.item_summaryorder_shop.setOnClickListener(new ToStudioClick(position));


//        1:初审中2：复审中3：查稿中4：样刊跟踪5：已完成
        holder.item_summaryorder_studioname_tv.setText(mList.get(position).getShopName());
        holder.item_summaryorder_state_tv.setText(mList.get(position).getServerState());
        holder.item_summaryorder_name_tv.setText(mList.get(position).getServiceName());
//        if (SummaryOrderActivity.type == 1) {
//            holder.item_summaryorder_price_tv.setText("￥" + mList.get(position).getMoney());
//        } else {
//            holder.item_summaryorder_price_tv.setText("￥" + mList.get(position).getSecondMoney());
//        }
//        //测试说把价格去掉
//        holder.item_summaryorder_price_tv.setVisibility(View.INVISIBLE);

        holder.item_summaryorder_type_tv.setText(mList.get(position).getUrlName());
        holder.item_summaryorder_img.setImageURI(Uri.parse(MyUrl.Pic + mList.get(position).getServiceImg()));

        if (SummaryOrderActivity.type == 1) {
            String state = mList.get(position).getServerState();
//            holder.item_summaryorder_returnmoney_tv.setVisibility(View.GONE);
            if (state.equals("等待初审")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setOnClickListener(new SummaryCancelClick(position));//取消投稿
            } else if (state.equals("初审未通过")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setOnClickListener(new DeleteClick(position));
            } else if (state.equals("初审通过")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_pay_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancel_tv.setOnClickListener(new SummaryCancelClick(position));//取消
                holder.item_summaryorder_pay_tv.setOnClickListener(new PayClick(position, 1));


            } else if (state.equals("失效")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setOnClickListener(new DeleteClick(position));
            }

        } else if (SummaryOrderActivity.type == 2) {
            String state = mList.get(position).getServerState();
            if (state.equals("等待复审")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
//                holder.item_summaryorder_returnmoney_tv.setVisibility(View.GONE);
            } else if (state.equals("复审未通过")) {
//                holder.item_summaryorder_returnmoney_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setOnClickListener(new DeleteClick(position));
            } else if (state.equals("复审通过")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setOnClickListener(new PayClick(position, 2));
//                holder.item_summaryorder_returnmoney_tv.setVisibility(View.GONE);
            }

        } else if (SummaryOrderActivity.type == 3) {
            holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
            holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
            holder.item_summaryorder_return_tv.setVisibility(View.GONE);
            holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
            holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
            holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
            holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
            holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
            holder.item_summaryorder_verification_tv.setVisibility(View.VISIBLE);
            holder.item_summaryorder_received_tv.setVisibility(View.GONE);
//            holder.item_summaryorder_returnmoney_tv.setVisibility(View.GONE);
            holder.item_summaryorder_verification_tv.setOnClickListener(new ConfirmCheckClick(position));

        } else if (SummaryOrderActivity.type == 4) {
            holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
            holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
            holder.item_summaryorder_return_tv.setVisibility(View.GONE);
            holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
            holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
            holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
            holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
            holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
            holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
            holder.item_summaryorder_received_tv.setVisibility(View.VISIBLE);
            holder.item_summaryorder_received_tv.setOnClickListener(new DeliveryClick(position));
//            holder.item_summaryorder_returnmoney_tv.setVisibility(View.GONE);
        } else if (SummaryOrderActivity.type == 5) {
            String state = mList.get(position).getServerState();
            if (state.equals("待评价")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
//                holder.item_summaryorder_returnmoney_tv.setVisibility(View.GONE);
                holder.item_summaryorder_evaluate_tv.setOnClickListener(new EvaluateClick(position));
            } else if (state.equals("已完成")) {
                holder.item_summaryorder_finished_tv.setVisibility(View.VISIBLE);
                holder.item_summaryorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_summaryorder_return_tv.setVisibility(View.GONE);
                holder.item_summaryorder_wait_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancelubmission_tv.setVisibility(View.GONE);
                holder.item_summaryorder_delete_tv.setVisibility(View.GONE);
                holder.item_summaryorder_cancel_tv.setVisibility(View.GONE);
                holder.item_summaryorder_pay_tv.setVisibility(View.GONE);
                holder.item_summaryorder_verification_tv.setVisibility(View.GONE);
                holder.item_summaryorder_received_tv.setVisibility(View.GONE);
//                holder.item_summaryorder_returnmoney_tv.setVisibility(View.GONE);
            }
        }




    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ToStudioClick implements View.OnClickListener {
        private int index;

        public ToStudioClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            PublicStaticData.StudioId = mList.get(index).getShopId() + "";
            mContext.startActivity(new Intent(mContext, StudioDetailsActivity.class));
        }
    }


    /**
     * 确认核实
     */
    class ConfirmCheckClick implements View.OnClickListener {
        private int index;

        public ConfirmCheckClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {

            new AlertDialog(mContext)
                    .builder().setMsg("确认核实？")
                    .setTitle("提示")
                    .setPositiveButton("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SendRequest.ConfirmCheck(mList.get(index).getMyServiceId_() + "", new mOkCallBack() {
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
                                    } else {
                                        T.shortToast(mContext, myBasebean.getMsg());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable e) {
                                    T.shortToast(mContext, "亲，请检查网络");
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


    /**
     * 服务评价
     */

    class EvaluateClick implements View.OnClickListener {
        private int index;

        public EvaluateClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, ToEvaluateActivity.class);
            intent.putExtra("ServiceId", mList.get(index).getMyServiceId_() + "");
            mContext.startActivity(intent);
        }
    }


    /**
     * 确认收刊
     */
    class DeliveryClick implements View.OnClickListener {
        private int index;

        public DeliveryClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {


            new AlertDialog(mContext)
                    .builder().setMsg("确认收刊？")
                    .setTitle("提示")
                    .setPositiveButton("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SendRequest.Delivery(mList.get(index).getMyServiceId_() + "", new mOkCallBack() {
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
                                    } else {
                                        T.shortToast(mContext, myBasebean.getMsg());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable e) {
                                    T.shortToast(mContext, "亲，请检查网络");
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

    /**
     * 支付
     * type   1:初审付款   2：复审付款
     */

    class PayClick implements View.OnClickListener {
        private int index;
        private int type;

        public PayClick(int index, int type) {
            this.index = index;
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            PublicStaticData.serviceType = "我要速审";
            PublicStaticData.PayType = type + "";
            PublicStaticData.serviceId = mList.get(index).getMyServiceId_() + "";
            PublicStaticData.ServiceFeeSum = type==1?mList.get(index).getMoney() + "":mList.get(index).getSecondMoney()+"";
//            0：初审付款   1：复审付款
            PublicStaticData.Sum=type==1?0:1;
            mContext.startActivity(new Intent(mContext, PayActivity.class));
        }
    }


    /**
     * 删除
     */
    class DeleteClick implements View.OnClickListener {
        private int index;

        public DeleteClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            new AlertDialog(mContext)
                    .builder().setMsg("确认删除？")
                    .setTitle("提示")
                    .setPositiveButton("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SendRequest.Delete(mList.get(index).getMyServiceId_() + "", new mOkCallBack() {
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
                                    } else {
                                        T.shortToast(mContext, myBasebean.getMsg());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable e) {
                                    T.shortToast(mContext, "亲，请检查网络");
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

    /**
     * 取消投稿  取消
     */
    class SummaryCancelClick implements View.OnClickListener {

        private int index;

        public SummaryCancelClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            new AlertDialog(mContext)
                    .builder().setMsg("确定取消？")
                    .setTitle("提示")
                    .setPositiveButton("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SendRequest.SummaryCancel(mList.get(index).getMyServiceId_() + "", new mOkCallBack() {
                                @Override
                                public void onSuccess(String response) {
                                    if (response.contains("<html>")) {
                                        T.shortToast(mContext, "服务器异常");
                                        return;
                                    }
                                    MyBasebean myBasebean = JSON.parseObject(response, MyBasebean.class);
                                    if (myBasebean.getResultCode() == 0) {
                                        mList.remove(index);
                                        sendBrocast();
//                                        notifyDataSetChanged();
                                    } else {
                                        T.shortToast(mContext, myBasebean.getMsg());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable e) {
                                    T.shortToast(mContext, "亲，请检查网络");
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_summaryorder_studioname_tv;//工作室名称
        TextView item_summaryorder_state_tv;//订单状态（待检测之类的）
        TextView item_summaryorder_name_tv;//服务名称

//        TextView item_summaryorder_price_tv;//服务价格
        SimpleDraweeView item_summaryorder_img;//工作室头像
        TextView item_summaryorder_type_tv;//文件名称


        TextView item_summaryorder_finished_tv;//订单完成（不可点击）
        TextView item_summaryorder_return_tv;//定金已退回（不可点击）
        TextView item_summaryorder_wait_tv;//等待审核（不可点击）
        TextView item_summaryorder_cancelubmission_tv;//取消投稿（可点击）
        TextView item_summaryorder_delete_tv;//删除（可点击）
        TextView item_summaryorder_cancel_tv;//取消（可点击）
        TextView item_summaryorder_pay_tv;//付款（可点击）
        TextView item_summaryorder_verification_tv;//确认核实
        TextView item_summaryorder_received_tv;//确认收刊
        TextView item_summaryorder_evaluate_tv;//评价
//        TextView item_summaryorder_returnmoney_tv;//定金已退回
        LinearLayout item_summaryorder_shop;
//        确认核实    确认收刊


        public MyViewHolder(View itemView) {

            super(itemView);
            item_summaryorder_studioname_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_studioname_tv);
            item_summaryorder_state_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_state_tv);
            item_summaryorder_name_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_name_tv);

//            item_summaryorder_price_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_price_tv);
            item_summaryorder_type_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_type_tv);
            item_summaryorder_img = (SimpleDraweeView) itemView.findViewById(R.id.item_summaryorder_img);


            item_summaryorder_finished_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_finished_tv);
            item_summaryorder_return_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_return_tv);
            item_summaryorder_wait_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_wait_tv);
            item_summaryorder_cancelubmission_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_cancelubmission_tv);


            item_summaryorder_delete_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_delete_tv);
            item_summaryorder_cancel_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_cancel_tv);
            item_summaryorder_pay_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_pay_tv);


            item_summaryorder_verification_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_verification_tv);
            item_summaryorder_received_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_received_tv);
            item_summaryorder_evaluate_tv = (TextView) itemView.findViewById(R.id.item_summaryorder_evaluate_tv);
            item_summaryorder_shop = (LinearLayout) itemView.findViewById(R.id.item_summaryorder_shop);

//            item_summaryorder_returnmoney_tv = (TextView) itemView.findViewById(item_summaryorder_returnmoney_tv);
        }
    }


    public void sendBrocast(){
        Intent intent = new Intent();
        intent.setAction("com.lql.getDataSummaryOrder");
        mContext.sendBroadcast(intent);
    }
}
