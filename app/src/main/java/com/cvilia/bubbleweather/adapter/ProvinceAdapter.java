package com.cvilia.bubbleweather.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubbleweather.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: lzy
 * date: 2020/9/28
 * describe：描述
 */
public class ProvinceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private List<String> list;

    public ProvinceAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.list = data;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String province) {
        baseViewHolder.setText(R.id.provinceTv, province);
    }
}
