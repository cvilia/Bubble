package com.cvilia.bubble.activity;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cvilia.bubble.adapter.BubbleAdapter;
import com.cvilia.bubble.adapter.Day7Adapter;
import com.cvilia.bubble.adapter.Hour7Adapter;
import com.cvilia.bubble.base.BaseActivity;
import com.cvilia.bubble.contact.BubbleContact;
import com.cvilia.bubble.databinding.ActivityBubbleBinding;
import com.cvilia.bubble.presenter.BubblePresenter;
import com.cvilia.bubble.route.PageUrlConfig;
@Route(path = PageUrlConfig.MAIN_PAGE)
public class BubbleActivity extends BaseActivity<BubblePresenter> implements BubbleContact.View, View.OnTouchListener {

    private ActivityBubbleBinding mViewBinding;

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected View inflatRootView() {

        mViewBinding = ActivityBubbleBinding.inflate(getLayoutInflater());

        return mViewBinding.getRoot();
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mViewBinding.bubbleRv.setLayoutManager(layoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mViewBinding.bubbleRv);
        mViewBinding.bubbleRv.setOnTouchListener(this);

        BubbleAdapter adapter = new BubbleAdapter(this);
        mViewBinding.bubbleRv.setAdapter(adapter);
    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected BubblePresenter getPresenter() {
        return new BubblePresenter();
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

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
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}