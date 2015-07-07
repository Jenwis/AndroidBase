package com.jenwis.android.base.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.jenwis.android.base.ui.BaseActivity;

public abstract class BaseDialogFragment extends DialogFragment {
    private boolean mIsShowing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mIsShowing = false;
        super.onDismiss(dialog);
    }

    public boolean isShowing() {
        return mIsShowing;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        mIsShowing = true;
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {

        }
        //super.show(manager, tag);
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        mIsShowing = true;
        return super.show(transaction, tag);
    }

    public void show(FragmentManager manager) {
        show(manager, "tag");
    }

    public void setDialogWidth(Context context, Dialog dialog) {
        DisplayMetrics dm = new DisplayMetrics();
        ((BaseActivity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        //取得窗口属性
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        lp.width = (int) (dm.widthPixels * 0.82f); // 宽度
        lp.alpha = 0.9f; // 透明度
        lp.dimAmount = 0.6f;      //设置黑暗度
        // 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
        // dialog.onWindowAttributesChanged(lp);
        dialogWindow.setAttributes(lp);
    }
}
