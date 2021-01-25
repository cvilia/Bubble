package com.cvilia.bubbleweather.adapter;

import android.content.Context;
import android.text.TextUtils;
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
import com.cvilia.bubbleweather.view.WeatherLineView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lzy
 * date: 2020/9/18
 * describe：描述
 */
public class Day7Adapter extends RecyclerView.Adapter<Day7Adapter.ViewHolder> {

    private static final String TAG = Day7Adapter.class.getSimpleName();

    private Day7WeatherBean day7Bean;
    private List<WeatherInfo> infos;
    private Context context;

    public Day7Adapter(Day7WeatherBean day7Bean, Context context) {
        this.day7Bean = day7Bean;
        this.context = context;
        getInfos();
    }

    private void getInfos() {
        infos = new ArrayList<>();
        if (day7Bean.getData().size() > 0) {
            List<Day7WeatherBean.DataBean> datas = day7Bean.getData();
            int index = 0;//用来记录抛出异常的数据
            try {
                //todo 针对不为空的温度应该做类型转换异常判断（抛出并捕获异常，还需要针对温度字符串做正则表达式确认其只含有数字）
                for (int i = 0; i < day7Bean.getData().size(); i++) {
                    index = i;
                    int max = Integer.parseInt(datas.get(i).getTem1());
                    int min = Integer.parseInt(datas.get(i).getTem2());
                    WeatherInfo info = new WeatherInfo(max, min, 0);
                    infos.add(info);
                }
            } catch (Exception e) {
                Log.e(TAG, "第" + index + "条数据出错");
            }
        }

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.day7_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((TextView) (holder.view.findViewById(R.id.weekTv))).setText(getWeekNick(day7Bean.getData().get(position).getWeek()));
        ((TextView) (holder.view.findViewById(R.id.weatherDescTv))).setText(day7Bean.getData().get(position).getWea());
        WeatherLineView<WeatherInfo> weatherLineView = holder.view.findViewById(R.id.lineView);
        weatherLineView.setDatas(infos, position);
    }


    /**
     * 将星期一转换为周一
     *
     * @param week
     * @return
     */
    private String getWeekNick(String week) {
        if (week.contains("一"))
            return "周一";
        else if (week.contains("二"))
            return "周二";
        else if (week.contains("三"))
            return "周三";
        else if (week.contains("四"))
            return "周四";
        else if (week.contains("五"))
            return "周五";
        else if (week.contains("六"))
            return "周六";
        else
            return "周日";
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
