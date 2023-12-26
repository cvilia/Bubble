package com.cvilia.bubble.activity;

import android.view.View;

import com.cvilia.base.BaseActivity;
import com.cvilia.bubble.databinding.ActivityMainBinding;
import com.cvilia.bubble.mvp.contact.MainContact;
import com.cvilia.bubble.mvp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContact.View {

    private ActivityMainBinding mBindings;

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

    }

    @Override
    protected void getData() {

    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }
}