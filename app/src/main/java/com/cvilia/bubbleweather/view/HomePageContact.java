package com.cvilia.bubbleweather.view;

import com.cvilia.bubbleweather.IPresenter;
import com.cvilia.bubbleweather.IView;
import com.cvilia.bubbleweather.bean.CurrentWeatherBean;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：描述
 */
public class HomePageContact {
    interface Presenter extends IPresenter<HomePageContact.View>{
        void requestCurrentWeather(String cityName);
    }

    interface View extends IView{
        void showSuccess(CurrentWeatherBean bean);
        void showFail();
    }
}
