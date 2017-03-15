package com.soft.test.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Desc:   RecyleView BaseHolder
 * Time:   2016-11-05 23:53
 * Author: chende
 */

public class BaseViewHoder extends RecyclerView.ViewHolder {

    private View contentView;

    public BaseViewHoder(View itemView) {
        super(itemView);
        this.contentView = itemView;
    }

    protected <T> T $(int id) {
        T subView = (T) contentView.findViewById(id);
        return subView;
    }
}
