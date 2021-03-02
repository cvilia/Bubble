package com.cvilia.bubble.utils;

import java.util.Objects;

/**
 * author: lzy
 * date: 2/7/21
 * describe：描述
 */
public class TextUtil {

    public static String formatCityName(String cityName) {
        if (cityName.length() > 2) {
            if (cityName.contains("市") || cityName.contains("区") || cityName.contains("县") || cityName.contains("乡") || cityName.contains("镇")) {
                return cityName.substring(0, cityName.length() - 1);
            }
        }
        return cityName;
    }

}
