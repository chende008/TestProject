package com.soft.test;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.soft.test.base.BaseActivity;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:11
 * Author: chende
 */

public class TestTwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.act_test_two);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViewAfterOnCreate() {
        backWithTitle("BaseFragment");
    }

    @Override
    public void initDataAfterOnCreate() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new TestFragment()).commitAllowingStateLoss();
    }
}
