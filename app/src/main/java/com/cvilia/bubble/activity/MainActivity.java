package com.cvilia.bubble.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubble.R;
import com.cvilia.bubble.base.BaseActivity;
import com.cvilia.bubble.contact.MainContact;
import com.cvilia.bubble.databinding.ActivityMainBinding;
import com.cvilia.bubble.presenter.MainPresenter;
import com.cvilia.bubble.route.PageUrlConfig;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class MainActivity extends BaseActivity<MainPresenter> implements MainContact.View {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        mPresenter.requestLauncherUrl();
    }

    @Override
    protected View inflatRootView() {
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getIntentData() {
        Intent intent = getIntent();
        String launcherUrl = intent.getStringExtra("picUrl");
        boolean downloadImg = intent.getBooleanExtra("downloadPic", false);
        if (!TextUtils.isEmpty(launcherUrl) && downloadImg)
            mPresenter.downloadLauncherPic(launcherUrl);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    public void dismissLoading() {

    }
}