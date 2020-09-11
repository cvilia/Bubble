package com.cvilia.bubbleweather.pages.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.R2;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.CurrentWeatherBean;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.utils.CopyDb2Local;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class WeatherActivity extends BaseActivity<HomePagePresenter> implements HomePageContact.View, OnRefreshListener {

    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final int REQUEST_CODE_SELECT_SITE = 0x1101;

    @BindView(R2.id.mainPageLl)
    LinearLayout mMainPageLl;//首页父布局，用来设置背景

    @BindView(R2.id.locateTv)
    TextView mCityName;

    @BindView(R2.id.tempTv)
    TextView mTempTv;

    @BindView(R2.id.weatherDescTv)
    TextView mWeatherDescTv;

    @BindView(R2.id.tempRangeTv)
    TextView mTempRangeTv;

    @BindView(R2.id.AQITv)
    TextView mAqiTv;

    @BindView(R2.id.updateTimeTv)
    TextView mUpdateTimeTv;

    @BindView(R2.id.refreshL)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R2.id.refreshHeader)
    BezierRadarHeader refreshHeader;

    private static boolean isFirstIn = true;

    @BindView(R2.id.selectSiteTv)
    TextView mSelectSiteTv;//跳转选择位置

    private boolean selectCity = false;//是否是选择过城市
    private String cityCode;


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
        mRefreshLayout.setPrimaryColorsId(R.color.bg_2d3093, R.color.app_main);
    }

    @Override
    protected void initWidgetEvent() {
        mRefreshLayout.setEnableLoadMore(false);
        if (isFirstIn) {
            isFirstIn = false;
            mRefreshLayout.autoRefresh();
        }
        mRefreshLayout.setOnRefreshListener(this);
        mSelectSiteTv.setOnClickListener(view -> {
            ARouter.getInstance().build(PageUrlConfig.SELECT_CITY_PAGE).navigation(this, REQUEST_CODE_SELECT_SITE);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_SITE && resultCode == RESULT_OK) {
            cityCode = data.getStringExtra("cityCode");
            mCityName.setText(data.getStringExtra("cityName"));
            selectCity = true;
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    protected void initData() {

//        if (!MMKV.defaultMMKV().decodeBool(Constants.COPY_DB_ALREADY, false))
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
        mRefreshLayout.finishRefresh();
        if (bean != null) {
            mTempTv.setText(String.format(getString(R.string.temperature), bean.getTem()));
            mWeatherDescTv.setText(bean.getWea());
            mTempRangeTv.setText(String.format(getString(R.string.temperature_min_max), bean.getTem_day(), bean.getTem_night()));
            mAqiTv.setText(String.format(getString(R.string.aqi), getAQI(bean.getAir())));
            mUpdateTimeTv.setText(String.format(getString(R.string.last_update_time), bean.getUpdate_time()));
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
    public void day7Success(Day7WeatherBean bean) {

    }

    @Override
    public void showDay7Failed() {

    }

    @Override
    public void locateSuccess(AMapLocation location) {
        mPresenter.requestWeatherByName(location.getDistrict());
        mPresenter.requestDay7(location.getDistrict());
        mCityName.setText(location.getDistrict());
    }

    @Override
    public void locateFailed() {
        mPresenter.requestWeatherByName("北京市");
        mPresenter.requestDay7("北京市");
        mCityName.setText("北京市");
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (selectCity) {
            mPresenter.startLocate(this);
        } else {
            mPresenter.requestWeatherByCode(cityCode);
        }
    }
}