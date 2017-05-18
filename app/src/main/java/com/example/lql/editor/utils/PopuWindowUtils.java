package com.example.lql.editor.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.lql.editor.R;

/**
 * Created by LQL on 2016/11/29.
 */

public class PopuWindowUtils {
   static TextView tv1,tv2,tv3,tv4;
    static ImageView img1,img2,img3,img4;
    static LinearLayout ly1,ly2,ly3,ly4;
    static Context mContext;
    // 创建一个PopuWidow对象
    static PopupWindow popWindow ;





    /**
     * 在底部显示PopuWindow
     *
     * @param parent 点击哪个控件显示
     */
    public static void showSinglePopupTitle(final Context mContext, View parent, final SingePopwCallback mSingePopwCallback) {
        PopuWindowUtils.mContext=mContext;
        LayoutInflater layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_popuwindow_title, null);

        popWindow=new PopupWindow(view);
        popWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        ly1=(LinearLayout)view.findViewById(R.id.pop_title_ly1);
        ly2=(LinearLayout)view.findViewById(R.id.pop_title_ly2);
        ly3=(LinearLayout)view.findViewById(R.id.pop_title_ly3);

        ly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSingePopwCallback.SingePopCallback(1);
                popWindow.dismiss();
            }
        });

        ly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSingePopwCallback.SingePopCallback(2);
                popWindow.dismiss();
            }
        });


        ly3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSingePopwCallback.SingePopCallback(3);
                popWindow.dismiss();
            }
        });



        // 使其聚集 ，要想监听菜单里控件的事件就必须要调用此方法
        popWindow.setFocusable(true);
        // 设置允许在外点击消失
        popWindow.setOutsideTouchable(false);
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // 软键盘不会挡着popupwindow
        popWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 设置菜单显示的位置
//        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        popWindow.showAsDropDown(parent);//在哪个控件的下方显示
    }






    /**
     * 在底部显示PopuWindow
     *
     * @param parent 点击哪个控件显示
     */
    public static void showSinglePopup(final Context mContext, View parent, final SingePopwCallback mSingePopwCallback) {
        PopuWindowUtils.mContext=mContext;
        LayoutInflater layoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_popuwindow, null);

//        popWindow= new PopupWindow(view, LinearLayout.LayoutParams.FILL_PARENT, 900, true);
//        popWindow= new PopupWindow(view, 2500, 900, true);
        popWindow=new PopupWindow(view);
        popWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

         tv1= (TextView) view.findViewById(R.id.pop_text1);
         img1= (ImageView) view.findViewById(R.id.pop_img1);
         ly1= (LinearLayout) view.findViewById(R.id.pop_ly1);

         tv2= (TextView) view.findViewById(R.id.pop_text2);
         img2= (ImageView) view.findViewById(R.id.pop_img2);
         ly2= (LinearLayout) view.findViewById(R.id.pop_ly2);


         tv3= (TextView) view.findViewById(R.id.pop_text3);
         img3= (ImageView) view.findViewById(R.id.pop_img3);
         ly3= (LinearLayout) view.findViewById(R.id.pop_ly3);


         tv4= (TextView) view.findViewById(R.id.pop_text4);
         img4= (ImageView) view.findViewById(R.id.pop_img4);
         ly4= (LinearLayout) view.findViewById(R.id.pop_ly4);

        if(PublicStaticData.PopNumber==1){
            tv1.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
            img1.setVisibility(View.VISIBLE);
        }else if(PublicStaticData.PopNumber==2){
            tv2.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
            img2.setVisibility(View.VISIBLE);
        }else if(PublicStaticData.PopNumber==3){
            tv3.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
            img3.setVisibility(View.VISIBLE);
        } else if(PublicStaticData.PopNumber==4){
            tv4.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
            img4.setVisibility(View.VISIBLE);
        }


        ly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setview();
                tv1.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
                img1.setVisibility(View.VISIBLE);
                mSingePopwCallback.SingePopCallback(1);
                popWindow.dismiss();
            }
        });

        ly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setview();
                tv2.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
                img2.setVisibility(View.VISIBLE);
                mSingePopwCallback.SingePopCallback(2);
                popWindow.dismiss();
            }
        });


        ly3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setview();
                tv3.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
                img3.setVisibility(View.VISIBLE);
                mSingePopwCallback.SingePopCallback(3);
                popWindow.dismiss();
            }
        });


        ly4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setview();
                tv4.setTextColor(mContext.getResources().getColor(R.color.color_1AB394));
                img4.setVisibility(View.VISIBLE);
                mSingePopwCallback.SingePopCallback(4);
                popWindow.dismiss();
            }
        });

        // 使其聚集 ，要想监听菜单里控件的事件就必须要调用此方法
        popWindow.setFocusable(true);
        // 设置允许在外点击消失
        popWindow.setOutsideTouchable(false);
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        // 软键盘不会挡着popupwindow
        popWindow
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 设置菜单显示的位置
//        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        popWindow.showAsDropDown(parent);//在哪个控件的下方显示
    }


    private static void setview(){

        tv1.setTextColor(mContext.getResources().getColor(R.color.color_8E8E8E));
        tv2.setTextColor(mContext.getResources().getColor(R.color.color_8E8E8E));
        tv3.setTextColor(mContext.getResources().getColor(R.color.color_8E8E8E));
        tv4.setTextColor(mContext.getResources().getColor(R.color.color_8E8E8E));


        img1.setVisibility(View.INVISIBLE);
        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        img4.setVisibility(View.INVISIBLE);
    }




    public interface SingePopwCallback {
        public void SingePopCallback(int number);
    }





}
