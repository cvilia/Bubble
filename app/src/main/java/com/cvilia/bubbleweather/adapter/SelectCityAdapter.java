package com.cvilia.bubbleweather.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.bean.City;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: lzy
 * date: 2020/9/11
 * describe：选择城市页面
 */
public class SelectCityAdapter extends BaseQuickAdapter<City, BaseViewHolder> {

    private List<City> cities;

    public SelectCityAdapter(int layoutResId, @Nullable List<City> data) {
        super(layoutResId, data);
        this.cities = data;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, City city) {
        baseViewHolder.setText(R.id.cityName, city.getLeaderZh() + " · " + city.getCityZh());
    }
}
