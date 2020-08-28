package com.cvilia.bubbleweather.base;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mmkv.MMKV;

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
        initMMKV();
    }

    /**
     * MMKV初始化
     */
    private void initMMKV() {
        String dir = MMKV.initialize(this);
        Log.d("BaseApplication",dir);
    }
}
