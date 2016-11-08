package com.masonliu.mtaplus;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liumeng on 16/11/8.
 */

public class MTAPlus {
    public static void init(String appKey, String channel) {
        com.tencent.stat.StatConfig.setAppKey(appKey);
        if (!TextUtils.isEmpty(channel)) {
            com.tencent.stat.StatConfig.setInstallChannel(channel);
        }
        com.tencent.stat.StatConfig.setDebugEnable(getBuidDebugable());
    }

    private static boolean getBuidDebugable() {
        try {
            final Class<?> activityThread = Class.forName("android.app.ActivityThread");
            final Method currentPackage = activityThread.getMethod("currentPackageName");
            final String packageName = (String) currentPackage.invoke(null, (Object[]) null);
            final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
            final Field DEBUG = buildConfig.getField("DEBUG");
            DEBUG.setAccessible(true);
            return DEBUG.getBoolean(null);
        } catch (final Throwable t) {
            return false;
        }
    }
}
