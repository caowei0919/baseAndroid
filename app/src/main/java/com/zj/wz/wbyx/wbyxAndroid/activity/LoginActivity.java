package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;

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
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.PhoneNumTools;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.EditTextWithDel;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.event.WxLoginEvent;
import com.zj.wz.wbyx.wbyxAndroid.view.LoginView;
import com.zj.wz.wbyx.wbyxAndroid.presenter.LoginPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.zj.wz.wbyx.baseandroid.config.Constant.APP_ID;

/**
 * FileName: LoginActivity
 * Author: 曹伟
 * Date: 2019/9/17 19:20
 * Description:
 */

public class LoginActivity extends BaseMvpActivity<LoginView,LoginPresenter>
        implements LoginView {

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.et_phone)
    EditTextWithDel etPhone ;  //手机号
    @BindView(R.id.et_msgCode)
    EditTextWithDel etMsgCode ;    //验证码
    @BindView(R.id.tv_sendMsg)
    TextView tvSendMsg ;    //发送验证码
    @BindView(R.id.btn_loginOrRegister)
    Button btnLoginOrRegister ;     //登录或者注册
    @BindView(R.id.img_weChat)
    ImageView imgWeChat ;

    private IWXAPI iwxapi ;
    private Disposable mDisposable;
    private boolean hasSendMsg = false ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.login_register));

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
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        regToWx();
    }

    /**
     * 手机号输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.et_phone,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onPhoneTextChange(Editable editable){
        checkEditGet(editable.toString());
    }

    /**
     * 手机号输入校验，并改变获取验证码按钮事件
     * @param s
     */
    private void checkEditGet(String s) {
        String phone = s.replaceAll(" ","");
        if(etMsgCode.getText().toString().equals(mContext.getResources()
                .getString(R.string.get_message))){
            tvSendMsg.setEnabled(phone.length() > 10 ? true : false);
        }
    }

    /**
     * 验证码输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.et_msgCode,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onMessageTextChange(Editable editable){
        checkEditLogin(editable.toString().trim());
    }


    /**
     * 验证码校验，并改变登录注册按钮点击状态
     * @param trim
     */
    private void checkEditLogin(String trim) {
        String code = trim.replace(" ","");
        btnLoginOrRegister.setEnabled((hasSendMsg && code.length() > 3) ? true : false);
    }

    /**
     * 控件点击事件监听
     */
    @OnClick({R.id.tv_sendMsg,R.id.btn_loginOrRegister,R.id.img_weChat})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_sendMsg :  //发送验证码
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
                    getPresenter().loginOrRegister(etPhone.getText().toString()
                                    .replace(" ","")
                            ,etMsgCode.getText().toString()
                                    .replace(" ",""));
                }
                break;

            case R.id.img_weChat :  //微信登录
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "hongbao_wechat_login";
                iwxapi.sendReq(req);
                break;
        }
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

    /**
     * 验证码获取失败
     */
    @Override
    public void sendMessageFailed() {
        tvSendMsg.setEnabled(true);
        tvSendMsg.setText(mContext.getResources().getString(R.string.get_message_again));
    }

    /**
     * 微信授权成功，需要绑定手机号
     * @param key
     */
    @Override
    public void needBindPhone(String key) {
        Intent intent = new Intent(mContext,BindPhoneActivity.class) ;
        intent.putExtra("key",key);
        startActivity(intent);
    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        iwxapi.registerApp(APP_ID);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(WxLoginEvent event){
        getPresenter().checkWxBind(event.getBaseResp().code);
    }

}
