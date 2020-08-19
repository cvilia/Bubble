package com.cvilia.bubbleweather.utils;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：描述
 */
public class RxUtil {

    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
