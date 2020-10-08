package com.cvilia.bubbleweather.pages.selectcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.location.AMapLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.R2;
import com.cvilia.bubbleweather.adapter.ProvinceAdapter;
import com.cvilia.bubbleweather.adapter.SelectCityAdapter;
import com.cvilia.bubbleweather.base.BaseActivity;
import com.cvilia.bubbleweather.bean.City;
import com.cvilia.bubbleweather.config.Constants;
import com.cvilia.bubbleweather.config.PageUrlConfig;
import com.cvilia.bubbleweather.utils.MMKVUtil;
import com.cvilia.bubbleweather.view.ProvinceDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择城市
 */
@Route(path = PageUrlConfig.SELECT_CITY_PAGE)
public class SelectCityActivity extends BaseActivity<SelectCityPresenter> implements SelectCityContact.View,
        View.OnClickListener {

    @BindView(R2.id.cityRecyclerView)
    RecyclerView mCityRecycler;
    @BindView(R2.id.provienceRecyclerView)
    RecyclerView mProvinceRecycler;

    @BindView(R2.id.centerTitleTv)
    TextView mTitleView;

    @BindView(R2.id.currentLocationRl)
    RelativeLayout mCurrentRl;

    @BindView(R2.id.cityNameTv)
    TextView mCityNameTv;

    private List<City> mCityInfos;
    private List<String> mProvinces;//省份
    private List<String> mCities;//市
    private List<String> mdistricts;//区

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
        super.onViewCreated();
        mPresenter.startLocate();
    }

    @Override
    protected void initWidgetEvent() {
        mCityRecycler.setLayoutManager(new LinearLayoutManager(this));
        mProvinceRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mTitleView.setText("城市选择");
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
        currentCity = location.getCity();
        mCityNameTv.setText(currentCity);
    }

    @Override
    public void locateFailed() {
        mCityNameTv.setText(currentCity);
    }

    /**
     * 获取省份
     */
    private void getProvinces() {

        List<String> list = new ArrayList<>();
        for (City city : mCityInfos) {
            list.add(city.getProvinceZh());
        }

        mProvinces = new ArrayList<>();

        for (String province : list) {
            if (!mProvinces.contains(province)) {
                mProvinces.add(province);
            }
        }
        mProvinceRecycler.addItemDecoration(new ProvinceDivider(this));
        ProvinceAdapter adapter = new ProvinceAdapter(R.layout.layout_province_item, mProvinces);
        mProvinceRecycler.setAdapter(adapter);
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
     * @param selectedInfos
     */
    private void getSlectedProvinceInfo(List<City> selectedInfos) {
        SelectCityAdapter adapter = new SelectCityAdapter(R.layout.layout_city_item, selectedInfos);
        mCityRecycler.setAdapter(adapter);
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

    @OnClick({R2.id.backIv, R2.id.currentLocationRl})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backIv:
                finish();
                break;
            case R.id.currentLocationRl:
                Intent intent = new Intent();
                intent.putExtra("cityCode", "");
                intent.putExtra("cityName", currentCity);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}