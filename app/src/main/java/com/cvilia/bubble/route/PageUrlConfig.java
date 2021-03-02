package com.cvilia.bubble.route;

/**
 * author:lzy
 * date:2020-07-01-00-58
 * describe:
 */
public class PageUrlConfig {

    private static final String MAIN = "/main";
    private static final String SHOW_ONCE = "/once";


    /**
     * MainActivity
     */

    public static final String MAIN_PAGE = MAIN + "/mainActivity";
    public static final String CITIES_PAGE = MAIN + "/citiesPage";
    public static final String SELECT_CITY_PAGE = MAIN + "/selectCityPage";
    public static final String CENTER_PAGE = MAIN + "/centerPage";
    public static final String SETTING_PAGE = MAIN + "/settingPage";
    public static final String SHARE_PAGE = MAIN + "/sharePage";

    public static final String PERMISSION_EXPLAIN_PAGE = SHOW_ONCE + "/permissionExplainActivity";


}
