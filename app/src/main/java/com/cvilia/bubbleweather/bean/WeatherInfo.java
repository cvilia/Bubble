package com.cvilia.bubbleweather.bean;

import com.cvilia.bubbleweather.listener.ITempData;

/**
 * author: lzy
 * date: 2020/9/18
 * describe：只包含温度信息的天气类
 */
public class WeatherInfo implements ITempData {

    private int maxTemp;
    private int minTemp;

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    @Override
    public int getMaxTemp() {
        return maxTemp;
    }

    @Override
    public int getMinTemp() {
        return minTemp;
    }
}
