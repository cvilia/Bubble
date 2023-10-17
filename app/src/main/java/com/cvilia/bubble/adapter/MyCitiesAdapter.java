package com.cvilia.bubble.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubble.R;
import com.cvilia.bubble.config.Constants;
import com.cvilia.bubble.utils.MMKVUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author: lzy
 * date: 1/28/21
 * describe：我的城市adapter
 */
public class MyCitiesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private WeakReference<AppCompatActivity> weakActivity;

    public MyCitiesAdapter(Context context, String city) {
        super(R.layout.item_my_cities_layout);
        setData(city);
    }

    private void setData(String city) {
        List<String> cities = new ArrayList<>();
        String json = MMKVUtil.getString(Constants.MY_CITIES, null);
        if (TextUtils.isEmpty(json)) {
            cities.add(city);
        } else {
            cities.addAll(Arrays.asList(json.split(",")));
        }
        setList(cities);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, String city) {
        if (getItemPosition(city) == 0) {
            if (holder.findView(R.id.cityIv) != null) {
                holder.findView(R.id.cityIv).setVisibility(View.VISIBLE);
            }
        }
    }
}
