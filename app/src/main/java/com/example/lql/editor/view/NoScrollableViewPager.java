package com.example.lql.editor.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wind on 2016/1/19.
 */
public class NoScrollableViewPager extends ViewPager {
    public NoScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NoScrollableViewPager(Context context) {
        super(context);
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
