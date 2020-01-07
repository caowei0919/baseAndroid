package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.bean.ApplyBuildBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefereesBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SchoolBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.ApplyForOwnerPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.ApplyForOwnerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * FileName: ApplyForOwnerActivity
 * Author: 曹伟
 * Date: 2019/9/25 15:48
 * Description: 申请店长
 */

public class ApplyForOwnerActivity extends BaseMvpActivity<ApplyForOwnerView, ApplyForOwnerPresenter>
        implements ApplyForOwnerView{
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;   //返回键
    @BindView(R.id.view_to_apply)
    LinearLayout viewToApply ;  //无申请状态时
    @BindView(R.id.view_status)
    RelativeLayout viewStatus ;     //有申请状态
    @BindView(R.id.et_applyName)
    EditText etApplyName ;  //姓名填写
    @BindView(R.id.tv_applyPhone)
    TextView tvApplyPhone ; //联系方式填写
    @BindView(R.id.tv_chooseSchool)
    TextView tvChooseSchool ;   //选择的学校
    @BindView(R.id.et_applyAddress)
    EditText etApplyAddress ;   //楼址填写
    @BindView(R.id.btn_apply)
    Button btnApply ;   //申请
    @BindView(R.id.tv_referees)
    TextView tvReferees ;    //推荐人


    private String s_id = "";   //学校id
    private String name = "";   //姓名
    private String address_detail = ""; //地址
    private String cellphone = "";  //推荐人手机号

    @Override
    protected int getLayoutId() {
        return R.layout.activity_apply_for_owner;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvApplyPhone.setText(Constant.getUser().getPhone());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().applyBuildStatus("","","","");
    }

    @OnClick({R.id.linear_back,R.id.tv_chooseSchool,R.id.tv_referees,R.id.btn_apply})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.linear_back :     //返回
                    finish();
                break;

            case R.id.tv_chooseSchool :     //选择学校
                    startActivity(new Intent(mContext, ChooseSchoolActivity.class));
                break;

            case R.id.tv_referees : //选择推荐人
                    startActivity(new Intent(mContext, ChooseRefereesActivity.class));
                break;

            case R.id.btn_apply :   //申请
                    if(TextUtils.isEmpty(name)){
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_input_your_name));
                        return;
                    }
                    if (TextUtils.isEmpty(s_id)){
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_to_select_school));
                        return;
                    }
                    if(TextUtils.isEmpty(address_detail)){
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_input_your_addr));
                        return;
                    }
                    getPresenter().applyBuild(s_id,tvApplyPhone.getText().toString()
                            ,name,address_detail,cellphone);
                break;
        }
    }

    /**
     * 申请人姓名输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.et_applyName,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onNameTextChange(Editable editable){
        name = editable.toString();
    }

    /**
     * 地址信息输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.et_applyName,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onAddrTextChange(Editable editable){
        address_detail = editable.toString();
    }

    /**
     * 获取申请信息成功
     * @param response
     */
    @Override
    public void applyBuildStateSuccess(ApplyBuildBean response) {
        if(response.getList() != null && response.getList().size() > 0){    //有申请信息
            switch (response.getList().get(0).getStatus()){
                case "1" :
                    viewToApply.setVisibility(View.GONE);
                    viewStatus.setVisibility(View.VISIBLE);
                    break;
            }
        }else{                                                              //无申请信息
            viewToApply.setVisibility(View.VISIBLE);
            viewStatus.setVisibility(View.GONE);
            tvTitle.setText(mContext.getResources().getString(R.string.apply_owner));
        }
    }

    /**
     * 提交申请楼长信息成功
     */
    @Override
    public void applyBuildSuccess() {
        ToastUtils.showLongToast(mContext.getResources().getString(R.string.apply_success));
        finish();
    }

    /**
     * 选择联系人回调
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefereesBean.MarketerBean bean){
        tvReferees.setText(bean.getCellphone());
        cellphone = bean.getCellphone();
    }

    /**
     * 学校选择回调
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SchoolBean bean){
        tvChooseSchool.setText(bean.getName());
        s_id = bean.getSchool_id() ;
    }
}
