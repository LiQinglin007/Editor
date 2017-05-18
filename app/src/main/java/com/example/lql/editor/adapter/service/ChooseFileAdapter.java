package com.example.lql.editor.adapter.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.activity.service.ChooseFileActivity;
import com.example.lql.editor.bean.ChooseFileBean;
import com.example.lql.editor.utils.PublicStaticData;

import java.util.ArrayList;

/**
 * Created by LQL on 2016/11/30.
 */

public class ChooseFileAdapter extends BaseAdapter {
    private ArrayList<ChooseFileBean.DataBean> mList;
    private Context mContext;
//     ArrayList<ImageView> mViews=new ArrayList<>();

    public ChooseFileAdapter(ArrayList<ChooseFileBean.DataBean> list, Context context) {

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


        view= LayoutInflater.from(mContext).inflate(R.layout.item_choose_file,null);
        TextView item_choosefile_articlename= (TextView) view.findViewById(R.id.item_choosefile_articlename);
        ImageView item_choosefile_articlestate= (ImageView) view.findViewById(R.id.item_choosefile_articlestate);
        LinearLayout  linearLayout= (LinearLayout) view.findViewById(R.id.linear_layout);
        linearLayout.setOnClickListener(new MyClick(i));
//        mViews.add(item_choosefile_articlestate);
        if(mList.get(i).getTag().equals("0")){
            item_choosefile_articlestate.setImageResource(R.drawable.btn_choosefile_nor);
        }else{
            item_choosefile_articlestate.setImageResource(R.drawable.btn_choosefile_sel);
        }
        item_choosefile_articlename.setText(mList.get(i).getName());
        return view;
    }




    class MyClick implements View.OnClickListener{
        private int index;

        public MyClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            PublicStaticData.IsFile=true;
            ChooseFileActivity.choose++;
            PublicStaticData.chooseFileBean=mList.get(index);
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setTag("0");
            }
            mList.get(index).setTag("1");
            notifyDataSetChanged();
        }
    }
}
