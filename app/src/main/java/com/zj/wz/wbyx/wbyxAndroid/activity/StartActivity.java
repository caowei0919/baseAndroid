package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.mvp.base.BaseApplication;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.DisplayUtils;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.wbyxAndroid.presenter.StartPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.StartView;


/**
 * FileName: StartActivity
 * Author: 曹伟
 * Date: 2019/9/17 17:50
 * Description: 启动页面
 */

public class StartActivity extends BaseMvpActivity<StartView, StartPresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
//        getPresenter().getSplashImage();
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        /**
         * 判断当前activity是不是为当前应用启动的第一个activity
         * 如果不是，则重新启动应用
         */
        if(!mContext.isTaskRoot()){
            Intent intent = getIntent();
            if(intent != null){
                String action = intent.getAction();
                if(intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)){
                    finish();
                    return;
                }
            }
            DisplayUtils.fullScreen(mContext,true);
        }
        checkPremissions();
    }

    /**
     *检查权限
     */
    private void checkPremissions() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            new RxPermissions(mContext)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.ACCESS_BACKGROUND_LOCATION
                            , Manifest.permission.READ_PHONE_STATE
                            , Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.ACCESS_COARSE_LOCATION)
                    .compose(RxUtils.applySchedulersLifeCycle(getMvpView()))
                    .subscribe(new RxObserver<Boolean>(){
                        @Override
                        public void onComplete() {
                            super.onComplete();
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            super.onNext(aBoolean);
                            if(aBoolean){
                                if(AndroidUtils.isGPSOPen(mContext)){
                                    if(!Constant.hasLogin()){
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                    }else{
                                        startActivity(new Intent(mContext, MainActivity.class));
                                    }
                                }else{
                                    AndroidUtils.openGPS(mContext);
                                }
                            }else{
                                showSettingPermissionDialog();
                            }
                        }
                    });
        }else{
            new RxPermissions(mContext)
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.READ_PHONE_STATE
                            , Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.ACCESS_COARSE_LOCATION)
                    .compose(RxUtils.applySchedulersLifeCycle(getMvpView()))
                    .subscribe(new RxObserver<Boolean>(){
                        @Override
                        public void onComplete() {
                            super.onComplete();
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            super.onNext(aBoolean);
                            if(aBoolean){
                                if(AndroidUtils.isGPSOPen(mContext)){
                                    if(!Constant.hasLogin()){
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                    }else{
                                        startActivity(new Intent(mContext, MainActivity.class));
                                    }
                                }else{
                                    AndroidUtils.openGPS(mContext);
                                }
                            }else{
                                showSettingPermissionDialog();
                            }
                        }
                    });
        }
    }

    /**
     * 缺少相关权限，提示用户前往设置页面去授权
     */
    private void showSettingPermissionDialog() {
        new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setMessage(mContext.getResources().getString(R.string.please_to_ensure_permission))
                .setPositiveButton(mContext.getResources().getString(R.string.exit)
                        , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        BaseApplication.getAppManager().exitApp();
                    }
                }).setNegativeButton(mContext.getResources().getString(R.string.allow)
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.setData(Uri.parse("package:" + mContext.getPackageName()));
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivityForResult(intent, Constant.PERMISSIONS_CODE);
                        }
                    }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constant.PERMISSIONS_CODE :
                new RxPermissions(mContext).requestEach(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.READ_PHONE_STATE
                        , Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                        .subscribe(permission -> {
                            if (permission.granted) {
                                if(AndroidUtils.isGPSOPen(mContext)){
                                    if(!Constant.hasLogin()){
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                    }else{
                                        startActivity(new Intent(mContext, MainActivity.class));
                                    }
                                }else{
                                    AndroidUtils.openGPS(mContext);
                                }
                            }else{
                                showSettingPermissionDialog();
                            }
                        });
                break;
        }
    }
}
