package com.cvilia.bubble.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cvilia.bubble.R;
import com.cvilia.bubble.base.BasePresenter;
import com.cvilia.bubble.contact.BubbleContact;
import com.cvilia.bubble.presenter.BubblePresenter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

public class BubbleActivity extends BasePresenter<BubblePresenter>implements BubbleContact.View, OnRefreshListener {


    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showRequestFailed() {

    }

    @Override
    public void locateFailed() {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}