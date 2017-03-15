package com.soft.test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soft.test.R;
import com.soft.test.base.BaseRecyleAdapter;
import com.soft.test.base.BaseViewHoder;
import com.soft.test.model.TestData;

import java.util.List;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:53
 * Author: chende
 */

public class TestRecycleAdapter extends BaseRecyleAdapter<TestData, TestRecycleAdapter.ViewHolder> {

    public TestRecycleAdapter(Context context, List<TestData> list) {
        super(context, list);
    }

    @Override
    public ViewHolder getHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void fillData(ViewHolder holder, int position) {
        TestData model = list.get(position);
        holder.tv_name.setText("(^_^) 姓名：" + model.name + "=====年龄：" + model.age);
    }

    class ViewHolder extends BaseViewHoder {//ViewHolder

        TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = $(R.id.tv_name);
        }
    }
}
