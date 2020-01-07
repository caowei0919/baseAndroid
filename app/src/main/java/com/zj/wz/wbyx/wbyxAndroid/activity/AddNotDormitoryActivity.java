package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.PhoneNumTools;
import com.zj.wz.wbyx.baseandroid.utils.ToastUtils;
import com.zj.wz.wbyx.baseandroid.utils.addr.CountyOptionsUtils;
import com.zj.wz.wbyx.baseandroid.utils.addr.Province;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.baseandroid.view.SwitchButton;
import com.zj.wz.wbyx.wbyxAndroid.bean.NotDormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.event.AddressEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.AddNotDormitoryPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.AddNotDormitoryView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * FileName: AddNotDormitoryActivity
 * Author: 曹伟
 * Date: 2019/10/18 9:30
 * Description:新增修改非宿舍地址
 */

public class AddNotDormitoryActivity extends BaseMvpActivity<AddNotDormitoryView
        , AddNotDormitoryPresenter> implements AddNotDormitoryView{
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.ed_name)
    EditText edName ;   //姓名
    @BindView(R.id.ed_phone)
    EditText edPhone ;  //手机号
    @BindView(R.id.tv_area)
    TextView tvArea ;   //区域
    @BindView(R.id.ed_areaDetail)
    EditText edAreaDetail ; //详细地址
    @BindView(R.id.switch_address)
    SwitchButton switchAddress ;    //默认地址
    @BindView(R.id.btn_complete)
    Button btnComplete ;    //完成

    private String name = "" ;  //姓名
    private String phone = "" ; //手机号
    private String areaDetail = "" ;    //详细地址
    private String area_id = "" ;

    private boolean isDefault = false ;
    private NotDormitoryAddress bean = null ;
    private OptionsPickerView cityPickerView ;
    private ArrayList<Province> provinceItems = new ArrayList<>();
    private ArrayList<ArrayList<Province.City>> cityItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Province.City.Area>>> areaItems = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_not_dormitory;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.not_dormitory));

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
        edAreaDetail.setFilters(new InputFilter[]{filter});
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
    @OnTextChanged(value = R.id.ed_areaDetail,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onAreaDetailTextChange(Editable editable){
        areaDetail = editable.toString().replaceAll(" ","");
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        isDefault = getIntent().getBooleanExtra("isDefalut",false);
        if(getIntent().getSerializableExtra("bean") != null){
            bean = (NotDormitoryAddress) getIntent().getSerializableExtra("bean");
            edName.setText(bean.getName());
            name = bean.getName();
            edPhone.setText(bean.getCellphone());
            phone = bean.getCellphone();
            edAreaDetail.setText(bean.getAddress_detail());
            areaDetail = bean.getAddress_detail();
            tvArea.setText((TextUtils.isEmpty(bean.getPname()) ? "" : bean.getPname()) //省
                    + (TextUtils.isEmpty(bean.getCname()) ? "" : bean.getCname())   //市
                    + (TextUtils.isEmpty(bean.getAname()) ? "" : bean.getAname())); //区
            //区域id选择，无区id选择市id，无市id选择省id
            area_id = (TextUtils.isEmpty(bean.getArea_id())
                    ? (TextUtils.isEmpty(bean.getCity_id())
                    ? (TextUtils.isEmpty(bean.getProvince_id())
                    ? "" : bean.getProvince_id()) : bean.getCity_id()):bean.getArea_id());
        }
    }

    @OnClick({R.id.linear_back,R.id.tv_area,R.id.btn_complete})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.linear_back:  //返回
                finish();
                break;

            case R.id.tv_area : //区域
                AndroidUtils.hideSoftInput(tvArea);
                showCityWheel();
                break;

            case R.id.btn_complete : //完成
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
                if(TextUtils.isEmpty(areaDetail)){    //详细地址
                    ToastUtils.showLongToast(mContext.getResources()
                            .getString(R.string.please_to_input_addr_detail));
                    return;
                }
                HashMap map = new HashMap();
                map.put("name",name);
                map.put("cellphone",phone);
                map.put("area_id",area_id);
                map.put("is_defaul", (switchAddress.isChecked() || isDefault)? "1" : "2");
                map.put("address_detail",areaDetail);
                map.put("type","2");
                String  address_info =  new Gson().toJson(map);
                if(bean != null){   //是修改地址操作
                    getPresenter().upDateAddress("2", address_info,bean.getId());
                }else{
                    getPresenter().addAddress("2", address_info);
                }
                break;
        }
    }

    /**
     * 展示省市区选择
     */
    private void showCityWheel() {
        provinceItems = CountyOptionsUtils.getProvinces();
        cityItems = CountyOptionsUtils.getCitys();
        areaItems = CountyOptionsUtils.getAreas();
        cityPickerView = new OptionsPickerView
                .Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String province = provinceItems.get(options1).getName();
                String city = cityItems.get(options1).get(options2).getName();
                String area = areaItems.get(options1).get(options2)
                        .get(options3).getName() ;
                area_id = TextUtils.isEmpty(areaItems.get(options1).get(options2)
                        .get(options3).getArea_id()) ? ""
                        :areaItems.get(options1).get(options2)
                        .get(options3).getArea_id() ;
                tvArea.setText(province + city + area);//选定后显示
            }
        })
                .setDividerColor(Color.BLACK)//这几个值没需求的可以不要
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();//这个不能丢

        cityPickerView.setPicker(provinceItems,cityItems,areaItems);//三级选择器（省市区）
        cityPickerView.show();
    }

    /**
     * 添加非宿舍地址成功
     * @param response
     */
    @Override
    public void addNotDormitoryAddress(NotDormitoryAddress response) {
        EventBus.getDefault().post(new AddressEvent());
        finish();
    }

    /**
     * 修改非宿舍地址成功
     */
    @Override
    public void upDateSuccess() {
        EventBus.getDefault().post(new AddressEvent());
        finish();
    }
}
