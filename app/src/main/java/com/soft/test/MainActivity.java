package com.soft.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.soft.test.base.BaseActivity;
import com.soft.test.utils.AnimUtils;

public class MainActivity extends BaseActivity {

    private long lastClickTime;
    private TextView tv_one;//1、BaseActivity 的基本封闭
    private TextView tv_two;//2、BaseFragment 的基本封装
    private TextView tv_three;//3、BaseAdapter 的基本封装
    private TextView tv_four;//4、BaseRecyleAdapter 的基本封装

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.act_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViewAfterOnCreate() {
        tv_one = $(R.id.tv_one);
        tv_two = $(R.id.tv_two);
        tv_three = $(R.id.tv_three);
        tv_four = $(R.id.tv_four);
    }

    @Override
    public void initDataAfterOnCreate() {
        registerOnClickListener(this, tv_one, tv_two, tv_three, tv_four);
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v;
        switch (v.getId()) {
            case R.id.tv_one://1、BaseActivity 的基本封装
                intent = new Intent(mContext, TestOneActivity.class);
                break;
            case R.id.tv_two://2、BaseFragment 的基本封装
                intent = new Intent(mContext, TestTwoActivity.class);
                break;
            case R.id.tv_three://3、BaseAdapter 的基本封装
                intent = new Intent(mContext, TestThreeActivity.class);
                break;
            case R.id.tv_four://4、BaseRecyleAdapter 的基本封装
                intent = new Intent(mContext, TestFourActivity.class);
                break;
        }
        intent.putExtra("data", textView.getText());
        AnimUtils.toLeftAnim(mContext, intent);
    }

    @Override
    public void onBackPressed() {
        if (lastClickTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            showToast("再按一次退出SimpleProject");
        }
        lastClickTime = System.currentTimeMillis();
    }
}
