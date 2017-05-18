package com.example.lql.editor.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.example.lql.editor.widget.pickerview.OptionsPopupWindow;
import com.example.lql.editor.widget.pickerview.TimePopupWindow;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 底部选择弹窗的工具类封装
 * Created by jax on 16/1/30.
 */
public class OnoptionsUtils {
    /**
     * 选择性别的弹窗部分
     */
    public static void showGardenSelect(Context context, View view, OptionsPopupWindow.OnOptionsSelectListener listener) {
        OptionsPopupWindow picker = new OptionsPopupWindow(context);
        final ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        picker.setPicker(list);
        picker.setOnoptionsSelectListener(listener);
        picker.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 选择生日
     * @param context
     * @param view
     * @param listener
     */
    public static void showDateSelect(Context context, View view, TimePopupWindow.OnTimeSelectListener listener) {
        TimePopupWindow pop = new TimePopupWindow(context, TimePopupWindow.Type.YEAR_MONTH_DAY);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        TimePopupWindow.setRange(1900,year);

        TimePopupWindow.setMonth(month+1);
        TimePopupWindow.setDay(day);


        pop.setOnTimeSelectListener(listener);
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 选择身高
     */
    public static void showheight(Context context, View view, OptionsPopupWindow.OnOptionsSelectListener listener) {
        OptionsPopupWindow picker = new OptionsPopupWindow(context);
        picker.setPicker(getData(0));
        picker.setSelectOptions(160);
        picker.setLabels("身高");
        picker.setOnoptionsSelectListener(listener);
        picker.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 选择体重
     */
    public static void showWeight(Context context, View view, OptionsPopupWindow.OnOptionsSelectListener listener) {
        OptionsPopupWindow picker = new OptionsPopupWindow(context);
        picker.setPicker(getData(1));
        picker.setSelectOptions(50);
        picker.setLabels("体重");
        picker.setOnoptionsSelectListener(listener);
        picker.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }


    /**
     * 选择体重
     */
    public static void showShencai(Context context, View view, OptionsPopupWindow.OnOptionsSelectListener listener) {
        OptionsPopupWindow picker = new OptionsPopupWindow(context);
        picker.setPicker(getData(2),getData(3),getData(4),getData(5),false);
        picker.setSelectOptions(1,90,70,80);
        picker.setLabels("罩杯","胸围","腰围","臀围");
        picker.setOnoptionsSelectListener(listener);
        picker.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * @return 获取数据
     */
    private static ArrayList<String> getData(int type){
        ArrayList<String> list = new ArrayList<>();
        if(type == 0){
            for (int i=0;i<230;i++){
                list.add(i + "cm");
            }
        }else if(type == 1){
            for (int i=0;i<150;i++){
                list.add(i + "kg");
            }
        }else if(type == 2){
            for (int i=0;i<8;i++){
                list.add(transforCup(i));
            }
        }else if(type == 3){
            for (int i=0;i<150;i++){
                list.add(i + "");
            }
        }else if(type == 4){
            for (int i=0;i<150;i++){
                list.add(i + "");
            }
        }else if(type == 5){
            for (int i=0;i<150;i++){
                list.add(i + "");
            }
        }
        return list;
    }


    /**
     * @param i
     * @return
     */
    private static String transforCup(int i){
        String cup = "";
        if (i==0){
            cup = "A";
        }else if(i==1){
            cup = "B";
        }else if(i==2){
            cup = "C";
        }else if(i==3){
            cup = "D";
        }else if(i==4){
            cup = "E";
        }else if(i==5){
            cup = "F";
        }else if(i==6){
            cup = "G";
        }else if(i==7){
            cup = "H";
        }
        return cup;
    }
}
