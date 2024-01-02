package com.cvilia.bubble.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.base.util.DeviceUtil;
import com.cvilia.bubble.R;
import com.cvilia.bubble.utils.DisplayUtil;
import com.qweather.sdk.bean.geo.GeoBean;
import com.qweather.sdk.bean.geo.GeoBean.LocationBean;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author: lzy
 * date: 2020/9/28
 * describe：单个TextView的控件，背景为灰色圆角形状
 */
public class TopCityAdapter extends BaseQuickAdapter<LocationBean, BaseViewHolder> {

    private WeakReference<FragmentActivity> weak;

    public TopCityAdapter(@Nullable List<LocationBean> data, Context context) {
        super(R.layout.layout_single_text_item, data);
        this.weak = new WeakReference<>((FragmentActivity) context);
    }


    @NotNull
    @Override
    protected BaseViewHolder createBaseViewHolder(@NotNull View view) {
        FragmentActivity activity = weak.get();
        view.getLayoutParams().width = (DeviceUtil.getPxWidth(activity) - DisplayUtil.dp2px(activity, 70)) / 3;
        return super.createBaseViewHolder(view);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, LocationBean cityInfo) {
        holder.setText(R.id.topCityTv, cityInfo.getName() + "·" + cityInfo.getAdm2());
    }
}
