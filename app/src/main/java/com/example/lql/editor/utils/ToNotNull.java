package com.example.lql.editor.utils;

import android.text.TextUtils;

/**
 * Created by LQL on 2016/12/14.
 */

public class ToNotNull {

    public static String StringUtils(String str){
        str=str.trim();
        if(str==null||TextUtils.isEmpty(str)||str.equals("null")){
            str="";
        }
        return  str;
    }



    public static int IntUtils(int res){
        if(res+""==null||TextUtils.isEmpty(res+"")){
            res=0;
        }
        return  res;
    }




}
