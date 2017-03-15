package com.soft.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.soft.test.base.BaseActivity;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:11
 * Author: chende
 */

public class TestOneActivity extends BaseActivity {

    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.act_test_one);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViewAfterOnCreate() {
        tv_test = $(R.id.tv_test);
        registerOnClickListener(this, tv_test);//注册点击事件
        backWithTitle(intent.getStringExtra("data"));
    }

    @Override
    public void initDataAfterOnCreate() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test://点击按钮
                showToast(tv_test.getText().toString());
                break;
        }
        super.onClick(v);
    }
}
