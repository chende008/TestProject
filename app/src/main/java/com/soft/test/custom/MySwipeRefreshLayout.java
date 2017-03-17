package com.soft.test.custom;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.soft.test.R;


/**
 * Desc:   用于处理显示与隐藏刷新状态View
 * Time:   2016-12-26 15:15
 * Author: chende
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeColors(getResources().getIntArray(R.array.refresh_colors));
    }

    public void showRefresh(final boolean isRefresh) {
        post(new Runnable() {

            @Override
            public void run() {
                setRefreshing(isRefresh);
            }
        });
    }
}
