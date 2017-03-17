package com.soft.test.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.soft.test.R;


/**
 * Desc:   页面数据加载"异常"信息提示
 * Time:   2016-10-28 23:26
 * Author: chende
 */

public class ErrorView extends RelativeLayout {

    private RelativeLayout contentView;
    private View loading_view;
    private View network_view;
    private View no_data_view;
    private View load_fail_view;
    private View[] views;
    private Animation fadeIn, fadeOut;

    public ErrorView(Context context) {
        super(context);
        initData(null);
    }

    private void initData(AttributeSet attrs) {
        contentView = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.ptr_load_view, this);
        loading_view = contentView.findViewById(R.id.loading_view);
        network_view = contentView.findViewById(R.id.network_view);
        no_data_view = contentView.findViewById(R.id.no_data_view);
        load_fail_view = contentView.findViewById(R.id.load_fail_view);
        views = new View[]{loading_view, network_view, no_data_view, load_fail_view};
        fadeIn = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fast_fade_out);
    }


    public void toLoading() {
        for (View view : views) {
            if (view == loading_view) {
                view.setVisibility(VISIBLE);
            } else {
                view.setVisibility(GONE);
            }
        }
    }

    public void toNetworkException() {
        for (View view : views) {
            if (view == network_view) {
                view.setVisibility(VISIBLE);
            } else {
                view.setVisibility(GONE);
            }
        }
    }

    public void toNoData() {
        for (View view : views) {
            if (view == no_data_view) {
                view.setVisibility(VISIBLE);
            } else {
                view.setVisibility(GONE);
            }
        }
    }

    public void toLoadFail() {
        for (View view : views) {
            if (view == load_fail_view) {
                view.setVisibility(VISIBLE);
            } else {
                view.setVisibility(GONE);
            }
        }
    }

    public void toLoadSuccess() {
        for (final View view : views) {
            if (view.getVisibility() == VISIBLE) {
                view.startAnimation(fadeOut);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setVisibility(GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }

    public void setNoDataView(View noDataView) {
        contentView.removeView(no_data_view);
        no_data_view = noDataView;
        views[2] = no_data_view;
        contentView.addView(no_data_view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        no_data_view.setVisibility(GONE);
    }

    public void setLoadFailView(View failView) {
        contentView.removeView(load_fail_view);
        load_fail_view = failView;
        views[3] = load_fail_view;
        contentView.addView(load_fail_view, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        load_fail_view.setVisibility(GONE);
    }
}
