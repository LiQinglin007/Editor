package com.example.lql.editor.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/*
* 文件名：MyGridView
* 描    述：自定义gridview   解决嵌套问题
* 作    者：liqinglin
* 时    间：2016.9.27
* 版    权：
*/
public class MyGridView extends GridView {

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView(Context context) {
		super(context);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
