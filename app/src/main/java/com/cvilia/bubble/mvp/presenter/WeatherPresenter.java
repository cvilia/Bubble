package com.cvilia.bubble.mvp.presenter;

import android.app.Activity;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.cvilia.base.mvp.BasePresenter;
import com.cvilia.bubble.mvp.contact.WeatherContact;
import com.cvilia.bubble.bean.Day7WeatherBean;
import com.cvilia.bubble.bean.RandomImage;
import com.cvilia.bubble.config.Constants;
import com.cvilia.bubble.net.Api;
import com.cvilia.bubble.net.HttpManager;
import com.cvilia.bubble.net.WeatherApi;
import com.cvilia.bubble.utils.MMKVUtil;
import com.cvilia.bubble.utils.TextUtil;
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
public class WeatherPresenter extends BasePresenter<WeatherContact.View> implements WeatherContact.Presenter {

    private static final String TAG = WeatherPresenter.class.getSimpleName();

    @Override
    public void requestWeatherInfo(String cityInfo) {
        requestWeather(TextUtil.formatCityName(cityInfo), true);
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
    public void requestLauncherBg() {
        //请求随机图片
        HashMap<String, String> map = new HashMap<>();
        map.put("method", "mobile");
        map.put("format", "json");
        map.put("lx", "suiji");

        HttpManager.getInstance().get(Api.LAUNCHER_IMAGE, map, new HttpManager.MyCallback() {
            @Override
            public void success(Response res) throws IOException {
                Gson gson = new Gson();
                if (res != null) {
                    RandomImage randomImage = gson.fromJson(Objects.requireNonNull(res.body()).string(), RandomImage.class);
                    if (randomImage.getCode().equals("200")) {
                        MMKVUtil.saveString(Constants.LAUNCHER_IMAGE, randomImage.getImgurl());
                    }
                }
            }

            @Override
            public void failed(IOException e) {

            }
        });
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
        map.put("version", WeatherApi.v9);
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
