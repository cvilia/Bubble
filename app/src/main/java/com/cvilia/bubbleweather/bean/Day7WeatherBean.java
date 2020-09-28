package com.cvilia.bubbleweather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: lzy
 * date: 2020/8/18
 * describe：七日内天气
 */
public class Day7WeatherBean implements Serializable {

    /**
     * cityid : 101110101
     * city : 西安
     * cityEn : xian
     * country : 中国
     * countryEn : China
     * update_time : 2019-04-18 11:30:00
     * data : [{"day":"18日（星期四）","date":"2019-04-18","week":"星期四","wea":"晴","wea_img":"qing","wea_day":"晴","wea_night":"晴","tem":"32","tem1":"34","tem2":"18","win":["东风","东北风"],"win_speed":"3-4级转4-5级","sunrise":"06:909","sunset":"19:17","air":"71","air_level":"良","air_tips":"空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"hours":[{"hours":"08时","wea":"晴","tem":"20","win":"东风","win_speed":"<3级"},{"hours":"09时","wea":"晴","tem":"21","win":"南风","win_speed":"<3级"},{"hours":"10时","wea":"晴","tem":"25","win":"西南风","win_speed":"<3级"},{"hours":"11时","wea":"晴","tem":"28","win":"东风","win_speed":"<3级"},{"hours":"12时","wea":"晴","tem":"30","win":"东北风","win_speed":"<3级"},{"hours":"13时","wea":"晴","tem":"31","win":"东北风","win_speed":"3-4级"},{"hours":"14时","wea":"晴","tem":"33","win":"东风","win_speed":"<3级"}],"index":[{"title":"紫外线指数","level":"很强","desc":"涂擦SPF20以上，PA++护肤品，避强光。"},{"title":"减肥指数","level":"一颗星","desc":"天热风大，可选择低强度运动。"},{"title":"血糖指数","level":"易波动","desc":"风力较大，血糖易波动，注意监测。"},{"title":"穿衣指数","level":"炎热","desc":"建议穿短衫、短裤等清凉夏季服装。"},{"title":"洗车指数","level":"较不宜","desc":"风力较大，洗车后会蒙上灰尘。"},{"title":"空气污染扩散指数","level":"良","desc":"气象条件有利于空气污染物扩散。"}]},{"day":"19日（星期五）","date":"2019-04-19","week":"星期五","wea":"多云转小雨","wea_img":"yun","wea_day":"多云","wea_night":"小雨","tem":"","tem1":"27","tem2":"16","win":["东北风","东北风"],"win_speed":"4-5级转3-4级","sunrise":"06:08","sunset":"19:18","air":"","air_level":"","air_tips":"","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"hours":[{"hours":"08时","wea":"晴","tem":"20","win":"东北风","win_speed":"3-4级"},{"hours":"09时","wea":"多云","tem":"22","win":"东北风","win_speed":"3-4级"},{"hours":"10时","wea":"多云","tem":"24","win":"东北风","win_speed":"3-4级"},{"hours":"11时","wea":"多云","tem":"25","win":"东北风","win_speed":"3-4级"},{"hours":"12时","wea":"多云","tem":"26","win":"东北风","win_speed":"4-5级"},{"hours":"13时","wea":"多云","tem":"26","win":"东北风","win_speed":"4-5级"},{"hours":"14时","wea":"多云","tem":"27","win":"东北风","win_speed":"4-5级"}],"index":[{"title":"紫外线指数","level":"弱","desc":"辐射较弱，涂擦SPF12-15、PA+护肤品。"},{"title":"减肥指数","level":"三颗星","desc":"风有点大，可选择室内运动燃脂。"},{"title":"血糖指数","level":"易波动","desc":"气温多变，血糖易波动，注意监测血糖变化。"},{"title":"穿衣指数","level":"舒适","desc":"建议穿长袖衬衫单裤等服装。"},{"title":"洗车指数","level":"不宜","desc":"有雨，雨水和泥水会弄脏爱车。"},{"title":"空气污染扩散指数","level":"良","desc":"气象条件有利于空气污染物扩散。"}]},{"day":"20日（星期六）","date":"2019-04-20","week":"星期六","wea":"小雨转阴","wea_img":"yu","wea_day":"小雨","wea_night":"阴","tem":"","tem1":"22","tem2":"15","win":["西南风","东北风"],"win_speed":"4-5级转<3级","sunrise":"06:07","sunset":"19:19","air":"","air_level":"","air_tips":"","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"hours":[{"hours":"08时","wea":"阴","tem":"17","win":"东北风","win_speed":"<3级"},{"hours":"09时","wea":"小雨","tem":"18","win":"南风","win_speed":"<3级"},{"hours":"10时","wea":"小雨","tem":"20","win":"西南风","win_speed":"<3级"},{"hours":"11时","wea":"小雨","tem":"21","win":"西南风","win_speed":"3-4级"},{"hours":"12时","wea":"小雨","tem":"21","win":"西南风","win_speed":"3-4级"},{"hours":"13时","wea":"小雨","tem":"21","win":"西南风","win_speed":"4-5级"},{"hours":"14时","wea":"小雨","tem":"21","win":"西南风","win_speed":"4-5级"}],"index":[{"title":"紫外线指数","level":"最弱","desc":"辐射弱，涂擦SPF8-12防晒护肤品。"},{"title":"减肥指数","level":"一颗星","desc":"风雨相伴，坚持室内运动吧。"},{"title":"血糖指数","level":"较易波动","desc":"风雨相伴，注意监测血糖变化。"},{"title":"穿衣指数","level":"较舒适","desc":"建议穿薄外套或牛仔裤等服装。"},{"title":"洗车指数","level":"不宜","desc":"有雨，雨水和泥水会弄脏爱车。"},{"title":"空气污染扩散指数","level":"良","desc":"气象条件有利于空气污染物扩散。"}]},{"day":"21日（星期日）","date":"2019-04-21","week":"星期日","wea":"多云","wea_img":"yun","wea_day":"多云","wea_night":"多云","tem":"","tem1":"25","tem2":"16","win":["东北风","东北风"],"win_speed":"<3级","sunrise":"06:06","sunset":"19:20","air":"","air_level":"","air_tips":"","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"hours":[{"hours":"08时","wea":"阴","tem":"18","win":"东北风","win_speed":"<3级"},{"hours":"11时","wea":"阴","tem":"20","win":"东北风","win_speed":"<3级"},{"hours":"14时","wea":"多云","tem":"24","win":"东北风","win_speed":"<3级"},{"hours":"17时","wea":"多云","tem":"24","win":"东北风","win_speed":"<3级"},{"hours":"20时","wea":"多云","tem":"21","win":"东北风","win_speed":"<3级"},{"hours":"23时","wea":"多云","tem":"19","win":"东北风","win_speed":"<3级"},{"hours":"02时","wea":"多云","tem":"18","win":"东北风","win_speed":"<3级"}],"index":[{"title":"紫外线指数","level":"弱","desc":"辐射较弱，涂擦SPF12-15、PA+护肤品。"},{"title":"减肥指数","level":"五颗星","desc":"天气较舒适，减肥正当时。"},{"title":"血糖指数","level":"较易波动","desc":"血糖较易波动，注意监测。"},{"title":"穿衣指数","level":"舒适","desc":"建议穿长袖衬衫单裤等服装。"},{"title":"洗车指数","level":"较适宜","desc":"无雨且风力较小，易保持清洁度。"},{"title":"空气污染扩散指数","level":"中","desc":"易感人群应适当减少室外活动。"}]},{"day":"22日（星期一）","date":"2019-04-22","week":"星期一","wea":"阴转多云","wea_img":"yun","wea_day":"阴","wea_night":"多云","tem":"","tem1":"26","tem2":"17","win":["东风","北风"],"win_speed":"<3级","sunrise":"06:04","sunset":"19:20","air":"","air_level":"","air_tips":"","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"hours":[{"hours":"08时","wea":"多云","tem":"18","win":"东北风","win_speed":"<3级"},{"hours":"11时","wea":"多云","tem":"21","win":"东北风","win_speed":"<3级"},{"hours":"14时","wea":"阴","tem":"25","win":"东风","win_speed":"<3级"},{"hours":"17时","wea":"阴","tem":"26","win":"东风","win_speed":"<3级"},{"hours":"20时","wea":"阴","tem":"23","win":"东风","win_speed":"<3级"},{"hours":"23时","wea":"阴","tem":"20","win":"东风","win_speed":"<3级"},{"hours":"02时","wea":"多云","tem":"18","win":"北风","win_speed":"<3级"}],"index":[{"title":"紫外线指数","level":"最弱","desc":"辐射弱，涂擦SPF8-12防晒护肤品。"},{"title":"减肥指数","level":"五颗星","desc":"天气较舒适，减肥正当时。"},{"title":"血糖指数","level":"易波动","desc":"血糖易波动，注意监测。"},{"title":"穿衣指数","level":"舒适","desc":"建议穿长袖衬衫单裤等服装。"},{"title":"洗车指数","level":"较适宜","desc":"无雨且风力较小，易保持清洁度。"},{"title":"空气污染扩散指数","level":"较差","desc":"气象条件较不利于空气污染物扩散。。"}]},{"day":"23日（星期二）","date":"2019-04-23","week":"星期二","wea":"晴转多云","wea_img":"yun","wea_day":"晴","wea_night":"多云","tem":"","tem1":"26","tem2":"15","win":["南风","西南风"],"win_speed":"<3级","sunrise":"06:03","sunset":"19:21","air":"","air_level":"","air_tips":"","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"hours":[{"hours":"08时","wea":"多云","tem":"21","win":"北风","win_speed":"<3级"},{"hours":"11时","wea":"多云","tem":"22","win":"西南风","win_speed":"<3级"},{"hours":"14时","wea":"晴","tem":"23","win":"南风","win_speed":"<3级"},{"hours":"17时","wea":"晴","tem":"23","win":"西风","win_speed":"<3级"},{"hours":"20时","wea":"晴","tem":"21","win":"南风","win_speed":"<3级"},{"hours":"23时","wea":"晴","tem":"18","win":"南风","win_speed":"<3级"},{"hours":"02时","wea":"晴","tem":"16","win":"西南风","win_speed":"<3级"}],"index":[{"title":"紫外线指数","level":"强","desc":"涂擦SPF大于15、PA+防晒护肤品。"},{"title":"减肥指数","level":"五颗星","desc":"天气较舒适，减肥正当时。"},{"title":"血糖指数","level":"较易波动","desc":"血糖较易波动，注意监测。"},{"title":"穿衣指数","level":"舒适","desc":"建议穿长袖衬衫单裤等服装。"},{"title":"洗车指数","level":"较适宜","desc":"无雨且风力较小，易保持清洁度。"},{"title":"空气污染扩散指数","level":"中","desc":"易感人群应适当减少室外活动。"}]},{"day":"24日（星期三）","date":"2019-04-24","week":"星期三","wea":"多云转晴","wea_img":"yun","wea_day":"多云","wea_night":"晴","tem":"","tem1":"30","tem2":"15","win":["东风","南风"],"win_speed":"<3级","sunrise":"06:02","sunset":"19:22","air":"","air_level":"","air_tips":"","alarm":{"alarm_type":"","alarm_level":"","alarm_content":""},"hours":[{"hours":"08时","wea":"多云","tem":"17","win":"西南风","win_speed":"<3级"},{"hours":"11时","wea":"多云","tem":"23","win":"南风","win_speed":"<3级"},{"hours":"14时","wea":"多云","tem":"29","win":"东风","win_speed":"<3级"},{"hours":"17时","wea":"多云","tem":"25","win":"东南风","win_speed":"<3级"},{"hours":"20时","wea":"多云","tem":"21","win":"东风","win_speed":"<3级"},{"hours":"23时","wea":"多云","tem":"20","win":"南风","win_speed":"<3级"},{"hours":"02时","wea":"晴","tem":"18","win":"南风","win_speed":"<3级"}],"index":[{"title":"紫外线指数","level":"中等","desc":"涂擦SPF大于15、PA+防晒护肤品。"},{"title":"减肥指数","level":"三颗星","desc":"天气较舒适，减肥正当时。"},{"title":"血糖指数","level":"易波动","desc":"血糖易波动，注意监测。"},{"title":"穿衣指数","level":"热","desc":"适合穿T恤、短薄外套等夏季服装。"},{"title":"洗车指数","level":"较适宜","desc":"无雨且风力较小，易保持清洁度。"},{"title":"空气污染扩散指数","level":"中","desc":"易感人群应适当减少室外活动。"}]}]
     */

    private String cityid;
    private String city;
    private String cityEn;
    private String country;
    private String countryEn;
    private String update_time;
    private List<DataBean> data;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
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
         * day : 18日（星期四）
         * date : 2019-04-18
         * week : 星期四
         * wea : 晴
         * wea_img : qing
         * wea_day : 晴
         * wea_night : 晴
         * tem : 32
         * tem1 : 34
         * tem2 : 18
         * win : ["东风","东北风"]
         * win_speed : 3-4级转4-5级
         * sunrise : 06:09
         * sunset : 19:17
         * air : 71
         * air_level : 良
         * air_tips : 空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！
         * alarm : {"alarm_type":"","alarm_level":"","alarm_content":""}
         * hours : [{"hours":"08时","wea":"晴","tem":"20","win":"东风","win_speed":"<3级"},{"hours":"09时","wea":"晴","tem":"21","win":"南风","win_speed":"<3级"},{"hours":"10时","wea":"晴","tem":"25","win":"西南风","win_speed":"<3级"},{"hours":"11时","wea":"晴","tem":"28","win":"东风","win_speed":"<3级"},{"hours":"12时","wea":"晴","tem":"30","win":"东北风","win_speed":"<3级"},{"hours":"13时","wea":"晴","tem":"31","win":"东北风","win_speed":"3-4级"},{"hours":"14时","wea":"晴","tem":"33","win":"东风","win_speed":"<3级"}]
         * index : [{"title":"紫外线指数","level":"很强","desc":"涂擦SPF20以上，PA++护肤品，避强光。"},{"title":"减肥指数","level":"一颗星","desc":"天热风大，可选择低强度运动。"},{"title":"血糖指数","level":"易波动","desc":"风力较大，血糖易波动，注意监测。"},{"title":"穿衣指数","level":"炎热","desc":"建议穿短衫、短裤等清凉夏季服装。"},{"title":"洗车指数","level":"较不宜","desc":"风力较大，洗车后会蒙上灰尘。"},{"title":"空气污染扩散指数","level":"良","desc":"气象条件有利于空气污染物扩散。"}]
         */

        private String day;
        private String date;
        private String week;
        private String wea;
        private String wea_img;
        private String wea_day;
        private String wea_night;
        private String tem;
        private String tem1;
        private String tem2;
        private String win_speed;
        private String sunrise;
        private String sunset;
        private String air;
        private String air_level;
        private String air_tips;
        private AlarmBean alarm;
        private List<String> win;
        private List<HoursBean> hours;
        private List<IndexBean> index;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
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

        public String getTem() {
            return tem;
        }

        public void setTem(String tem) {
            this.tem = tem;
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

        public String getWin_speed() {
            return win_speed;
        }

        public void setWin_speed(String win_speed) {
            this.win_speed = win_speed;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getAir() {
            return air;
        }

        public void setAir(String air) {
            this.air = air;
        }

        public String getAir_level() {
            return air_level;
        }

        public void setAir_level(String air_level) {
            this.air_level = air_level;
        }

        public String getAir_tips() {
            return air_tips;
        }

        public void setAir_tips(String air_tips) {
            this.air_tips = air_tips;
        }

        public AlarmBean getAlarm() {
            return alarm;
        }

        public void setAlarm(AlarmBean alarm) {
            this.alarm = alarm;
        }

        public List<String> getWin() {
            return win;
        }

        public void setWin(List<String> win) {
            this.win = win;
        }

        public List<HoursBean> getHours() {
            return hours;
        }

        public void setHours(List<HoursBean> hours) {
            this.hours = hours;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public static class AlarmBean {
            /**
             * alarm_type :
             * alarm_level :
             * alarm_content :
             */

            private String alarm_type;
            private String alarm_level;
            private String alarm_content;

            public String getAlarm_type() {
                return alarm_type;
            }

            public void setAlarm_type(String alarm_type) {
                this.alarm_type = alarm_type;
            }

            public String getAlarm_level() {
                return alarm_level;
            }

            public void setAlarm_level(String alarm_level) {
                this.alarm_level = alarm_level;
            }

            public String getAlarm_content() {
                return alarm_content;
            }

            public void setAlarm_content(String alarm_content) {
                this.alarm_content = alarm_content;
            }
        }

        public static class HoursBean {
            /**
             * hours : 08时
             * wea : 晴
             * tem : 20
             * win : 东风
             * win_speed : <3级
             */

            private String day;
            private String wea;
            private String tem;
            private String win;
            private String win_speed;

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getWea() {
                return wea;
            }

            public void setWea(String wea) {
                this.wea = wea;
            }

            public String getTem() {
                return tem;
            }

            public void setTem(String tem) {
                this.tem = tem;
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
        }

        public static class IndexBean {
            /**
             * title : 紫外线指数
             * level : 很强
             * desc : 涂擦SPF20以上，PA++护肤品，避强光。
             */

            private String title;
            private String level;
            private String desc;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }
    }
}
