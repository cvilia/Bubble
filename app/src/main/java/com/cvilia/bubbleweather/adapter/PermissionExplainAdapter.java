package com.cvilia.bubbleweather.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubbleweather.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：启动页权限说明弹窗权限列表recyclerview adapter
 */
public class PermissionExplainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PermissionExplainAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, String text) {
        holder.setText(R.id.permissionNameTv,text);
    }
}
