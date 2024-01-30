package com.cvilia.bubble.activity.weather;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.cvilia.base.BaseActivity;
import com.cvilia.bubble.adapter.WeatherFragmentAdapter;
import com.cvilia.bubble.databinding.ActivityMainBinding;
import com.cvilia.bubble.fragment.TestFragment;
import com.cvilia.bubble.log.BubbleLogger;
import com.cvilia.bubble.mvp.contact.WeatherActivityContact;
import com.cvilia.bubble.mvp.contact.WeatherFragmentContact;
import com.cvilia.bubble.mvp.presenter.WeatherActivityPresenter;
import com.cvilia.bubble.route.PageUrlConfig;

import java.util.Arrays;
import java.util.List;

@Route(path = "/home/weatherPage")
public class WeatherActivity extends BaseActivity<WeatherActivityPresenter> implements WeatherActivityContact.View, AMapLocationListener {

    private ActivityMainBinding mBindings;

    /**
     * 定位相关
     */
    private AMapLocationClient mMapClient;
    private AMapLocationClientOption mMapOption;


    @Override
    protected void onViewCreated() {

    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivityMainBinding.inflate(getLayoutInflater());
        return mBindings.getRoot();
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    protected void initView() {
        mBindings.actionBar2.setOnClickListener(v -> {
            ARouter.getInstance().build(PageUrlConfig.CITIES_PAGE).navigation();
        });
        mBindings.bubbleIndicator.setIndicatorCount(2);

        WeatherFragmentAdapter adapter = new WeatherFragmentAdapter(this, initFragmentList());
        mBindings.viewPager.setAdapter(adapter);
        mBindings.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mBindings.bubbleIndicator.setCurrentPosition(position);
                mBindings.bubbleIndicator.postInvalidate();
            }
        });

    }

    private List<Fragment> initFragmentList() {
        // todo 根据用户选择，展示多个fragment
        TestFragment viewPage2Fragment = TestFragment.newInstance("我是Fragment1", "");
        TestFragment viewPage2Fragment2 = TestFragment.newInstance("我是Fragment2", "");
        return Arrays.asList(viewPage2Fragment, viewPage2Fragment2);
    }

    @Override
    protected void getData() {

        getLocation();

    }

    private void getLocation() {
        mMapOption = new AMapLocationClientOption();
        mMapOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mMapOption.setOnceLocation(true);
        mMapOption.setNeedAddress(true);
        mMapClient = new AMapLocationClient(this);
        mMapClient.setLocationListener(this);
        mMapClient.setLocationOption(mMapOption);
        mMapClient.startLocation();
    }

    @Override
    protected WeatherActivityPresenter getPresenter() {
        return new WeatherActivityPresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (location != null) {
            if (location.getErrorCode() == 0) {
                BubbleLogger.d(TAG, "AMapLocation Success：" + location);
                mBindings.cityNameTv.setText(location.getPoiName());
            } else {
                BubbleLogger.d(TAG, "AMapLocation Error: ErrorCode: " + location.getErrorCode()
                        + "，ErrorInfo：" + location.getErrorInfo());
            }
        }
    }
}