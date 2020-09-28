package com.cvilia.bubbleweather.utils;

import android.content.Context;

/**
 * author: lzy
 * date: 2020/9/18
 * describe：dimen转化工具类
 */
public class DimenUtil {

    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2sp(Context context, float px) {

        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5f);
    }

    public static int sp2px(Context context, float sp) {

        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }
}
