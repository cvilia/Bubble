package com.cvilia.bubble.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cvilia.bubble.R;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.cvilia.bubble.bean.Day7WeatherBean.DataBean.HoursBean;

/**
 * author: lzy
 * date: 2020/9/27
 * describe：描述
 */
public class Hour7Adapter extends RecyclerView.Adapter<ViewHolder> {


    private static final String TAG = Hour7Adapter.class.getSimpleName();
    private final List<HoursBean> hoursInfo;
    //    private Context context;
    private WeakReference<AppCompatActivity> weakActivity;

    public Hour7Adapter(List<HoursBean> hoursInfo, Context context) {
        this.hoursInfo = hoursInfo;
        this.weakActivity = new WeakReference<>((AppCompatActivity) context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final AppCompatActivity activity = weakActivity.get();
        View view = LayoutInflater.from(activity).inflate(R.layout.item_hour7_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoursBean hoursBean = hoursInfo.get(position);
        ((TextView) (holder.view.findViewById(R.id.hourTv))).setText(String.format("%s:00", hoursBean.getHours().substring(0, hoursBean.getHours().length() - 1)));
        ((TextView) (holder.view.findViewById(R.id.windLevelTv))).setText(hoursBean.getWin_speed().substring(0, hoursBean.getHours().length() - 1));
        ((TextView) (holder.view.findViewById(R.id.tempTv))).setText(String.format("%s°", hoursBean.getTem()));
        ((ImageView) (holder.view.findViewById(R.id.windDriectionIv))).setImageDrawable(getWindDriectionImg(hoursBean.getWin()));
        ((TextView) (holder.view.findViewById(R.id.windLevelTv))).setText(hoursBean.getWin_speed().substring(0, hoursBean.getWin_speed().length() - 1));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private Drawable getWindDriectionImg(String win) {
        final AppCompatActivity activity = weakActivity.get();
        int drawableId = R.drawable.icon_east;
        if (win.contains("西")) {
            if (win.equals("西风")) {
                drawableId = R.drawable.icon_west;
            } else if (win.equals("西南风")) {
                drawableId = R.drawable.icon_southwest;
            } else {
                drawableId = R.drawable.icon_northwest;
            }
        } else if (win.contains("东")) {
            if (win.equals("东风")) {
                drawableId = R.drawable.icon_east;
            } else if (win.equals("东南风")) {
                drawableId = R.drawable.icon_southeast;
            } else {
                drawableId = R.drawable.icon_northeast;
            }
        } else if (win.equals("北风")) {
            drawableId = R.drawable.icon_north;
        } else {
            drawableId = R.drawable.icon_south;
        }
        return ResourcesCompat.getDrawable(activity.getResources(), drawableId, null);
    }

    @Override
    public int getItemCount() {
        return hoursInfo.size();
    }

//    class ViewHolder extends RecyclerView.ViewHolder {
//
//        public View view;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            view = itemView;
//        }
//    }
}
