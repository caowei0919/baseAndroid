package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bumptech.glide.Glide;
import com.lcw.library.imagepicker.ImagePicker;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.retrofit.RxObserver;
import com.zj.wz.wbyx.baseandroid.utils.FileUtils;
import com.zj.wz.wbyx.baseandroid.utils.GlideLoader;
import com.zj.wz.wbyx.baseandroid.utils.RxUtils;
import com.zj.wz.wbyx.baseandroid.utils.TimeUtils;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.bean.UpdataUserBean;
import com.zj.wz.wbyx.wbyxAndroid.event.BindEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.ChangeInfoEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.UserInfoPresenter;
import com.zj.wz.wbyx.wbyxAndroid.utils.DialogUtils;
import com.zj.wz.wbyx.wbyxAndroid.view.UserInfoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.zj.wz.wbyx.baseandroid.config.Constant.REQUEST_OPEN_CAMERA;
import static com.zj.wz.wbyx.baseandroid.config.Constant.REQUEST_TAKE_PHOTO;

/**
 * FileName: UserInfoActivity
 * Author: 曹伟
 * Date: 2019/9/24 21:06
 * Description:个人资料
 */

public class UserInfoActivity extends BaseMvpActivity<UserInfoView, UserInfoPresenter>
        implements UserInfoView , TextWatcher {

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;   //返回
    @BindView(R.id.img_changeAvatar)
    CircleImageView imgChangeAvatar ;   //头像
    @BindView(R.id.tv_save)
    TextView tvSave ;   //保存
    @BindView(R.id.et_nick)
    EditText etNick ;       //昵称
    @BindView(R.id.lin_nick)
    LinearLayout linNick ;      //昵称父view，用于处理editText自动获焦点问题
    @BindView(R.id.tv_gender)
    TextView tvGender ; //性别
    @BindView(R.id.tv_birthday)
    TextView tvBirthday ;   //生日
    @BindView(R.id.tv_invite)
    TextView tvInvite ; //邀请人
    @BindView(R.id.tv_wechatNum)
    TextView tvWechatNum ;  //微信号
    @BindView(R.id.tv_phone)
    TextView tvPhone ;  //手机号

    private TimePickerView pvCustomTime ;   //时间选择器
    private Dialog dialogN ;
    private Uri imageUri ;  //拍照回调uri
    private File mTmpFile ;     //照片文件
    private String changeBirthDay = "" ;  //生日修改
    private String changeAvatarPath = "" ;    //头像
    private String changeNickName = "" ;      //昵称
    private String changeGender = "" ;  //性别
    private UpdataUserBean updataUserBean ;
    private boolean isFinish = false ;
    private boolean hasUpDate = false ;
    private boolean hasLoad = true ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.user_info));
        tvTitle.setTextColor(mContext.getResources().getColor(R.color.c_333333));
        linNick.setFocusable(true);
        linNick.setFocusableInTouchMode(true);
        etNick.addTextChangedListener(this);
        initCustomPickView();
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getMemberUser();
    }

    @OnClick({R.id.linear_back,R.id.img_changeAvatar,R.id.tv_birthday,R.id.tv_gender
            ,R.id.tv_save,R.id.tv_invite})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                isFinish = true ;
                if(TextUtils.isEmpty(changeBirthDay) && TextUtils.isEmpty(changeGender)
                        && TextUtils.isEmpty(changeNickName) && TextUtils.isEmpty(changeAvatarPath)){
                    finish();
                }else{
                    DialogUtils.showCenterDialog(mContext, mContext.getResources()
                            .getString(R.string.to_save_user_info), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(TextUtils.isEmpty(changeBirthDay)){
                                changeBirthDay = updataUserBean.getBirthday() ;
                            }
                            if(TextUtils.isEmpty(changeGender)){
                                changeGender = updataUserBean.getSex() ;
                            }
                            if(TextUtils.isEmpty(changeNickName)){
                                changeNickName = updataUserBean.getNickname();
                            }
                            getPresenter().updataUserInfo(changeBirthDay,changeGender,changeNickName);
                            if(!TextUtils.isEmpty(changeAvatarPath)){
                                hasUpDate = false ;
                                getPresenter().initLuBanIO(mContext,changeAvatarPath);
                            }
                        }
                    },new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                }
                break;

            case R.id.img_changeAvatar :    //修改头像
                    showBottomDialog(mContext.getResources().getString(R.string.camera)
                            ,mContext.getResources().getString(R.string.pick_for_album)
                            ,true);
                break;

            case R.id.tv_birthday :     //选择日期
                    if(!pvCustomTime.isShowing()){
                        pvCustomTime.show();
                    }
                break;

            case R.id.tv_gender :   //选择性别
                    showBottomDialog(mContext.getResources().getString(R.string.male)
                            ,mContext.getResources().getString(R.string.female)
                            ,false);
                break;

            case R.id.tv_save :     //保存修改
                    if(TextUtils.isEmpty(changeBirthDay)){
                        changeBirthDay = updataUserBean.getBirthday() ;
                    }
                    if(TextUtils.isEmpty(changeGender)){
                        changeGender = updataUserBean.getSex() ;
                    }
                    if(TextUtils.isEmpty(changeNickName)){
                        changeNickName = updataUserBean.getNickname();
                    }
                    getPresenter().updataUserInfo(changeBirthDay,changeGender,changeNickName);
                    if(!TextUtils.isEmpty(changeAvatarPath)){
                        getPresenter().initLuBanIO(mContext,changeAvatarPath);
                    }
                break;

            case R.id.tv_invite :   //邀请人
                    startActivity(new Intent(mContext, InviteActivity.class));
                break;
        }
    }

    private void showBottomDialog(String firstName , String secondName , boolean isCamera) {
        //实例化对象
        dialogN = new AlertDialog.Builder(mContext).create();
        //显示dialog
        dialogN.show();
        dialogN.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.BOTTOM);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.select_gender_dialog);
        TextView tvFirst =window.findViewById(R.id.tv_first);
        tvFirst.setText(firstName);
        TextView tvSecond =window.findViewById(R.id.tv_second);
        tvSecond.setText(secondName);
        TextView tvCancel =window.findViewById(R.id.tv_cancel);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        tvFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCamera){
                    takeCamera();   //拍照
                }else{
                    changeGender = Constant.CODE_GENDER_MALE ;
                    tvGender.setText(firstName);
//                    getPresenter().updataGender(Constant.CODE_GENDER_MALE);     //男性
                }
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });
        tvSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isCamera){
                    openAlbum();    //相册
                }else{
                    changeGender = Constant.CODE_GENDER_FEMALE ;
                    tvGender.setText(secondName);
//                    getPresenter().updataGender(Constant.CODE_GENDER_FEMALE);   //女性
                }
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        ImagePicker.getInstance()
                .setTitle(mContext.getResources().getString(R.string.select_picture))//设置标题
                .showCamera(false)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                .setImageLoader(new GlideLoader())
                .start(mContext, REQUEST_OPEN_CAMERA);
    }

    /**
     * 拍照
     */
    private void takeCamera() {
        new RxPermissions(mContext)
                .request(Manifest.permission.CAMERA
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .compose(RxUtils.applySchedulersLifeCycle(getMvpView()))
                .subscribe(new RxObserver<Boolean>(){
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean){      //授权通过拍摄照片
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (cameraIntent.resolveActivity(mContext.getPackageManager()) != null){
                                mTmpFile = FileUtils.createTmpFile(mContext);
                                //通过FileProvider创建一个content类型的Uri
                                imageUri = FileProvider.getUriForFile(mContext
                                        , "wbyx.wz.zj.com.fileprovider", mTmpFile);
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                mContext.startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
                            }
                        }else{
                            ToastUtils.showLongToast(mContext.getResources()
                                    .getString(R.string.dont_allow_permissions));
                        }
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO :   //拍照
                    changeAvatarPath = mTmpFile.getAbsolutePath();
                    Glide.with(mContext)
                            .load(changeAvatarPath)
                            .error(R.mipmap.img_default)
                            .placeholder(R.mipmap.img_default)
                            .dontAnimate()
                            .into(imgChangeAvatar);
//                    getPresenter().initLuBanIO(mContext,mTmpFile.getAbsolutePath());
                    break;

                default :  //相册选取
                    changeAvatarPath = data.getStringArrayListExtra(ImagePicker
                            .EXTRA_SELECT_IMAGES).get(0) ;
                    Glide.with(mContext)
                            .load(changeAvatarPath)
                            .error(R.mipmap.img_default)
                            .placeholder(R.mipmap.img_default)
                            .dontAnimate()
                            .into(imgChangeAvatar);
//                    getPresenter().initLuBanIO(mContext
//                            ,data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES).get(0));
                    break;
            }
        }
    }

    /**
     * 头像修改成功
     * @param imgFil
     */
    @Override
    public void changeImageSuccess(File imgFil) {
        hasLoad = true ;
        changeAvatarPath = "" ;
        EventBus.getDefault().post(new ChangeInfoEvent());
        Glide.with(mContext)
                .load(imgFil)
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(imgChangeAvatar);
        if(isFinish && hasUpDate && hasLoad){
            finish();
        }
    }

    /**
     * 获取个人资料用户信息
     * @param updataUserBean
     */
    @Override
    public void getMemberUserSuccess(UpdataUserBean updataUserBean) {
        this.updataUserBean = updataUserBean ;
        Glide.with(mContext)
                .load(updataUserBean.getAvatar())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(imgChangeAvatar);
        //1表示男  2表示女  可能未填写返回空，处理一下
        tvGender.setText(updataUserBean.getSex() != null
                ? (updataUserBean.getSex().equals(Constant.CODE_GENDER_MALE)
                ? mContext.getResources().getString(R.string.male)
                : mContext.getResources().getString(R.string.female)) : "");
        tvBirthday.setText(updataUserBean.getBirthday() != null
                ? updataUserBean.getBirthday() : "");
        tvInvite.setText(!TextUtils.isEmpty(updataUserBean.getInvite_phone())
                ? updataUserBean.getInvite_name() + "\r\r\r\r" + updataUserBean.getInvite_phone()
                : mContext.getResources().getString(R.string.unbind));
        tvInvite.setEnabled(TextUtils.isEmpty(updataUserBean.getInvite_phone()));
        //0 未绑定微信  1 已绑定微信
        tvWechatNum.setText(updataUserBean.getBinding_wx() == 0
                ? mContext.getResources().getString(R.string.unbind)
                : mContext.getResources().getString(R.string.bind));
        tvPhone.setText(updataUserBean.getCellphone());
        etNick.removeTextChangedListener(this);
        etNick.setText(updataUserBean.getNickname() != null ?
                (TextUtils.isEmpty(etNick.getText().toString().trim())
                        ? updataUserBean.getNickname() : etNick.getText().toString().trim())
                : mContext.getResources().getString(R.string.please_to_input_your_nick));
        etNick.addTextChangedListener(this);
    }

    /**
     * 昵称修改成功
     * @param nick
     */
    @Override
    public void updataNickSuccess(UpdataUserBean nick) {
        EventBus.getDefault().post(new ChangeInfoEvent());
        changeNickName ="" ;
        changeGender ="" ;
        changeBirthDay = "" ;
        etNick.removeTextChangedListener(this);
        etNick.setText(nick.getNickname());
        etNick.addTextChangedListener(this);
        //1表示男  2表示女  可能未填写返回空，处理一下
        tvGender.setText(nick.getSex() != null
                ? (nick.getSex().equals(Constant.CODE_GENDER_MALE)
                ? mContext.getResources().getString(R.string.male)
                : mContext.getResources().getString(R.string.female)) : "");
        tvBirthday.setText(nick.getBirthday() != null
                ? nick.getBirthday() : "");
        tvSave.setVisibility(View.GONE);
        hasUpDate = true ;
        if(isFinish && hasUpDate && hasLoad){
            finish();
        }
    }

    /**
     * 初始化时间选择器
     */
    private void initCustomPickView() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.set(1900,1,1);    //开始时间
        endDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH)
                , selectedDate.get(Calendar.DATE));     //结束时间设置为当前日期
        pvCustomTime = new TimePickerView
                .Builder(mContext, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date dd, View v) {
                        changeBirthDay = TimeUtils.getYMD(dd) ;
                        tvBirthday.setText(changeBirthDay);
//                        getPresenter().updataBirthday(TimeUtils.getYMD(dd));
                    }
                })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .isCyclic(false)    //是否循环
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = v.findViewById(R.id.tv_finish);
                        TextView ivCancel = v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(mContext.getResources().getColor(R.color.c_F5F5F5))
                .build();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BindEvent event){
        getPresenter().getMemberUser();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        changeNickName = s.toString().trim() ;
        tvSave.setVisibility(View.VISIBLE);
    }
}
