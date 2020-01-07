package com.zj.wz.wbyx.wbyxAndroid.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseDialog;


/**
 * FileName: LoadingDialog
 * Author: 曹伟
 * Date: 2019/9/29 15:35
 * Description:
 */

public class LoadingDialog extends BaseDialog {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.customDialogTheme);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_loading_layout;
    }

    @Override
    protected void setupView(View rootView) {
        getDialog().setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

}
