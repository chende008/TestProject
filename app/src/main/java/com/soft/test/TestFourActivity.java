package com.soft.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.soft.test.adapter.TestRecycleAdapter;
import com.soft.test.base.BaseActivity;
import com.soft.test.model.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:57
 * Author: chende
 */

public class TestFourActivity extends BaseActivity implements Handler.Callback {

    private RecyclerView recyleView;
    private TestRecycleAdapter adapter;
    private List<TestData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.act_test_four);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViewAfterOnCreate() {
        recyleView = $(R.id.recyleView);
        backWithTitle("测试标题");
    }

    @Override
    public void initDataAfterOnCreate() {
        list.addAll(testData(0));
        adapter = new TestRecycleAdapter(mContext, list);
        recyleView.setLayoutManager(new LinearLayoutManager(mContext));
        recyleView.setAdapter(adapter);
        adapter.refreshData(list);
        new Handler(this).sendEmptyMessageDelayed(0, 2000);
        showToast("模拟网络加载数据中，请稍候...");
    }

    private ArrayList<TestData> testData(int index) {
        Random random = new Random();
        ArrayList arrayList = new ArrayList();
        for (int i = index; i < index + 10; i++) {
            arrayList.add(new TestData("No." + (i + 1), random.nextInt(100)));
        }
        return arrayList;
    }

    @Override
    public boolean handleMessage(Message msg) {
        showToast("数据加载成功");
        list.addAll(testData(list.size()));
        adapter.refreshData(list);
        return false;
    }

}
