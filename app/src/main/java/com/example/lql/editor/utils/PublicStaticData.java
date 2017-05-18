package com.example.lql.editor.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.lql.editor.bean.ChooseFileBean;
import com.example.lql.editor.bean.ChooseSubjectBean;
import com.example.lql.editor.bean.NoticeBean;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;


/**
 * Created by LQL on 2016/10/11.
 */

public class PublicStaticData {
    public static ShareSDK myShareSDK;
    public static SharedPreferences mySharedPreferences;
    public static ArrayList<Activity> mActivityList=new ArrayList<>();//
    public static int MainFragmentNmuber=0;//
    public static int PopNumber=0;
    public static int PopNumberTitle=1;
    public static String ChooseType="";//我要投稿 选择类型
    public static String serviceType="";//我要投稿  查重   降重
    public static ChooseSubjectBean.DataBean chooseSubjectId=null;//选中的那个   学科方向，职称，学历
    public static ChooseFileBean.DataBean chooseFileBean=null;//选中的那个   学科方向，职称，学历
    public static boolean IsFile=false;//是不是选择文件
    public static String ServiceId="";//服务id
    public static  String Shopid="";//工作室编号    投稿时候
    public static  String Productid="";//服务编号   投稿时候
    public static  String ServiceFee="";//服务价格(单价)  投稿时候
    public static  String ServiceFeeSum="";//服务价格(总价)  投稿时候
    public static String serviceId="";//需要支付的时候有一个id
    public static String StudioId="";//工作室id
    public static String OrderId="";//订单id
    public static String PayType="1";//支付类型
    public static String  Ordertype="";//  0:查重  1：降重  2：速审
    public static int LoginType=0;//第三方登录方式    1:QQ 2：微信 3：微博
    public static  String openId="";
    public static NoticeBean.DataBean myNotice=null;
    public static String Name="";
    public static boolean UpdataApp=false;//是不是主动更新
    public static String ShopName="";//商店名称
    public static boolean toMain=false;//在登录页面是不是跳转到首页
    public static int ServiceType=1;//
    public static int Sum=-1;//速审订单：0：初审付款   1：复审付款
}
