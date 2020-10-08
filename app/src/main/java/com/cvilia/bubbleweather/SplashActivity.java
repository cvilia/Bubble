package com.cvilia.bubbleweather;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.utils.CopyDb2Local;
import com.cvilia.bubbleweather.utils.MMKVUtil;
import com.cvilia.bubbleweather.utils.StatusUtil;
import com.tencent.mmkv.MMKV;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtil.hideStatusBar(this);
        setContentView(R.layout.activity_splash);
        Observable.timer(3, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            boolean isFirstStart = MMKVUtil.getBool(Constants.FIRST_START, true);
            ARouter.getInstance().build(isFirstStart ? PageUrlConfig.PERMISSION_EXPLAIN_PAGE : PageUrlConfig.MAIN_PAGE).navigation(this, new NavCallback() {
                @Override
                public void onArrival(Postcard postcard) {
                    finish();
                }
            });
        });
    }
}