package com.soft.test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soft.test.R;
import com.soft.test.base.TBaseAdapter;
import com.soft.test.base.TBaseHolder;
import com.soft.test.model.TestData;

import java.util.List;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:53
 * Author: chende
 */

public class TestAdapter extends TBaseAdapter<TestData> {

    public TestAdapter(Context context, List<TestData> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TestData model = list.get(position);
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_test, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText("(^_^) 姓名：" + model.name + "=====年龄：" + model.age);
        return convertView;
    }

    class ViewHolder extends TBaseHolder {//ViewHolder

        TextView tv_name;

        public ViewHolder(View view) {
            tv_name = $(view, R.id.tv_name);
        }
    }

}
