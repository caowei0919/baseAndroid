package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.PhoneNumTools;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.EditTextWithDel;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.presenter.BindPhonePresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.BindPhoneView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * FileName: BindPhoneActivity
 * Author: 曹伟
 * Date: 2019/11/25 18:03
 * Description:绑定手机号
 */

public class BindPhoneActivity extends BaseMvpActivity<BindPhoneView, BindPhonePresenter>
        implements BindPhoneView {
    @BindView(R.id.et_phone)
    EditTextWithDel etPhone ;   //手机号输入
    @BindView(R.id.tv_sendMsg)
    TextView tvSendMsg ;        //获取验证码
    @BindView(R.id.et_msgCode)
    EditTextWithDel etMsgCode ; //验证码输入
    @BindView(R.id.btn_loginOrRegister)
    Button btnLoginOrRegister ; //绑定

    private Disposable mDisposable;
    private String key = "" ;
    private boolean hasSendMsg = false ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wxentry;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));

        //禁止输入框输入空格或者换行符
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start
                    , int end, Spanned dest, int dstart, int dend) {
                if(source.equals(" ") || source.toString().contentEquals("\n")){
                    return "";
                }
                return null;
            }
        };
        etPhone.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(11)});
        etPhone.setText(Constant.getLastPhone());

        etMsgCode.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(4)});
    }

    /**
     * 手机号输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.et_phone,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onPhoneTextChange(Editable editable){
        if(editable.toString().trim().length() == 11){
            tvSendMsg.setEnabled(true);
        }else{
            tvSendMsg.setEnabled(false);
        }
    }

    /**
     * 验证码输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.et_msgCode,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onMessageTextChange(Editable editable){
        if(editable.toString().trim().length() == 4 && hasSendMsg){
            btnLoginOrRegister.setEnabled(true);
        }else{
            btnLoginOrRegister.setEnabled(false);
        }
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        key = TextUtils.isEmpty(getIntent().getStringExtra("key")) ? ""
                : getIntent().getStringExtra("key");
    }

    @OnClick({R.id.tv_cancel,R.id.tv_sendMsg,R.id.btn_loginOrRegister})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_cancel :
                    finish();
                break;

            case R.id.tv_sendMsg :
                if(AndroidUtils.isFastClick()){
                    if(PhoneNumTools.checkMobile(etPhone.getText()
                            .toString().replace(" ",""))){
                        getPresenter().sendMessage(etPhone.getText()
                                .toString().replace(" ",""));
                    }else{
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_write_phone_right));
                    }
                }
                break;

            case R.id.btn_loginOrRegister :     //登录或者注册
                if(AndroidUtils.isFastClick()){
                    getPresenter().WxLogin(key,etPhone.getText().toString()
                                    .replace(" ","")
                            ,etMsgCode.getText().toString()
                                    .replace(" ",""));
                }
                break;
        }
    }

    /**
     * 获取验证码成功
     */
    @Override
    public void sendMessageSuccess() {
        tvSendMsg.setEnabled(false);
        hasSendMsg = true ;
        tvSendMsg.setText(String.valueOf(Constant.COUNT_DOWN_TIME));
        mDisposable = Flowable.intervalRange(0, Constant.COUNT_DOWN_TIME + 2
                , 0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (mDisposable == null)
                            return;
                        if (!mDisposable.isDisposed())
                            tvSendMsg.setText(String.format(Constant.TEXT_FORMAT_COUNT_DOWN
                                    ,Constant.COUNT_DOWN_TIME - aLong));
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        tvSendMsg.setEnabled(true);
                        tvSendMsg.setText(mContext.getResources()
                                .getString(R.string.get_message_again));
                    }
                })
                .subscribe();
    }

    /**
     * 验证码获取失败
     */
    @Override
    public void sendMessageFailed() {
        tvSendMsg.setEnabled(true);
        tvSendMsg.setText(mContext.getResources().getString(R.string.get_message_again));
    }

    @Override
    protected void onDestroy() {
        if(mDisposable!=null && !mDisposable.isDisposed()){
            mDisposable.dispose();
            mDisposable = null ;
        }
        super.onDestroy();
    }

    /**
     * 登录成功
     */
    @Override
    public void LoginSuccess() {
        Constant.setLastPhone(etPhone.getText().toString().replaceAll(" ",""));
        Constant.setLoginStatus(true);
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
