package com.cvilia.bubbleweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cvilia.bubbleweather.R;

import java.lang.ref.WeakReference;

/**
 * author: lzy
 * date: 1/28/21
 * describe：我的城市adapter
 */
public class MyCitiesAdapter extends RecyclerView.Adapter<MyCitiesAdapter.ViewHolder> {


    private WeakReference<AppCompatActivity> weakActivity;

    public MyCitiesAdapter(Context context) {
        this.weakActivity = new WeakReference<>((AppCompatActivity) context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final AppCompatActivity activity = weakActivity.get();
        View view = LayoutInflater.from(activity).inflate(R.layout.my_cities_item, parent, false);
        return new MyCitiesAdapter.ViewHolder(view);
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
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
