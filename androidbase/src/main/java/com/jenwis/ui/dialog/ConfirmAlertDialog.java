package com.jenwis.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.ttwg.base.R;

/**
 * Created by 阮小七 on 2015/11/4.
 * 确认/取消dialog
 */
public class ConfirmAlertDialog extends Dialog implements View.OnClickListener {

    private TextView mTextViewTitle;
    private TextView mTextViewCancel;
    private TextView mTextViewConfirm;

    private View mDialogView;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private Animation mOverlayOutAnim;
    private boolean mCloseFromCancel;
    private OnDialogClickListener mCancelClickListener;
    private OnDialogClickListener mConfirmClickListener;
    private String mCancelText;
    private String mConfirmText;
    private String mTitleText;

    public ConfirmAlertDialog(Context context) {
        super(context, R.style.alert_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        mModalOutAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCloseFromCancel) {
                            ConfirmAlertDialog.super.cancel();
                        } else {
                            ConfirmAlertDialog.super.dismiss();
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mOverlayOutAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                WindowManager.LayoutParams wlp = getWindow().getAttributes();
                wlp.alpha = 1 - interpolatedTime;
                getWindow().setAttributes(wlp);
            }
        };
        mOverlayOutAnim.setDuration(120);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_confirm_dialog);
        findView();
        setView();
        setListener();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.text_view_confirm) {
            confirm();
        } else if (id == R.id.text_view_cancel) {
            otherCancel();
        }
    }

    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }

    @Override
    public void cancel() {
        dismissWithAnimation(true);
    }

    public void dismissWithAnimation() {
        dismissWithAnimation(false);
    }

    private void dismissWithAnimation(boolean fromCancel) {
        mCloseFromCancel = fromCancel;
        mTextViewConfirm.startAnimation(mOverlayOutAnim);
        mDialogView.startAnimation(mModalOutAnim);
    }

    private void findView() {
        mTextViewTitle = (TextView) findViewById(R.id.text_view_title);
        mTextViewCancel = (TextView) findViewById(R.id.text_view_cancel);
        mTextViewConfirm = (TextView) findViewById(R.id.text_view_confirm);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
    }

    private void setView() {
        mTextViewTitle.setText(mTitleText);
        mTextViewCancel.setText(mCancelText);
        mTextViewConfirm.setText(mConfirmText);
    }

    private void confirm() {
        if (mConfirmClickListener != null) {
            mConfirmClickListener.onClick(this);
        } else {
            dismissWithAnimation();
        }
    }

    private void otherCancel() {
        if (mCancelClickListener != null) {
            mCancelClickListener.onClick(this);
        } else {
            dismissWithAnimation();
        }
    }

    private void setListener() {
        if (mTextViewCancel != null) {
            mTextViewCancel.setOnClickListener(this);
        }
        if (mTextViewConfirm != null) {
            mTextViewConfirm.setOnClickListener(this);
        }
    }

    public void setCancelText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mCancelText = text;
            if (mTextViewCancel != null) {
                mTextViewCancel.setText(text);
            }
        }
    }

    public void setConfirmText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mConfirmText = text;
            if (mTextViewConfirm != null) {
                mTextViewConfirm.setText(text);
            }
        }
    }

    public void setTitleText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTitleText = text;
            if (mTextViewTitle != null) {
                mTextViewTitle.setText(text);
            }
        }
    }

    public void setCancelClickListener(OnDialogClickListener listener) {
        mCancelClickListener = listener;
    }

    public void setConfirmClickListener(OnDialogClickListener listener) {
        mConfirmClickListener = listener;
    }

    public interface OnDialogClickListener {
        void onClick(ConfirmAlertDialog confirmAlertDialog);
    }
}
