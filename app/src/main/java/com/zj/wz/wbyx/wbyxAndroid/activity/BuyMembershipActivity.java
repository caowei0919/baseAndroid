package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.VipProducAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.VipProducPayAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.VipProducBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.BuyMembershipPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.BuyMembershipView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: BuyMembershipActivity
 * Author: 曹伟
 * Date: 2019/11/19 20:45
 * Description:购买会员
 */

public class BuyMembershipActivity extends BaseMvpActivity<BuyMembershipView, BuyMembershipPresenter>
        implements BuyMembershipView, VipProducAdapter.OnVipProductClick
        , VipProducPayAdapter.OnPayTypeClick {
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.recycle_memberShip)
    RecyclerView recycleMemberShip ;    //会员套餐列表
    @BindView(R.id.recycle_payType)
    RecyclerView recyclePayType ;   //支付方式
    @BindView(R.id.tv_remind)
    TextView tvRemind ;     //会员到期提醒
    @BindView(R.id.tv_toPay)
    TextView tvToPay ;  //去支付
    @BindView(R.id.tv_price)
    TextView tvPrice ;  //价格



    private VipProducAdapter vipProducAdapter ;     //会员套餐适配器
    private VipProducPayAdapter vipProducPayAdapter ;   //支付方式
    private List<VipProducBean.ListBean> listBeans = new ArrayList<>();     //套餐列表
    private List<VipProducBean.PayListBean> payListBeans = new ArrayList<>() ;  //支付方式
    private String vipId = "" ;   //套餐id
    private String payType = "" ;   //支付类型
    private String appIp = "" ; //用户ip

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_member_ship;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.buy_vip));

        vipProducAdapter = new VipProducAdapter(listBeans,mContext,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext );
        //设置布局管理器
        recycleMemberShip.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        layoutManager.setReverseLayout(false);
        //设置Adapter
        recycleMemberShip.setAdapter(vipProducAdapter);
        recycleMemberShip.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext
                ,LinearLayoutManager.HORIZONTAL
                ,20
                ,mContext.getResources().getColor(R.color.c_FFFFFF)));

        vipProducPayAdapter = new VipProducPayAdapter(payListBeans,mContext,this);
        LinearLayoutManager layoutManagerPayType = new LinearLayoutManager(mContext );
        //设置布局管理器
        recyclePayType.setLayoutManager(layoutManagerPayType);
        //设置为垂直布局，这也是默认的
        layoutManagerPayType.setOrientation(OrientationHelper. VERTICAL);
        layoutManagerPayType.setReverseLayout(false);
        //设置Adapter
        recyclePayType.setAdapter(vipProducPayAdapter);
        recyclePayType.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext
                ,LinearLayoutManager.HORIZONTAL
                ,2
                ,mContext.getResources().getColor(R.color.c_EEEEEE)));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        appIp = AndroidUtils.getLocalIpAddress(mContext);
        getPresenter().getVipProduct();
    }

    @OnClick({R.id.linear_back,R.id.tv_toPay})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back :     //返回
                    finish();
                break;

            case R.id.tv_toPay :    //去支付
                    getPresenter().setBuyVip(vipId,payType,appIp);
                break;
        }
    }

    /**
     * 获取会员套餐成功
     * @param response
     */
    @Override
    public void getVipProductSuccess(VipProducBean response) {
        listBeans.clear();
        listBeans.addAll(response.getList());
        vipProducAdapter.notifyDataSetChanged();

        payListBeans.clear();
        payListBeans.addAll(response.getPay_list());
        vipProducPayAdapter.notifyDataSetChanged();

        tvRemind.setVisibility(response.getList().size() > 3 ? View.INVISIBLE : View.VISIBLE);

    }

    @Override
    public void onVipProductClick(int position) {
        vipProducAdapter.setIndex(position);
        tvPrice.setText(mContext.getResources().getString(R.string.rmb)
                + listBeans.get(position).getPrice());
    }

    @Override
    public void onCheck(int position) {
        tvPrice.setText(mContext.getResources().getString(R.string.rmb)
                + listBeans.get(position).getPrice());
        vipId = listBeans.get(position).getVid();
    }

    @Override
    public void onPayTypeClick(int position) {
        vipProducPayAdapter.setPosition(position);
    }

    @Override
    public void onPayTypeCheck(int position) {
        payType = payListBeans.get(position).getPay_code() ;
    }
}
