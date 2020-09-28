package com.cvilia.bubbleweather.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

/**
 * author: lzy
 * date: 2020/9/10
 * describe：设备和app工具类
 */
public class DeviceUtil {

    public static PackageInfo getAppInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取屏幕宽度
     * @param context 当前屏幕
     * @return 宽度px
     */
    public static int getPxWidth(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context 当前屏幕
     * @return 高度px
     */
    public static int getPxHeight(Context context){

        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        return dm.heightPixels;
    }


}
