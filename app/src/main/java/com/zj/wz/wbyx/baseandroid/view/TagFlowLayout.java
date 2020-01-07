package com.zj.wz.wbyx.baseandroid.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.wbyxAndroid.bean.SkuBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * tag标签 （筛选、tag用）
 * Created by dalong  on 2017/8/2.
 */

public class TagFlowLayout extends FlowLayout {
    // 可选择的
    public final static int STYPE_SELECT = 0;
    //便签 没有选中区分
    public final static int STYPE_TAG = 1;
    private ColorStateList mTextColor = getResources()
            .getColorStateList(R.color.ui_flowlayout_item_text_bg);//文字选择器;
    private int mBackground;
    private int mTextSize;
    private int mPaddingLeft, mPaddingRight, mPaddingTop, mPaddingBottom;
    private int maxSelectNum;
    private boolean isSingle;
    private boolean mCancelSelf;//在isSingle为true下起作用,true为运行取消自己，false为不允许
    private int mType;
    private List<SkuBean> mData = new ArrayList<>();
    //选择回调
    public OnSelectListener mOnSelectListener;

    public Context mContext;

    // view列表
    private List<View> mItemViews = new ArrayList<View>();

    private int mSelectPosition = -1;
    private int mChildMinWidth;//最低宽度

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        mType = a.getInt(R.styleable.FlowLayout_flow_type, STYPE_SELECT);
        isSingle = a.getBoolean(R.styleable.FlowLayout_flow_isSingle, false);
        mCancelSelf = a.getBoolean(R.styleable.FlowLayout_flow_cancelSelf, false);
        maxSelectNum = a.getInt(R.styleable.FlowLayout_flow_maxSelectNum, -1);
        mPaddingLeft = a.getDimensionPixelSize(R.styleable.FlowLayout_flow_paddingLeft, dip2px(10));
        mPaddingRight = a.getDimensionPixelSize(R.styleable.FlowLayout_flow_paddingRight, dip2px(10));
        mPaddingTop = a.getDimensionPixelSize(R.styleable.FlowLayout_flow_paddingTop, dip2px(6));
        mPaddingBottom = a.getDimensionPixelSize(R.styleable.FlowLayout_flow_paddingBottom, dip2px(6));
        mTextSize = a.getDimensionPixelSize(R.styleable.FlowLayout_flow_textSize, -1);
        mTextColor = a.getColorStateList(R.styleable.FlowLayout_flow_textColor);
        mBackground = a.getResourceId(R.styleable.FlowLayout_flow_background, R.drawable.ui_flowlayout_item_bg_selector);
        a.recycle();
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setFlowData(List<SkuBean> data) {
        this.mData = data;
        removeAllViews();
        mItemViews.clear();
        addViews(mData);
        postInvalidate();
    }

    /**
     * 设置是否是单选
     *
     * @param isSingle
     */
    public void setIsSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    /**
     * 设置type
     *
     * @param stype
     */
    public void setType(int stype) {
        this.mType = stype;
        if (stype != STYPE_SELECT) {
            setAllUnEnable();
        } else {
            setAllEnable();
        }
    }

    /**
     * 获取type
     *
     * @return
     */
    public int getType() {
        return mType;
    }

    /**
     * 设置最低宽度
     *
     * @param mChildMinWidth
     */
    public void setMinWidth(int mChildMinWidth) {
        this.mChildMinWidth = mChildMinWidth;
    }

    /**
     * add View
     *
     * @param mData
     */
    private void addViews(List<SkuBean> mData) {
        for (int i = 0; i < mData.size(); i++) {
            addChildView(mData.get(i), i);
        }
        if (mType != STYPE_SELECT) {
            setAllUnEnable();
        }
    }

    /**
     * 添加view
     *
     * @param flow 数据
     */
    private void addChildView(SkuBean flow, int index) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.ui_view_flow_item, null);
        FlowCheckBox chk = (FlowCheckBox) itemView.findViewById(R.id.single_select_chk);
        TextView mNoGoods = (TextView) itemView.findViewById(R.id.single_select_no_goods);
        chk.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        chk.setOnClickListener(new OnItemClickEvent());
        chk.setText(flow.getFlowName());
        chk.setClickable(true);
//        if (!TextUtils.isEmpty(flow.reservedParams)) {
//            if (Integer.parseInt(flow.reservedParams) == 0) {//库存为0就显示蒙层
//                mNoGoods.setVisibility(VISIBLE);
//                chk.setClickable(false);
//            } else {
//                mNoGoods.setVisibility(GONE);
//                chk.setClickable(true);
//            }
//        }

        if (mTextSize != -1)
            chk.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        if (mTextColor != null) chk.setTextColor(mTextColor);
        chk.setBackgroundResource(mBackground);
        chk.setTag(index);
        if (mChildMinWidth != 0) {
            chk.setMinWidth(mChildMinWidth);
        }
        if (flow.drawableRes != 0) {
            Drawable drawable = getResources().getDrawable(flow.drawableRes);
            chk.setDrawableLeft(drawable);
        }
        setUnChecked(chk);
        // 测量最长的一个条目的位置
        measureView(itemView);
        mItemViews.add(itemView);
        addView(itemView);

        if (mType != STYPE_SELECT) {
            setUnRnable(itemView);
        }
        if ("0".equals(flow.getReservedParams())){
            chk.setEnabled(false);
            mNoGoods.setVisibility(VISIBLE);
        }else {
            chk.setEnabled(true);
            mNoGoods.setVisibility(GONE);
        }
    }


    /**
     * 测量一个单选按钮的长度
     *
     * @param v view
     */
    public void measureView(View v) {
        if (v == null) {
            return;
        }
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);

        v.measure(w, h);
    }

    /**
     * (点击事件)
     * 条目FlowCheckBox的点击事件
     */
    private class OnItemClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FlowCheckBox chk = (FlowCheckBox) v;
            if (chk.isChecked()) {
                mSelectPosition = (Integer) chk.getTag();
                if (maxSelectNum != -1 && !isSingle && isSelectedIndexs().size() > maxSelectNum) {
                    setUnChecked(chk);
                    if (mOnSelectListener != null) {
                        mOnSelectListener.onOutLimit();// 超出限制
                    }
                    return;
                }
                notifyAllItemView(mSelectPosition);
                ///只要使用这个回调就知道选中的是哪个了   我靠
                if (mOnSelectListener != null) {
                    mOnSelectListener.onSelect(mSelectPosition);
                }
            } else {
                int currentSelect = (Integer) chk.getTag();
                if (isSingle && !mCancelSelf) {
                    if (currentSelect == mSelectPosition) {
                        setChecked(chk);
                        return;
                    }
                }
                mSelectPosition = -1;
                ///只要使用这个回调就知道选中的是哪个了   我靠
                if (mOnSelectListener != null) {
                    mOnSelectListener.onSelect(mSelectPosition);
                }
            }
        }
    }


    /**
     * 设置默认选中项 name
     *
     * @param selectName 选中的name
     */
    public void setDefaultSelectName(String selectName) {
        for (int i = 0; i < mData.size(); i++) {
            SkuBean propertyValuesJson = mData.get(i);
            if (propertyValuesJson.getFlowName().equals(selectName)) {
                notifyAllItemView(i);
                return;
            }

        }

    }

    /**
     * 设置不选中
     *
     * @param index
     */
    public void setUnSelectByPosition(int index) {
        FlowCheckBox chk = (FlowCheckBox) mItemViews.get(index).findViewById(R.id.single_select_chk);
        setUnChecked(chk);
    }

    /**
     * 设置默认选中项 id
     *
     * @param selectId 要设置选中的id
     */
    public void setDefaultSelectId(String selectId) {
        for (int i = 0; i < mData.size(); i++) {
            SkuBean propertyValuesJson = mData.get(i);
            if (propertyValuesJson.getFlowId().equals(selectId)) {
                notifyAllItemView(i);
                return;
            }
        }

    }

    /**
     * 设置默认选中项
     *
     * @param select 设置默认选中的index
     */
    public void setDefaultSelect(int select) {
        notifyAllItemView(select);
    }

    public void setDefaultSelects(int[] select) {
        for (int i = 0; i < select.length; i++) {
            notifyAllItemView(select[i]);
        }
    }

    /**
     * 多个选中
     * 根据SkuBean --selectId来设置选中 select集合
     * 设置默认选中项 id
     *
     * @param selectId
     */
    public void setDefaultSelectIds(String[] selectId) {
        for (int i = 0; i < selectId.length; i++) {
            setDefaultSelectId(selectId[i]);
        }
    }

    /**
     * 通过id设置选中的项目(1;2;3;4;5;6)
     *
     * @param ids
     */
    public void setDefaultSelectByIds(String ids) {
        if (TextUtils.isEmpty(ids) || ids.split(";").length <= 0) {
            return;
        }
        String[] split = ids.split(";");
        for (int i = 0; i < split.length; i++) {
            String id = split[i];
            for (int j = 0; j < mData.size(); j++) {
                if (mData.get(j).getFlowId().equals(id)) {
                    notifyAllItemView(j);
                    break;
                }
            }
        }
    }

    /**
     * 获取所有选中selectID,分号隔开
     *
     * @return
     */
    public String getSelectedIds() {
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = 0; i < isSelectedIndexs().size(); i++) {
            if (flag) {
                flag = false;
                sb.append(mData.get(isSelectedIndexs().get(i)).getFlowId());
            } else {
                sb.append(";").append(mData.get(isSelectedIndexs().get(i)).getFlowId());
            }
        }
        return sb.toString();
    }

    /**
     * 获取所有选中的tag
     *
     * @return
     */
    public List<String> getSelectedNameList() {
        List<String> tags = new ArrayList<>();
        if (!TextUtils.isEmpty(getSelectedNames())) {
            String[] selectNames = getSelectedNames().split(";");
            tags = Arrays.asList(selectNames);
        }
        return tags;
    }

    /**
     * 获取所有选中selectID
     *
     * @return
     */
    public String getSelectedNames() {
        StringBuilder sb = new StringBuilder();
        boolean isFlag = true;
        List<Integer> list = isSelectedIndexs();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (isFlag) {
                sb.append(mData.get(list.get(i)).getFlowName());
                isFlag = false;
            } else {
                sb.append(";").append(mData.get(list.get(i)).getFlowName());
            }
        }
        return sb.toString();
    }

    /**
     * 复位 所有的选中清空
     */
    public void reset() {
        for (int i = 0; i < mItemViews.size(); i++) {
            FlowCheckBox chk = (FlowCheckBox) mItemViews.get(i).findViewById(R.id.single_select_chk);
            setUnChecked(chk);
        }
    }

    /**
     * 刷新数据
     *
     * @param mSelectPosition 选中
     */
    private void notifyAllItemView(int mSelectPosition) {
        for (int i = 0; i < mItemViews.size(); i++) {
            FlowCheckBox chk = (FlowCheckBox) mItemViews.get(i).findViewById(R.id.single_select_chk);
            int tag = (Integer) chk.getTag();
            //选中状态  单选
            if (mSelectPosition >= 0) {
                if (chk.isEnabled()) {
                    if (tag == mSelectPosition) {
                        this.mSelectPosition = mSelectPosition;
                        setChecked(chk);
                    } else {
                        if (isSingle) {
                            setUnChecked(chk);
                        }
                    }
                }

            }

        }
    }

    /**
     * 设置所有可点击
     */
    public void setAllEnable() {
        for (int i = 0; i < mItemViews.size(); i++) {
            FlowCheckBox chk = (FlowCheckBox) mItemViews.get(i).findViewById(R.id.single_select_chk);
            setEnabled(chk);
        }
    }

    /**
     * 设置不可点击项
     *
     * @param position 位置
     */
    public void setUnEnable(int position) {
        for (int i = 0; i < mItemViews.size(); i++) {
            FlowCheckBox chk = (FlowCheckBox) mItemViews.get(i).findViewById(R.id.single_select_chk);
            int tag = (Integer) chk.getTag();
            if (tag == position)
                setUnEnabled(chk);
        }
    }

    /**
     * 设置所有不可点击
     */
    public void setAllUnEnable() {
        for (View itemView : mItemViews) {
            setUnRnable(itemView);
        }
    }

    /**
     * 设置不可点击
     *
     * @param view 子view
     */
    public void setUnRnable(View view) {
        FlowCheckBox chk = (FlowCheckBox) view.findViewById(R.id.single_select_chk);
        setUnEnabled(chk);
    }

    /**
     * 获取是否被选择
     *
     * @return 是否被选择了
     */
    public boolean isSelectPosition() {
        for (int i = 0; i < mItemViews.size(); i++) {
            FlowCheckBox chk = (FlowCheckBox) mItemViews.get(i).findViewById(R.id.single_select_chk);
            if (chk.isChecked())
                return true;
        }
        return false;
    }

    /**
     * 获取选择的那个index(单选的时候可以直接用这个方法，如果的多选你用这个方法就会默认拿到第一个)
     *
     * @return 返回选中的集合
     */
    public int isSelectedIndex() {
        for (int i = 0; i < mItemViews.size(); i++) {
            FlowCheckBox chk = (FlowCheckBox) mItemViews.get(i).findViewById(R.id.single_select_chk);
            if (chk.isChecked())
                return i;
        }
        return -1;
    }

    /**
     * 获取选择的集合(多选)
     *
     * @return 返回选中的index集合
     */
    public List<Integer> isSelectedIndexs() {
        List<Integer> mList = new ArrayList<>();
        for (int i = 0; i < mItemViews.size(); i++) {
            FlowCheckBox chk = (FlowCheckBox) mItemViews.get(i).findViewById(R.id.single_select_chk);
            if (chk.isChecked()) {
                mList.add(i);
            }
        }
        return mList;
    }

    /**
     * 设置还没有选择的样式
     *
     * @param view 子view
     */
    private void setUnChecked(FlowCheckBox view) {
        view.setChecked(false);
    }

    /**
     * 设置被选择的样式
     *
     * @param view FlowCheckBox
     */
    private void setChecked(FlowCheckBox view) {
        view.setChecked(true);
    }

    /**
     * 设置被设置能点击的样式
     *
     * @param view FlowCheckBox
     */
    private void setEnabled(FlowCheckBox view) {
        view.setEnabled(true);
    }

    /**
     * 设置不能被点击的样式
     *
     * @param view FlowCheckBox
     */
    private void setUnEnabled(FlowCheckBox view) {
        view.setEnabled(false);
    }


    /**
     * 接口
     *
     * @author zhouweilong
     */
    public interface OnSelectListener {
        void onSelect(int position);

        void onOutLimit();//超出限制数量

    }

    /**
     * 实现接口回调
     *
     * @param l 接口
     */
    public void setOnSelectListener(OnSelectListener l) {
        this.mOnSelectListener = l;
    }

    /**
     * dp转px
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转sp
     */
    public int px2sp(float pxValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
