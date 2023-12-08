package com.cvilia.bubble.mvp.contact;

import com.cvilia.base.mvp.*;

/**
 * author: lzy
 * date: 1/28/21
 * describe：描述
 */
public class MyCitiesContact {
    public interface Presenter extends IPresenter<View> {
        void requestWeatherInfo();
    }

    public interface View extends IView {
        void requestSuccess();
    }

}
