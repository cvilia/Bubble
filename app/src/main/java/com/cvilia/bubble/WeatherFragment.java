package com.cvilia.bubble;

import android.os.Bundle;
import android.view.View;

import com.cvilia.bubble.base.BaseFragment;
import com.cvilia.bubble.databinding.FragmentWeatherBinding;
import com.cvilia.bubble.presenter.BubblePresenter;

/**
 * author: lzy
 * date: 3/2/21
 * describe：描述
 */
public class WeatherFragment extends BaseFragment<BubblePresenter> {

    private FragmentWeatherBinding mBinding;

    public static WeatherFragment getInstance(String cityName) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cityName", cityName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View inflateRootView() {
        mBinding = FragmentWeatherBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getIntent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BubblePresenter getPresenter() {
        return new BubblePresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }
}
