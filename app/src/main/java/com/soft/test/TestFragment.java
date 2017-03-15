package com.soft.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.soft.test.base.BaseFragment;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:35
 * Author: chende
 */

public class TestFragment extends BaseFragment {

    private TextView tv_test;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentLayout(R.layout.frag_test);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViewAfterOnCreate() {
        tv_test = $(R.id.tv_test);
        registerOnClickListener(this, tv_test);//注册点击事件
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
