package com.cvilia.bubbleweather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * author: lzy
 * date: 2020/8/19
 * describe：描述
 */
@Entity
public class City {

    /**
     * cityEn : beijing
     * cityZh : 北京
     * id : 101010100
     * lat : 39.904989
     * leaderEn : beijing
     * leaderZh : 北京
     * lon : 116.405285
     * provinceEn : beijing
     * provinceZh : 北京
     */

    @Id
    private String id;

    @Property
    private String cityEn;

    @Property
    private String cityZh;

    @Property
    private String code;

    @Property
    private String lat;

    @Property
    private String leaderEn;

    @Property
    private String leaderZh;

    @Property
    private String lon;

    @Property
    private String provinceEn;

    @Property
    private String provinceZh;

    @Generated(hash = 275414895)
    public City(String id, String cityEn, String cityZh, String code, String lat,
            String leaderEn, String leaderZh, String lon, String provinceEn,
            String provinceZh) {
        this.id = id;
        this.cityEn = cityEn;
        this.cityZh = cityZh;
        this.code = code;
        this.lat = lat;
        this.leaderEn = leaderEn;
        this.leaderZh = leaderZh;
        this.lon = lon;
        this.provinceEn = provinceEn;
        this.provinceZh = provinceZh;
    }

    @Generated(hash = 750791287)
    public City() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityEn() {
        return this.cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityZh() {
        return this.cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLeaderEn() {
        return this.leaderEn;
    }

    public void setLeaderEn(String leaderEn) {
        this.leaderEn = leaderEn;
    }

    public String getLeaderZh() {
        return this.leaderZh;
    }

    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }

    public String getLon() {
        return this.lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getProvinceEn() {
        return this.provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getProvinceZh() {
        return this.provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }
    
}
