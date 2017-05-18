package com.example.lql.editor.utils;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * 短信验证码倒计时
 */

public class CountDownUtils {
    public static boolean run = false;
    public static int time = 60;//剩余时间
    static TextView codetv;
    static TimeThread mTimeThread;

    public static void startTime(TextView codetv, boolean runing) {
        mTimeThread = new TimeThread();
        CountDownUtils.codetv = codetv;
        run = runing;
        mTimeThread.start();
        handler.sendEmptyMessage(0);
    }

    static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (run) {
                if (time > 0) {
                    run = true;
                    time--;
                    codetv.setText("剩余" + time + "秒");
                    codetv.setClickable(false);
                } else {
                    run = false;
                    codetv.setText("获取验证码");
                    codetv.setClickable(true);
                    time = 60;
                }
            } else {
                if (time > 0 && time < 60) {
                    codetv.setText("剩余" + time + "秒");
                    codetv.setClickable(false);
                    run = true;
                } else {
                    codetv.setText("获取验证码");
                    codetv.setClickable(true);
                    time = 60;
                }
            }
            super.handleMessage(msg);
        }
    };


    static class TimeThread extends Thread {
        @Override
        public void run() {
            while (run) {
                try {
                    sleep(1000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            super.run();
        }
    }


}
