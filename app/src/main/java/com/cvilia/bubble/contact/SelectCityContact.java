package com.cvilia.bubble.contact;

import com.amap.api.location.AMapLocation;
import com.cvilia.bubble.base.IPresenter;
import com.cvilia.bubble.base.IView;
import com.cvilia.bubble.bean.City;

import java.util.List;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：描述
 */
public abstract class SelectCityContact {

    public interface Presenter extends IPresenter<View> {
        void readDb(String cityName);
        void startLocate();
    }

    public interface View extends IView {
        void searchSuccess(List<String> cities);

        void locateSuccess(AMapLocation location);

        void locateFailed();
    }

}
