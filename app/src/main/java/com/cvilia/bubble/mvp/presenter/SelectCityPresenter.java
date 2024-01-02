package com.cvilia.bubble.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.cvilia.base.mvp.BasePresenter;
import com.cvilia.bubble.bean.City;
import com.cvilia.bubble.log.BubbleLogger;
import com.cvilia.bubble.mvp.contact.SelectCityContact;
import com.cvilia.bubble.sql.CityDao;
import com.cvilia.bubble.sql.DaoMaster;
import com.cvilia.bubble.sql.DaoMaster.DevOpenHelper;
import com.cvilia.bubble.sql.DaoSession;
import com.cvilia.bubble.utils.TextUtil;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Range;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.view.QWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：选择城市Presenter
 */
public class SelectCityPresenter extends BasePresenter<SelectCityContact.View> implements SelectCityContact.Presenter {

    private static final String TAG = SelectCityPresenter.class.getSimpleName();

    @Override
    public void readDb(String cityName) {
        SQLiteDatabase db;
        try (DevOpenHelper helper = new DevOpenHelper((Activity) mView, "weatherDb.db", null)) {
            db = helper.getWritableDatabase();
        }
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession mDaosession = daoMaster.newSession();
        CityDao dao = mDaosession.getCityDao();
        List<City> cities = dao.loadAll();
        cityName = TextUtil.formatCityName(cityName);
        if (cities != null) {
            /**
             * 需要根据用户的搜索内容进行筛选
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

    @Override
    public void requestTopCity() {
        QWeather.getGeoTopCity((Context) mView, 15, Range.CN, Lang.ZH_HANS, new QWeather.OnResultGeoListener() {
            @Override
            public void onError(Throwable throwable) {
                BubbleLogger.d(TAG, "requestTopCity Error：ErrorInfo=" + throwable.getMessage());
            }

            @Override
            public void onSuccess(GeoBean geoBean) {
                BubbleLogger.d(TAG, "Get Top City Success");
                if (geoBean != null && geoBean.getLocationBean() != null) {
                    List<GeoBean.LocationBean> results = geoBean.getLocationBean();
                    for (GeoBean.LocationBean bean : results) {
                        System.out.println("name=" + bean.getName() + ",上级城市=" + bean.getAdm2()
                                + ",省份=" + bean.getAdm1() + ",属性=" + bean.getType());
                    }
                    mView.loadTopCity(geoBean);
                }
            }
        });
    }

    @Override
    public void searchCity(String city) {
        QWeather.getGeoCityLookup((Context) mView, city, Range.CN, 15, Lang.ZH_HANS, new QWeather.OnResultGeoListener() {
            @Override
            public void onError(Throwable throwable) {
                BubbleLogger.d(TAG, "searchCity Error：ErrorInfo=" + throwable.getMessage());
            }

            @Override
            public void onSuccess(GeoBean geoBean) {
                BubbleLogger.d(TAG, "searchCity Success");
                if (geoBean != null && geoBean.getLocationBean() != null) {
                    List<GeoBean.LocationBean> results = geoBean.getLocationBean();
                    for (GeoBean.LocationBean bean : results) {
                        System.out.println("name=" + bean.getName() + ",上级城市=" + bean.getAdm2()
                                + ",省份=" + bean.getAdm1() + ",属性=" + bean.getType());
                    }
                    mView.loadSearchCity(geoBean);
                }
            }
        });
    }
}
