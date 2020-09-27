package com.cvilia.bubbleweather.bean;

import com.cvilia.bubbleweather.listener.ITempData;

/**
 * author: lzy
 * date: 2020/9/18
 * describe：只包含温度信息的天气类7天
 */
public class WeatherInfo implements ITempData {

    private int maxTemp;
    private int minTemp;
    private int normalTemp;//画但曲线的时候用

    public WeatherInfo(int maxTemp, int minTemp,int normalTemp) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.normalTemp = normalTemp;
    }

    @Override
    public int getMaxTemp() {
        return maxTemp;
    }

    @Override
    public int getMinTemp() {
        return minTemp;
    }

    @Override
    public int getNormalTemp() {
        return normalTemp;
    }
}
