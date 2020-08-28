package com.cvilia.bubbleweather.pages.home;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.cvilia.bubbleweather.base.BasePresenter;
import com.cvilia.bubbleweather.bean.CurrentWeatherBean;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;
import com.cvilia.bubbleweather.net.Api;
import com.cvilia.bubbleweather.net.HttpManager;
import com.cvilia.bubbleweather.net.WeatherApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import okhttp3.Response;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：首页presenter
 */
public class HomePagePresenter extends BasePresenter<HomePageContact.View> implements HomePageContact.Presenter {

    private static final String TAG = HomePagePresenter.class.getSimpleName();

    @Override
    public void requestCurrentWeather(String cityName) {
        if (!TextUtils.isEmpty(cityName)) {
            if (cityName.contains("市") || cityName.contains("区") || cityName.contains("县")) {
                String district = cityName.substring(0, cityName.length() - 1);
                requestWeather(district);
            } else {
                requestWeather(cityName);
            }
        }
    }

    @Override
    public void requestDay7(String cityName) {
        if (!TextUtils.isEmpty(cityName)) {
            if (cityName.contains("市") || cityName.contains("区") || cityName.contains("县")) {
                String district = cityName.substring(0, cityName.length() - 1);
                requestDay7Weather(district);
            } else {
                requestDay7Weather(cityName);
            }
        }

    }

    @Override
    public void startLocate(Context context) {
        AMapLocationClient client = new AMapLocationClient(context);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        option.setOnceLocation(true);
        option.setNeedAddress(true);
        client.setLocationOption(option);
        client.stopLocation();
        client.startLocation();
        client.setLocationListener(aMapLocation -> {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                mView.locateSuccess(aMapLocation);
            } else {
                mView.locateFailed();
            }
        });
    }

    /**
     * 请求7日天气
     *
     * @param cityName
     */
    private void requestDay7Weather(String cityName) {
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", WeatherApi.appid);
        map.put("appsecret", WeatherApi.appSecret);
        map.put("city", cityName);
        HttpManager.getInstance().get(Api.OTHERS, map, new HttpManager.MyCallback() {
            @Override
            public void success(Response res) throws IOException {
                if (res != null) {
                    Gson gson = new Gson();
                    Day7WeatherBean bean = gson.fromJson(Objects.requireNonNull(res.body().string()), Day7WeatherBean.class);
                    mView.day7Success(bean);
                }
            }

            @Override
            public void failed(IOException e) {

            }
        });
    }

    /**
     * 请求当日天气
     *
     * @param cityName
     */
    private void requestWeather(String cityName) {
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", WeatherApi.appid);
        map.put("appsecret", WeatherApi.appSecret);
        map.put("city", cityName);
        HttpManager.getInstance().get(Api.CURRENT_WEATHER, map, new HttpManager.MyCallback() {
            @Override
            public void success(Response res) throws IOException {
                if (res != null) {
                    Gson gson = new Gson();
                    String json = Objects.requireNonNull(res.body()).string();
                    CurrentWeatherBean bean = gson.fromJson(json, CurrentWeatherBean.class);
                    Log.d(TAG, bean.toString());
                    mView.showSuccess(bean);
                }
            }

            @Override
            public void failed(IOException e) {

            }
        });
    }
}
