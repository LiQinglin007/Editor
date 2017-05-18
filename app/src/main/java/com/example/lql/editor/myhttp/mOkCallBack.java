package com.example.lql.editor.myhttp;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by LQL on 2016/12/1.
 */

public abstract class mOkCallBack implements Callback {
    Handler handler = new Handler();
    @Override
    public void onFailure(Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailure(e);
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String str = response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(str);
            }
        });
    }


    public abstract void onSuccess(String response);

    public abstract void onFailure(Throwable e);

}
