package com.example.lql.editor.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.lql.editor.R;

/**
 * Created by LQL on 2016/11/30.
 */

public class DialogUtils {
    static Dialog dialog;

    /**
     * @param mContext        上下文
     * @param mDialogCallBack 回调
     * @param type            类型  1：降重   2：投稿   3：支付
     *                        速审订单：5：初审付款   6：复审付款
     */
    public static void showMyDialog(Context mContext, final DialogCallBack mDialogCallBack, int type) {
//        if(dialog==null){
        dialog = new Dialog(mContext, R.style.myTheme_dialog);
//        }
        final View view = LayoutInflater.from(mContext).inflate(
                R.layout.dialog_submit_success, null);
        dialog.setContentView(view);
        TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView tv1 = (TextView) dialog.findViewById(R.id.dialog_tv1);
        TextView tv2 = (TextView) dialog.findViewById(R.id.dialog_tv2);
        TextView submit = (TextView) dialog.findViewById(R.id.dialog_submit);

        if (type == 1) {
            tv2.setVisibility(View.GONE);
            title.setText("提交成功");
            tv1.setText("请等待小编反馈通知");
        } else if (type == 2) {
            tv2.setVisibility(View.GONE);
            title.setText("提交成功");
            tv1.setText("投稿进度可在个人中心速审订单中查看");
        } else if (type == 3) {
            tv2.setVisibility(View.VISIBLE);
            title.setText("支付成功");
            tv1.setText("请耐心等待检测结果");
            tv2.setText("可在个人中心查重订单中查看检测进度");
        } else if (type == 5) {
            tv2.setVisibility(View.VISIBLE);
            title.setText("支付成功");
            tv1.setText("请耐心等待复审结果");
            tv2.setText("可在个人中心速审订单中查看检测进度");
        } else if (type == 6) {
            tv2.setVisibility(View.VISIBLE);
            title.setText("支付成功");
            tv1.setText("请耐心等待安排查稿");
            tv2.setText("可在个人中心速审订单中查看检测进度");
        } else {
            tv2.setVisibility(View.GONE);
            title.setText("支付成功");
            tv1.setText("可在个人中心降重订单中查看进度");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogCallBack.callback();
                dialog.dismiss();
            }
        });
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
//                lp.alpha = 0.9f; // 透明度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (ScreenUtils.getScreenHeight(mContext) * 0.35); // 高度设置为屏幕的0.6
        p.width = (int) (ScreenUtils.getScreenHeight(mContext) * 0.5); // 宽度设置为屏幕的0.65
        p.alpha = 0.9f; // 透明度
        dialogWindow.setAttributes(p);
        dialog.show();
    }


//    您的**元提现申请已提交，相应余额已冻结，如您已实名认证，此次提现将于2-48小时到账，请注意查询支付宝到账情况。未实名认证的用户，冻结金额2-48小时会自动返还账户余额



    public static void showMyDialogTX(Context mContext,String content, final DialogCallBack mDialogCallBack) {
        dialog = new Dialog(mContext, R.style.myTheme_dialog);
        final View view = LayoutInflater.from(mContext).inflate(
                R.layout.dialog_submit_tixian, null);
        dialog.setContentView(view);
        TextView tv1 = (TextView) dialog.findViewById(R.id.dialog_tv1);
        TextView submit = (TextView) dialog.findViewById(R.id.dialog_submit);
        tv1.setText("您的"+content+"元提现申请已提交，相应余额已冻结，如您已实名认证，此次提现将于2-48小时到账，请注意查询支付宝到账情况。未实名认证的用户，冻结金额2-48小时会自动返还账户余额\n");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogCallBack.callback();
                dialog.dismiss();
            }
        });
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (ScreenUtils.getScreenHeight(mContext) * 0.42); // 高度设置为屏幕的0.6
        p.width = (int) (ScreenUtils.getScreenHeight(mContext) * 0.5); // 宽度设置为屏幕的0.65
        p.alpha = 0.9f; // 透明度
        dialogWindow.setAttributes(p);
        dialog.show();
    }



    public interface DialogCallBack {
        public void callback();
    }

}
