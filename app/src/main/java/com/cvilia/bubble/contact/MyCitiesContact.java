package com.cvilia.bubble.contact;

import com.cvilia.bubble.base.IPresenter;
import com.cvilia.bubble.base.IView;

/**
 * author: lzy
 * date: 1/28/21
 * describe：描述
 */
public class MyCitiesContact {
    public interface Presenter extends IPresenter<MyCitiesContact.View> {
        void requestWeatherInfo();
    }

    public interface View extends IView {
        void requestSuccess();
    }

}
