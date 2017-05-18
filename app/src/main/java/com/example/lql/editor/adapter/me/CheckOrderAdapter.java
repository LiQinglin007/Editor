package com.example.lql.editor.adapter.me;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.lql.editor.R;
import com.example.lql.editor.activity.myaccount.CheckOrderActivity;
import com.example.lql.editor.activity.myaccount.ToEvaluateActivity;
import com.example.lql.editor.activity.studio.StudioDetailsActivity;
import com.example.lql.editor.bean.CheckOrderBean;
import com.example.lql.editor.bean.MyBasebean;
import com.example.lql.editor.myhttp.HttpUtils;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.myhttp.SaveFileCallBack;
import com.example.lql.editor.myhttp.SendRequest;
import com.example.lql.editor.myhttp.mOkCallBack;
import com.example.lql.editor.utils.PublicStaticData;
import com.example.lql.editor.utils.T;
import com.example.lql.editor.utils.ToDouble;
import com.example.lql.editor.widget.RecyclerView.OnItemClickLitener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import zhangphil.iosdialog.widget.AlertDialog;

/**
 * Created by LQL on 2016/11/29.  查重订单适配器
 */

public class CheckOrderAdapter extends  RecyclerView.Adapter<CheckOrderAdapter.MyViewHolder>{
    private ArrayList<CheckOrderBean.DataBean> mList;
    private Context mContext;

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



    public CheckOrderAdapter(ArrayList<CheckOrderBean.DataBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public CheckOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CheckOrderAdapter.MyViewHolder myViewHolder=new CheckOrderAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_check_order,parent,false));
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

        holder.item_checkorder_shop.setOnClickListener(new ToStudioClick(position));
        holder.item_checkorder_studioname_tv.setText(mList.get(position).getShopName());
        //        1:待检测 2：检测中 3：已完成
        holder.item_checkorder_state_tv.setText(mList.get(position).getServerState());
        holder.item_checkorder_name_tv.setText(mList.get(position).getServiceName());
        holder. item_checkorder_price_tv.setText("￥"+ ToDouble.toDpuble(mList.get(position).getMoney()));
        holder.item_checkorder_type_tv.setText(mList.get(position).getUrlName());
        holder.item_checkorder_img.setImageURI(Uri.parse(MyUrl.Pic+mList.get(position).getServiceImg()));

        if(CheckOrderActivity.type==1){
            holder.item_checkorder_cancel_tv.setVisibility(View.VISIBLE);
            holder.item_checkorder_evaluate_tv.setVisibility(View.GONE);
            holder.item_checkorder_time_tv.setVisibility(View.GONE);
            holder.item_checkorder_report_tv.setVisibility(View.GONE);
            holder.item_checkorder_cancel_tv.setOnClickListener(new CancelClick(position));
        }else if(CheckOrderActivity.type==2){
            holder.item_checkorder_cancel_tv.setVisibility(View.GONE);
            holder.item_checkorder_evaluate_tv.setVisibility(View.GONE);
            holder.item_checkorder_time_tv.setVisibility(View.VISIBLE);
            holder.item_checkorder_report_tv.setVisibility(View.GONE);
            holder.item_checkorder_time_tv.setText("同类文章检测平均时长为"+mList.get(position).getAvg()+"分钟");
        }else{
            if(mList.get(position).getServerState().equals("待评价")){
                holder.item_checkorder_cancel_tv.setVisibility(View.GONE);
                holder.item_checkorder_evaluate_tv.setVisibility(View.VISIBLE);
                holder.item_checkorder_time_tv.setVisibility(View.GONE);
                holder.item_checkorder_report_tv.setVisibility(View.VISIBLE);
                holder.item_checkorder_evaluate_tv.setOnClickListener(new EvaluateClick(position));
                holder.item_checkorder_report_tv.setOnClickListener(new DownClick(position));
            }else if(mList.get(position).getServerState().equals("已完成")){
                holder.item_checkorder_cancel_tv.setVisibility(View.GONE);
                holder.item_checkorder_evaluate_tv.setVisibility(View.GONE);
                holder.item_checkorder_time_tv.setVisibility(View.GONE);
                holder.item_checkorder_report_tv.setVisibility(View.VISIBLE);
                holder.item_checkorder_report_tv.setOnClickListener(new DownClick(position));
            }
        }
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
     * 下载
     */
    ProgressDialog pDialog;

    class DownClick implements View.OnClickListener{
        private int index;

        public DownClick(int index) {
            this.index = index;
        }
        @Override
        public void onClick(View view) {
            if(TextUtils.isEmpty(mList.get(index).getDownLoadUrl())||(mList.get(index).getDownLoadUrl()).equals("")){
                T.shortToast(mContext,"没有下载地址，请联系商铺。");
                return;
            }
            pDialog=new ProgressDialog(mContext);
            pDialog.setMessage("加载中...");
            pDialog.show();

            //判断外部存储是否可以读写
                String state = Environment.getExternalStorageState();
                if (!Environment.MEDIA_MOUNTED.equals(state)) {
                    handler.sendEmptyMessage(2);
                    pDialog.dismiss();
                    return;
                }
            Request request = new Request.Builder()
                    .url( MyUrl.Pic+mList.get(index).getDownLoadUrl())
                    .tag(this)
                    .build();
            Call call = HttpUtils.getInstance().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    T.shortToast(mContext,"亲，请检查网络");
                    pDialog.dismiss();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    SendRequest.saveFile(1,response, mList.get(index).getUrlName(), new SaveFileCallBack() {
                        @Override
                        public void myFileCallBack(int code) {
                            pDialog.dismiss();
                            if(code==0){
                                handler.sendEmptyMessage(0);
                            }else{
                                handler.sendEmptyMessage(1);
                            }
                        }
                    });
                }
            });

        }
    }


    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                //保存成功
                showDialog("保存成功，请在文件管理/学术圈oss文件夹中查看");
            }else if(msg.what==1){
                //保存失败
                showDialog("保存失败");
            }else if(msg.what==2){
                //当前存储卡不可用
                showDialog("当前存储卡不可用");
            }
        }
    };




    private void showDialog(String message){
        new AlertDialog(mContext)
                .builder().setMsg(message)
                .setTitle("提示")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .show();
    }



    /**
     * 评价
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
     * 取消检测
     */
    class  CancelClick implements View.OnClickListener{
        private int index;

        public CancelClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            new AlertDialog(mContext)
                    .builder().setMsg("确定取消检测？")
                    .setTitle("提示")
                    .setPositiveButton("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            SendRequest.CancelRepeat(mList.get(index).getMyServiceId_()+"", new mOkCallBack() {
                                @Override
                                public void onSuccess(String response) {
                                    if(response.contains("<html>")){
                                        T.shortToast(mContext,"服务器异常");
                                        return;
                                    }
                                    MyBasebean myBasebean= JSON.parseObject(response,MyBasebean.class);
                                    if(myBasebean.getResultCode()==0){
                                        mList.remove(index);
                                        //往activity发广播，更新数据
                                        sendBrocast();
//                                        notifyDataSetChanged();
                                    }
                                    T.shortToast(mContext,myBasebean.getMsg());
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
        TextView item_checkorder_studioname_tv;//工作室名称
        TextView item_checkorder_state_tv;//订单状态（待检测之类的）
        TextView item_checkorder_name_tv;//服务名称
        TextView item_checkorder_price_tv;//服务价格
        SimpleDraweeView item_checkorder_img;//工作室头像
        TextView item_checkorder_type_tv;//文件名称


        TextView item_checkorder_cancel_tv;//取消检测（可点击）
        TextView item_checkorder_evaluate_tv;//服务评价（可点击）
        TextView item_checkorder_time_tv;//检测同类文章需要时常20分钟（不可点击）
        TextView item_checkorder_report_tv;//下载报告（可点击）
        LinearLayout item_checkorder_shop;


        public MyViewHolder(View itemView) {

            super(itemView);
            item_checkorder_studioname_tv= (TextView) itemView.findViewById(R.id.item_checkorder_studioname_tv);
            item_checkorder_state_tv= (TextView) itemView.findViewById(R.id.item_checkorder_state_tv);
            item_checkorder_name_tv= (TextView) itemView.findViewById(R.id.item_checkorder_name_tv);

            item_checkorder_price_tv= (TextView) itemView.findViewById(R.id.item_checkorder_price_tv);
            item_checkorder_type_tv= (TextView) itemView.findViewById(R.id.item_checkorder_type_tv);
            item_checkorder_cancel_tv= (TextView) itemView.findViewById(R.id.item_checkorder_cancel_tv);

            item_checkorder_evaluate_tv= (TextView) itemView.findViewById(R.id.item_checkorder_evaluate_tv);
            item_checkorder_time_tv= (TextView) itemView.findViewById(R.id.item_checkorder_time_tv);
            item_checkorder_report_tv= (TextView) itemView.findViewById(R.id.item_checkorder_report_tv);

            item_checkorder_img= (SimpleDraweeView) itemView.findViewById(R.id.item_checkorder_img);
            item_checkorder_shop= (LinearLayout) itemView.findViewById(R.id.item_checkorder_shop);
        }
    }


    public void sendBrocast(){
        Intent intent = new Intent();
        intent.setAction("com.lql.getDataCheckOrder");
        mContext.sendBroadcast(intent);
    }
}
