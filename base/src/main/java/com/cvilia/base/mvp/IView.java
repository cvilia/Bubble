package com.cvilia.base.mvp;

public interface IView {
    /**
     * 加载
     */
    void loading();

    /**
     * 加载结束
     */
    void dismissLoading();
}
