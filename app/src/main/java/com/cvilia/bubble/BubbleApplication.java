package com.cvilia.bubble;


import com.cvilia.base.BaseApplication;
import com.cvilia.bubble.sql.DaoSession;
import com.qweather.sdk.view.HeConfig;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：描述
 */
public class BubbleApplication extends BaseApplication {

    private static DaoSession mDaosession;

    @Override
    public void onCreate() {
        super.onCreate();
        init();

    }

    private void init() {
        initGreenDao();
        initHeFeng();
    }

    private void initHeFeng() {
        HeConfig.init("HE1704281517141231", "5bded0f8760f4fcf9bba2ba5d164f108");
    }

    private void initGreenDao() {

    }

    public static DaoSession getDaoSessionInstance() {
        return mDaosession;
    }
}
