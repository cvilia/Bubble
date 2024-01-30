package com.cvilia.bubble.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvilia.base.BaseFragment;
import com.cvilia.bubble.R;
import com.cvilia.bubble.mvp.contact.WeatherFragmentContact;
import com.cvilia.bubble.mvp.presenter.WeatherFragmentPresenter;
import com.qweather.sdk.bean.weather.WeatherNowBean;

public class WeatherFragment extends BaseFragment<WeatherFragmentPresenter> implements WeatherFragmentContact.View {

    private static final String LOCATION_ID = "location_id";
    private static final String LOCATION = "location_name";

    private String locationId;
    private String location;

    public static WeatherFragment newInstance(String locationId, String location) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LOCATION_ID, locationId);
        bundle.putString(LOCATION, location);
        return fragment;
    }

    @Override
    protected void onCreate() {
        if (getArguments() != null) {
            locationId = getArguments().getString(LOCATION_ID);
            location = getArguments().getString(LOCATION);
        }
    }

    @Override
    protected View inflateRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getIntent() {

    }

    @Override
    protected void initData() {
        mPresenter.getWeatherNow(locationId);
    }

    @Override
    protected WeatherFragmentPresenter getPresenter() {
        return new WeatherFragmentPresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void loadWeatherNow(WeatherNowBean weatherNow) {

    }
}
