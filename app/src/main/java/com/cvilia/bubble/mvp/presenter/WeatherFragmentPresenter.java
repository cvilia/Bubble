package com.cvilia.bubble.mvp.presenter;

import android.content.Context;

import com.cvilia.base.mvp.BasePresenter;
import com.cvilia.bubble.log.BubbleLogger;
import com.cvilia.bubble.mvp.contact.WeatherFragmentContact;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Unit;
import com.qweather.sdk.bean.weather.WeatherNowBean;
import com.qweather.sdk.view.QWeather;

public class WeatherFragmentPresenter extends BasePresenter<WeatherFragmentContact.View> implements WeatherFragmentContact.Presenter {

    private static final String TAG = WeatherFragmentPresenter.class.getSimpleName();

    @Override
    public void getWeatherNow(String locationID) {
        QWeather.getWeatherNow((Context) mView, locationID, Lang.ZH_HANS, Unit.METRIC, new QWeather.OnResultWeatherNowListener() {
            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onSuccess(WeatherNowBean weatherNowBean) {
                BubbleLogger.d(TAG, "get current weather Success");
                mView.loadWeatherNow(weatherNowBean);
            }
        });
    }
}
