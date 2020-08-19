package com.cvilia.bubbleweather.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.bubbleweather.ActivityManager;
import com.cvilia.bubbleweather.IView;

import org.greenrobot.eventbus.EventBus;

import me.jessyan.autosize.internal.CustomAdapt;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView, CustomAdapt {

    protected Activity mContext;

    protected T mPresenter;

    private boolean isFullScreen = true;

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        mContext = this;

        ActivityManager.getInstance().addActivity(this);
        mPresenter = getPresenter();
        if (registerEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getIntentData();
        initWidget();
        onViewCreated();

    }

    protected void onViewCreated() {

        /**
         * 隐藏action bar
         */
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        /**
         * 隐藏状态栏
         */
        if (isFullScreen) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    protected abstract void initWidget();

    protected abstract void getIntentData();

    private boolean registerEventBus() {
        return false;
    }


    protected abstract int getLayoutId();

    protected abstract T getPresenter();

    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 360;
    }
}
