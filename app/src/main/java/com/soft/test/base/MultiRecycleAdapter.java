package com.soft.test.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.soft.test.custom.ErrorView;
import com.soft.test.custom.MoreView;
import com.soft.test.custom.MoreViewType;
import com.soft.test.enums.ErrorViewType;
import com.soft.test.interfaces.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import static com.soft.test.enums.ErrorViewType.LOADING;


/**
 * Desc:   RecyleView 适配器基类（多元）
 * Time:   2016-11-05 22:15
 * Author: chende
 */

public abstract class MultiRecycleAdapter<T, ItemHolder extends BaseViewHoder> extends RecyclerView.Adapter<BaseViewHoder> {

    private static final int ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1;
    private static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;
    private static final int ITEM_TYPE_HEADER = Integer.MAX_VALUE - 3;

    protected Context mContext;//通用上下文
    protected LayoutInflater inflater;
    protected List<T> list = new ArrayList<>();
    protected Toast toast;
    private ErrorView errorView;//数据异常View
    private MoreView moreView;//加载更多View
    private View headerView;//headerView
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean needLoadMore;//标记是否有更多数据
    private boolean canLoadData = true;//标记是否可以加载数据

    public MultiRecycleAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.toast = Toast.makeText(mContext, "", Toast.LENGTH_LONG);
        this.errorView = new ErrorView(mContext);
        this.errorView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.moreView = new MoreView(mContext);
        this.moreView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setEmptyView(LOADING);
    }

    @Override
    public BaseViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_EMPTY) {
            return new BaseViewHoder(errorView);
        }
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            return new BaseViewHoder(moreView);
        }
        if (viewType == ITEM_TYPE_HEADER) {
            return new BaseViewHoder(headerView);
        }
        return getHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHoder holder, int position) {
        if (isEmpty()) return;
        if (isHeaderViewPos(position)) return;
        if (isShowLoadMore(position)) {//可以加载更多数据
            if (mOnLoadMoreListener != null && canLoadData) {//避免多次回调，导致数据错乱
                canLoadData = false;
                mOnLoadMoreListener.onLoadMore();
            }
            return;
        }
        fillData((ItemHolder) holder, position - headerCount());
        canLoadData = true;
    }

    private boolean isHeaderViewPos(int position) {
        return position < headerCount();
    }

    private int headerCount() {
        return headerView == null ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        if (isEmpty()) return 1;
        return list.size() + (needLoadMore ? 1 : 0) + headerCount();
    }

    public boolean noData() {//获取List大小
        return list == null || list.size() == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        if (isHeaderViewPos(position)) {
            return ITEM_TYPE_HEADER;
        }
        return super.getItemViewType(position - headerCount());
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                @Override
                public int getSpanSize(int position) {
                    if (isEmpty() || isShowLoadMore(position) || isHeaderViewPos(position)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHoder holder) {
        super.onViewAttachedToWindow(holder);
        if (isEmpty() || isShowLoadMore(holder.getLayoutPosition()) || getItemViewType(holder.getLayoutPosition()) == ITEM_TYPE_HEADER) {
            ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) params).setFullSpan(true);
            }
        }
    }

    public boolean isEmpty() {
        return (list == null ? 0 : list.size() + headerCount()) == 0;
    }

    private boolean isShowLoadMore(int position) {
        return needLoadMore && (!isEmpty() && position >= list.size() + headerCount());
    }

    public void setEmptyView(ErrorViewType viewType) {//数据加载异常类别
        if (viewType != LOADING) {//默认置为 loading 故不需要清空数据
            list.clear();
        }
        switch (viewType) {
            case NET_EXCEPTION://网络异常
                errorView.toNetworkException();
                break;
            case LOADING://数据加载中
                errorView.toLoading();
                break;
            case LOAD_FAIL://加载失败
                errorView.toLoadFail();
                break;
            case NO_DATA://无数据
                errorView.toNoData();
                break;
        }
        notifyDataSetChanged();
    }

    public void setEmptyView(int resId) {
        errorView.setNoDataView(LayoutInflater.from(mContext).inflate(resId, null));
    }

    public void setFailView(int resId) {
        errorView.setLoadFailView(LayoutInflater.from(mContext).inflate(resId, null));
    }

    public void setMoreView(MoreViewType viewType) {//加载更多数据异常类别
        switch (viewType) {
            case LOADING://正在加载中...
                moreView.toLoading();
                break;
            case RELOAD://加载失败，点击重试
                moreView.toReload(mOnLoadMoreListener);
                break;
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
    }

    public void addHeaderView(View view) {//添加HeaderView
        this.headerView = view;
        this.headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        notifyDataSetChanged();
    }

    public void refreshData(List<T> list, boolean neenLoadMore) {
        this.list = list;
        this.needLoadMore = neenLoadMore;
        if (list == null || list.size() == 0) {//无数据
            setEmptyView(ErrorViewType.NO_DATA);
        }
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
