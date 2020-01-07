package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.baseandroid.view.RatingBar;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.DormitoryShopInfoPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.DormitoryShopInfoView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * FileName: DormitoryShopInfoFragment
 * Author: 曹伟
 * Date: 2019/11/14 15:26
 * Description:宿舍小店商铺信息
 */

public class DormitoryShopInfoFragment extends BaseMvpFragment<DormitoryShopInfoView
        , DormitoryShopInfoPresenter> implements DormitoryShopInfoView {

    @BindView(R.id.tv_shopScope)
    TextView tvShopScope ;  //配送范围
    @BindView(R.id.tv_shopTime)
    TextView tvShopTime ;   //配送时间
    @BindView(R.id.tv_shopPhone)
    TextView tvShopPhone ;  //服务电话
    @BindView(R.id.ratingBar_shop)
    RatingBar ratingBarShop ;   //店铺评分
    @BindView(R.id.tv_shopInfo)
    TextView tvShopInfo ;   //店铺简介

    public static DormitoryShopInfoFragment newInstance() {
        Bundle args = new Bundle();
        DormitoryShopInfoFragment fragment = new DormitoryShopInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_info;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {

    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(DormitoryBean.ShoperBean shoperBean){
        //配送范围
        tvShopScope.setText(TextUtils.isEmpty(shoperBean.getShop_address()) ? ""
                : shoperBean.getShop_address());
        String OpenTime = "" ;
        if(null != shoperBean.getOpen_time()){
            for (int i= 0 ; i < shoperBean.getOpen_time().size() ; i ++){
                OpenTime =  TextUtils.isEmpty(OpenTime) ? shoperBean.getOpen_time().get(i).getHour()
                        : OpenTime + "\n" + shoperBean.getOpen_time().get(i).getHour();
            }
        }
        tvShopTime.setText(OpenTime);
        tvShopPhone.setText(TextUtils.isEmpty(shoperBean.getPhone()) ? ""
                : shoperBean.getPhone());
        ratingBarShop.setSelectedNumber(TextUtils.isEmpty(shoperBean.getGrade())
                ? 0 : Integer.valueOf(shoperBean.getGrade()));
        tvShopInfo.setText(TextUtils.isEmpty(shoperBean.getShop_info()) ? ""
                : shoperBean.getShop_info());
    }
}
