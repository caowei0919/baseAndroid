package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PhoneNumTools;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.view.FlowLayout;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.bean.BuildListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.bean.SchoolBean;
import com.zj.wz.wbyx.wbyxAndroid.event.AddressEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.AddDormitoryPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.AddDormitoryView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * FileName: AddDormitoryActivity
 * Author: 曹伟
 * Date: 2019/10/16 10:51
 * Description: 新增宿舍地址
 */

public class AddDormitoryActivity extends BaseMvpActivity<AddDormitoryView, AddDormitoryPresenter>
        implements AddDormitoryView {
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.ed_name)
    EditText edName ;   //姓名输入框
    @BindView(R.id.ed_phone)
    EditText edPhone ;  //手机号输入框
    @BindView(R.id.tv_chooseSchool)
    TextView tvChooseSchool ;   //学校选择
    @BindView(R.id.flow_build)
    FlowLayout flowBuild ;  //楼栋
    @BindView(R.id.ed_buildNum)
    EditText edBuildNum ;   //楼栋号输入框
    @BindView(R.id.ed_roomNum)
    EditText edRoomNum ;    //宿舍号输入框

    private String phone = "";  //手机号
    private String name = "" ;  //姓名
    private String buildNum = "" ;  //楼栋号
    private String roomNum = "" ;   //宿舍号
    private String schoolId = "" ;  //学校id
    private String buildId = "" ;   //楼栋id
    private String area_id = "" ;   //区域编号
    private String schoolName = "";     //学校名称
    public String buildName = "";   //楼栋名称

    private List<TextView> packageInfoViews = new ArrayList<>();
    private DormitoryAddress bean = null ;

    private boolean isDefault = false ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_dormitory;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.dormitory));

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
        edPhone.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(13)});
        edName.setFilters(new InputFilter[]{filter});
        edBuildNum.setFilters(new InputFilter[]{filter});
        edRoomNum.setFilters(new InputFilter[]{filter});
    }

    /**
     * 手机号输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.ed_phone,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onPhoneTextChange(Editable editable){
        phone = editable.toString().replaceAll(" ","");
    }

    /**
     * 姓名输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.ed_name,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onNameTextChange(Editable editable){
        name = editable.toString().replaceAll(" ","");
    }

    /**
     * 楼栋号输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.ed_buildNum,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onBuildNumTextChange(Editable editable){
        buildNum = editable.toString().replaceAll(" ","");
    }

    /**
     * 宿舍号输入监听
     * @param editable
     */
    @OnTextChanged(value = R.id.ed_roomNum,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onRoomNumTextChange(Editable editable){
        roomNum = editable.toString().replaceAll(" ","");
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        isDefault = getIntent().getBooleanExtra("isDefalut",false);
        //修改地址,则可以根据选择了的学校直接搜索楼栋
        if(getIntent().getSerializableExtra("bean") != null){
            bean = (DormitoryAddress) getIntent().getSerializableExtra("bean");
            edName.setText(bean.getName());
            name = bean.getName() ;
            edPhone.setText(bean.getCellphone());
            phone = bean.getCellphone() ;
            tvChooseSchool.setText(bean.getSchool_name());
            schoolId = bean.getSchool_id() ;
            buildId = bean.getBuilding_id();
            schoolName = bean.getSchool_name();
            buildName = bean.getBuilding_name();
            getPresenter().getSchoolBuild("3",""
                    ,bean.getArea_id(),schoolId,1);
        }
    }

    @OnClick({R.id.linear_back,R.id.tv_applyBuild,R.id.tv_chooseSchool,R.id.btn_sure})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back :  //返回
                finish();
                break;

            case R.id.tv_applyBuild :   //申请楼长
                    startActivity(new Intent(mContext, ApplyForOwnerActivity.class));
                break;

            case R.id.tv_chooseSchool : //选择学校
                    startActivity(new Intent(mContext, ChooseSchoolActivity.class));
                break;

            case R.id.btn_sure :    //确定
                    if(TextUtils.isEmpty(name)){    //姓名校验
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_to_write_name));
                        return;
                    }
                    if(TextUtils.isEmpty(phone)){      //手机号
                        ToastUtils.showLongToast(mContext.getResources()
                            .getString(R.string.please_to_input_your_phone));
                        return;
                    }
                    if(PhoneNumTools.isMobile(phone)){      //手机号
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_write_phone_right));
                        return;
                    }
                    if(TextUtils.isEmpty(schoolId)){    //学校
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_to_select_school));
                        return;
                    }
                    if(TextUtils.isEmpty(buildId)){     //宿舍楼
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_to_select_school));
                        return;
                    }
                    if(TextUtils.isEmpty(buildNum)){     //宿舍楼
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_input_your_build_name));
                        return;
                    }
                    if(TextUtils.isEmpty(roomNum)){     //宿舍号
                        ToastUtils.showLongToast(mContext.getResources()
                                .getString(R.string.please_to_input_your_room_num));
                        return;
                    }

                    Map map = new HashMap();
                    map.put("name", name);
                    map.put("cellphone", phone);
                    map.put("area_id", area_id);
                    map.put("school_id", schoolId);
                    map.put("building_id", buildId);
                    map.put("room_num", schoolName + buildName);
                    map.put("is_defaul", isDefault ? "1" : "2");
                    map.put("address_detail", buildNum + roomNum);
                    map.put("type", "1");
                    String address_info = new Gson().toJson(map) ;
                    if(bean != null){   //修改操作
                        getPresenter().updataAddress("1", address_info,bean.getId());
                    }else{  //添加操作
                        getPresenter().addAddress("1", address_info);
                    }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(SchoolBean bean){
        schoolName = bean.getName();
        tvChooseSchool.setText(schoolName);
        schoolId = bean.getSchool_id() ;
        area_id = bean.getArea_id();
        getPresenter().getSchoolBuild("3","",bean.getArea_id(),schoolId,1);
    }

    //将点击的view之外的控件背景修改
    private void refreshFlow() {
        for(int i=0;i<packageInfoViews.size();i++){
            packageInfoViews.get(i).setSelected(false);
        }
    }

    /**
     * 获取宿舍楼栋成功
     * @param response
     */
    @Override
    public void getBuildSuccess(BuildListBean response) {
        packageInfoViews.clear();
        flowBuild.removeAllViews();
        for (int j = 0 ; j < response.size(); j++){
            View view = LayoutInflater.from(mContext).inflate(R.layout.add_dormitory_item_view,null);
            TextView tv_group = view.findViewById(R.id.dorm_num_tv);
            tv_group.setText(response.get(j).getName());
            packageInfoViews.add(tv_group);
            flowBuild.addView(view);
            int finalJ = j;
            tv_group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isSelect = v.isSelected();
                    refreshFlow();
                    v.setSelected(!isSelect);
                    buildId = response.get(finalJ).getBuilding_id();
                    schoolId = response.get(finalJ).getSchool_id() ;
                    buildName = response.get(finalJ).getName() ;
                }

            });
        }
    }

    /**
     * 添加宿舍地址成功
     * @param response
     */
    @Override
    public void addAddress(DormitoryAddress response) {
        EventBus.getDefault().post(new AddressEvent());
        finish();
    }

    /**
     * 修改宿舍地址成功
     */
    @Override
    public void upDateSuccess() {
        EventBus.getDefault().post(new AddressEvent());
        finish();
    }
}
