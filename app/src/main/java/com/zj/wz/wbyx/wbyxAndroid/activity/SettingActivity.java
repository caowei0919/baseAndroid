package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.CacheUtils;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.bean.VersionBean;
import com.zj.wz.wbyx.wbyxAndroid.event.LoginOutEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.UpdateVersionEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.SettingPresenter;
import com.zj.wz.wbyx.wbyxAndroid.utils.DialogUtils;
import com.zj.wz.wbyx.wbyxAndroid.view.SettingView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: SettingActivity
 * Author: 曹伟
 * Date: 2019/10/10 18:35
 * Description:
 */

public class SettingActivity extends BaseMvpActivity<SettingView, SettingPresenter>
        implements SettingView {
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;       //返回
    @BindView(R.id.tv_version)
    TextView tvVersion ;    //版本号

    private boolean hasNewVersion = false ;

    @Override
    protected int getLayoutId() {
        return R.layout.setting_activity;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.setting));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        tvVersion.setText(AndroidUtils.getAppVersionName());
        getPresenter().checkVersion();
    }

    @OnClick({R.id.linear_back,R.id.lin_feedback,R.id.lin_service,R.id.lin_clearCache
            ,R.id.lin_newsSetting,R.id.lin_checkVersion,R.id.lin_logOut})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;

            case R.id.lin_feedback :    //问题反馈
                startActivity(new Intent(mContext, FeedBackActivity.class));
                break;

            case R.id.lin_service : //服务条款
                startActivity(new Intent(mContext, ServiceActivity.class));
                break;

            case R.id.lin_clearCache :  //清理缓存
                CacheUtils.cleanExternalCache(mContext);
                DialogUtils.showCleanSuccess(mContext);
                break;

            case R.id.lin_newsSetting : //消息设置
                startActivity(new Intent(mContext, NewsSettingActivity.class));
                break;

            case R.id.lin_checkVersion :    //版本更新
                if(hasNewVersion){
                    DialogUtils.showVersionDialog(mContext);
                }else{
                    DialogUtils.showCenterDialog(mContext
                            ,mContext.getResources().getString(R.string.is_last_version));
                }
                break;

            case R.id.lin_logOut :  //退出登录
                DialogUtils.showCenterDialog(mContext
                        ,mContext.getResources().getString(R.string.are_you_to_log_out)
                        ,new LoginOutEvent());
                break;
        }
    }

    /**
     * 获取服务器版本成功
     * @param versionBean
     */
    @Override
    public void checkVersionSuccess(VersionBean versionBean) {
        hasNewVersion = (versionBean.getCompare() == 1
                && !versionBean.getVersion().equals(AndroidUtils.getAppVersionName()));
    }

    /**
     * 更新
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateVersionEvent event) {
        startActivity(new Intent(mContext, UpdateVersionActivity.class));
    }
}
