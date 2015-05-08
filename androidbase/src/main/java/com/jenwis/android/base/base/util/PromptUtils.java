package com.jenwis.android.base.base.util;

import android.content.Context;
import android.widget.Toast;

public class PromptUtils {
    public static void showToast(Context context, String msg) {
        if (context == null) return;
        Toast toast = getToast(context, msg);
        if (toast != null) toast.show();
    }

    public static void showToast(Context context, int resourceId) {
        if (context == null) return;
        Toast toast = getToast(context, context.getResources().getString(resourceId));
        if (toast != null) toast.show();
    }

    private static Toast getToast(Context context, String msg) {
        return Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }
}
