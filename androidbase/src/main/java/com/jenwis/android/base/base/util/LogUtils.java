package com.jenwis.android.base.base.util;

import android.util.Log;

/**
 * Created by zhengyuji on 15/6/17.
 */
public class LogUtils {
    private static final boolean IS_DEBUG = true;
    private static final String TAG = "LogUtils";

    public static void LogD(String log) {
        if (IS_DEBUG) {
            Log.d(TAG, log);
        }
    }

    public static void LogD(String tag, String log) {
        if (IS_DEBUG) {
            Log.d(tag, log);
        }
    }
}
