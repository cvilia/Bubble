package com.cvilia.bubbleweather.activity.cities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.location.AMapLocation;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.adapter.ProvinceAdapter;
import com.cvilia.bubbleweather.adapter.SelectCityAdapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.City;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.route.PageUrlConfig;
import com.cvilia.bubbleweather.databinding.ActivitySelectCityBinding;
import com.cvilia.bubbleweather.utils.MMKVUtil;
import com.cvilia.bubbleweather.view.ProvinceDivider;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择城市
 */
@Route(path = PageUrlConfig.SELECT_CITY_PAGE)
public class SelectCityActivity extends BaseActivity<SelectCityPresenter> implements SelectCityContact.View,
        View.OnClickListener {

    private ActivitySelectCityBinding mBindings;


    private List<City> mCityInfos;

    private String currentCity = "北京市";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void onViewCreated() {
        StatusBarUtil.setLightMode(this);
        mPresenter.startLocate();
    }

    @Override
    protected View inflatRootView() {
        mBindings = ActivitySelectCityBinding.inflate(getLayoutInflater());

        return mBindings.getRoot();
    }

    @Override
    protected void initWidgetEvent() {
        mBindings.cityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBindings.provienceRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mBindings.actionBar.centerTitleTv.setText("城市选择");
        mBindings.currentLocationRl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.readDb();
    }

    @Override
    protected void getIntentData() {

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
            this.mCityInfos = cityList;
//            SelectCityAdapter adapter = new SelectCityAdapter(R.layout.layout_city_item, cityList);
//            adapter.setOnItemClickListener(this);
//            mCityRecycler.setAdapter(adapter);
//            this.mCityInfos = cityList;
            getProvinces();
        }
    }

    @Override
    public void locateSuccess(AMapLocation location) {
        currentCity = location.getDistrict();
        mBindings.cityNameTv.setText(String.format("%s · %s · %s", location.getProvince(), location.getCity(), currentCity));
    }

    @Override
    public void locateFailed() {
        mBindings.cityNameTv.setText(currentCity);
    }

    /**
     * 获取省份
     */
    private void getProvinces() {

        List<String> list = new ArrayList<>();
        for (City city : mCityInfos) {
            list.add(city.getProvinceZh());
        }

        //省份
        List<String> mProvinces = new ArrayList<>();

        for (String province : list) {
            if (!mProvinces.contains(province)) {
                mProvinces.add(province);
            }
        }
        mBindings.provienceRecyclerView.addItemDecoration(new ProvinceDivider(this));
        ProvinceAdapter adapter = new ProvinceAdapter(R.layout.layout_province_item, mProvinces);
        mBindings.provienceRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            List<City> selectedInfos = new ArrayList<>();
            for (City city : mCityInfos) {
                if (city.getProvinceZh().equals(adapter.getItem(position))) {
                    selectedInfos.add(city);
                }
            }
            getSlectedProvinceInfo(selectedInfos);
        });
    }


    /**
     * 加载选定省份的城市
     *
     * @param selectedInfos 选择的省份信息
     */
    private void getSlectedProvinceInfo(List<City> selectedInfos) {
        SelectCityAdapter adapter = new SelectCityAdapter(R.layout.layout_city_item, selectedInfos);
        mBindings.cityRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            City city = selectedInfos.get(position);
            MMKVUtil.saveString(Constants.SELECTED_CITY, city.getCityZh());
            Intent intent = new Intent();
            intent.putExtra("cityCode", city.getId());
            intent.putExtra("cityName", city.getCityZh());
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.backIv) {
            finish();
        }
        if (v.getId() == R.id.currentLocationRl) {
            Intent intent = new Intent();
            intent.putExtra("cityCode", "");
            intent.putExtra("cityName", currentCity);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}