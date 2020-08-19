package com.cvilia.bubbleweather.net

import com.cvilia.bubbleweather.bean.AirportWeatherBean
import com.cvilia.bubbleweather.bean.CurrentWeatherBean
import com.cvilia.bubbleweather.bean.Day15WeatherBean
import com.cvilia.bubbleweather.bean.Day7WeatherBean
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author: lzy
 * date: 2020/8/18
 * describe：描述
 * 所有接口中的citi字段不要带市和区
 */
open interface WeatherApi {

    companion object {
        const val BASE_URL = "https://www.tianqiapi.com/"
        const val v3 = "v3";//15日天气预报
        const val v4 = "v4";//国内机场天气
        const val v9 = "v9";//15日天气
        const val v10 = "v10";//空气质量指数
        const val appid = "34413883"
        const val appSecret = "aMHyhA5T"
    }

    /**
     * 获取实时天气
     */
    @GET("free/day")
    fun getCurrentWeather(@Query("appid") appid: String, @Query("appsecret") appsecret: String,
                          @Query("city") city: String): Flowable<CurrentWeatherBean>

    /**
     * 获取7日天气
     */
    @GET("api")
    fun getDay7Weather(@Query("appid") appid: String, @Query("appsecret") appsecret: String,
                       @Query("version") version: String, @Query("city") city: String): Flowable<Day7WeatherBean>

    /**
     * 获取15日天气
     */
    @GET("api")
    fun getDay15Weather(@Query("appid") appid: String, @Query("appsecret") appsecret: String,
                       @Query("version") version: String, @Query("city") city: String): Flowable<Day15WeatherBean>

    /**
     * 获取机场天气
     * [iata] 机场代码，三字码
     */
    @GET("api")
    fun getAirportWeather(@Query("appid") appid: String, @Query("appsecret") appsecret: String,
                       @Query("version") version: String, @Query("iata") iata: String): Flowable<AirportWeatherBean>

    /**
     * 查询空气质量
     */
    @GET("api")
    fun getAQIWeather(@Query("appid") appid: String, @Query("appsecret") appsecret: String,
                       @Query("version") version: String, @Query("city") city: String): Flowable<Day7WeatherBean>
}