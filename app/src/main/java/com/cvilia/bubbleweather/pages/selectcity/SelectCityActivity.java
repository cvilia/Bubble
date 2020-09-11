package com.cvilia.bubbleweather.pages.selectcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.R2;
import com.cvilia.bubbleweather.adapter.SelectCityAdapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.City;
import com.cvilia.bubbleweather.config.PageUrlConfig;

import java.util.List;

import butterknife.BindView;

/**
 * 选择城市
 */
@Route(path = PageUrlConfig.SELECT_CITY_PAGE)
public class SelectCityActivity extends BaseActivity<SelectCityPresenter> implements SelectCityContact.View, OnItemClickListener {

    @BindView(R2.id.cityRecyclerView)
    RecyclerView mCityRecycler;

    private List<City> mCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    protected void initWidgetEvent() {
        mCityRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mPresenter.readDb();
    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_city;
    }

    @Override
    protected SelectCityPresenter getPresenter() {
        return new SelectCityPresenter();
    }

    @Override
    public void loading() {

    }

    @Override
    public void dismissLoading() {

    }


    @Override
    public void readDbSuccess(List<City> cityList) {
        if (cityList != null) {
            SelectCityAdapter adapter = new SelectCityAdapter(R.layout.layout_city_item, cityList);
            adapter.setOnItemClickListener(this);
            mCityRecycler.setAdapter(adapter);
            this.mCities = cityList;
        }
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
        City city = mCities.get(position);
        Intent intent = new Intent();
        intent.putExtra("cityCode", city.getId());
        intent.putExtra("cityName", city.getCityZh());
        setResult(RESULT_OK, intent);
        finish();
    }
}