package com.cvilia.bubbleweather.pages.selectcity;

import com.cvilia.bubbleweather.IPresenter;
import com.cvilia.bubbleweather.IView;
import com.cvilia.bubbleweather.bean.City;

import java.util.List;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：描述
 */
public abstract class SelectCityContact {

    interface Presenter extends IPresenter<View> {
        void readDb();
    }

    interface View extends IView {
        void readDbSuccess(List<City> cityList);
    }

}
