package com.soft.test.base;

import android.view.View;

/**
 * Desc:   ViewHolder基类
 * Time:   2016-10-24 19:38
 * Author: chende
 */

public class TBaseHolder {

    @SuppressWarnings({"unchecked"})
    protected <T> T $(View view, int id) {
        T subView = (T) view.findViewById(id);
        return subView;
    }
}
