package com.jenwis.android.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhengyuji on 15/2/2.
 */
public abstract class BaseFragment extends Fragment {
    private BaseActivity mActivity;
    protected View mContentView;
    protected ActionBar mActionBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        initActionBar();
        findView();
        setView();
        setViewListener();

        return mContentView;
    }

    private void initActionBar() {
        mActionBar = ((BaseMainActivity) mActivity).getBPActionBar();
    }

    public BaseActivity getBPMonitorBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }

        return mActivity;
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

    public abstract void onPageSelected();

    public abstract void onPageDisSelected();

    public abstract void onDataChange(int type, Object obj);

    public ArrayList<BaseFragment> getFragmentList() {
        BaseActivity bpMonitorBaseActivity = getBPMonitorBaseActivity();
        if (bpMonitorBaseActivity != null) {
            return bpMonitorBaseActivity.getFragmentList();
        }

        return null;
    }
}
