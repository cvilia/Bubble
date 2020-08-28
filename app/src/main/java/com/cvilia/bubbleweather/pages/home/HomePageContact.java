package com.cvilia.bubbleweather.pages.home;

import com.cvilia.bubbleweather.IPresenter;
import com.cvilia.bubbleweather.IView;
import com.cvilia.bubbleweather.bean.CurrentWeatherBean;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：描述
 */
public class HomePageContact {
    interface Presenter extends IPresenter<HomePageContact.View>{
        void requestCurrentWeather(String cityName);
        void requestDay7(String cityName);
    }

    interface View extends IView{
        void showSuccess(CurrentWeatherBean bean);
        void showFail();

        void day7Success(Day7WeatherBean bean);
        void showDay7Failed();
    }
}
