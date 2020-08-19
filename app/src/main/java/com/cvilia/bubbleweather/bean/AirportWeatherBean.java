package com.cvilia.bubbleweather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：机场天气
 */
public class AirportWeatherBean implements Serializable {

    /**
     * iata : SHE
     * airport : 沈阳桃仙国际机场
     * update_time : 2019-04-18 18:00
     * data : [{"hour":"08时","wea":"多云","tem1":11,"tem2":8,"win":"北风","win_speed":"5.9","humidity":"43.0","rain":"0.00"},{"hour":"11时","wea":"晴","tem1":12,"tem2":9,"win":"北风","win_speed":"6.4","humidity":"25.7","rain":"0.00"},{"hour":"14时","wea":"晴","tem1":14,"tem2":12,"win":"北风","win_speed":"5.8","humidity":"21.2","rain":"0.00"},{"hour":"17时","wea":"晴","tem1":14,"tem2":13,"win":"北风","win_speed":"4.8","humidity":"20.0","rain":"0.00"},{"hour":"20时","wea":"多云","tem1":13,"tem2":6,"win":"北风","win_speed":"2.6","humidity":"43.8","rain":"0.00"},{"hour":"23时","wea":"阴","tem1":7,"tem2":3,"win":"东北风","win_speed":"1.8","humidity":"53.5","rain":"0.00"},{"hour":"02时","wea":"阴","tem1":4,"tem2":2,"win":"西南风","win_speed":"1.3","humidity":"74.4","rain":"0.00"},{"hour":"05时","wea":"阴","tem1":3,"tem2":2,"win":"北风","win_speed":"0.9","humidity":"75.2","rain":"0.00"},{"hour":"08时","wea":"晴","tem1":11,"tem2":2,"win":"西北风","win_speed":"0.7","humidity":"38.4","rain":"0.00"}]
     */

    private String iata;
    private String airport;
    private String update_time;
    private List<DataBean> data;

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * hour : 08时
         * wea : 多云
         * tem1 : 11
         * tem2 : 8
         * win : 北风
         * win_speed : 5.9
         * humidity : 43.0
         * rain : 0.00
         */

        private String hour;
        private String wea;
        private int tem1;
        private int tem2;
        private String win;
        private String win_speed;
        private String humidity;
        private String rain;

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getWea() {
            return wea;
        }

        public void setWea(String wea) {
            this.wea = wea;
        }

        public int getTem1() {
            return tem1;
        }

        public void setTem1(int tem1) {
            this.tem1 = tem1;
        }

        public int getTem2() {
            return tem2;
        }

        public void setTem2(int tem2) {
            this.tem2 = tem2;
        }

        public String getWin() {
            return win;
        }

        public void setWin(String win) {
            this.win = win;
        }

        public String getWin_speed() {
            return win_speed;
        }

        public void setWin_speed(String win_speed) {
            this.win_speed = win_speed;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getRain() {
            return rain;
        }

        public void setRain(String rain) {
            this.rain = rain;
        }
    }
}
