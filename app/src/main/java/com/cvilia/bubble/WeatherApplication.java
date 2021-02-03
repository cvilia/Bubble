package com.cvilia.bubble;


import com.cvilia.bubble.base.BaseApplication;
import com.cvilia.bubble.sql.DaoSession;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：描述
 */
public class WeatherApplication extends BaseApplication {

    private static DaoSession mDaosession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {

    }

    public static DaoSession getDaoSessionInstance() {
        return mDaosession;
    }
}