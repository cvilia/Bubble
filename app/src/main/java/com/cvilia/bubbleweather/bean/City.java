package com.cvilia.bubbleweather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

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
    private Long id;
    @Property(nameInDb = "cityEn")
    private String cityEn;

    @Property(nameInDb = "cityZh")
    private String cityZh;

    @Property(nameInDb = "code")
    private String code;

    @Property(nameInDb = "lat")
    private String lat;

    @Property(nameInDb = "leaderEn")
    private String leaderEn;

    @Property(nameInDb = "leaderZh")
    private String leaderZh;

    @Property(nameInDb = "lon")
    private String lon;

    @Property(nameInDb = "provinceEn")
    private String provinceEn;

    @Property(nameInDb = "provinceZh")
    private String provinceZh;

    @Generated(hash = 1584036377)
    public City(Long id, String cityEn, String cityZh, String code, String lat,
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLeaderEn() {
        return leaderEn;
    }

    public void setLeaderEn(String leaderEn) {
        this.leaderEn = leaderEn;
    }

    public String getLeaderZh() {
        return leaderZh;
    }

    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }
}
