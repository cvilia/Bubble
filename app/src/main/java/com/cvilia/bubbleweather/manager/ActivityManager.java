package com.cvilia.bubbleweather.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * author: lzy
 * date: 2020/8/17
 * describe：Activity管理类
 */
public class ActivityManager {

    private Stack<Activity> activities;

    private static class SingleActivityManager {
        private static ActivityManager instance = new ActivityManager();
    }

    private ActivityManager() {
        activities = new Stack<>();
    }

    public static ActivityManager getInstance() {
        return SingleActivityManager.instance;
    }

    public int getActivitySize() {
        return activities != null ? activities.size() : 0;
    }

    /**
     * 向栈中添加activity
     *
     * @param activity 待添加activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除当亲Activity
     * @param activity 当前Activity
     */
    public void removeActivity(Activity activity) {
        if (activities != null && activities.size() > 0) {
            if (activity != null) {
                activities.remove(activity);
                activity.finish();
            }
        }
    }


    /**
     * 移除所有的activity
     */
    public void removeAllActivity() {
        try {
            for (int i = 0; i < activities.size(); i++) {
                if (activities.get(i) != null) {
                    activities.get(i).finish();
                }
            }
            activities.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
