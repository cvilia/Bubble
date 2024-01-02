package com.cvilia.bubble.mvp.contact;

import com.amap.api.location.AMapLocation;
import com.cvilia.base.mvp.IPresenter;
import com.cvilia.base.mvp.IView;
import com.qweather.sdk.bean.geo.GeoBean;

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

        void requestTopCity();
    }

    public interface View extends IView {
        void searchSuccess(List<String> cities);

        void locateSuccess(AMapLocation location);

        void locateFailed();

        void loadTopCity(GeoBean bean);
    }

}
