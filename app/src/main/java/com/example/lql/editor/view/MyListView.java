package com.example.lql.editor.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/*
* 文件名：ListView
* 描    述：自定义ListView   解决嵌套问题
* 作    者：liqinglin
* 时    间：2016.8.16
* 版    权：
*/
public class MyListView extends ListView {
	
	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
