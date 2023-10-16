package com.cvilia.bubble.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvilia.bubble.R;

public class BubbleAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;

    public BubbleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bubble_main_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((TextView) (holder.view.findViewById(R.id.itemTv))).setText("这里是第" + (position + 1) + "个item");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
