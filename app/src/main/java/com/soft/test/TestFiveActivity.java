package com.soft.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.soft.test.adapter.TestMultiRecycleAdapter;
import com.soft.test.base.BaseActivity;
import com.soft.test.custom.MySwipeRefreshLayout;
import com.soft.test.enums.ErrorViewType;
import com.soft.test.interfaces.OnLoadMoreListener;
import com.soft.test.model.TestData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:57
 * Author: chende
 */

public class TestFiveActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    private MySwipeRefreshLayout refreshLayout;
    private RecyclerView recycleView;
    private TestMultiRecycleAdapter multiAdapter;
    private TextView tv_network;//网络异常
    private TextView tv_nodata;//暂无数据
    private TextView tv_load_fail;//加载失败
    private TextView tv_loading;//加载中
    private List<TestData> list = new ArrayList<>();
    private TextView[] textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.act_test_five);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViewAfterOnCreate() {
        refreshLayout = $(R.id.refreshLayout);
        recycleView = $(R.id.recycleview);
        tv_network = $(R.id.tv_network);
        tv_nodata = $(R.id.tv_nodata);
        tv_load_fail = $(R.id.tv_load_fail);
        tv_loading = $(R.id.tv_loading);
        textViews = new TextView[]{tv_network, tv_nodata, tv_load_fail, tv_loading};
        registerOnClickListener(this, tv_network, tv_nodata, tv_load_fail, tv_loading);
        backWithTitle("MultiRecycleAdapter");
    }

    @Override
    public void initDataAfterOnCreate() {
        multiAdapter = new TestMultiRecycleAdapter(mContext, list);
        recycleView.setLayoutManager(new LinearLayoutManager(mContext));
        recycleView.setAdapter(multiAdapter);
        refreshLayout.setOnRefreshListener(this);
        multiAdapter.setOnLoadMoreListener(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        refreshLayout.showRefresh(true);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                multiAdapter.refreshData(testData(0), true);
                refreshLayout.showRefresh(false);
            }
        }, 500);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                list.addAll(testData(list.size()));
                multiAdapter.refreshData(list, true);
                refreshLayout.showRefresh(false);
            }
        }, 500);
    }

    private ArrayList<TestData> testData(int index) {
        Random random = new Random();
        ArrayList arrayList = new ArrayList();
        for (int i = index; i < index + 25; i++) {
            arrayList.add(new TestData("No." + (i + 1), random.nextInt(100)));
        }
        return arrayList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_network://网络异常
                showCurrentView(tv_network);
                list.clear();
                multiAdapter.setEmptyView(ErrorViewType.NET_EXCEPTION);
                break;
            case R.id.tv_nodata://暂无数据
                showCurrentView(tv_nodata);
                list.clear();
                multiAdapter.setEmptyView(ErrorViewType.NO_DATA);
                break;
            case R.id.tv_load_fail://加载失败
                showCurrentView(tv_load_fail);
                list.clear();
                multiAdapter.setEmptyView(ErrorViewType.LOAD_FAIL);
                break;
            case R.id.tv_loading://加载中
                showCurrentView(tv_loading);
                list.clear();
                multiAdapter.setEmptyView(ErrorViewType.LOADING);
                onRefresh();
                break;
        }
        super.onClick(v);
    }

    private void showCurrentView(TextView currentView) {
        for (TextView textView : textViews) {
            textView.setSelected(currentView == textView);
        }
    }

}
