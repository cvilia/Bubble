package com.cvilia.bubble.activity.cities;

import com.cvilia.bubble.base.IPresenter;
import com.cvilia.bubble.base.IView;

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
