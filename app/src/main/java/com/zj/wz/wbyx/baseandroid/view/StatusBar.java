package com.zj.wz.wbyx.baseandroid.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


/**
 * FileName: StartBar
 * Author: 曹伟
 * Date: 2019/9/18 9:20
 * Description:
 */

public class StatusBar extends View {
    private int height ;

    public StatusBar(Context context) {
        super(context);
    }

    public StatusBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics());
        } else {
            height = StatusBarUtil.getStatusBarHeight(context);
            if (height == 0) {
                setVisibility(GONE);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
