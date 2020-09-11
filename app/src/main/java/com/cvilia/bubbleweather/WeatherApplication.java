package com.cvilia.bubbleweather;


import android.database.sqlite.SQLiteDatabase;

import com.cvilia.bubbleweather.base.BaseApplication;
import com.cvilia.bubbleweather.sql.DaoMaster;
import com.cvilia.bubbleweather.sql.DaoSession;
import com.cvilia.bubbleweather.utils.CopyDb2Local;

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
//        if (!MMKV.defaultMMKV().decodeBool(Constants.COPY_DB_ALREADY, false))
        CopyDb2Local.copy2localdb(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cities.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaosession = daoMaster.newSession();
    }

    public static DaoSession getDaoSessionInstance() {
        return mDaosession;
    }
}
