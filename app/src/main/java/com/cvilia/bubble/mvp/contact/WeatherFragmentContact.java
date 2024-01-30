package com.cvilia.bubble.mvp.contact;

import com.cvilia.base.mvp.IPresenter;
import com.cvilia.base.mvp.IView;
import com.qweather.sdk.bean.weather.WeatherNowBean;

public class WeatherFragmentContact {

    public interface Presenter extends IPresenter<View> {

        void getWeatherNow(String locationID);
    }

    public interface View extends IView {
        void loadWeatherNow(WeatherNowBean weatherNow);
    }
}
