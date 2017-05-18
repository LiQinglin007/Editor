package com.example.lql.editor.application;

import android.app.Activity;
import android.app.Application;

import com.example.lql.editor.myhttp.HttpUtils;
import com.example.lql.editor.utils.PublicStaticData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by LQL on 2016/10/11.
 */

public class MyApplication extends Application {
    private static MyApplication mContext;

    public synchronized static MyApplication getInstance() {
        if (mContext == null) {
            mContext = new MyApplication();
        }
        return mContext;
    }



    @Override
    public void onCreate() {
        mContext = this;




        PublicStaticData.mySharedPreferences = getSharedPreferences("Editor_lql", Activity.MODE_PRIVATE);//初始化PreferencesUtils
        PublicStaticData.myShareSDK= new ShareSDK();
        PublicStaticData.myShareSDK.initSDK(getApplicationContext());



        okhttp3.OkHttpClient okHttpClient = HttpUtils.getInstance();
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(mContext, okHttpClient)
        .build();
        Fresco.initialize(mContext, config);

        //设置Picasso
        Picasso.setSingletonInstance(new Picasso.Builder(mContext).
                downloader(new OkHttpDownloader(getUnsafeOkHttpClient()))
                .build());

        super.onCreate();
    }



    public static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;

                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
