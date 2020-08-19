package com.cvilia.bubbleweather;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：描述
 */
public interface IPresenter<T extends IView> {

    void attachView(T view);

    void detachView();
}
