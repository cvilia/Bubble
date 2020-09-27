package com.cvilia.bubbleweather.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;
import com.cvilia.bubbleweather.bean.WeatherInfo;
import com.cvilia.bubbleweather.utils.TempParseUtil;
import com.cvilia.bubbleweather.view.SingleWeatherLine;

import java.util.ArrayList;
import java.util.List;

import static com.cvilia.bubbleweather.bean.Day7WeatherBean.DataBean.*;

/**
 * author: lzy
 * date: 2020/9/27
 * describe：描述
 */
public class Hour7Adapter extends RecyclerView.Adapter<Hour7Adapter.ViewHolder> {


    private static final String TAG = Hour7Adapter.class.getSimpleName();

    private Day7WeatherBean day7Bean;
    private List<WeatherInfo> infos;
    private Context context;
    private List<String> hours;

    public Hour7Adapter(Day7WeatherBean day7Bean, Context context) {
        this.day7Bean = day7Bean;
        this.context = context;
        getInfos();
    }

    private void getInfos() {
        infos = new ArrayList<>();
        hours = new ArrayList<>();
        if (day7Bean.getData().size() > 0) {
            List<HoursBean> datas = day7Bean.getData().get(0).getHours();
            int index = 0;//用来记录抛出异常的数据
            try {
                if (datas.size() >= 7) {
                    for (int i = 0; i < datas.size(); i++) {
                        index = i;
                        if (i == 7) {
                            break;
                        }
                        WeatherInfo info = new WeatherInfo(0, 0, TempParseUtil.getTemp(datas.get(i).getTem()));
                        hours.add(datas.get(i).getDay().substring(3));
                        infos.add(info);
                    }
                } else {
                    for (int i = 0; i < 7; i++) {
                        WeatherInfo info = null;
                        String hourName = "";
                        if (i < datas.size()) {
                            index = i;
                            info = new WeatherInfo(0, 0, TempParseUtil.getTemp(datas.get(i).getTem()));
                            hourName = datas.get(i).getDay().substring(3);
                        } else {
                            info = new WeatherInfo(0, 0, TempParseUtil.getTemp(day7Bean.getData().get(1).getHours().get(i - datas.size()).getTem()));
                            hourName = day7Bean.getData().get(1).getHours().get(i - datas.size()).getDay().substring(3);
                        }
                        infos.add(info);
                        hours.add(hourName);
                    }
                }


            } catch (Exception e) {
                Log.e(TAG, "第" + index + "条数据" + datas.get(0).getDay() + "出错");
            }
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hour7_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((TextView) (holder.view.findViewById(R.id.weekTv))).setText(hours.get(position));
        ((TextView) (holder.view.findViewById(R.id.weatherDescTv))).setText(day7Bean.getData().get(position).getWea());
        SingleWeatherLine<WeatherInfo> singleView = holder.view.findViewById(R.id.lineView);
        singleView.setDatas(infos, position);
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
