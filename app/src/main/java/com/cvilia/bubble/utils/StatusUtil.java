package com.cvilia.bubble.utils;

import android.app.Activity;
import android.view.WindowManager;

/**
 * author: lzy
 * date: 2020/8/27
 * describe：描述
 */
public class StatusUtil {

    /**
     * 隐藏状态栏
     */
    public static void hideStatusBar(Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(lp);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 显示状态栏
     */
    public static void showStatusBar(Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(lp);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

}
