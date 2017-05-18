package com.example.lql.editor.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class T {

	public static void shortToast(Context mContext, String text)
	{
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}
	public static void longToast(Context mContext, String text)
	{
		Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
	}
	private static ProgressDialog pDialog;
	public static void showMyDialog(Context context, String message) {
		if(pDialog==null){
			pDialog = new ProgressDialog(context);
		}
		pDialog.setTitle("提示");
		pDialog.setMessage(message);
		pDialog.show();
	}
	
	public static void dissDialog() {
		try {
			if (pDialog != null) {
				pDialog.dismiss();
			}
		} catch (Exception e) {
		}
	}


}
