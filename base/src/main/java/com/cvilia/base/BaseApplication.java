package com.cvilia.base;

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

    public static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        if (BuildConfig.DEBUG) {// 这两行必须写在init之前，否则这些配置在init过程中将无效
            Log.d(BaseApplication.class.getSimpleName(),"debug模式");
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        initMMKV();
    }


    /**
     * MMKV初始化
     */
    private void initMMKV() {
        String dir = MMKV.initialize(this);
        Log.d("BaseApplication", dir);
    }
}
