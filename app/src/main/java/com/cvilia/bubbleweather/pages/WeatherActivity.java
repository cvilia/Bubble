package com.cvilia.bubbleweather.pages;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.location.AMapLocation;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.CurrentWeatherBean;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class WeatherActivity extends BaseActivity<HomePagePresenter> implements HomePageContact.View, View.OnTouchListener {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private TextView cityName, currentTemTv, weatherDescTv, minMaxTempTv, aqiTv, dateTimeTv;
    private AMapLocation mLocation;
    private RelativeLayout weatherInfoRl;

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
        currentTemTv = findViewById(R.id.tempTv);
        weatherDescTv = findViewById(R.id.weatherDescTv);
        minMaxTempTv = findViewById(R.id.minMaxTempTv);
        aqiTv = findViewById(R.id.AQITv);
        weatherInfoRl = findViewById(R.id.weatherInfoRl);
        dateTimeTv = findViewById(R.id.dateTimeTv);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initWidgetEvent() {
        weatherInfoRl.setClickable(true);
        weatherInfoRl.setOnTouchListener(this);
    }

    @Override
    protected void initData() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"
                + " HH:mm");
        dateTimeTv.setText(sdf.format(new Date()));
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    protected void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            mLocation = intent.getParcelableExtra(Constants.AMAPLOCATION);
            if (null != mLocation && !TextUtils.isEmpty(mLocation.getDistrict())) {
                mPresenter.requestCurrentWeather(mLocation.getDistrict());
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
        runOnUiThread(() -> reloadPage(bean));
    }

    private void reloadPage(CurrentWeatherBean bean) {
        if (bean != null) {
            cityName.setText(bean.getCity());
            currentTemTv.setText(String.format(getString(R.string.temperature), bean.getTem()));
            weatherDescTv.setText(bean.getWea());
            minMaxTempTv.setText(String.format(getString(R.string.temperature_min_max),
                    bean.getTem_day(), bean.getTem_night()));
            aqiTv.setText(String.format(getString(R.string.aqi), getAQI(bean.getAir())));
        }
    }

    private String getAQI(String air) {
        String aqi = "未知";
        if (!TextUtils.isEmpty(air)) {
            int intAqi = Integer.parseInt(air);
            if (intAqi <= 50) {
                aqi = "优";
            } else if (intAqi <= 100) {
                aqi = "良";
            } else if (intAqi <= 150) {
                aqi = "轻度污染";
            } else if (intAqi <= 200) {
                aqi = "中度污染";
            } else if (intAqi <= 300) {
                aqi = "重度污染";
            } else {
                aqi = "严重污染";
            }
        }
        return aqi;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (v.getId() == R.id.weatherInfoRl) {
                    weatherInfoRl.setBackgroundColor(Color.parseColor("#0f100000"));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (v.getId() == R.id.weatherInfoRl) {
                    weatherInfoRl.setBackgroundColor(Color.parseColor("#00000000"));
                }
                break;
        }
        return false;
    }
}