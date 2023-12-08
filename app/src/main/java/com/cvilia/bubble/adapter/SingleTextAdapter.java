package com.cvilia.bubble.adapter;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.base.util.DeviceUtil;
import com.cvilia.bubble.R;
import com.cvilia.bubble.utils.DisplayUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author: lzy
 * date: 2020/9/28
 * describe：单个TextView的控件，背景为灰色圆角形状
 */
public class SingleTextAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private WeakReference<AppCompatActivity> weak;

    public SingleTextAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.layout_single_text_item, data);
        this.weak = new WeakReference<>((AppCompatActivity) context);
    }

    @NotNull
    @Override
    protected BaseViewHolder createBaseViewHolder(@NotNull View view) {
        AppCompatActivity activity = weak.get();
        view.getLayoutParams().width = (DeviceUtil.getPxWidth(activity) - DisplayUtil.dp2px(activity, 70)) / 3;
        return super.createBaseViewHolder(view);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String content) {
        baseViewHolder.setText(R.id.contentTv, content);
    }
}
