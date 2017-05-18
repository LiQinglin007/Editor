package com.example.lql.editor.widget.RecyclerView;

import android.support.v7.widget.RecyclerView;

/**
 * Created by LQL on 2016/12/9.
 */

public class IsBottom {
    public static  boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
}
