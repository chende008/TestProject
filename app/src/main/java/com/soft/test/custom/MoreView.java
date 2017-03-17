package com.soft.test.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.soft.test.R;
import com.soft.test.interfaces.OnLoadMoreListener;
import com.soft.test.utils.Utils;


/**
 * Desc:   加载更多View
 * Time:   2016-12-26 13:27
 * Author: chende
 */

public class MoreView extends LinearLayout {

    private View contentView;
    private View view_loading;
    private View view_reload;
    private OnLoadMoreListener listener;

    public MoreView(Context context) {
        super(context);
        initData(null);
    }

    private void initData(AttributeSet attrs) {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.ptr_load_more, this);
        view_loading = contentView.findViewById(R.id.view_loading);
        view_reload = contentView.findViewById(R.id.view_reload);
        view_reload.setOnClickListener(new OnClickListener() {//加载更多事件

            @Override
            public void onClick(View v) {
                if (Utils.isFastClick(v)) {//防止重复点击
                    return;
                }
                if (listener != null) {
                    toLoading();
                    new Handler() {//200毫秒之后调用加载更多

                        @Override
                        public void handleMessage(Message msg) {
                            listener.onLoadMore();
                            super.handleMessage(msg);
                        }
                    }.sendEmptyMessageDelayed(0, 200);
                }
            }
        });
    }

    public void toReload(OnLoadMoreListener mOnLoadMoreListener) {//点击重新加载
        this.listener = mOnLoadMoreListener;
        view_loading.setVisibility(GONE);
        view_reload.setVisibility(VISIBLE);
    }

    public void toLoading() {//正在加载中
        view_loading.setVisibility(VISIBLE);
        view_reload.setVisibility(GONE);
    }

}
