package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enlogy.statusview.StatusView;
import com.google.gson.Gson;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.DormitoryAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.NotDormitoryAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddressBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.bean.NotDormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.event.AddressEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.DeleteAddressEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.MyAddrPresenter;
import com.zj.wz.wbyx.wbyxAndroid.utils.DialogUtils;
import com.zj.wz.wbyx.wbyxAndroid.view.MyAddrView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: MyAddrActivity
 * Author: 曹伟
 * Date: 2019/10/15 11:46
 * Description:我的地址
 */

public class MyAddrActivity extends BaseMvpActivity<MyAddrView, MyAddrPresenter>
        implements MyAddrView , DormitoryAdapter.OnDoritoryItemClick
        , NotDormitoryAdapter.OnDoritoryItemClick{

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.status_address)
    StatusView statusAddress ;
    @BindView(R.id.recycle_dormitory)
    RecyclerView recycleDormitory ;     //宿舍地址
    @BindView(R.id.relative_dormitory)
    RelativeLayout relativeDormitory ;  //宿舍地址标题栏
    @BindView(R.id.relative_expend)
    RelativeLayout relativeExpend ; //宿舍地址展开收起
    @BindView(R.id.tv_onAll)
    TextView tvOnAll ;
    @BindView(R.id.recycle_notDormitory)
    RecyclerView recycleNotDormitory ;  //非宿舍地址
    @BindView(R.id.tv_other)
    TextView tvOther ;  //非宿舍地址标题
    @BindView(R.id.relative_otherExpend)
    RelativeLayout relativeOtherExpend ;    //非宿舍地址展开收起
    @BindView(R.id.tv_onAllToOther)
    TextView tvOnAllToOther ;


    private DormitoryAdapter mDormitoryAdapter ;
    private List<DormitoryAddress> dormitoryAddressList = new ArrayList<>();
    private NotDormitoryAdapter mNotDormitoryAdapter ;
    private List<NotDormitoryAddress> notDormitoryAddressList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_addr;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.my_address));

        initRecycleDormitory();
        initNotDormitory();
    }

    /**
     * 宿舍地址recycleView初始化
     */
    private void initRecycleDormitory() {
        mDormitoryAdapter = new DormitoryAdapter(mContext,dormitoryAddressList
                ,this);
        mDormitoryAdapter.setExpend(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        //设置布局管理器
        recycleDormitory.setLayoutManager(layoutManager);
        recycleDormitory.setHasFixedSize(true);
        recycleDormitory.setNestedScrollingEnabled(false);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycleDormitory.setAdapter(mDormitoryAdapter);
        recycleDormitory.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,1
                ,mContext.getResources().getColor(R.color.c_EEEEEE)));
        //设置增加或删除条目的动画
        recycleDormitory.setItemAnimator( new DefaultItemAnimator());
    }

    /**
     * 非宿舍地址recycleView初始化
     */
    private void initNotDormitory(){
        mNotDormitoryAdapter = new NotDormitoryAdapter(mContext,notDormitoryAddressList
                ,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        //设置布局管理器
        recycleNotDormitory.setLayoutManager(layoutManager);
        recycleNotDormitory.setHasFixedSize(true);
        recycleNotDormitory.setNestedScrollingEnabled(false);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycleNotDormitory.setAdapter(mNotDormitoryAdapter);
        recycleNotDormitory.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,1
                ,mContext.getResources().getColor(R.color.c_EEEEEE)));
        //设置增加或删除条目的动画
        recycleNotDormitory.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        statusAddress.showEmptyContent();
        getPresenter().getAllAddress();
    }

    @OnClick({R.id.linear_back,R.id.tv_addNotDormitory,R.id.tv_addDormitory,R.id.relative_expend
            ,R.id.relative_otherExpend})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back :  //返回
                    finish();
                break;

            case R.id.tv_addDormitory : //增加宿舍地址
                    Intent addDormitoryIntent = new Intent(mContext, AddDormitoryActivity.class);
                    addDormitoryIntent.putExtra("isDefalut"
                            ,dormitoryAddressList.size() > 0 ? false : true);
                    startActivity(addDormitoryIntent);
                break;

            case R.id.tv_addNotDormitory :  //增加非宿舍地址
                Intent addNotDormitoryIntent = new Intent(mContext, AddDormitoryActivity.class);
                addNotDormitoryIntent.putExtra("isDefalut"
                        ,notDormitoryAddressList.size() > 0 ? false : true);
                    startActivity(new Intent(mContext, AddNotDormitoryActivity.class));
                break;

            case R.id.relative_expend : //宿舍地址展开收起
                    mDormitoryAdapter.setExpend(!mDormitoryAdapter.isExpend());
                    mDormitoryAdapter.notifyDataSetChanged();
                    tvOnAll.setSelected(!mDormitoryAdapter.isExpend());
                    tvOnAll.setText(!mDormitoryAdapter.isExpend()
                            ? mContext.getResources().getString(R.string.pack_up)
                            : mContext.getResources().getString(R.string.on_all));
                break;

            case R.id.relative_otherExpend :    //非宿舍地址展开收起
                    mNotDormitoryAdapter.setExpend(!mNotDormitoryAdapter.isExpend());
                    mNotDormitoryAdapter.notifyDataSetChanged();
                    tvOnAllToOther.setSelected(!mNotDormitoryAdapter.isExpend());
                    tvOnAllToOther.setText(!mNotDormitoryAdapter.isExpend()
                            ? mContext.getResources().getString(R.string.pack_up)
                            : mContext.getResources().getString(R.string.on_all));
                break;
        }
    }

    /**
     * 获取所有地址成功
     * @param response
     */
    @Override
    public void getAllAddressSuccess(AddressBean response) {
        /**
         * 有地址信息
         */
        if((response.getElse_address() != null && response.getElse_address().size() > 0)
                || (response.getShop_address() != null && response.getShop_address().size() > 0)){
            statusAddress.showContent();
        }else{
            statusAddress.showEmptyContent();
        }

        dormitoryAddressList.clear();
        dormitoryAddressList.addAll(response.getShop_address());
        relativeDormitory.setVisibility((response.getShop_address() != null
                && response.getShop_address().size() > 0) ? View.VISIBLE : View.GONE);
        relativeExpend.setVisibility((response.getShop_address() != null
                && response.getShop_address().size() > 2) ? View.VISIBLE : View.GONE);
        mDormitoryAdapter.notifyDataSetChanged();

        notDormitoryAddressList.clear();
        notDormitoryAddressList.addAll(response.getElse_address());
        tvOther.setVisibility((response.getElse_address() != null
                && response.getElse_address().size() > 0) ? View.VISIBLE : View.GONE);
        relativeOtherExpend.setVisibility((response.getElse_address() != null
                && response.getElse_address().size() > 2) ? View.VISIBLE : View.GONE);
        mNotDormitoryAdapter.notifyDataSetChanged();
    }

    /**
     * 宿舍地址设置默认
     * @param dormitoryAddress
     */
    @Override
    public void setDefaultClick(DormitoryAddress dormitoryAddress) {
        Map map = new HashMap();
        map.put("name", dormitoryAddress.getName());
        map.put("cellphone", dormitoryAddress.getCellphone());
        map.put("area_id", dormitoryAddress.getArea_id());
        map.put("school_id", dormitoryAddress.getSchool_id());
        map.put("building_id", dormitoryAddress.getBuilding_id());
        map.put("room_num", dormitoryAddress.getRoom_num());
        map.put("is_defaul","1");
        map.put("address_detail",dormitoryAddress.getAddress_detail());
        map.put("type", "1");
        String address_info = new Gson().toJson(map) ;
        getPresenter().upDateDormitory("1",address_info,dormitoryAddress.getId());
    }

    /**
     * 编辑宿舍地址
     * @param dormitoryAddress
     */
    @Override
    public void onWriteClick(DormitoryAddress dormitoryAddress) {
        Intent intent = new Intent(mContext, AddDormitoryActivity.class);
        intent.putExtra("bean",dormitoryAddress);
        startActivity(intent);
    }

    /**
     * 删除宿舍地址
     * @param dormitoryAddress
     */
    @Override
    public void onDeleteClick(DormitoryAddress dormitoryAddress) {
        DialogUtils.showCenterDialog(mContext
                ,mContext.getResources().getString(R.string.delete_the_address)
                ,new DeleteAddressEvent(dormitoryAddress.getName()
                        ,dormitoryAddress.getId()));
    }

    /**
     * item点击时间，用于处理选择地址后回调
     */
    @Override
    public void onItemClick(DormitoryAddress dormitoryAddress) {

    }

    /**
     * 当地址有发生变更的事件处理
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(AddressEvent event){
        getPresenter().getAllAddress();
    }

    /**
     * 删除宿舍地址
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(DeleteAddressEvent event){
        Map map = new HashMap();
        map.put("name", event.getName());
        String address_info = new Gson().toJson(map) ;
        getPresenter().upDateDormitory("3",address_info,event.getId());
    }

    /**
     * 宿舍地址设置默认
     * @param dormitoryAddress
     */
    @Override
    public void setDefaultClick(NotDormitoryAddress dormitoryAddress) {
        Map map = new HashMap();
        map.put("name", dormitoryAddress.getName());
        map.put("cellphone", dormitoryAddress.getCellphone());
        map.put("area_id", dormitoryAddress.getArea_id());
        map.put("school_id", "");
        map.put("building_id", "");
        map.put("room_num", "");
        map.put("is_defaul","1");
        map.put("address_detail",dormitoryAddress.getAddress_detail());
        map.put("type", "2");
        String address_info = new Gson().toJson(map) ;
        getPresenter().upDateDormitory("2",address_info,dormitoryAddress.getId());
    }

    /**
     * 编辑宿舍地址
     * @param dormitoryAddress
     */
    @Override
    public void onWriteClick(NotDormitoryAddress dormitoryAddress) {
        Intent intent = new Intent(mContext, AddNotDormitoryActivity.class);
        intent.putExtra("bean",dormitoryAddress);
        startActivity(intent);
    }

    /**
     * 删除宿舍地址
     * @param dormitoryAddress
     */
    @Override
    public void onDeleteClick(NotDormitoryAddress dormitoryAddress) {
        DialogUtils.showCenterDialog(mContext
                ,mContext.getResources().getString(R.string.delete_the_address)
                ,new DeleteAddressEvent(dormitoryAddress.getName()
                        ,dormitoryAddress.getId()));
    }

    /**
     * item点击时间，用于处理选择地址后回调
     */
    @Override
    public void onItemClick(NotDormitoryAddress notDormitoryAddress) {

    }
}
