package com.example.lql.editor.adapter.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.OrderDetailsBean;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/25.   订单详情里边的listview的适配器
 */

public class OrderdetailsAdapter extends BaseAdapter{
    private ArrayList<OrderDetailsBean>  mList;
    private Context mContext;

    public OrderdetailsAdapter(ArrayList<OrderDetailsBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(mContext).inflate(R.layout.item_orderdetails,null);
        TextView typename= (TextView) view.findViewById(R.id.item_orderdetails_typename_tv);
        TextView time= (TextView) view.findViewById(R.id.item_orderdetails_time_tv);

        typename.setText(mList.get(i).getServerName());
        time.setText(mList.get(i).getServiceTime());

        return view;
    }
}
