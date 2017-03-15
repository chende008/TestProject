package com.soft.test.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:31
 * Author: chende
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected Context mContext;//上下文
    protected View contentView;//parentView
    private int layoutResId;//布局 id
    private Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        if (contentView == null) {
            contentView = inflater.inflate(layoutResId, container, false);
        }
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent != null) {
            parent.removeView(contentView);
        }
        initViewAfterOnCreate();
        initDataAfterOnCreate();
        return contentView;
    }

    public void setContentLayout(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    protected <T> T $(int id) {
        T view = (T) contentView.findViewById(id);
        return view;
    }

    protected void showToast(String str) {
        if (!TextUtils.isEmpty(str)) {
            toast.setText(str);
            toast.show();
        }
    }

    protected void showToast(int strId) {
        showToast(getString(strId));
    }

    protected void registerOnClickListener(BaseFragment fragment, View... views) {//注册点击事件
        for (View view : views) {
            view.setOnClickListener(fragment);
        }
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 用于从xml文件中inflate控件View
     */
    public abstract void initViewAfterOnCreate();

    /**
     * 给View填充数据
     */
    public abstract void initDataAfterOnCreate();

}
