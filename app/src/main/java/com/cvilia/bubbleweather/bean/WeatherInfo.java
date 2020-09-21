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

    public WeatherInfo(int maxTemp, int minTemp) {
        this.maxTemp = maxTemp;
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
