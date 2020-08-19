package com.cvilia.bubbleweather.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：描述
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
