package com.cvilia.bubbleweather.utils;

import android.text.TextUtils;

/**
 * author: lzy
 * date: 2020/9/27
 * describe：温度相关工具类
 */
public class TempParseUtil {

    private static final int DEFAULT_TEMP = 10;

    /**
     * 去除温度后面的非数字部分
     *
     * @param value
     * @return
     */
    public static int getTemp(String value) {

        if (TextUtils.isEmpty(value)) {
            return DEFAULT_TEMP;
        } else {
            String temp = value.substring(0, value.length() - 1);
            return Integer.parseInt(temp);
        }

    }

}
