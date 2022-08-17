package com.cvilia.bubble.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.bubble.manager.ActivityManager;
import com.cvilia.bubble.utils.DeviceUtil;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import me.jessyan.autosize.internal.CustomAdapt;

import static com.cvilia.bubble.base.BaseApplication.app;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView, CustomAdapt {

    protected static final String TAG = BaseActivity.class.getSimpleName();

    protected Activity mContext;

    protected T mPresenter;

    public LocalChangedBroadcastReceiver mLocalChangedReceiver;//用于监听系统语言切换

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inflatRootView());
        ARouter.getInstance().inject(this);
        Log.d(TAG, "新的实例");
//        setupUI(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0));
        mLocalChangedReceiver = new LocalChangedBroadcastReceiver();
        mContext = this;
        ActivityManager.getInstance().addActivity(this);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (registerEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getIntentData();
        StatusBarUtil.setTranslucent(this, 0);
        onViewCreated();
        initView();

    }

    protected abstract void onViewCreated();

    protected abstract View inflatRootView();

    protected abstract void initView();

    protected abstract void getIntentData();

    public boolean registerEventBus() {
        return true;
    }

    protected abstract T getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        ActivityManager.getInstance().removeActivity(this);
    }

    /**
     * 会导致重复创建实例
     *
     * @param view
     */
    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                DeviceUtil.hideSoftKeyboard(BaseActivity.this);
                v.performClick();
                return false;
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }


    @Override
    public boolean isBaseOnWidth() {
        return true;
    }

    @Override
    public float getSizeInDp() {
        return 360;
    }

    /**
     * 监听系统语言切换
     */
    public static class LocalChangedBroadcastReceiver extends BroadcastReceiver {

        public LocalChangedBroadcastReceiver() {
            register();
        }

        public void unRegister() {
            app.unregisterReceiver(this);
        }

        private void register() {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_LOCALE_CHANGED);
            app.registerReceiver(this, filter);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), Intent.ACTION_LOCALE_CHANGED)) {
                Toast.makeText(app, "监听到系统语言切换", Toast.LENGTH_LONG).show();
                if (ActivityManager.getInstance().getActivitySize() > 0) {
                    ActivityManager.getInstance().removeAllActivity();
                    System.exit(0);
                }
            }
        }
    }

}
