package com.cvilia.bubbleweather.pages;

import android.text.TextUtils;
import android.util.Log;

import com.cvilia.bubbleweather.base.BasePresenter;
import com.cvilia.bubbleweather.bean.CurrentWeatherBean;
import com.cvilia.bubbleweather.net.Api;
import com.cvilia.bubbleweather.net.HttpManager;
import com.cvilia.bubbleweather.net.WeatherApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

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
                    String json = res.body().string();
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
