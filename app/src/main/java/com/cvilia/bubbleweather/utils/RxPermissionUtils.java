package com.cvilia.bubbleweather.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tbruyelle.rxpermissions3.Permission;
import com.tbruyelle.rxpermissions3.RxPermissions;

/**
 * author:lzy
 * date:2020-09-23
 * describe: RxPermission
 */
public class RxPermissionUtils {

    /**
     * Activity
     *
     * @param activity
     * @param permissions
     */
    public static void requestPermissions(AppCompatActivity activity, String[] permissions, OnPermissionCallBack callBack) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEachCombined(permissions)
                .subscribe(permission -> {
                    if (permission.granted) {//全部授予才会执行
                        callBack.onPermissionsGranted();
                    } else if (permission.shouldShowRequestPermissionRationale) {//只要有一个权限拒绝都会执行到这里
                        callBack.onAtLeastOneReject(permission);
                    } else {//除了授予的权限，全部拒绝并不再询问才会走到这里来
                        callBack.onAllRejectAndDoNotAskAgain(permission);
                    }
                });
    }

    public static void requestPermissions(Fragment fragment, String[] permissions, OnPermissionCallBack callback) {
        RxPermissions rxPermissions = new RxPermissions(fragment);
        rxPermissions.requestEachCombined(permissions)
                .subscribe(permission -> {
                    if (permission.granted) {//全部授予才会执行
                        callback.onPermissionsGranted();
                    } else if (permission.shouldShowRequestPermissionRationale) {//只要有一个权限拒绝都会执行到这里
                        callback.onAtLeastOneReject(permission);
                    } else {//除了授予的权限，全部拒绝并不再询问才会走到这里来
                        callback.onAllRejectAndDoNotAskAgain(permission);
                    }
                });
    }

    /**
     * 判断权限是否全部授予
     *
     * @param avtivity
     * @param permissions 待请求的权限
     * @return false = 部分权限未授予  true = 已授予所有权限
     */
    public static boolean checkPermissions(@NonNull AppCompatActivity avtivity, String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(avtivity);
        return checkPermissions(rxPermissions, permissions);
    }

    public static boolean checkPermissions(@NonNull Fragment fragment, String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(fragment);
        return checkPermissions(rxPermissions, permissions);
    }

    /**
     * @param rxPermissions
     * @param permissions
     * @return
     */
    private static boolean checkPermissions(@NonNull RxPermissions rxPermissions, String... permissions) {
        boolean result = true;
        for (int i = 0; i < permissions.length; i++) {
            if (!rxPermissions.isGranted(permissions[i])) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 申请单个权限
     *
     * @param activity
     * @param permission
     * @return
     */
    public static boolean checkSinglePermission(AppCompatActivity activity, String permission) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.isGranted(permission);
    }

    /**
     * 申请单个权限
     *
     * @param fragment
     * @param permission
     * @return
     */
    public static boolean checkSinglePermission(Fragment fragment, String permission) {
        RxPermissions rxPermissions = new RxPermissions(fragment);
        return rxPermissions.isGranted(permission);
    }

    /**
     * 权限组权限筛选
     *
     * @param permissions
     * @param activity
     * @return
     */
    public static String[] filter(AppCompatActivity activity, String[] permissions) {
        int count = 0;
        for (String permission : permissions) {
            if (!RxPermissionUtils.checkSinglePermission(activity, permission)) {
                count++;
            }
        }
        if (count != 0) {
            int index = 0;
            String[] results = new String[count];
            for (String permission : permissions) {
                if (!RxPermissionUtils.checkSinglePermission(activity, permission)) {
                    results[index] = permission;
                    index++;
                }
            }
            return results;
        } else {
            return null;
        }
    }

    public static String[] filter(Fragment fragment, String[] permissions) {
        int count = 0;
        for (String permission : permissions) {
            if (!RxPermissionUtils.checkSinglePermission(fragment, permission)) {
                count++;
            }
        }
        if (count != 0) {
            int index = 0;
            String[] results = new String[count];
            for (String permission : permissions) {
                if (!RxPermissionUtils.checkSinglePermission(fragment, permission)) {
                    results[index] = permission;
                    index++;
                }
            }
            return results;
        } else {
            return null;
        }
    }

    /**
     * 跳转到权限设置界面
     */
    public static void toAppSetting(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }


    public interface OnPermissionCallBack {
        void onPermissionsGranted();//所有权限都授予

        void onAtLeastOneReject(Permission permission);//至少有一个权限点击了拒绝（不管其他是同意还是拒绝并不再询问）

        void onAllRejectAndDoNotAskAgain(Permission permission);//所有权限都点击了拒绝且不再询问
    }

}
