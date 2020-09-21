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
    public void requestWeatherInfo(String cityInfo) {
        String regex = "^[0-9]+$";
        if (cityInfo.matches(regex)) {
            requestWeather(cityInfo, false);
        } else {
            if (cityInfo.contains("市") || cityInfo.contains("区") || cityInfo.contains("县")) {
                cityInfo = cityInfo.substring(0, cityInfo.length() - 1);
            }
            requestWeather(cityInfo, true);
        }
    }

    @Override
    public void startLocate(Context context) {
        AMapLocationClient client = new AMapLocationClient(context);
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

    /**
     * 请求7日天气
     *
     * @param cityInfo 城市id或城市名称
     */
    private void requestWeather(String cityInfo, boolean isName) {
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", WeatherApi.appid);
        map.put("appsecret", WeatherApi.appSecret);
        if (isName)
            map.put("city", cityInfo);
        else
            map.put("cityid", cityInfo);
        HttpManager.getInstance().get(Api.OTHERS, map, new HttpManager.MyCallback() {
            @Override
            public void success(Response res) throws IOException {
                if (res != null) {
                    Gson gson = new Gson();
                    Day7WeatherBean bean = gson.fromJson(Objects.requireNonNull(res.body().string()), Day7WeatherBean.class);
                    mView.showRequestSuccess(bean);
                }
            }

            @Override
            public void failed(IOException e) {

            }
        });
    }
}
