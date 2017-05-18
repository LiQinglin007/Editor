package com.example.lql.editor.myhttp;

import android.content.Context;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by LQL on 2016/12/1.
 */

public class OkHttpUtils {
    public static void MyPost(RequestBody formBody, String Url, mOkCallBack myCallBack) {
        //设置缓存时间为60秒
//        CacheControl cacheControl = new CacheControl.Builder()
//                .maxAge(1, TimeUnit.SECONDS)
//                .build();
        Request request = new Request.Builder()
                .url(Url)
                .post(formBody)
//                .cacheControl(cacheControl)
                .build();
        Call call = HttpUtils.getInstance().newCall(request);
        call.enqueue(myCallBack);
    }


    public static void DownLoadUtils(String Url, Context mContext,mOkCallBack mOkCallBack){
        Request request = new Request.Builder()
                .url(Url)
                .tag(mContext)
                .build();
        Call call = HttpUtils.getInstance().newCall(request);
        call.enqueue(mOkCallBack);
    }


    public static void FilePost( RequestBody formBody,String Url,mOkCallBack mOkCallBack){
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + "...")
                .url(Url)
                .post(formBody)
                .build();
        Call call = HttpUtils.getInstance().newCall(request);
        call.enqueue(mOkCallBack);
//        mOkHttpClient.newCall(request).enqueue(mOkCallBack);
    }






}
