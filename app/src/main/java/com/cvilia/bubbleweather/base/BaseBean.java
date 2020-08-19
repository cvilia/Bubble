package com.cvilia.bubbleweather.base;

/**
 * author: lzy
 * date: 2020/8/19
 * describe：描述
 */
public class BaseBean<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
