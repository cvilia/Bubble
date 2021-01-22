package com.cvilia.bubbleweather.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.adapter.Day7Adapter;
import com.cvilia.bubbleweather.adapter.Hour7Adapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;
import com.cvilia.bubbleweather.bean.Day7WeatherBean.DataBean;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.databinding.ActivityMainBinding;
import com.cvilia.bubbleweather.utils.CopyDb2Local;
import com.cvilia.bubbleweather.utils.MMKVUtil;
import com.cvilia.bubbleweather.view.RecyclerViewDivider;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class WeatherActivity extends BaseActivity<HomePagePresenter> implements HomePageContact.View, OnRefreshListener {

    //    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final int REQUEST_CODE_SELECT_SITE = 0x1101;

    private ActivityMainBinding mBindings;


    private static boolean isFirstIn = true;

    private boolean selectCity = false;//是否是选择过城市
    private String cityCode;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalChangedReceiver.unRegister();
        mLocalChangedReceiver = null;
    }

    @Override
    protected void onViewCreated() {
        setFullScreen(false);
        super.onViewCreated();
    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivityMainBinding.inflate(getLayoutInflater());
        return mBindings.getRoot();
    }

    @Override
    protected void initWidget() {
        mBindings.refreshL.setPrimaryColorsId(R.color.bg_90b8d1, R.color.app_main);
    }


    @Override
    protected void initWidgetEvent() {
        mBindings.refreshL.setEnableLoadMore(false);
        if (isFirstIn) {
            isFirstIn = false;
            mBindings.refreshL.autoRefresh();
        }
        mBindings.refreshL.setOnRefreshListener(this);
        mBindings.actionBar.selectSiteTv.setOnClickListener(view -> ARouter.getInstance().build(PageUrlConfig.SELECT_CITY_PAGE).navigation(this, REQUEST_CODE_SELECT_SITE));

        LinearLayoutManager day7Lp = new LinearLayoutManager(this);
        day7Lp.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBindings.day7RecyclerView.setLayoutManager(day7Lp);
        mBindings.day7RecyclerView.addItemDecoration(new RecyclerViewDivider(null, this));
        LinearLayoutManager hour7Lp = new LinearLayoutManager(this);

        hour7Lp.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBindings.hourRecyclerView.setLayoutManager(hour7Lp);
        mBindings.hourRecyclerView.addItemDecoration(new RecyclerViewDivider(null, this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_SITE && resultCode == RESULT_OK) {
            assert data != null;
            cityCode = data.getStringExtra("cityCode");
            cityName = data.getStringExtra("cityName");
            mBindings.locateTv.setText(data.getStringExtra("cityName"));
            selectCity = true;
            mBindings.refreshL.autoRefresh();
        }
    }


    @Override
    protected void initData() {
        CopyDb2Local.copy2localdb(this);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    protected void getIntentData() {
    }

    @Override
    protected HomePagePresenter getPresenter() {
        return new HomePagePresenter();
    }


    /**
     * 重新加载当日天气信息
     *
     * @param bean 天气实体类
     */
    private void reloadCurrentInfo(Day7WeatherBean bean) {
        DataBean todayInfo = bean.getData().get(0);
        if (todayInfo != null) {
            mBindings.tempTv.setText(String.format(getString(R.string.temperature), todayInfo.getTem()));
            mBindings.weatherDescTv.setText(todayInfo.getWea());
            mBindings.tempRangeTv.setText(String.format(getString(R.string.temperature_min_max), todayInfo.getTem1(),
                    todayInfo.getTem2()));
            String exchange = getString(R.string.aqi, getAQI(todayInfo.getAir()));
            mBindings.AQITv.setText(Html.fromHtml(exchange));
            mBindings.updateTimeTv.setText(String.format(getString(R.string.last_update_time), bean.getUpdate_time().substring(11)));
        }
        reloadDay7Weather(bean);
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

    /**
     * 加载7日天气信息
     *
     * @param bean 天气信息
     */
    private void reloadDay7Weather(Day7WeatherBean bean) {
        Day7Adapter day7Adapter = new Day7Adapter(bean, this);
        mBindings.day7RecyclerView.setAdapter(day7Adapter);

        Hour7Adapter adapter = new Hour7Adapter(bean, this);
        mBindings.hourRecyclerView.setAdapter(adapter);

    }

    @Override
    public void showRequestSuccess(Day7WeatherBean bean) {
        mBindings.refreshL.finishRefresh();
        if (bean != null) runOnUiThread(() -> reloadCurrentInfo(bean));
    }

    @Override
    public void showRequestFailed() {

    }

    @Override
    public void locateSuccess(AMapLocation location) {
        if (TextUtils.isEmpty(MMKVUtil.getString(Constants.SELECTED_CITY))) {
            mPresenter.requestWeatherInfo(location.getDistrict());
            mBindings.locateTv.setText(location.getDistrict());
        } else {
            mPresenter.requestWeatherInfo(MMKVUtil.getString(Constants.SELECTED_CITY));
            mBindings.locateTv.setText(MMKVUtil.getString(Constants.SELECTED_CITY));
        }
    }

    @Override
    public void locateFailed() {
        if (TextUtils.isEmpty(MMKVUtil.getString(Constants.SELECTED_CITY))) {
            mBindings.refreshL.finishRefresh();
            mPresenter.requestWeatherInfo("北京市");
            mBindings.locateTv.setText("北京市");
        } else {
            mPresenter.requestWeatherInfo(MMKVUtil.getString(Constants.SELECTED_CITY));
            mBindings.locateTv.setText(MMKVUtil.getString(Constants.SELECTED_CITY));
        }
    }


    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (!selectCity) {
            mPresenter.startLocate();
        }
        if (selectCity) {
            if (TextUtils.isEmpty(cityCode)) {
                mPresenter.requestWeatherInfo(cityName);
            } else {
                mPresenter.requestWeatherInfo(cityCode);
            }
        }
    }
}