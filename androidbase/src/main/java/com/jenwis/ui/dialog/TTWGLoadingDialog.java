package com.jenwis.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.ImageView;

import com.jenwis.base.util.LogUtils;
import com.ttwg.base.R;

public class TTWGLoadingDialog extends Dialog {
    private View mDialogView;
    private AnimationDrawable mAnimationDrawableLoading;
    private AnimationSet mModalInAnim;
    private AnimationSet mModalOutAnim;
    private Animation mOverlayOutAnim;
    private ImageView mImageViewLoading;
    private boolean mCloseFromCancel;

    public TTWGLoadingDialog(Context context) {
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
                            TTWGLoadingDialog.super.cancel();
                        } else {
                            TTWGLoadingDialog.super.dismiss();
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_loading_ttwg);
        findView();
    }

    private void findView() {
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mImageViewLoading = (ImageView) findViewById(R.id.image_view_loading);
        setView();
    }

    private void setView() {
        mImageViewLoading.setBackgroundResource(R.anim.anim_loading_ttwg);
        mAnimationDrawableLoading = (AnimationDrawable) mImageViewLoading.getBackground();
    }

    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
        if (mAnimationDrawableLoading != null) {
            mAnimationDrawableLoading.start();
            LogUtils.LogD("TTWGLoadingDialog Animation start");
        }
    }

    /**
     * The real Dialog.cancel() will be invoked async-ly after the animation finishes.
     */
    @Override
    public void cancel() {
        dismissWithAnimation(true);
    }

    private void dismissWithAnimation(boolean fromCancel) {
        mCloseFromCancel = fromCancel;
        mDialogView.startAnimation(mModalOutAnim);

    }

    public void stopLoadingAnimation() {
        if (mAnimationDrawableLoading != null) {
            mAnimationDrawableLoading.stop();
            LogUtils.LogD("TTWGLoadingDialog Animation stop");
        }
    }
}