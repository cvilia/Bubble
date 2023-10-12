package com.cvilia.bubble.contact;

import com.amap.api.location.AMapLocation;
import com.cvilia.bubble.base.IPresenter;
import com.cvilia.bubble.base.IView;
import com.cvilia.bubble.bean.Day7WeatherBean;

public class BubbleContact {
    public interface Presenter extends IPresenter<BubbleContact.View> {
        void startLocate();
    }

    public interface View extends IView {

        void showRequestFailed();
        void locateFailed();

    }
}
