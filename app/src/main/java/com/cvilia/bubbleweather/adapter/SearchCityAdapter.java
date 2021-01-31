package com.cvilia.bubbleweather.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubbleweather.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author:lzy
 * date:2021-02-01-00-25
 * describe:
 */
public class SearchCityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchCityAdapter(@Nullable List<String> data) {
        super(R.layout.item_search_city, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String city) {
        TextView cityName = baseViewHolder.itemView.findViewById(R.id.cityNameTv);
        cityName.setText(city);
    }
}
