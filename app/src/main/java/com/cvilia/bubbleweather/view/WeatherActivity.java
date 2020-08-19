package com.cvilia.bubbleweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.CurrentWeatherBean;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class WeatherActivity extends BaseActivity<HomePagePresenter> implements HomePageContact.View {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private CurrentWeatherBean currentWeather;
    private TextView cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        setFullScreen(false);
        super.onViewCreated();
    }

    @Override
    protected void initWidget() {
        cityName = findViewById(R.id.locateTv);
        if(currentWeather==null){
            cityName.setText("京市");
        }else {
            cityName.setText(currentWeather.getCity());
        }
    }


    @Override
    protected void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            String districtName = intent.getStringExtra(Constants.DISTRICT_NAME);
            if (!TextUtils.isEmpty(districtName)) {
                mPresenter.requestCurrentWeather(districtName);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected HomePagePresenter getPresenter() {
        return new HomePagePresenter();
    }

    @Override
    public void showSuccess(CurrentWeatherBean bean) {
        this.currentWeather = bean;
    }

    @Override
    public void showFail() {

    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }
}