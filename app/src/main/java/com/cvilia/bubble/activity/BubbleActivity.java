package com.cvilia.bubble.activity;

import static com.cvilia.bubble.model.BubbleModel.*;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cvilia.base.BaseActivity;
import com.cvilia.bubble.R;
import com.cvilia.bubble.adapter.BubbleAdapter;
import com.cvilia.bubble.databinding.ActivityBubbleBinding;
import com.cvilia.bubble.model.BubbleModel;
import com.cvilia.bubble.mvp.contact.BubbleContact;
import com.cvilia.bubble.mvp.presenter.BubblePresenter;
import com.cvilia.bubble.route.PageUrlConfig;

@Route(path = PageUrlConfig.MAIN_PAGE)
public class BubbleActivity extends BaseActivity<BubblePresenter> implements BubbleContact.View, OnItemClickListener {

    private ActivityBubbleBinding mBindings;

    @Override
    public void showAllComponent(BubbleModel bubble) {
        if (bubble != null) {
            GridLayoutManager gridManager = new GridLayoutManager(this, 3);
            BubbleAdapter adapter = new BubbleAdapter(R.layout.item_bubble_grid, bubble.getData(), this);
            adapter.setOnItemClickListener(this);
            mBindings.bubbleRv.setAdapter(adapter);
            mBindings.bubbleRv.setLayoutManager(gridManager);
        }
    }

    @Override
    public boolean registerEventBus() {
        return false;
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivityBubbleBinding.inflate(getLayoutInflater());
        return mBindings.getRoot();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void getData() {
        mPresenter.getAllComponent(this);
    }

    @Override
    protected BubblePresenter getPresenter() {
        return new BubblePresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        Data data = (Data) adapter.getItem(position);
        if (data != null) {
            ARouter.getInstance().build(data.getArouter()).navigation();
        }
    }
}