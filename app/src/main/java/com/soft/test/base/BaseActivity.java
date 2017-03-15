package com.soft.test.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soft.test.R;
import com.soft.test.utils.AnimUtils;


/**
 * Desc:   Activity基类
 * Time:   2016-09-23 15:08
 * Author: chende
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected Context mContext;//通用上下文
    protected Intent intent;
    private Toast toast;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        intent = getIntent() == null ? new Intent() : getIntent();
        initViewAfterOnCreate();
        initDataAfterOnCreate();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            AnimUtils.toRightAnim(mContext);//左移动画关闭当前页面
        }
    }

    protected <T> T $(int id) {
        T view = (T) findViewById(id);
        return view;
    }

    protected void registerOnClickListener(BaseActivity mActivity, View... views) {//注册点击事件
        for (View view : views) {
            view.setOnClickListener(mActivity);
        }
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

    protected void backWithTitle(String text) {
        TextView tv = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(text) && tv != null) {
            tv.setText(text);
        }
        getLeftIV().setOnClickListener(this);
    }

    protected ImageView getLeftIV() {// 显示返回按钮并取得引用
        ImageView iv = (ImageView) findViewById(R.id.iv_left);
        if (iv != null) {
            iv.setVisibility(View.VISIBLE);
        }
        return iv;
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
