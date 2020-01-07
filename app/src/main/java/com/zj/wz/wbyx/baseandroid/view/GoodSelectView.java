package com.zj.wz.wbyx.baseandroid.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zj.wz.wbyx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * FileName: GoodSelectView
 * Author: 曹伟
 * Date: 2019/11/13 10:49
 * Description:
 */

public class GoodSelectView extends LinearLayout {
    @BindView(R.id.home_mall_default)
    TextView mHomeMallDefault;
    @BindView(R.id.home_mall_price)
    LinearLayout mHomeMallPrice;
    @BindView(R.id.home_mall_num)
    LinearLayout mHomeMallNum;
    @BindView(R.id.home_mall_new)
    LinearLayout mHomeMallNew;
    @BindView(R.id.home_mall_price_name)
    TextView homeMallPriceName;
    @BindView(R.id.home_mall_price_img)
    ImageView homeMallPriceImg;
    @BindView(R.id.home_mall_num_name)
    TextView homeMallNumName;
    @BindView(R.id.home_mall_num_img)
    ImageView homeMallNumImg;
    @BindView(R.id.home_mall_new_name)
    TextView homeMallNewName;
    @BindView(R.id.home_mall_new_img)
    ImageView homeMallNewImg;

    private boolean mPrice = true;
    private boolean mNum = true;
    private boolean mNew = true;

    public boolean ismPrice() {
        return mPrice;
    }

    public void setmPrice(boolean mPrice) {
        this.mPrice = mPrice;
    }

    public boolean ismNum() {
        return mNum;
    }

    public void setmNum(boolean mNum) {
        this.mNum = mNum;
    }

    public boolean ismNew() {
        return mNew;
    }

    public void setmNew(boolean mNew) {
        this.mNew = mNew;
    }

    public GoodSelectView(Context context) {
        this(context, null);
    }

    public GoodSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoodSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_goods_select, this);
        ButterKnife.bind(this);
        selectedView(mHomeMallDefault);
    }

    @OnClick({R.id.home_mall_default, R.id.home_mall_price, R.id.home_mall_num, R.id.home_mall_new})
    public void onViewClicked(View view) {
        selectedView(view);
    }

    private void selectedView(View view) {
        mHomeMallDefault.setSelected(false);
        homeMallPriceName.setSelected(false);
        homeMallNumName.setSelected(false);
        homeMallNewName.setSelected(false);

        homeMallPriceImg.setImageResource(R.mipmap.icon_default);
        homeMallNumImg.setImageResource(R.mipmap.icon_default);
        homeMallNewImg.setImageResource(R.mipmap.icon_default);

        switch (view.getId()) {
            case R.id.home_mall_default:
                if (mListener != null) {
                    mListener.OnSelectItemClick(0);
                }
                mHomeMallDefault.setSelected(true);
                selectedImg(view);
                break;
            case R.id.home_mall_price:
                if (mListener != null) {
                    mListener.OnSelectItemClick(1);
                }
                homeMallPriceImg.setImageResource(R.drawable.search_sort_img);
                homeMallPriceName.setSelected(true);
                selectedImg(view);
                break;
            case R.id.home_mall_num:
                if (mListener != null) {
                    mListener.OnSelectItemClick(2);
                }
                homeMallNumImg.setImageResource(R.drawable.search_sort_img);
                homeMallNumName.setSelected(true);
                selectedImg(view);
                break;
            case R.id.home_mall_new:
                if (mListener != null) {
                    mListener.OnSelectItemClick(3);
                }
                homeMallNewImg.setImageResource(R.drawable.search_sort_img);
                homeMallNewName.setSelected(true);
                selectedImg(view);
                break;
        }
    }


    private void selectedImg(View view) {
        switch (view.getId()) {
            case R.id.home_mall_default:
                homeMallPriceImg.setSelected(true);
                homeMallNumImg.setSelected(true);
                homeMallNewImg.setSelected(true);
                setmPrice(true);
                setmNum(true);
                setmNew(true);
                break;
            case R.id.home_mall_price:
                if (homeMallPriceImg.isSelected()) {
                    homeMallPriceImg.setSelected(false);
                    setmPrice(false);
                } else {
                    homeMallPriceImg.setSelected(true);
                    setmPrice(true);
                }
                homeMallNumImg.setSelected(true);
                homeMallNewImg.setSelected(true);
                setmNum(true);
                setmNew(true);
                break;
            case R.id.home_mall_num:
                if (homeMallNumImg.isSelected()) {
                    homeMallNumImg.setSelected(false);
                    setmNum(false);
                } else {
                    homeMallNumImg.setSelected(true);
                    setmNum(true);
                }
                homeMallPriceImg.setSelected(true);
                homeMallNewImg.setSelected(true);
                setmPrice(true);
                setmNew(true);
                break;
            case R.id.home_mall_new:
                if (homeMallNewImg.isSelected()) {
                    homeMallNewImg.setSelected(false);
                    setmNew(false);
                } else {
                    homeMallNewImg.setSelected(true);
                    setmNew(true);
                }
                homeMallPriceImg.setSelected(true);
                homeMallNumImg.setSelected(true);
                setmPrice(true);
                setmNum(true);
                break;
        }
    }

    private OnSelectItemClickListener mListener;

    public void setOnSelectItemClickListener(OnSelectItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnSelectItemClickListener {

        /**
         * @param position 0：默认  1：价格 2：销量  3：新品
         */
        void OnSelectItemClick(int position);

    }
}
