package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import com.zj.wz.wbyx.baseandroid.utils.PhoneNumTools;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.view.InviteView;
import com.zj.wz.wbyx.wbyxAndroid.bean.InviteCheckBean;
import com.zj.wz.wbyx.wbyxAndroid.event.BindEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.InvitePresenter;

/**
 * FileName: InviteActivity
 * Author: 曹伟
 * Date: 2019/9/30 17:10
 * Description: 绑定邀请人
 */

public class InviteActivity extends BaseMvpActivity<InviteView,InvitePresenter>
        implements InviteView {

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;   //返回
    @BindView(R.id.et_inviteCode)
    EditText etInviteCode ;     //邀请码输入框
    @BindView(R.id.btn_bind)
    Button btnBind ;    //绑定

    private Dialog dialogN ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.bind_inviter));
        tvTitle.setTextColor(mContext.getResources().getColor(R.color.c_1B1B1B));
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.linear_back,R.id.btn_bind})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;

            case R.id.btn_bind :    //绑定
                    checkInvitePhone();
                break;
        }
    }

    /**
     * 检验邀请人手机号
     */
    private void checkInvitePhone() {
        String invitePhone = etInviteCode.getText().toString()
                .replaceAll(" ","").trim() ;
        if(!PhoneNumTools.checkMobile(invitePhone)){
            ToastUtils.showLongToast(mContext.getResources()
                    .getString(R.string.please_write_phone_right));
            return;
        }
        getPresenter().checkInvitePhone(invitePhone);
    }

    @Override
    public void checkInviteSuccess(InviteCheckBean inviteCheckBeanBaseBean) {
        if(null == dialogN){
            dialogN = new AlertDialog.Builder(mContext).create();
        }
        dialogN.show();
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.invite_dialog);
        CircleImageView circleAvatar = dialogN.findViewById(R.id.circle_avatar);
        Glide.with(mContext)
                .load(inviteCheckBeanBaseBean.getAvatar())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(circleAvatar);
        TextView tvNickName = dialogN.findViewById(R.id.tv_nickName) ;
        tvNickName.setText(inviteCheckBeanBaseBean.getNickname());
        TextView tvPhone = dialogN.findViewById(R.id.tv_phone) ;
        tvPhone.setText(mContext.getResources().getString(R.string.invite_code)
                + ":\r" +inviteCheckBeanBaseBean.getCellphone());
        TextView tvCancel = dialogN.findViewById(R.id.tv_cancel) ;
        TextView tvSure = dialogN.findViewById(R.id.tv_sure) ;

        //取消
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });

        //确定
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().BindInvite(inviteCheckBeanBaseBean.getCellphone());
            }
        });
    }

    /**
     * 绑定成功
     */
    @Override
    public void BindSuccess() {
        if(dialogN.isShowing()){
            dialogN.dismiss();
            dialogN=null;
        }
        ToastUtils.showLongToast(mContext.getResources().getString(R.string.bind_success));
        EventBus.getDefault().post(new BindEvent());
        finish();
    }
}
