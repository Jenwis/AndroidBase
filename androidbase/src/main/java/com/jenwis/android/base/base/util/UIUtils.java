package com.jenwis.android.base.base.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by zhengyuji on 9/4/14.
 */
public class UIUtils {

    private static int width;
    private static int height;

    public static void init(Context context) {
        Resources res = context.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    public static int getScreenWidth() {
        return width;
    }

    ;

    public static int getScreenHeight() {
        return height;
    }

    ;

    public static int dip2px(Context context, float dipValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * density + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static float getTextWidth(String text) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        return paint.measureText(text);
    }

    public static float getTextHeight(String text) {
        Paint pFont = new Paint();
        Rect rect = new Rect();
        pFont.getTextBounds(text, 0, 1, rect);
        return rect.height();
    }

    public static int setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        return totalHeight;
    }
}
