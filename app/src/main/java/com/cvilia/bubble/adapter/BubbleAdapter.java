package com.cvilia.bubble.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubble.R;
import com.cvilia.bubble.bean.Music;

import java.util.List;

public class BubbleAdapter extends BaseQuickAdapter<Music, BaseViewHolder> {

    private Context context;

    public BubbleAdapter(List<Music> musics) {
        super(R.layout.item_bubble_main_layout, musics);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, Music music) {
        holder.setText(R.id.musicNameTv, music.getName().split("\\.")[0]);
        holder.setText(R.id.singerTv, music.getSinger());
    }
}
