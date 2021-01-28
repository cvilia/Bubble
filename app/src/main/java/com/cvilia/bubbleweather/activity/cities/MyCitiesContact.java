package com.cvilia.bubbleweather.activity.cities;

import com.cvilia.bubbleweather.base.IPresenter;
import com.cvilia.bubbleweather.base.IView;

/**
 * author: lzy
 * date: 1/28/21
 * describe：描述
 */
public class MyCitiesContact {
    interface Presenter extends IPresenter<MyCitiesContact.View> {
        void requestWeatherInfo();
    }

    interface View extends IView {
        void requestSuccess();
    }

}
