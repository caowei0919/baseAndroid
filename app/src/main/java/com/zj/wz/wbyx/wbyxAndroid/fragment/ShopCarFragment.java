package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.enlogy.statusview.StatusView;
import com.google.gson.Gson;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.ShopCarAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.event.DeleteAllInvalidEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.GoodsInShopEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.ShopCarEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.ShopCarSelectAllEvent;
import com.zj.wz.wbyx.wbyxAndroid.event.UpdataPriceAndNumEvent;
import com.zj.wz.wbyx.wbyxAndroid.presenter.ShopCarPresenter;
import com.zj.wz.wbyx.wbyxAndroid.utils.Arith;
import com.zj.wz.wbyx.wbyxAndroid.view.ShopCarView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: ShopCarFragment
 * Author: 曹伟
 * Date: 2019/9/20 20:27
 * Description: 购物车fragment
 */

public class ShopCarFragment extends BaseMvpFragment<ShopCarView, ShopCarPresenter>
        implements ShopCarView {
    @BindView(R.id.status_shopCar)
    StatusView statusShopCar ;
    @BindView(R.id.recycle_shopCar)
    RecyclerView recycleShopCar ;       //购物车
    @BindView(R.id.tv_totalPrice)
    TextView tvTotalPrice ;     //结算总金额
    @BindView(R.id.checkbox_shopCar)
    CheckBox checkboxShopCar ;  //全选
    @BindView(R.id.tv_settlement)
    TextView tvSettlement ;     //结算数量

    private int settlementNum = 0 ; //结算数量
    private String totalPrice = "0" ;   //结算总价格
    private List<String> recomSelectedIds = new ArrayList<>() ;    //对应global类型的选中商品id集合
    private List<String> recomUnSelectedIds = new ArrayList<>() ;   //未选中
    private List<String> daySelectedIds = new ArrayList<>() ;       //对应city类型的选中商品id集合
    private List<String> dayUnSelectedIds = new ArrayList<>() ;       //未选中
    private List<String> dormSelectedIds = new ArrayList<>();       //对应dorm类型的选中商品id集合
    private List<String> dormUnSelectedIds = new ArrayList<>();       //未选中

    private ShopCarAdapter adapter ;
    private List<ShopCarBean.ShopBean> shopBeans = new ArrayList<>();
    private List<ShopCarBean.InvalidBean> invalidBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopcar;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {
        StatusBarUtil.setStatusBarColor(getActivity()
                ,mContext.getResources().getColor(R.color.c_FF3333));
        statusShopCar.showEmptyContent();
        tvTotalPrice.setText(mContext.getResources().getString(R.string.rmb) + totalPrice);
        tvSettlement.setText(mContext.getResources().getString(R.string.settlement)
                + "(" + settlementNum + ")");
        statusShopCar.setOnItemClickListener(R.id.img_goShop, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLog.e("去逛逛");
                statusShopCar.showContent();
            }
        });

        /**
         * 底部全选监听，改变所有商铺的选中状态
         */
        checkboxShopCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adapter.setlectedAll(isChecked);
            }
        });

        initRecycle();
    }

    @OnClick({R.id.tv_delete})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_delete :
                fillSelectedIds();
                Map carMap = new HashMap();
                Map map = new HashMap();
                map.put("recom", recomSelectedIds);
                map.put("day", daySelectedIds);
                map.put("dorm", dormSelectedIds);
                String strEntity = new Gson().toJson(map);
                carMap.put("cart_data", strEntity);
                getPresenter().deleteAllSelected(carMap);
                break;
        }
    }

    /**
     * 初始化recycle
     */
    private void initRecycle() {
        adapter = new ShopCarAdapter(mContext,shopBeans,invalidBeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext );
        //设置布局管理器
        recycleShopCar.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        layoutManager.setReverseLayout(false);
        //设置Adapter
        recycleShopCar.setAdapter(adapter);
        recycleShopCar.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext
                ,LinearLayoutManager.HORIZONTAL
                ,20
                ,mContext.getResources().getColor(R.color.c_F8F8F8)));
        //设置增加或删除条目的动画
        recycleShopCar.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getShopCarGoods(Constant.getMyLocation().getAdCode());
    }

    /**
     * 页面不可见时，上传用户选中和未选中的状态
     * @param visible
     */
    @Override
    protected void onVisibilityChanged(boolean visible) {
        super.onVisibilityChanged(visible);
        if (!visible){
            fillSelectedIds();
            Map carMap = new HashMap();
            Map map = new HashMap();
            if(recomSelectedIds.size() > 0 || daySelectedIds.size() > 0
                    || dormSelectedIds.size() > 0){
                map.put("recom", recomSelectedIds);
                map.put("day", daySelectedIds);
                map.put("dorm", dormSelectedIds);
                String selectedEntity = new Gson().toJson(map);
                carMap.put("cart_data", selectedEntity);
                carMap.put("checked", "1");
                getPresenter().putUserSelected(carMap);         //上传选中状态
            }

            if(recomUnSelectedIds.size() > 0 || dayUnSelectedIds.size() > 0
                    || dormUnSelectedIds.size() > 0){
                map.clear();
                carMap.clear();
                map.put("recom", recomUnSelectedIds);
                map.put("day", dayUnSelectedIds);
                map.put("dorm", dormUnSelectedIds);
                String unSelectedEntity = new Gson().toJson(map);
                carMap.put("cart_data", unSelectedEntity);
                carMap.put("checked", "0");
                getPresenter().putUserSelected(carMap);         //上传未选中状态
            }
        }
    }

    /**
     * 购物车角标获取成功后，获取购物车列表
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(ShopCarEvent event){
        getPresenter().getShopCarGoods(Constant.getMyLocation().getAdCode());
    }

    /**
     * 更新底部结算信息
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(UpdataPriceAndNumEvent event){
        tvTotalPrice.setText(mContext.getResources().getString(R.string.rmb) + event.getAllPrice());
        tvSettlement.setText(mContext.getResources().getString(R.string.settlement)
                + "(" + event.getAllNum() + ")");
    }

    /**
     * 全选或者取消全选
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(ShopCarSelectAllEvent event){
        checkboxShopCar.setChecked(event.isSelectAll());
        checkboxShopCar.setOnCheckedChangeListener(null);
    }

    /**
     * 清空失效商品
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(DeleteAllInvalidEvent event){
        daySelectedIds.clear();
        recomSelectedIds.clear();
        dormSelectedIds.clear();
        for (ShopCarBean.InvalidBean bean : invalidBeans) {
            switch (bean.getGoods_type()){
                case "global" :     //窝边推荐
                    recomSelectedIds.add(bean.getCart_id());
                    break;

                case "dorm" :       //宿舍小店
                    dormSelectedIds.add(bean.getCart_id());
                    break;

                case "city" :       //窝边超市
                    daySelectedIds.add(bean.getCart_id());
                    break;
            }
        }
        Map carMap = new HashMap();
        Map map = new HashMap();
        map.put("recom", recomSelectedIds);
        map.put("day", daySelectedIds);
        map.put("dorm", dormSelectedIds);
        String strEntity = new Gson().toJson(map);
        carMap.put("cart_data", strEntity);
        getPresenter().deleteAllSelected(carMap);
    }

    /**
     * 列表商品相关操作（数量加减，删除，点击进入详情）
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(GoodsInShopEvent event){
        switch (event.getEventTag()){
            case ADD:   //数量加

                break;

            case SUBTRACTION:   //数量减

                break;

            case ITEM:  //item点击进入详情

                break;

            case DELETE:
                daySelectedIds.clear();
                recomSelectedIds.clear();
                dormSelectedIds.clear();
                switch (event.getBean().getGoods_type()){
                    case "global" :     //窝边推荐
                        recomSelectedIds.add(event.getBean().getCart_id());
                        break;

                    case "dorm" :       //宿舍小店
                        dormSelectedIds.add(event.getBean().getCart_id());
                        break;

                    case "city" :       //窝边超市
                        daySelectedIds.add(event.getBean().getCart_id());
                        break;
                }
                Map carMap = new HashMap();
                Map map = new HashMap();
                map.put("recom", recomSelectedIds);
                map.put("day", daySelectedIds);
                map.put("dorm", dormSelectedIds);
                String strEntity = new Gson().toJson(map);
                carMap.put("cart_data", strEntity);
                getPresenter().deleteAllSelected(carMap);
                break;  //删除商品
        }
    }

    /**
     * 获取购物车商品成功
     * @param response
     */
    @Override
    public void getShopCartShopSuccess(ShopCarBean response) {
        shopBeans.clear();
        invalidBeans.clear();
        if(response.getShop()!=null && response.getShop().size() > 0){
            shopBeans.addAll(response.getShop());
        }
        if(response.getInvalid()!=null && response.getInvalid().size() > 0){
            invalidBeans.addAll(response.getInvalid());
        }
        if(shopBeans.size() > 0 || invalidBeans.size() > 0){
            statusShopCar.showContent();
            adapter.notifyDataSetChanged();
            resetBottomDate();
        }else{
            statusShopCar.showEmptyContent();
        }
    }

    /**
     * 删除购物车选中成功
     */
    @Override
    public void deleteSelectedSuccess() {
        getPresenter().getShopCarGoods(Constant.getMyLocation().adCode);
    }

    /**
     * 获取选中的商品和数量
     */
    private void resetBottomDate() {
        boolean isSelectAll = true ;
        BigDecimal allprice =new  BigDecimal("0");
        BigDecimal allNum = new BigDecimal("0");
        if(shopBeans!=null) {
            for (int i = 0; i < shopBeans.size(); i++) {
                List<ShopCarBean.ShopBean.GoodsBean> mdata = shopBeans.get(i).getGoods();
                for (int y = 0; y < mdata.size(); y++) {
                    if (mdata.get(y).getIs_selected().equals("1")) {
                        BigDecimal interestRate = new BigDecimal(mdata.get(y).getCart_num()); //数量
                        allNum = Arith.add(allNum, interestRate);
                        double interest = Arith.mul(Double.valueOf(mdata.get(y)
                                .getPrice_normal()), interestRate);
                        allprice = allprice.add(BigDecimal.valueOf(interest));
                    }else{
                        isSelectAll = false ;
                    }
                }
            }
            checkboxShopCar.setChecked(isSelectAll);
            tvTotalPrice.setText(mContext.getResources().getString(R.string.rmb) + allprice);
            tvSettlement.setText(mContext.getResources().getString(R.string.settlement)
                    + "(" + allNum + ")");
        }
    }

    /**
     * 填充选中商品进行分类填充
     */
    private void fillSelectedIds(){
        daySelectedIds.clear();
        dayUnSelectedIds.clear();
        dormSelectedIds.clear();
        dormUnSelectedIds.clear();
        recomSelectedIds.clear();
        recomUnSelectedIds.clear();
        for (int i = 0; i < shopBeans.size() ; i++){
            switch (shopBeans.get(i).getShop_type()){
                case "global" :     //窝边推荐
                    for (ShopCarBean.ShopBean.GoodsBean bean:shopBeans.get(i).getGoods()) {
                        if(bean.getIs_selected().equals("1")){
                            recomSelectedIds.add(bean.getCart_id());
                        }else{
                            recomUnSelectedIds.add(bean.getCart_id());
                        }
                    }
                    break;

                case "dorm" :       //宿舍小店
                    for (ShopCarBean.ShopBean.GoodsBean bean:shopBeans.get(i).getGoods()) {
                        if(bean.getIs_selected().equals("1")){
                            dormSelectedIds.add(bean.getCart_id());
                        }else{
                            dormUnSelectedIds.add(bean.getCart_id());
                        }
                    }
                    break;

                case "city" :       //窝边超市
                    for (ShopCarBean.ShopBean.GoodsBean bean:shopBeans.get(i).getGoods()) {
                        if(bean.getIs_selected().equals("1")){
                            daySelectedIds.add(bean.getCart_id());
                        }else{
                            dayUnSelectedIds.add(bean.getCart_id());
                        }
                    }
                    break;
            }
        }
    }
}
