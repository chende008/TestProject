package com.soft.test.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Desc:   RecyleView 适配器基类
 * Time:   2016-11-05 22:15
 * Author: chende
 */

public abstract class BaseRecyleAdapter<T, ItemHolder extends BaseViewHoder> extends RecyclerView.Adapter<BaseViewHoder> {

    protected Context mContext;//通用上下文
    protected LayoutInflater inflater;
    protected List<T> list = new ArrayList<>();
    protected Toast toast;

    public BaseRecyleAdapter(Context mContext, List<T> list) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.toast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

    @Override
    public BaseViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHoder holder, int position) {
        fillData((ItemHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void refreshData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    protected void toShow(String text) {
        toast.setText(text);
        toast.show();
    }

    protected void toShow(int resId) {
        toShow(mContext.getString(resId));
    }

    public abstract ItemHolder getHolder(ViewGroup parent, int viewType);

    public abstract void fillData(ItemHolder holder, int position);

}
