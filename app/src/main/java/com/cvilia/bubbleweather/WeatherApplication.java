package com.cvilia.bubbleweather;


import com.cvilia.bubbleweather.base.BaseApplication;
import com.cvilia.bubbleweather.sql.DaoMaster;
import com.cvilia.bubbleweather.sql.DaoSession;

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

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this,"");

    }
}
