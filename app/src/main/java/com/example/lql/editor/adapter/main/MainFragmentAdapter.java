package com.example.lql.editor.adapter.main;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lql.editor.R;
import com.example.lql.editor.bean.MainGetService;
import com.example.lql.editor.myhttp.MyUrl;
import com.example.lql.editor.utils.ToDouble;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import static com.example.lql.editor.R.id.item_main_img;
import static com.example.lql.editor.R.id.item_main_name_tv;

/**
 * Created by LQL on 2016/11/24.
 */

public class MainFragmentAdapter  extends BaseAdapter {
    private ArrayList<MainGetService.DataBean> mList;
    private Context mContext;

    public MainFragmentAdapter(ArrayList<MainGetService.DataBean> list, Context context) {
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
        MainFragmentAdapter.MyViewHolder mMyViewHolder;
        if(view==null){
            mMyViewHolder=new MainFragmentAdapter.MyViewHolder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_main_fragment,null);
            mMyViewHolder.item_main_name_tv= (TextView) view.findViewById(item_main_name_tv);
            mMyViewHolder.item_main_type_tv= (TextView) view.findViewById(R.id.item_main_type_tv);
            mMyViewHolder.item_main_price_tv= (TextView) view.findViewById(R.id.item_main_price_tv);
            mMyViewHolder.item_main_priceold_tv= (TextView) view.findViewById(R.id.item_main_priceold_tv);

            mMyViewHolder.item_main_number_tv= (TextView) view.findViewById(R.id.item_main_number_tv);
            mMyViewHolder.item_main_img= (SimpleDraweeView) view.findViewById(item_main_img);
            view.setTag(mMyViewHolder);
        }else{
            mMyViewHolder= (MainFragmentAdapter.MyViewHolder) view.getTag();
        }


//        String ServicePrice=mList.get(i).getServicePrice()




        mMyViewHolder.item_main_name_tv.setText(mList.get(i).getServiceName()+"");
//        Type1:检测查重 2：修改降重3：编辑速审
        if(mList.get(i).getType()==1){
            mMyViewHolder.item_main_type_tv.setText("检测查重");
            //原价
            mMyViewHolder.item_main_priceold_tv.setText("￥"+ToDouble.toDpuble(mList.get(i).getOriginalCost()));
            mMyViewHolder.item_main_priceold_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            if(mList.get(i).getServicePrice().contains("-")||mList.get(i).getServicePrice().contains("~")){

            }else {
                if (Double.parseDouble(mList.get(i).getServicePrice()) == mList.get(i).getOriginalCost() || mList.get(i).getOriginalCost() == 0) {
                    mMyViewHolder.item_main_priceold_tv.setVisibility(View.GONE);
                } else {
                    mMyViewHolder.item_main_priceold_tv.setVisibility(View.VISIBLE);
                }
            }
        }else if(mList.get(i).getType()==2){
            mMyViewHolder.item_main_type_tv.setText("修改降重");
            //原价
            mMyViewHolder.item_main_priceold_tv.setText("￥"+ ToDouble.toDpuble(mList.get(i).getOriginalCost()));
            mMyViewHolder.item_main_priceold_tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线

            if(mList.get(i).getServicePrice().contains("-")||mList.get(i).getServicePrice().contains("~")){

            }else {
                if (mList.get(i).getServicePrice().equals("" + mList.get(i).getOriginalCost()) || mList.get(i).getOriginalCost() == 0) {
                    mMyViewHolder.item_main_priceold_tv.setVisibility(View.GONE);
                } else {
                    mMyViewHolder.item_main_priceold_tv.setVisibility(View.VISIBLE);
                }
            }



        }else if(mList.get(i).getType()==3){
            mMyViewHolder.item_main_type_tv.setText("编辑速审");
            mMyViewHolder.item_main_priceold_tv.setVisibility(View.INVISIBLE);
        }
        //现在的价格
        mMyViewHolder.item_main_price_tv.setText("￥"+mList.get(i).getServicePrice());
        //月销量
        mMyViewHolder.item_main_number_tv.setText("月销量："+mList.get(i).getCount());
        //设置图片
        Uri uri = Uri.parse(MyUrl.Pic+mList.get(i).getPicture());
//        Uri uri = Uri.parse("https://www.baidu.com/img/bd_logo1.png");

        mMyViewHolder.item_main_img.setImageURI(uri);
        return view;
    }


    class MyViewHolder{
        TextView item_main_name_tv;//名称
        TextView item_main_type_tv;//服务类型检测查重
        TextView item_main_price_tv;//现在的价格
        TextView item_main_number_tv;//月销量
        TextView item_main_priceold_tv;//原来的价格
        SimpleDraweeView item_main_img;
    }
}