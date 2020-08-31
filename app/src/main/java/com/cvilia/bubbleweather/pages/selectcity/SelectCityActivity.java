package com.cvilia.bubbleweather.pages.selectcity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.config.PageUrlConfig;

/**
 * 选择城市
 */
@Route(path = PageUrlConfig.SELECT_CITY_PAGE)
public class SelectCityActivity extends BaseActivity<SelectCityPresenter> implements SelectCityContact.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initWidgetEvent() {
        mPresenter.readJson(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_city;
    }

    @Override
    protected SelectCityPresenter getPresenter() {
        return new SelectCityPresenter();
    }

    @Override
    public void readJsonSuccess() {

    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }
}