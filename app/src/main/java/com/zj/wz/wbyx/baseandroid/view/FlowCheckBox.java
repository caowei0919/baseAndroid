package com.zj.wz.wbyx.baseandroid.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.zj.wz.wbyx.R;


/**
 * 流布局用的自定义checkbox
 * Created by dalong  on 2017/8/5.
 */

@SuppressLint("AppCompatCustomView")
public class FlowCheckBox extends CheckBox {
    private int mDrawableHeight;
    private int mDrawableWidth;
    private Drawable drawableLeft = null;
    private Drawable drawableTop = null;
    private Drawable drawableRight = null;
    private Drawable drawableBottom = null;

    public FlowCheckBox(Context context) {
        this(context, null, 0);
    }

    public FlowCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowCheckBox);
        setClickable(true);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.FlowCheckBox_drawableHeight) {
                mDrawableHeight = a.getDimensionPixelSize(R.styleable.FlowCheckBox_drawableHeight, 50);
            } else if (attr == R.styleable.FlowCheckBox_drawableWidth) {
                mDrawableWidth = a.getDimensionPixelSize(R.styleable.FlowCheckBox_drawableWidth, 50);
            } else if (attr == R.styleable.FlowCheckBox_drawableTop) {
                drawableTop = a.getDrawable(attr);
            } else if (attr == R.styleable.FlowCheckBox_drawableBottom) {
                drawableRight = a.getDrawable(attr);
            } else if (attr == R.styleable.FlowCheckBox_drawableRight) {
                drawableBottom = a.getDrawable(attr);
            } else if (attr == R.styleable.FlowCheckBox_drawableLeft) {
                drawableLeft = a.getDrawable(attr);
            }
        }
        a.recycle();
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    public void setDrawableLeft(Drawable drawableLeft) {
        this.drawableLeft = drawableLeft;
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
                                                        Drawable top,
                                                        Drawable right,
                                                        Drawable bottom) {

        if (left != null) {
            left.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        setCompoundDrawables(left, top, right, bottom);
    }

    public Drawable getDrawableLeft() {
        return drawableLeft;
    }
}
