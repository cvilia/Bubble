package com.cvilia.bubbleweather.activity.cities;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cvilia.bubbleweather.adapter.MyCitiesAdapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.databinding.ActivityMyCitiesBinding;
import com.cvilia.bubbleweather.route.PageUrlConfig;
import com.cvilia.bubbleweather.utils.DisplayUtil;
import com.jaeger.library.StatusBarUtil;

@Route(path = PageUrlConfig.CITIES_PAGE)
public class MyCitiesActivity extends BaseActivity<MyCitesPresenter> implements MyCitiesContact.View {

    private ActivityMyCitiesBinding mBindings;
    private MyCitiesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreated() {
        StatusBarUtil.setLightMode(this);
    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivityMyCitiesBinding.inflate(getLayoutInflater());
        return mBindings.getRoot();
    }

    @Override
    protected void initWidget() {
    }

    @Override
    protected void initWidgetEvent() {
        mBindings.searchLl.setOnClickListener(v -> ARouter.getInstance().build(PageUrlConfig.SELECT_CITY_PAGE).navigation(this));
        mBindings.backIv.setOnClickListener(v -> finish());
        mAdapter = new MyCitiesAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mBindings.recyclerView.setLayoutManager(manager);
        mBindings.recyclerView.setAdapter(mAdapter);
        mBindings.recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildAdapterPosition(view) == 0){
                    outRect.top = 0;
                }else {
                    outRect.top = DisplayUtil.dp2px(MyCitiesActivity.this,15);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected MyCitesPresenter getPresenter() {
        return new MyCitesPresenter();
    }


    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void requestSuccess() {

    }
}