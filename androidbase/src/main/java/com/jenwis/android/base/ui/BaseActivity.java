package com.jenwis.android.base.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.jenwis.android.base.base.anim.LayoutAnimation;
import com.jenwis.android.base.ui.dialog.LoadingDialog;

import java.util.ArrayList;

/**
 * Created by zhengyuji on 15/1/30.
 * 说明：
 * 抽象类
 * 作为所有Activity的顶级类，所有公共的功能和属性在此类中实现和定义
 */
public abstract class BaseActivity extends FragmentActivity {
    private LoadingDialog mLoadingDialog;
    //主页的所有Fragment
    private ArrayList<BaseFragment> mFragmentList;

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
        new LayoutAnimation().startAnimation(getWindow().getDecorView(), LayoutAnimation.AnimationType.SCALE);
    }

    public abstract void init();

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
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.newInstance();
        }
        try {
            //找到是否有已经添加的同一个fragment，如果有，就移除
            dismissDialog();
            mLoadingDialog.show(getSupportFragmentManager(), "loading");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissDialog() {
        try {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<BaseFragment> getFragmentList() {
        return mFragmentList;
    }

    public void setFragmentList(ArrayList<BaseFragment> fragmentList) {
        this.mFragmentList = fragmentList;
    }
}
