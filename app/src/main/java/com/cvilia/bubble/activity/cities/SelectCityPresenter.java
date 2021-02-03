package com.cvilia.bubble.activity.cities;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.cvilia.bubble.base.BasePresenter;
import com.cvilia.bubble.bean.City;
import com.cvilia.bubble.sql.CityDao;
import com.cvilia.bubble.sql.DaoMaster;
import com.cvilia.bubble.sql.DaoSession;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：选择城市Presenter
 */
public class SelectCityPresenter extends BasePresenter<SelectCityContact.View> implements SelectCityContact.Presenter {
    @Override
    public void readDb(String cityName) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper((Activity) mView, "weatherDb.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession mDaosession = daoMaster.newSession();
        CityDao dao = mDaosession.getCityDao();
        List<City> cities = dao.loadAll();
        if (cities != null) {
            /**
             * 需要根据用户的搜索内容进行筛选
             *
             */
            List<String> result = new ArrayList<>();
            for (City city : cities) {
                if (city.getLeaderZh().equals(cityName)) {//说明当前搜索的是市或者区
                    result = getSearchResults(cityName, cities);
                    break;
                } else if (city.getCityZh().equals(cityName)) {
                    result.add(cityName + " - " + city.getLeaderZh() + " · " + city.getProvinceZh());
                    break;
                }
            }
            mView.searchSuccess(result);
        }
    }

    private List<String> getSearchResults(String higherAreaName, List<City> cities) {
        List<String> results = new ArrayList<>();
        for (City city : cities) {
            if (city.getLeaderZh().equals(higherAreaName)) {
                results.add(city.getCityZh() + " - " + higherAreaName + " · " + city.getProvinceZh());
            }
        }
        return results;
    }

    @Override
    public void startLocate() {
        AMapLocationClient client = new AMapLocationClient((Activity) mView);
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
