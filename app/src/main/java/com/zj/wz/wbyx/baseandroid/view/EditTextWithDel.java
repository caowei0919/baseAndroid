package com.zj.wz.wbyx.baseandroid.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;


/**
 * FileName: EditTextWithDel
 * Author: 曹伟
 * Date: 2019/9/20 21:50
 * Description:
 */

public class EditTextWithDel extends AppCompatEditText {
    private Drawable imgInable;
    private Drawable imgAble;
    private Context mContext;
    private CleanEdiTextListener cleanEdiTextListener;

    public EditTextWithDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        imgInable = mContext.getResources().getDrawable(R.drawable.img_delete);
        imgAble = mContext.getResources().getDrawable(R.drawable.img_delete);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }


    private void setDrawable() {
        if (length() >= 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            PLog.e("eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 70;
            if (rect.contains(eventX, eventY)) {
                setText("");
                if (cleanEdiTextListener != null) {
                    cleanEdiTextListener.onCleanEdiText(true);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }


    public void setShakeAnimation() {
        this.startAnimation(shakeAnimation(5));
    }

    public void setCleanText(CleanEdiTextListener cleanEdiTextListener) {
        this.cleanEdiTextListener = cleanEdiTextListener;
    }


    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
    }

    public interface CleanEdiTextListener {
        void onCleanEdiText(boolean isCleanText);
    }

}
