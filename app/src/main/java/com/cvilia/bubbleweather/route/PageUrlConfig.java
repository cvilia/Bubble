package com.cvilia.bubbleweather.route;

/**
 * author:lzy
 * date:2020-07-01-00-58
 * describe:
 */
public class PageUrlConfig {

    private static final String HOME = "/home";
    private static final String SHOW_ONCE = "/once";


    /**
     * MainActivity
     */

    public static final String MAIN_PAGE = HOME + "/mainActivity";
    public static final String CITIES_PAGE = HOME + "/citiesPage";
    public static final String SELECT_CITY_PAGE = CITIES_PAGE + "/selectCityPage";
    public static final String CENTER_PAGE = HOME + "/centerPage";
    public static final String SETTING_PAGE = CENTER_PAGE + "/settingPage";
    public static final String SHARE_PAGE = HOME + "/sharePage";

    public static final String PERMISSION_EXPLAIN_PAGE = SHOW_ONCE + "/permissionExplainActivity";


}
