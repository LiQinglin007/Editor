package com.example.lql.editor.utils;

import java.text.DecimalFormat;

/**
 * Created by LQL on 2016/12/30.
 */

public class ToDouble {
    public static DecimalFormat df  ;
    public static String Str="";

    public static String  toDpuble(double price ){
        if(df==null){
            df   = new DecimalFormat("######0.00");
        }
        Str=df.format(price)+"";
        return  Str;
    }


}
