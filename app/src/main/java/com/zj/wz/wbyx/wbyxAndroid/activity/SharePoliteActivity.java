package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.SharePolitePresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.SharePoliteView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zj.wz.wbyx.baseandroid.config.Constant.TYPE_SHARE;

/**
 * FileName: SharePoliteActivity
 * Author: 曹伟
 * Date: 2019/10/15 10:34
 * Description:
 */

public class SharePoliteActivity extends BaseMvpActivity<SharePoliteView, SharePolitePresenter>
        implements SharePoliteView{

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.img_adv)
    ImageView imgAdv ;  //分享广播图

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_polite;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.share_polite));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getAdv(TYPE_SHARE);
    }

    @OnClick({R.id.linear_back,R.id.btn_share})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;

            case R.id.btn_share :   //分享

                break;
        }
    }

    /**
     * 获取广告成功
     * @param response
     */
    @Override
    public void getAdvSuccess(AdvListBean response) {
        Glide.with(mContext)
            .load(response.get(0).getImage())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
            .into(imgAdv);
    }
}
