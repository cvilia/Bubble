package com.cvilia.bubbleweather.activity;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.listener.TwoButtonClickListener;
import com.cvilia.bubbleweather.utils.RxPermissionUtils;
import com.cvilia.bubbleweather.utils.StatusUtil;
import com.cvilia.bubbleweather.view.MessageTwoButtonDialog;
import com.tbruyelle.rxpermissions3.Permission;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.jessyan.autosize.internal.CancelAdapt;

/**
 * 开屏页、申请部分隐私权限，如定位和存储权限
 * 倒计时
 */

public class SplashActivity extends AppCompatActivity implements CancelAdapt {

    private static final String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtil.hideStatusBar(this);
        setContentView(R.layout.activity_splash);
        Observable.timer(3, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            if (!RxPermissionUtils.checkPermissions(this, PERMISSIONS)) {
                MessageTwoButtonDialog dialog = new MessageTwoButtonDialog(this, getString(R.string.permission_explain_location), new TwoButtonClickListener() {
                    @Override
                    public void onConfirm() {
                        RxPermissionUtils.requestPermissions(SplashActivity.this, PERMISSIONS, new RxPermissionUtils.OnPermissionCallBack() {
                            @Override
                            public void onPermissionsGranted() {
                                ARouter.getInstance().build(PageUrlConfig.MAIN_PAGE).navigation(SplashActivity.this, new NavCallback() {
                                    @Override
                                    public void onArrival(Postcard postcard) {
                                        finish();
                                    }
                                });
                            }

                            @Override
                            public void onAtLeastOneReject(Permission permission) {
                                finish();
                            }

                            @Override
                            public void onAllRejectAndDoNotAskAgain(Permission permission) {
                                RxPermissionUtils.toAppSetting(SplashActivity.this);
                            }
                        });
                    }

                    @Override
                    public void onCancle() {
                        finish();
                    }
                });
                dialog.show();
            } else {
                ARouter.getInstance().build(PageUrlConfig.MAIN_PAGE).navigation();
            }

        });
    }
}