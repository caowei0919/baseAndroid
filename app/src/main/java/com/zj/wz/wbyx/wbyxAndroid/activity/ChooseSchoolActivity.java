package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.baseandroid.utils.addr.CountyOptionsUtils;
import com.zj.wz.wbyx.baseandroid.utils.addr.Province;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.ChooseSchoolAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.ChooseSchool;
import com.zj.wz.wbyx.wbyxAndroid.bean.SchoolBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SelectSchoolBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.ChooseSchoolPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.ChooseSchoolView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * FileName: ChooseSchoolActivity
 * Author: 曹伟
 * Date: 2019/10/7 14:38
 * Description:选择学校
 */

public class ChooseSchoolActivity extends BaseMvpActivity<ChooseSchoolView, ChooseSchoolPresenter>
        implements ChooseSchoolView, ChooseSchoolAdapter.OnItemClick {

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;   //返回键
    @BindView(R.id.ed_search)
    EditText edSearch ;     //搜素学校
    @BindView(R.id.tv_location)
    TextView tvLocation ;   //地址
    @BindView(R.id.recycle_chooseSchool)
    RecyclerView recycleChooseSchool ;  //选择学校
    @BindView(R.id.smart_chooseSchool)
    SmartRefreshLayout smartChooseSchool ;

    private ChooseSchoolAdapter adapter ;
    private OptionsPickerView cityPickerView ;
    private List<SchoolBean> schoolBeans = new ArrayList<>();
    private ArrayList<Province> provinceItems = new ArrayList<>();
    private ArrayList<ArrayList<Province.City>> cityItems = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Province.City.Area>>> areaItems = new ArrayList<>();
    private String current_city_name ;  //当前城市
    private String area_id = "";    //区域id
    private String school_id = "";  //学校id
    private int page = 1 ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_school;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.choose_school));
        initRecycle();
        //刷新加载更多操作
        smartChooseSchool.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                //尚未填写搜索信息的情况下，搜索附近学校
                if(TextUtils.isEmpty(edSearch.getText().toString().trim())){
                    getPresenter().getNearSchool(TextUtils.isEmpty(area_id) ? "1" : "2"
                            ,current_city_name,area_id,school_id,page);
                }else{
                    getPresenter().searchSchool(edSearch.getText().toString().trim()
                            ,area_id,page);
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1 ;
                //尚未填写搜索信息的情况下，搜索附近学校
                if(TextUtils.isEmpty(edSearch.getText().toString().trim())){
                    getPresenter().getNearSchool(TextUtils.isEmpty(area_id) ? "1" : "2"
                            ,current_city_name,area_id,school_id,page);
                }else{
                    getPresenter().searchSchool(edSearch.getText().toString().trim()
                            ,area_id,page);
                }
            }
        });
    }

    /**
     * 初始化recycleView配置
     */
    private void initRecycle() {
        adapter = new ChooseSchoolAdapter(mContext,schoolBeans,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        //设置布局管理器
        recycleChooseSchool.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycleChooseSchool.setAdapter(adapter);
        recycleChooseSchool.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,1
                ,mContext.getResources().getColor(R.color.c_EEEEEE)));
        //设置增加或删除条目的动画
        recycleChooseSchool.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        current_city_name = Constant.getMyLocation().getCityName() ;
        getPresenter().getNearSchool("1",current_city_name
                ,area_id,school_id,page);
    }

    /**
     * 输入框搜索监听
     * @param code
     * @param keyEvent
     * @return
     */
    @OnEditorAction(R.id.ed_search)
    public boolean OnEditorAction(int code ,KeyEvent keyEvent){
        //以下方法防止两次发送请求
        if (code == EditorInfo.IME_ACTION_SEND ||
                (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            switch (keyEvent.getAction()) {
                case KeyEvent.ACTION_UP:
                    AndroidUtils.hideSoftInput(edSearch);   //收起软键盘
                    //发送请求
                    String keyWord = edSearch.getText().toString().trim();
                    if (null == keyWord){
                        return true ;
                    }
                    page = 1 ;
                    getPresenter().searchSchool(keyWord,area_id,page);
                    return true;
                default:
                    return true;
            }
        }
        return false;
    }

    /**
     * @param view 点击监听
     */
    @OnClick({R.id.linear_back,R.id.tv_location})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.linear_back :     //返回
                    finish();
                break;

            case R.id.tv_location : //选择地址
                    showCityWheel();
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
                tvLocation.setText(province + city + area);//选定后显示
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
     * 获取附近学校成功
     * @param school
     */
    @Override
    public void getNearSchool(ChooseSchool.SchoolBean school) {
        if(school.getData() != null && school.getData().size() > 0){
            if(page == 1){
                smartChooseSchool.finishRefresh();
                schoolBeans.clear();
            }else{
                smartChooseSchool.finishLoadMore();
            }
            for (ChooseSchool.SchoolBean.DataBean bean:school.getData()) {
                schoolBeans.add(new SchoolBean(bean.getSchool_id(),bean.getName()));
            }
            if (schoolBeans.size() == Integer.valueOf(school.getTotal())){
                smartChooseSchool.setEnableLoadMore(false);
            }else{
                smartChooseSchool.setEnableLoadMore(true);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void searchSchoolSuccess(SelectSchoolBean baseBean) {
        if(baseBean.getData() != null && baseBean.getData().size() > 0){
            if(page == 1){
                smartChooseSchool.finishRefresh();
                schoolBeans.clear();
            }else{
                smartChooseSchool.finishLoadMore();
            }
            for (SelectSchoolBean.DataBean bean:baseBean.getData()) {
                schoolBeans.add(new SchoolBean(bean.getSchool_id(),bean.getName()));
            }
            if (schoolBeans.size() == Integer.valueOf(baseBean.getTotal())){
                smartChooseSchool.setEnableLoadMore(false);
            }else{
                smartChooseSchool.setEnableLoadMore(true);
            }
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 选择某个学校(附近学校)
     * @param bean
     */
    @Override
    public void onItemClick(SchoolBean bean) {
        school_id = bean.getSchool_id() ;
        EventBus.getDefault().post(bean);
        finish();
    }
}
