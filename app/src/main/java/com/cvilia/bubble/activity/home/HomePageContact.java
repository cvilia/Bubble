package com.cvilia.bubble.activity.home;

import com.amap.api.location.AMapLocation;
import com.cvilia.bubble.base.IPresenter;
import com.cvilia.bubble.base.IView;
import com.cvilia.bubble.bean.Day7WeatherBean;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：描述
 */
public class HomePageContact {
    interface Presenter extends IPresenter<View> {
        void requestWeatherInfo(String cityInfo);
        void startLocate();
    }

    interface View extends IView {
        void showRequestSuccess(Day7WeatherBean bean);

        void showRequestFailed();

        void locateSuccess(AMapLocation location);

        void locateFailed();

    }
}
