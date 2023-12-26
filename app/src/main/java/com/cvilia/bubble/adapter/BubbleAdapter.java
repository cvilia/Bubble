package com.cvilia.bubble.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubble.R;
import com.cvilia.bubble.model.BubbleModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class BubbleAdapter extends BaseQuickAdapter<BubbleModel.Data, BaseViewHolder> {
    private WeakReference<Activity> weakActivity;

    public BubbleAdapter(int layoutResId, @Nullable List<BubbleModel.Data> data, Context context) {
        super(layoutResId, data);
        weakActivity = new WeakReference<>((Activity) context);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, BubbleModel.Data data) {
        //todo 加载图片会占用大量内存，寻找更优方案
//        final Activity activity = weakActivity.get();
        holder.setText(com.cvilia.bubble.R.id.componentTitle, data.getTitle());
        ImageView imageView = holder.getView(R.id.componentIv);
//        Glide.with(activity).load(data.getSrcUrl()).thumbnail(0.0025f).into(imageView);
    }
}
