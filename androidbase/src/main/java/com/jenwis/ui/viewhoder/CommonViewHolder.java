package com.jenwis.ui.viewhoder;

import android.support.v4.util.SparseArrayCompat;
import android.view.View;

/**
 * Created by zhengyuji on 15/2/13.
 */
public class CommonViewHolder {
    public static <T extends View> T get(View view, int id) {
        SparseArrayCompat<View> viewHolder = (SparseArrayCompat<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArrayCompat();
            view.setTag(viewHolder);
        }

        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }

        return (T) childView;
    }
}
