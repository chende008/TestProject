package com.soft.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

/**
 * Desc:   一些常用的静态方法工具类
 * Time:   2016-09-23 12:54
 * Author: chende
 */
public class Utils {

    private static long lastClickTime;
    private static View lastView;


    public synchronized static boolean isFastClick(View currentView) {
        long time = System.currentTimeMillis();
        if ((time - lastClickTime < 500) && (lastView == currentView)) {
            return true;
        }
        lastClickTime = time;
        lastView = currentView;
        return false;
    }


    public static boolean netWorkAvailable(Context context) {
        if (context != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }

}

