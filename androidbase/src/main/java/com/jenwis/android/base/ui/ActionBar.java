package com.jenwis.android.base.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jenwis.android.base.R;

/**
 * Created by zhengyuji on 15/2/2.
 */
public class ActionBar extends RelativeLayout {
    private int mTitleId;
    private ColorStateList mTitleColorId;
    private Context mContext;
    private int mLeftActionResId;
    private int mLeftActionDrawableId;
    private int mRightActionResId;
    private int mRightActionDrawableId;
    private ColorStateList mRightActionStringColorId;
    private TextView mTextViewTitle;
    private TextView mTextViewLeftAction;
    private TextView mTextViewRightAction;
    private View mDivideView;
    private boolean isLeftActionViewVisible = true;
    private boolean isRightActionViewVisible = false;
    private boolean isDivideViewVisible = true;


    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttrs(context, attrs);
        LayoutInflater.from(mContext).inflate(R.layout.layout_common_actionbar, this, true);
        initActionbar();
    }

    public ActionBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray types = context.obtainStyledAttributes(attrs, R.styleable.ActionbarView);
        isLeftActionViewVisible = types.getBoolean(R.styleable.ActionbarView_isLeftViewVisible, true);
        isRightActionViewVisible = types.getBoolean(R.styleable.ActionbarView_isRightViewVisible, false);
        mTitleId = types.getResourceId(R.styleable.ActionbarView_titleResId, -1);
        mTitleColorId = types.getColorStateList(R.styleable.ActionbarView_titleColorResId);
        mLeftActionDrawableId = types.getResourceId(R.styleable.ActionbarView_leftActionDrawableId, R.drawable.btn_back_selector);
        mLeftActionResId = types.getResourceId(R.styleable.ActionbarView_leftActionStringResId, -1);
        mRightActionDrawableId = types.getResourceId(R.styleable.ActionbarView_rightActionDrawableId, -1);
        mRightActionResId = types.getResourceId(R.styleable.ActionbarView_rightActionStringResId, -1);
        mRightActionStringColorId = types.getColorStateList(R.styleable.ActionbarView_rightActionStringColorId);
        isDivideViewVisible = types.getBoolean(R.styleable.ActionbarView_isDivideVisible, true);
    }

    private void initActionbar() {
        mTextViewTitle = (TextView) findViewById(R.id.text_view_action_title);
        mTextViewLeftAction = (TextView) findViewById(R.id.text_view_action_left);
        mTextViewRightAction = (TextView) findViewById(R.id.text_view_action_right);
        mDivideView = findViewById(R.id.divide_view);

        if (isLeftActionViewVisible) {
            mTextViewLeftAction.setVisibility(VISIBLE);
        } else {
            mTextViewLeftAction.setVisibility(INVISIBLE);
        }

        if (isRightActionViewVisible) {
            mTextViewRightAction.setVisibility(VISIBLE);
        } else {
            mTextViewRightAction.setVisibility(INVISIBLE);
        }

        Drawable drawable;
        if (mLeftActionDrawableId != -1) {
            drawable = getResources().getDrawable(mLeftActionDrawableId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTextViewLeftAction.setCompoundDrawables(drawable, null, null, null);
        }

        if (mRightActionDrawableId != -1) {
            drawable = getResources().getDrawable(mRightActionDrawableId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTextViewRightAction.setCompoundDrawables(null, null, drawable, null);
        }

        if (mLeftActionResId != -1) {
            mTextViewLeftAction.setText(mLeftActionResId);
        }

        if (mRightActionResId != -1) {
            mTextViewRightAction.setText(mRightActionResId);
        }

        if (mRightActionStringColorId != null) {
            mTextViewLeftAction.setTextColor(mRightActionStringColorId);
        } else {
            ColorStateList colorStateList = getResources().getColorStateList(R.color.color_text_selector);
            mTextViewLeftAction.setTextColor(colorStateList);
        }

        if (mRightActionStringColorId != null) {
            mTextViewRightAction.setTextColor(mRightActionStringColorId);
        } else {
            ColorStateList colorStateList = getResources().getColorStateList(R.color.color_text_selector);
            mTextViewRightAction.setTextColor(colorStateList);
        }
        if (mTitleId != -1) {
            mTextViewTitle.setText(mTitleId);
        }
        if (mTitleColorId != null) {
            mTextViewTitle.setTextColor(mTitleColorId);
        } else {
            mTextViewTitle.setTextColor(getResources().getColor(R.color.color_ffffff));
        }
        if (isDivideViewVisible) {
            mDivideView.setVisibility(VISIBLE);
        } else {
            mDivideView.setVisibility(GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setLeftActionClickListener(OnClickListener listener) {
        mTextViewLeftAction.setOnClickListener(listener);
    }

    public void setRightActionClickListener(OnClickListener listener) {
        mTextViewRightAction.setOnClickListener(listener);
    }

    public void setLeftActionVisible(int view) {
        mTextViewLeftAction.setVisibility(view);
    }

    public void setLeftActionResId(int leftActionResId) {
        this.mLeftActionResId = leftActionResId;
        mTextViewLeftAction.setText(mLeftActionResId);
        mTextViewLeftAction.setCompoundDrawables(null, null, null, null);
    }

    public void setTitle(int title) {
        mTextViewTitle.setText(title);
    }

    public void setTitle(String title) {
        mTextViewTitle.setText(title);
    }

    public void setTitleDrawable(int drawableId) {
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTextViewTitle.setCompoundDrawables(null, null, drawable, null);
        mTextViewTitle.setText("");
    }

    public void setRightActionVisible(int visible) {
        mTextViewRightAction.setVisibility(visible);
    }

    public void setRightActionResId(int mRightActionResId) {
        this.mRightActionResId = mRightActionResId;
        mTextViewRightAction.setText(mRightActionResId);
        mTextViewRightAction.setCompoundDrawables(null, null, null, null);
    }

    public void setLeftActionDrawableId(int leftActionDrawableId) {
        this.mLeftActionDrawableId = leftActionDrawableId;
        Drawable drawable = getResources().getDrawable(mLeftActionDrawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTextViewLeftAction.setCompoundDrawables(drawable, null, null, null);
    }

    public void setRightActionDrawableId(int rightActionDrawableId) {
        this.mRightActionDrawableId = rightActionDrawableId;
        Drawable drawable = getResources().getDrawable(mRightActionDrawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTextViewRightAction.setCompoundDrawables(null, null, drawable, null);
    }

    public void setRightActionStringColorId(ColorStateList colorStateList) {
        mRightActionStringColorId = colorStateList;
        mTextViewRightAction.setTextColor(mRightActionStringColorId);
    }

    public void setTextColor(int colorId) {
        mTextViewRightAction.setTextColor(colorId);
        mTextViewTitle.setTextColor(colorId);
    }

    public String getTextViewRightAction() {
        if (mTextViewRightAction == null) return null;
        return mTextViewRightAction.getText().toString();
    }

    public String getTextViewLeftAction() {
        if (mTextViewLeftAction == null) return null;
        return mTextViewLeftAction.getText().toString();
    }
}
