package com.jenwis.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenwis.ui.actionbar.ActionBar;

/**
 * Created by zhengyuji on 15/2/2.
 */
public abstract class BaseActionBarFragment extends BaseFragment {
    protected ActionBar mActionBar;

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
        mActionBar = ((BaseActionBarActivity) getBaseActivity()).getMyActionBar();
    }
}
