package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.baseandroid.utils.TimeUtils;
import com.zj.wz.wbyx.wbyxAndroid.activity.ApplyForOwnerActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.BuyMembershipActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.FeedBackActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MyAddrActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.MyGroupActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.OrderActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SettingActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.SharePoliteActivity;
import com.zj.wz.wbyx.wbyxAndroid.activity.UserInfoActivity;
import com.zj.wz.wbyx.wbyxAndroid.bean.MemberCenterBean;
import com.zj.wz.wbyx.wbyxAndroid.event.ChangeInfoEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.MinePresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.MineView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: MineFragment
 * Author: 曹伟
 * Date: 2019/9/20 20:27
 * Description: 我的fragment
 */

public class MineFragment extends BaseMvpFragment<MineView, MinePresenter> implements MineView{

    @BindView(R.id.circle_avatar)
    CircleImageView circleAvatar ;  //头像
    @BindView(R.id.tv_nickName)
    TextView tvNickName ;   //昵称
    @BindView(R.id.tv_inviteCode)
    TextView tvInviteCode ;     //邀请码
    @BindView(R.id.tv_regTime)
    TextView tvRegTime ;    // 注册时间
    @BindView(R.id.img_vip)
    ImageView imgVip ;      //vip和普通用户表示
    @BindView(R.id.img_writeInfo)
    ImageView imgWriteInfo ;       //编辑
    @BindView(R.id.tv_reducePrice)
    TextView tvReducePrice ;    //会员节省金额
    @BindView(R.id.tv_vipLimit)
    TextView tvVipLimit ;   //vip剩余天数
    @BindView(R.id.img_renewal)
    ImageView imgRenewal ;   //立即续费(会员)
    @BindView(R.id.img_buy)
    ImageView imgBuy ;  //立即购买(普通用户)
    @BindView(R.id.view_vip)
    RelativeLayout viewVip ;    //会员tab
    @BindView(R.id.view_common)
    RelativeLayout viewCommon ; //普通会员tab

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {
        Glide.with(getContext())
                .load(Constant.getUser().getAvatar())
                .error(R.mipmap.img_default)
                .into(circleAvatar);
        tvInviteCode.setText(getContext().getResources().getString(R.string.invite_code)
                + "\r\r\r" + Constant.getUser().getPhone());
        tvRegTime.setText(getContext().getResources().getString(R.string.registered_time)
                + "\r\r\r" + Constant.getUser().getReg_time());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getMemberCenter();
    }

    /**
     * 获取会员中心信息回调
     * @param memberCenterBean
     */
    @Override
    public void getMemberBeanSuccess(MemberCenterBean memberCenterBean) {
        tvReducePrice.setText(getContext().getResources().getString(R.string.all_most_to_reduce)
                + memberCenterBean.getUser().getEconomize_price());
        tvVipLimit.setText(getContext().getResources().getString(R.string.also)
                + TimeUtils.intervalDay(Long.parseLong(memberCenterBean.getUser().getLimit_vip()))
                + getContext().getResources().getString(R.string.due_to_for_day));
        Glide.with(getContext())
                .load(memberCenterBean.getUser().getAvatar())
                .error(R.mipmap.img_default)
                .into(circleAvatar);
        Constant.getUser().setAvatar(memberCenterBean.getUser().getAvatar());
        Constant.getUser().setIs_vip(Integer.valueOf(memberCenterBean.getUser().getIs_vip()));
        Constant.getUser().setNickname(memberCenterBean.getUser().getNickname());
        tvNickName.setText(memberCenterBean.getUser().getNickname());
        Glide.with(getContext())
                .load(Constant.isVipUser() ? R.mipmap.user_vip : R.mipmap.user_common)
                .into(imgVip);
        viewVip.setVisibility(Constant.isVipUser() ? View.VISIBLE : View.GONE);
        viewCommon.setVisibility(Constant.isVipUser() ? View.GONE : View.VISIBLE);
        tvNickName.setText(Constant.getUser().getNickname());
    }

    @OnClick({R.id.img_writeInfo,R.id.tv_becomeOwner,R.id.tv_myGroup,R.id.tv_setting
            ,R.id.tv_feedBack,R.id.tv_share,R.id.tv_location,R.id.tv_toCheckAll
            ,R.id.tv_payment,R.id.tv_send,R.id.tv_receive,R.id.tv_evaluate,R.id.tv_afterSales
            ,R.id.img_buy,R.id.img_renewal})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.img_writeInfo :   //修改个人资料
                    startActivity(new Intent(mContext, UserInfoActivity.class));
                break;

            case R.id.tv_becomeOwner :      //成为店长
                    startActivity(new Intent(mContext, ApplyForOwnerActivity.class));
                break;

            case R.id.tv_myGroup :  //我的拼团
                    startActivity(new Intent(mContext, MyGroupActivity.class));
                break;

            case R.id.tv_feedBack : //问题反馈
                    startActivity(new Intent(mContext, FeedBackActivity.class));
                break;

            case R.id.tv_setting :  //设置
                    startActivity(new Intent(mContext, SettingActivity.class));
                break;

            case R.id.tv_share :    //分享有礼
                    startActivity(new Intent(mContext, SharePoliteActivity.class));
                break;

            case R.id.tv_location : //我的地址
                    startActivity(new Intent(mContext, MyAddrActivity.class));
                break;

            case R.id.tv_toCheckAll :  //全部订单
                    startActivity(new Intent(mContext, OrderActivity.class));
                break;

            case R.id.tv_payment :  //待付款订单
                Intent paymentIntent = new Intent(mContext,OrderActivity.class);
                paymentIntent.putExtra("index",1);
                startActivity(paymentIntent);
                break;

            case R.id.tv_send :  //待发货订单
                Intent sendIntent = new Intent(mContext,OrderActivity.class);
                sendIntent.putExtra("index",2);
                startActivity(sendIntent);
                break;

            case R.id.tv_receive :  //待收货订单
                Intent receiveIntent = new Intent(mContext,OrderActivity.class);
                receiveIntent.putExtra("index",3);
                startActivity(receiveIntent);
                break;

            case R.id.tv_evaluate :  //待评价订单
                Intent evaluateIntent = new Intent(mContext,OrderActivity.class);
                evaluateIntent.putExtra("index",4);
                startActivity(evaluateIntent);
                break;

            case R.id.tv_afterSales :  //售后订单
                Intent afterSalesIntent = new Intent(mContext,OrderActivity.class);
                afterSalesIntent.putExtra("index",5);
                startActivity(afterSalesIntent);
                break;

            case R.id.img_buy : //立即购买
                startActivity(new Intent(mContext, BuyMembershipActivity.class));
                break;

            case R.id.img_renewal : //立即续费
                mContext.startActivity(new Intent(mContext,BuyMembershipActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangeInfoEvent event){
        getPresenter().getMemberCenter();
    }
}
