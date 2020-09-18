package com.cvilia.bubbleweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cvilia.bubbleweather.R;
import com.cvilia.bubbleweather.bean.Day7WeatherBean;
import com.cvilia.bubbleweather.bean.WeatherInfo;
import com.cvilia.bubbleweather.view.WeatherLineView;

import java.util.List;

/**
 * author: lzy
 * date: 2020/9/18
 * describe：描述
 */
public class Day7Adapter extends RecyclerView.Adapter<Day7Adapter.ViewHolder> {

    private Day7WeatherBean day7Bean;
    private List<WeatherInfo> infos;
    private Context context;
    private int maxTemp;
    private int minTemp;

    public Day7Adapter(Day7WeatherBean day7Bean, List<WeatherInfo> infos, Context context) {
        this.day7Bean = day7Bean;
        this.infos = infos;
        this.context = context;
        getExtremeTemp(infos);
    }

    /**
     * 计算最大和最小天气
     *
     * @param infos
     */
    private void getExtremeTemp(List<WeatherInfo> infos) {

        int[] heigh = new int[7];
        int[] low = new int[7];
        for (int i = 0; i < 7; i++) {
            heigh[i] = infos.get(i).getMaxTemp();
            low[i] = infos.get(i).getMinTemp();
        }

        int[] array = new int[heigh.length + low.length];

        for (int i = 0; i < 14; i++) {
            if (i < 7) {
                array[i] = heigh[i];
            } else {
                array[i] = low[i-7];
            }
        }
        this.maxTemp = result(array,true);
        this.minTemp = result(array,false);

    }

    /**
     * 冒泡排序求最大最小值
     *
     * @param nums
     * @param needMax
     * @return
     */
    private int result(int[] nums, boolean needMax) {

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int s = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = s;
                }
            }

        }
        for (int temp : nums) {
            System.out.print(temp + "---");
        }
        return needMax ? nums[nums.length - 1] : nums[0];
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_7_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((TextView) (holder.view.findViewById(R.id.weekTv))).setText(getWeekNick(day7Bean.getData().get(position).getWeek()));
        ((TextView) (holder.view.findViewById(R.id.weatherDescTv))).setText(day7Bean.getData().get(position).getWea());
        WeatherLineView<WeatherInfo> weatherLineView = holder.view.findViewById(R.id.lineView);
        weatherLineView.setDatas(infos, maxTemp, minTemp, position);

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
