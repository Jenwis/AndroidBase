package com.jenwis.base.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Created by zhengyuji on 15/11/12.
 */
public class DataUtils {

    public static String getMetaDataInApplication(Context context, String dataName) {
        Object data = null;
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            data = appInfo.metaData.get(dataName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        LogUtils.LogD("getMetaDataInApplication", dataName + "-->" + data);
        return data != null ? data.toString() : null;
    }
}
