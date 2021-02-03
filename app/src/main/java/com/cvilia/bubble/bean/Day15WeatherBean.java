package com.cvilia.bubble.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：15日天气
 */
public class Day15WeatherBean implements Serializable {


    /**
     * cityid : 101120201
     * update_time : 2020-08-18 15:08:08
     * city : 青岛
     * data : [{"date_nl":"廿九","date":"2020-08-18","week":"星期二","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"30","tem2":"26","win":"南风3-4级","win_day":"南风3-4级","win_night":"南风3-4级"},{"date_nl":"初一","date":"2020-08-19","week":"星期三","wea":"阴转小雨","wea_c":"02","wea_img":"yu","wea_day":"阴","wea_night":"小雨","tem1":"30","tem2":"26","win":"南风3-4级","win_day":"南风3-4级","win_night":"南风3-4级"},{"date_nl":"初二","date":"2020-08-20","week":"星期四","wea":"小雨","wea_c":"07","wea_img":"yu","wea_day":"小雨","wea_night":"小雨","tem1":"29","tem2":"23","win":"南风3-4级转北风3-4级","win_day":"南风3-4级","win_night":"北风3-4级"},{"date_nl":"初三","date":"2020-08-21","week":"星期五","wea":"阴","wea_c":"02","wea_img":"yin","wea_day":"阴","wea_night":"阴","tem1":"27","tem2":"24","win":"北风3-4级转东北风3-4级","win_day":"北风3-4级","win_night":"东北风3-4级"},{"date_nl":"初四","date":"2020-08-22","week":"星期六","wea":"小雨转多云","wea_c":"07","wea_img":"yun","wea_day":"小雨","wea_night":"多云","tem1":"27","tem2":"25","win":"东南风3-4级转南风3-4级","win_day":"东南风3-4级","win_night":"南风3-4级"},{"date_nl":"初五","date":"2020-08-23","week":"星期日","wea":"多云转小雨","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"小雨","tem1":"28","tem2":"27","win":"南风4-5级","win_day":"南风4-5级","win_night":"南风4-5级"},{"date_nl":"初六","date":"2020-08-24","week":"星期一","wea":"小雨转中雨","wea_c":"07","wea_img":"yu","wea_day":"小雨","wea_night":"中雨","tem1":"29","tem2":"25","win":"南风4-5级转西风4-5级","win_day":"南风4-5级","win_night":"西风4-5级"},{"date_nl":"初七","date":"2020-08-25","week":"星期二","wea":"雨","wea_c":"301","wea_img":"yu","wea_day":"雨","wea_night":"雨","tem1":"28","tem2":"24","win":"微风","win_day":"微风","win_night":"微风"},{"date_nl":"初八","date":"2020-08-26","week":"星期三","wea":"雨","wea_c":"301","wea_img":"yu","wea_day":"雨","wea_night":"雨","tem1":"29","tem2":"24","win":"微风","win_day":"微风","win_night":"微风"},{"date_nl":"初九","date":"2020-08-27","week":"星期四","wea":"雨转多云","wea_c":"301","wea_img":"yun","wea_day":"雨","wea_night":"多云","tem1":"28","tem2":"23","win":"微风","win_day":"微风","win_night":"微风"},{"date_nl":"初十","date":"2020-08-28","week":"星期五","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"28","tem2":"22","win":"微风","win_day":"微风","win_night":"微风"},{"date_nl":"十一","date":"2020-08-29","week":"星期六","wea":"多云转雨","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"雨","tem1":"28","tem2":"23","win":"微风","win_day":"微风","win_night":"微风"},{"date_nl":"十二","date":"2020-08-30","week":"星期日","wea":"雨转阴","wea_c":"301","wea_img":"yu","wea_day":"雨","wea_night":"阴","tem1":"28","tem2":"21","win":"微风","win_day":"微风","win_night":"微风"},{"date_nl":"十三","date":"2020-08-31","week":"星期一","wea":"阴","wea_c":"02","wea_img":"yin","wea_day":"阴","wea_night":"阴","tem1":"25","tem2":"20","win":"东北风3-4级转微风","win_day":"东北风3-4级","win_night":"微风"},{"date_nl":"十四","date":"2020-09-01","week":"星期二","wea":"阴转多云","wea_c":"02","wea_img":"yun","wea_day":"阴","wea_night":"多云","tem1":"24","tem2":"20","win":"微风","win_day":"微风","win_night":"微风"},{"date_nl":"十五","date":"2020-09-02","week":"星期三","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"27","tem2":"23","win":"","win_day":"","win_night":""},{"date_nl":"十六","date":"2020-09-03","week":"星期四","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"27","tem2":"23","win":"","win_day":"","win_night":""},{"date_nl":"十七","date":"2020-09-04","week":"星期五","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"27","tem2":"23","win":"","win_day":"","win_night":""},{"date_nl":"十八","date":"2020-09-05","week":"星期六","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"27","tem2":"23","win":"","win_day":"","win_night":""},{"date_nl":"十九","date":"2020-09-06","week":"星期日","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"27","tem2":"23","win":"","win_day":"","win_night":""},{"date_nl":"二十","date":"2020-09-07","week":"星期一","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"27","tem2":"22","win":"","win_day":"","win_night":""},{"date_nl":"廿一","date":"2020-09-08","week":"星期二","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"26","tem2":"22","win":"","win_day":"","win_night":""},{"date_nl":"廿二","date":"2020-09-09","week":"星期三","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"26","tem2":"21","win":"","win_day":"","win_night":""},{"date_nl":"廿三","date":"2020-09-10","week":"星期四","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"26","tem2":"21","win":"","win_day":"","win_night":""},{"date_nl":"廿四","date":"2020-09-11","week":"星期五","wea":"小雨","wea_c":"07","wea_img":"yu","wea_day":"小雨","wea_night":"小雨","tem1":"26","tem2":"21","win":"","win_day":"","win_night":""},{"date_nl":"廿五","date":"2020-09-12","week":"星期六","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"26","tem2":"21","win":"","win_day":"","win_night":""},{"date_nl":"廿六","date":"2020-09-13","week":"星期日","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"26","tem2":"21","win":"","win_day":"","win_night":""},{"date_nl":"廿七","date":"2020-09-14","week":"星期一","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"21","win":"","win_day":"","win_night":""},{"date_nl":"廿八","date":"2020-09-15","week":"星期二","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"21","win":"","win_day":"","win_night":""},{"date_nl":"廿九","date":"2020-09-16","week":"星期三","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初一","date":"2020-09-17","week":"星期四","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初二","date":"2020-09-18","week":"星期五","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初三","date":"2020-09-19","week":"星期六","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初四","date":"2020-09-20","week":"星期日","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"24","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初五","date":"2020-09-21","week":"星期一","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初六","date":"2020-09-22","week":"星期二","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"25","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初七","date":"2020-09-23","week":"星期三","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"24","tem2":"20","win":"","win_day":"","win_night":""},{"date_nl":"初八","date":"2020-09-24","week":"星期四","wea":"小雨","wea_c":"07","wea_img":"yu","wea_day":"小雨","wea_night":"小雨","tem1":"24","tem2":"19","win":"","win_day":"","win_night":""},{"date_nl":"初九","date":"2020-09-25","week":"星期五","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"24","tem2":"19","win":"","win_day":"","win_night":""},{"date_nl":"初十","date":"2020-09-26","week":"星期六","wea":"多云","wea_c":"01","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem1":"24","tem2":"19","win":"","win_day":"","win_night":""}]
     */

    private String cityid;
    private String update_time;
    private String city;
    private List<DataBean> data;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * date_nl : 廿九
         * date : 2020-08-18
         * week : 星期二
         * wea : 多云
         * wea_c : 01
         * wea_img : yun
         * wea_day : 多云
         * wea_night : 多云
         * tem1 : 30
         * tem2 : 26
         * win : 南风3-4级
         * win_day : 南风3-4级
         * win_night : 南风3-4级
         */

        private String date_nl;
        private String date;
        private String week;
        private String wea;
        private String wea_c;
        private String wea_img;
        private String wea_day;
        private String wea_night;
        private String tem1;
        private String tem2;
        private String win;
        private String win_day;
        private String win_night;

        public String getDate_nl() {
            return date_nl;
        }

        public void setDate_nl(String date_nl) {
            this.date_nl = date_nl;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWea() {
            return wea;
        }

        public void setWea(String wea) {
            this.wea = wea;
        }

        public String getWea_c() {
            return wea_c;
        }

        public void setWea_c(String wea_c) {
            this.wea_c = wea_c;
        }

        public String getWea_img() {
            return wea_img;
        }

        public void setWea_img(String wea_img) {
            this.wea_img = wea_img;
        }

        public String getWea_day() {
            return wea_day;
        }

        public void setWea_day(String wea_day) {
            this.wea_day = wea_day;
        }

        public String getWea_night() {
            return wea_night;
        }

        public void setWea_night(String wea_night) {
            this.wea_night = wea_night;
        }

        public String getTem1() {
            return tem1;
        }

        public void setTem1(String tem1) {
            this.tem1 = tem1;
        }

        public String getTem2() {
            return tem2;
        }

        public void setTem2(String tem2) {
            this.tem2 = tem2;
        }

        public String getWin() {
            return win;
        }

        public void setWin(String win) {
            this.win = win;
        }

        public String getWin_day() {
            return win_day;
        }

        public void setWin_day(String win_day) {
            this.win_day = win_day;
        }

        public String getWin_night() {
            return win_night;
        }

        public void setWin_night(String win_night) {
            this.win_night = win_night;
        }
    }
}
