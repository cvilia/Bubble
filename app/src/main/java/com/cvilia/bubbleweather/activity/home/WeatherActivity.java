package com.cvilia.bubbleweather.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.R2;
import com.cvilia.bubbleweather.adapter.Day7Adapter;
import com.cvilia.bubbleweather.adapter.Hour7Adapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;
import com.cvilia.bubbleweather.bean.Day7WeatherBean.DataBean;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.utils.CopyDb2Local;
import com.cvilia.bubbleweather.utils.MMKVUtil;
import com.cvilia.bubbleweather.view.RecyclerViewDivider;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class WeatherActivity extends BaseActivity<HomePagePresenter> implements HomePageContact.View, OnRefreshListener {

//    private static final String TAG = WeatherActivity.class.getSimpleName();
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
    private String cityName;

    @BindView(R.id.day7RecyclerView)
    RecyclerView mDay7RecyclerView;

    @BindView(R.id.hourRecyclerView)
    RecyclerView mHourRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        ActivityMainBinding mViewBind = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(mViewBind.getRoot());

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
    protected void initWidget() {
        mRefreshLayout.setPrimaryColorsId(R.color.bg_90b8d1, R.color.app_main);
    }


    @Override
    protected void initWidgetEvent() {
        mRefreshLayout.setEnableLoadMore(false);
        if (isFirstIn) {
            isFirstIn = false;
            mRefreshLayout.autoRefresh();
        }
        mRefreshLayout.setOnRefreshListener(this);
        mSelectSiteTv.setOnClickListener(view -> ARouter.getInstance().build(PageUrlConfig.SELECT_CITY_PAGE).navigation(this, REQUEST_CODE_SELECT_SITE));

        LinearLayoutManager day7Lp = new LinearLayoutManager(this);
        day7Lp.setOrientation(LinearLayoutManager.HORIZONTAL);
        mDay7RecyclerView.setLayoutManager(day7Lp);
        mDay7RecyclerView.addItemDecoration(new RecyclerViewDivider(null, this));
        LinearLayoutManager hour7Lp = new LinearLayoutManager(this);

        hour7Lp.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHourRecycler.setLayoutManager(hour7Lp);
        mHourRecycler.addItemDecoration(new RecyclerViewDivider(null, this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_SITE && resultCode == RESULT_OK) {
            assert data != null;
            cityCode = data.getStringExtra("cityCode");
            cityName = data.getStringExtra("cityName");
            mCityName.setText(data.getStringExtra("cityName"));
            selectCity = true;
            mRefreshLayout.autoRefresh();
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
    protected int getLayoutId() {
        return R.layout.activity_main;
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
            mTempTv.setText(String.format(getString(R.string.temperature), todayInfo.getTem()));
            mWeatherDescTv.setText(todayInfo.getWea());
            mTempRangeTv.setText(String.format(getString(R.string.temperature_min_max), todayInfo.getTem1(),
                    todayInfo.getTem2()));
            String exchange = getString(R.string.aqi, getAQI(todayInfo.getAir()));
            mAqiTv.setText(Html.fromHtml(exchange));
            mUpdateTimeTv.setText(String.format(getString(R.string.last_update_time), bean.getUpdate_time().substring(11)));
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
        mDay7RecyclerView.setAdapter(day7Adapter);

        Hour7Adapter adapter = new Hour7Adapter(bean, this);
        mHourRecycler.setAdapter(adapter);

    }

    @Override
    public void showRequestSuccess(Day7WeatherBean bean) {
        mRefreshLayout.finishRefresh();
        if (bean != null) runOnUiThread(() -> reloadCurrentInfo(bean));
    }

    @Override
    public void showRequestFailed() {

    }

    @Override
    public void locateSuccess(AMapLocation location) {
        if (TextUtils.isEmpty(MMKVUtil.getString(Constants.SELECTED_CITY))) {
            mPresenter.requestWeatherInfo(location.getDistrict());
            mCityName.setText(location.getDistrict());
        } else {
            mPresenter.requestWeatherInfo(MMKVUtil.getString(Constants.SELECTED_CITY));
            mCityName.setText(MMKVUtil.getString(Constants.SELECTED_CITY));
        }
    }

    @Override
    public void locateFailed() {
        if (TextUtils.isEmpty(MMKVUtil.getString(Constants.SELECTED_CITY))) {
            mRefreshLayout.finishRefresh();
            mPresenter.requestWeatherInfo("北京市");
            mCityName.setText("北京市");
        } else {
            mPresenter.requestWeatherInfo(MMKVUtil.getString(Constants.SELECTED_CITY));
            mCityName.setText(MMKVUtil.getString(Constants.SELECTED_CITY));
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