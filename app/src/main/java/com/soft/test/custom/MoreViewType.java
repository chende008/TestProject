package com.soft.test.custom;

/**
 * 类描述:加载更多数据异常View
 */
public enum MoreViewType {

    LOADING("正在加载中..."),
    RELOAD("数据加载失败，点击重试");

    private String text;

    MoreViewType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
