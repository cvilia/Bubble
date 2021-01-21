package com.cvilia.bubbleweather.activity.selectcity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.cvilia.bubbleweather.base.BasePresenter;
import com.cvilia.bubbleweather.bean.City;
import com.cvilia.bubbleweather.sql.CityDao;
import com.cvilia.bubbleweather.sql.DaoMaster;
import com.cvilia.bubbleweather.sql.DaoSession;

import java.util.List;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：选择城市Presenter
 */
public class SelectCityPresenter extends BasePresenter<SelectCityContact.View> implements SelectCityContact.Presenter {
    @Override
    public void readDb() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper((Activity) mView, "weatherDb.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession mDaosession = daoMaster.newSession();
        CityDao dao = mDaosession.getCityDao();
        List<City> cities = dao.loadAll();
        if (cities != null)
            mView.readDbSuccess(cities);
    }

    @Override
    public void startLocate() {
        AMapLocationClient client = new AMapLocationClient((Activity)mView);
        client.setLocationListener(aMapLocation -> {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mView.locateSuccess(aMapLocation);
            } else {
                mView.locateFailed();
            }
        });
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        option.setOnceLocation(true);
        option.setNeedAddress(true);
        client.setLocationOption(option);
        client.stopLocation();
        client.startLocation();
    }
}
