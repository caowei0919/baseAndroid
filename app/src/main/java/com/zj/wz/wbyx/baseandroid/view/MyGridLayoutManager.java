package com.zj.wz.wbyx.baseandroid.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * FileName: MyGridLayoutManager
 * Author: 曹伟
 * Date: 2019/10/29 15:04
 * Description:
 */

public class MyGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;
    private ILayoutCompleteListener mListener;
    public MyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }
    public MyGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        int count = getChildCount();
        int itemsHeight = 0;
        for (int i = 0; i < count; i++) {
            View item = getChildAt(i);
            if (item == null) {
                continue;
            }
            itemsHeight += item.getMeasuredHeight();
        }
        if (mListener != null) {
            mListener.onLayoutCompleted(itemsHeight);
        }
    }

    public void setLayoutCompleteListener(ILayoutCompleteListener listener) {
        mListener = listener;
    }

    public interface ILayoutCompleteListener {
        void onLayoutCompleted(int itemsHeight);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }
}
