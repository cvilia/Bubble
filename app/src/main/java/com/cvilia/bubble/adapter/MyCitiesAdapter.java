package com.cvilia.bubble.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
public class MyCitiesAdapter extends RecyclerView.Adapter<ViewHolder> {
    private WeakReference<AppCompatActivity> weakActivity;
    private List<String> cities;

    public MyCitiesAdapter(Context context, @NonNull String city) {
        this.weakActivity = new WeakReference<>((AppCompatActivity) context);
        initCities(city);
    }

    /**
     * 当前1.0.1版本首页只显示一个城市，为当前定位或者用户手动选择
     * 用户可以手动选择五座城市
     *
     * @param city
     */
    private void initCities(String city) {
        cities = new ArrayList<>();
        String json = MMKVUtil.getString(Constants.MY_CITIES, null);
        if (TextUtils.isEmpty(json)) {
            cities.add(city);
        } else {
            cities.addAll(Arrays.asList(json.split(",")));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final AppCompatActivity activity = weakActivity.get();
        View view = LayoutInflater.from(activity).inflate(R.layout.my_cities_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AppCompatActivity activity = weakActivity.get();
//        if (position % 2 == 0) {
//            holder.view.findViewById(R.id.rootId).setBackgroundResource(R.drawable.round_rectangle_blue);
//        }
        if (position == 0) {
            holder.view.findViewById(R.id.cityIv).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

}
