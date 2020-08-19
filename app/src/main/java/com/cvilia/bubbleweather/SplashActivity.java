package com.cvilia.bubbleweather;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.cvilia.bubbleweather.component.PermissionExplainDialog;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.listener.IPermissionAcceptClick;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 开屏页、申请部分隐私权限，如定位和存储权限
 * 倒计时
 */

public class SplashActivity extends AppCompatActivity implements IPermissionAcceptClick, AMapLocationListener {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private final RxPermissions rxPermissions = new RxPermissions(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showPermissionDialog();

    }

    /**
     * 初始化高德定位
     */
    private void initAliLocation() {
        AMapLocationClient mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClient.setLocationListener(this);
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);
        setOptionConfig(mOption);
        mLocationClient.setLocationOption(mOption);
        mLocationClient.stopLocation();
        mLocationClient.startLocation();
    }

    /**
     * 配置高德option
     *
     * @param option
     */
    private void setOptionConfig(AMapLocationClientOption option) {
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        option.setLocationCacheEnable(true);
    }

    /**
     * 权限检测
     */
    private void showPermissionDialog() {
        if (!rxPermissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION) || !rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionExplainDialog dialog = new PermissionExplainDialog(this);
            dialog.setOnPermissionAcceptClick(this);
            dialog.show();
        } else {
            initAliLocation();
//            enterMainPage(null,null,null,null);
        }
    }

    @Override
    public void onAccept() {
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                            if (granted) {
                                initAliLocation();
                            } else {
                                System.exit(0);
                            }
                        }
                );
    }

    /**
     * 进入主界面
     *
     * @param code 地区码
     * @param province 省
     * @param city 市
     * @param district 区
     */
    private void enterMainPage(String code, String province, String city, String district) {
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    ARouter.getInstance().build(PageUrlConfig.MAIN_PAGE).withString(Constants.CITY_CODE, TextUtils.isEmpty(code) ? "CN101010100" : "CN" + code)
                            .withString(Constants.PROVINCE_NAME,TextUtils.isEmpty(province) ? "北京" : province)
                            .withString(Constants.CITY_NAME,TextUtils.isEmpty(province) ? "北京市" : city)
                            .withString(Constants.DISTRICT_NAME,TextUtils.isEmpty(district) ? "朝阳区" : district)
                            .navigation(SplashActivity.this, new NavCallback() {
                                @Override
                                public void onArrival(Postcard postcard) {
                                    finish();
                                }
                            });
                });
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (location != null) {
            if (location.getErrorCode() == 0) {
                Log.d(TAG, location.toStr());
                String citiCode = location.getCityCode();
                String adCode = location.getAdCode();
                String cityName = location.getCity();
                enterMainPage(adCode + citiCode, location.getProvince(), location.getCity(), location.getDistrict());
            } else {
                enterMainPage(null, null, null, null);
            }
        } else {
            enterMainPage(null, null, null, null);
        }

    }
}