package com.zj.wz.wbyx.baseandroid.mvp.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zj.wz.wbyx.baseandroid.utils.DisplayUtils;


/**
 * FileName: BaseDialog
 * Author: 曹伟
 * Date: 2019/9/29 15:31
 * Description:
 */

public abstract class BaseDialog extends DialogFragment {
    protected DialogInterface.OnShowListener mOnShowListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setStyle(DialogFragment.STYLE_NORMAL, R.style.customDialogTheme);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        setup(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Full Screen Area
        getDialog().getWindow()
                .setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
        getDialog().getWindow()
                .getDecorView()
                .setPadding(DisplayUtils.dp2px(0), DisplayUtils.dp2px(0), DisplayUtils.dp2px(0), DisplayUtils
                        .dp2px(0));

        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);

        if (mOnShowListener != null) {
            getDialog().setOnShowListener(mOnShowListener);
        }
    }

    private void setup(View rootView) {
        setupView(rootView);
    }

    protected abstract int getLayoutId();

    protected abstract void setupView(View rootView);

    public DialogInterface.OnShowListener getOnShowListener() {
        return mOnShowListener;
    }

    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        mOnShowListener = onShowListener;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        if (isAdded()) {
            FragmentTransaction addft = manager.beginTransaction();
            addft.remove(this).commitAllowingStateLoss();
        }
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }
}
