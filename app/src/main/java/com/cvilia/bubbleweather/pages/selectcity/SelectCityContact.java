package com.cvilia.bubbleweather.pages.selectcity;

import android.content.Context;

import com.cvilia.bubbleweather.IPresenter;
import com.cvilia.bubbleweather.IView;

/**
 * author: lzy
 * date: 2020/8/31
 * describe：描述
 */
public abstract class SelectCityContact {

    interface Presenter extends IPresenter<View> {
        void readJson(Context context);
    }

    interface View extends IView {
        void readJsonSuccess();
    }

}
