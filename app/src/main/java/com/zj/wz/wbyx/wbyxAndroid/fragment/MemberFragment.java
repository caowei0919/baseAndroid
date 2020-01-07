package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.wbyxAndroid.adapter.MemberCenterImgAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.MemberCenterBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.MemberPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.MemberView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: MemberFragment
 * Author: 曹伟
 * Date: 2019/9/20 20:26
 * Description: 会员fragment
 */

public class MemberFragment extends BaseMvpFragment<MemberView, MemberPresenter>
        implements MemberView{

    @BindView(R.id.circle_avatar)
    CircleImageView circleAvatar ;  //头像
    @BindView(R.id.tv_nickName)
    TextView tvNickName ;   //昵称
    @BindView(R.id.tv_dueToTheTime)
    TextView tvDueToTheTime ;   //到期时间
    @BindView(R.id.img_vip)
    ImageView imgVip ;  //是否是会员
    @BindView(R.id.tv_vipToReduce)
    TextView tvVipToReduce ;    //会员优惠提示
    @BindView(R.id.tv_renewal)
    TextView tvRenewal ;    //续费
    @BindView(R.id.recycle_member_img)
    RecyclerView recycleMemberImg ;     //会员也图片展示

    private MemberCenterImgAdapter adapter ;
    private List<MemberCenterBean.ImgBean> imgBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {
        initRecycle();
        Glide.with(getContext())
                .load(Constant.getUser().getAvatar())
                .error(R.mipmap.img_default)
                .into(circleAvatar);
        tvNickName.setText(Constant.getUser().getNickname());
        Glide.with(getContext())
                .load(Constant.isVipUser() ? R.mipmap.user_vip : R.mipmap.user_common)
                .into(imgVip);
    }

    /**
     * 初始化recycleView
     */
    private void initRecycle() {
        adapter = new MemberCenterImgAdapter(getContext(),imgBeans);
        recycleMemberImg.setNestedScrollingEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycleMemberImg.setLayoutManager(manager);
        recycleMemberImg.setAdapter(adapter);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getMemberCenter();
    }

    /**
     * 获取会员信息成功
     * @param memberCenterBean
     */
    @Override
    public void getMemberBeanSuccess(MemberCenterBean memberCenterBean) {
        tvVipToReduce.setText(getContext().getResources().getString(R.string.vip_can_economize)
                + memberCenterBean.getUser().getEconomize_price());
        tvRenewal.setText(getContext().getResources().getString(R.string.promptly_renewal)+"\r\r"
                + memberCenterBean.getUser().getMonthCard_price()
                + getContext().getResources().getString(R.string.at_month));
        tvDueToTheTime.setVisibility(Constant.isVipUser() ? View.VISIBLE : View.INVISIBLE);
        tvDueToTheTime.setText(Constant.getUser().getExp_time()
                + getContext().getResources().getString(R.string.due_to));
        imgBeans.addAll(memberCenterBean.getImg()) ;
        adapter.notifyDataSetChanged();
    }
}
