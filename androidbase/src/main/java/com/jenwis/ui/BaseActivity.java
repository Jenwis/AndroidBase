package com.jenwis.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.inputmethod.InputMethodManager;

import com.jenwis.base.anim.LayoutAnimation;
import com.jenwis.base.util.LogUtils;
import com.jenwis.ui.dialog.SweetAlertDialog;
import com.jenwis.ui.dialog.TTWGLoadingDialog;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

/**
 * Created by zhengyuji on 15/1/30.
 * 说明：
 * 抽象类
 * 作为所有Activity的顶级类，所有公共的功能和属性在此类中实现和定义
 */
public abstract class BaseActivity extends FragmentActivity {
    //主页的所有Fragment
    private ArrayList<BaseFragment> mFragmentList;
    protected boolean mLayoutAnimation = true;
    private ArrayList<TTWGLoadingDialog> mLoadingDialogList = new ArrayList();

    public void setBaseContentView(int layoutId, boolean layoutAnimation) {
        setContentView(layoutId);
        this.mLayoutAnimation = layoutAnimation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        * 加此判断处理是为了避免从后台切回到app时由于FragmentActivity被回收而崩溃
        * */
        if (savedInstanceState != null) {
            savedInstanceState.putParcelable("android:support:fragments", null);
        }

        init();
        findView();
        setView();
        setViewListener();

        super.onCreate(savedInstanceState);
        if (mLayoutAnimation) {
            new LayoutAnimation().startAnimation(getWindow().getDecorView(), LayoutAnimation.AnimationType.SCALE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.LogD("BaseActivity", "onNewIntent");
        // must store the new intent unless getIntent() will return the old one
        setIntent(intent);
    }

    /*用于初始化一些对象，如各种逻辑类对象*/
    public void init() {

    }

    /*
    * 通过findViewById初始化类，必须实现
    * */
    public abstract void findView();

    /*
    * 为所有的view赋值，必须实现
    * */
    public abstract void setView();

    /*
    * 为所有的view设置监听事件，必须实现
    * */
    public abstract void setViewListener();

    public void showDialog() {
        for (TTWGLoadingDialog dialog : mLoadingDialogList) {
            if (dialog != null && dialog.isShowing()) {
                try {
                    dialog.dismiss();
                    mLoadingDialogList.remove(dialog);
                } catch (Exception e) {
                    LogUtils.LogD("SweetAlertDialog", "dismiss Exception e-->" + e.getMessage());
                    if (dialog != null) {
                        dialog.stopLoadingAnimation();
                    }
                }
            }
        }

        TTWGLoadingDialog dialog = null;
        try {
            dialog = showLoadingDialog(true, false);
            mLoadingDialogList.add(dialog);
        } catch (Exception e) {
            LogUtils.LogD("SweetAlertDialog", "showLoadingDialog Exception e-->" + e.getMessage());
            if (dialog != null) {
                dialog.stopLoadingAnimation();
            }
        }
    }

    public void dismissDialog() {
        for (TTWGLoadingDialog dialog : mLoadingDialogList) {
            if (dialog != null && dialog.isShowing()) {
                try {
                    dialog.dismiss();
                    mLoadingDialogList.remove(dialog);
                } catch (Exception e) {
                    LogUtils.LogD("SweetAlertDialog", "dismiss Exception e-->" + e.getMessage());
                    if (dialog != null) {
                        dialog.stopLoadingAnimation();
                    }
                }
            }
        }
    }

    public void showMessageDialog(int msgResId, boolean cancelable, boolean cancelableOnTouchOutside) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
        sweetAlertDialog.setTitleText(getString(msgResId));
        sweetAlertDialog.setCancelable(cancelable);
        sweetAlertDialog.setCanceledOnTouchOutside(cancelableOnTouchOutside);
        sweetAlertDialog.show();
    }

    public void showMessageDialog(String msg, boolean cancelable, boolean cancelableOnTouchOutside) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this);
        sweetAlertDialog.setTitleText(msg);
        sweetAlertDialog.setCancelable(cancelable);
        sweetAlertDialog.setCanceledOnTouchOutside(cancelableOnTouchOutside);
        sweetAlertDialog.show();
    }

    public TTWGLoadingDialog showLoadingDialog(boolean cancelable, boolean cancelableOnTouchOutside) {
        TTWGLoadingDialog ttwgLoadingDialog = new TTWGLoadingDialog(this);
        ttwgLoadingDialog.setCancelable(cancelable);
        ttwgLoadingDialog.setCanceledOnTouchOutside(cancelableOnTouchOutside);
        ttwgLoadingDialog.show();

        return ttwgLoadingDialog;
    }

    public ArrayList<BaseFragment> getFragmentList() {
        return mFragmentList;
    }

    public void setFragmentList(ArrayList<BaseFragment> fragmentList) {
        this.mFragmentList = fragmentList;
    }

    public void setLayoutAnimation(boolean layoutAnimation) {
        this.mLayoutAnimation = layoutAnimation;
    }

    public boolean hideSofKeyIfNecessary() {
        if (getCurrentFocus() != null) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

        return false;
    }
}
