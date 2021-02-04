package com.cvilia.bubble;


import com.cvilia.bubble.base.BaseApplication;
import com.cvilia.bubble.sql.DaoSession;

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
    }

    private void initGreenDao() {

    }

    public static DaoSession getDaoSessionInstance() {
        return mDaosession;
    }
}
