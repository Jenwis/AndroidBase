package com.jenwis.android.base.ui.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenwis.android.base.R;

public class LoadingDialog extends BaseDialogFragment {

    private static final String TAG = "loading_dialog";

    public LoadingDialog() {
    }

    public static LoadingDialog newInstance() {
        LoadingDialog mLoadingDialog = new LoadingDialog();
        return mLoadingDialog;
    }

    public void onCreate(Bundle savedInstanceState) {
        int style = DialogFragment.STYLE_NO_TITLE;
        int theme = R.style.style_loading_dialog;
        setStyle(style, theme);
        setCancelable(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_common_dialog_loading, container, false);
        return rootView;
    }


}
