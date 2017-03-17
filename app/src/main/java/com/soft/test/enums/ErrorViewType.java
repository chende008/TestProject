package com.soft.test.enums;

/**
 * 类描述:数据异常View
 */
public enum ErrorViewType {

    NET_EXCEPTION("网络异常"),
    LOADING("数据加载中"),
    LOAD_FAIL("加载失败"),
    NO_DATA("没有数据");

    private String text;

    ErrorViewType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
