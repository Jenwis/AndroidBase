package com.jenwis.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jenwis.ui.actionbar.ActionBar;
import com.ttwg.base.R;

/**
 * Created by zhengyuji on 15/2/2.
 * 说明：
 * 抽象类
 * 主Activity（splash后的Activity）的基类，继承BaseActivity，
 * 此类主要实现对ActionBar的统一定义
 * 规定所有Activity的顶级布局为RelativeLayout,然后再一个RelativeLayout
 */
public abstract class BaseActionBarActivity extends BaseActivity {
    private View mContainerView;
    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initActionBar();
        setContentView(mContainerView);
        super.onCreate(savedInstanceState);
    }

    /*
    * 对所有ActionBar的统一添加
    * */
    private void initActionBar() {
        mActionBar = (ActionBar) LayoutInflater.from(this).inflate(R.layout.layout_actionbar, null);
        if (mContainerView != null && mContainerView instanceof RelativeLayout) {
            RelativeLayout.LayoutParams lp
                    = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT
                    , RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            mActionBar.setLayoutParams(lp);

            RelativeLayout.LayoutParams lp1
                    = (RelativeLayout.LayoutParams) (((RelativeLayout) mContainerView)
                    .getChildAt(0)).getLayoutParams();
            lp1.addRule(RelativeLayout.BELOW, mActionBar.getId());
            (((RelativeLayout) mContainerView).getChildAt(0)).setLayoutParams(lp1);
            ((RelativeLayout) mContainerView).addView(mActionBar);
        }
    }

    /*
    -----------------------------------begin setBaseContentView--------------------------------
    * 此6个设置布局的方法，为所有具体类所调用，替代调用Activity自带的setContentView方法
    * */
    public void setBaseContentView(int layoutResId) {
        mContainerView = LayoutInflater.from(this).inflate(layoutResId, null);
    }

    public void setBaseContentView(View view) {
        mContainerView = view;
    }

    public void setBaseContentView(View view, ViewGroup.LayoutParams params) {
        mContainerView = view;
        mContainerView.setLayoutParams(params);
    }

    public void setBaseContentView(int layoutResId, boolean layoutAnimation) {
        mContainerView = LayoutInflater.from(this).inflate(layoutResId, null);
        this.mLayoutAnimation = layoutAnimation;
    }

    public void setBaseContentView(View view, boolean layoutAnimation) {
        mContainerView = view;
        this.mLayoutAnimation = layoutAnimation;
    }

    public void setBaseContentView(View view, ViewGroup.LayoutParams params, boolean layoutAnimation) {
        mContainerView = view;
        mContainerView.setLayoutParams(params);
        this.mLayoutAnimation = layoutAnimation;
    }
    /*----------------------------------end setBaseContentView-------------------------------*/

    public ActionBar getMyActionBar() {
        return mActionBar;
    }
}
