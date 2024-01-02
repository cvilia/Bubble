package com.cvilia.bubble.mvp.presenter;

import android.content.Context;

import com.cvilia.base.mvp.BasePresenter;
import com.cvilia.bubble.log.BubbleLogger;
import com.cvilia.bubble.mvp.contact.MyCitiesContact;
import com.qweather.sdk.bean.base.Lang;
import com.qweather.sdk.bean.base.Range;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.geo.GeoBean.LocationBean;
import com.qweather.sdk.view.QWeather;

import java.util.List;

/**
 * author: lzy
 * date: 1/28/21
 * describe：描述
 */
public class MyCitesPresenter extends BasePresenter<MyCitiesContact.View> implements MyCitiesContact.Presenter {

    private static final String TAG = MyCitesPresenter.class.getSimpleName();
}
