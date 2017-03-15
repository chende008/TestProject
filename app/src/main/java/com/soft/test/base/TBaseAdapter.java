package com.soft.test.base;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:   ???
 * Time:   2017-03-15 10:52
 * Author: chende
 */

public abstract class TBaseAdapter<E> extends android.widget.BaseAdapter {

    protected Context mContext;//通用上下文
    private Toast toast;
    protected LayoutInflater inflater;
    protected List<E> list = new ArrayList<E>();

    public TBaseAdapter(Context context, List<E> list) {
        this.mContext = context;
        if (list != null && list.size() > 0) {
            this.list = list;
        }
        this.toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected void toShow(String str) {
        if (!TextUtils.isEmpty(str)) {
            toast.setText(str);
            toast.show();
        }
    }

    protected void toShow(int strId) {
        toShow(mContext.getString(strId));
    }

    public void refreshData(List<E> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addData(List<E> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
