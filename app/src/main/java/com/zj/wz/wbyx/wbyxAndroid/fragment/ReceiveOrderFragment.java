package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enlogy.statusview.StatusView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.wbyxAndroid.adapter.OrderAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderListBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.OrderPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.OrderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.zj.wz.wbyx.baseandroid.config.Constant.PAGE_NUM;
import static com.zj.wz.wbyx.baseandroid.config.Constant.RECEIVE_ORDER;

/**
 * FileName: HomeFragment
 * Author: 曹伟
 * Date: 2019/9/20 20:25
 * Description: 待收货订单fragment
 */

public class ReceiveOrderFragment extends BaseMvpFragment<OrderView, OrderPresenter>
        implements OrderView{

    @BindView(R.id.recycle_order)
    RecyclerView recycleOrder ;  //订单列表
    @BindView(R.id.status_order)
    StatusView statusOrder ;
    @BindView(R.id.smart_order)
    SmartRefreshLayout smartOrder ;

    private int page = 1 ;  //页码
    private OrderAdapter adapter ;
    private List<OrderBean> orderBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {
        statusOrder.showEmptyContent();
        smartOrder.setEnableLoadMore(false);
        smartOrder.setEnableRefresh(false);
        smartOrder.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page ++ ;
                getPresenter().getOrder(RECEIVE_ORDER,page,PAGE_NUM);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1 ;
                getPresenter().getOrder(RECEIVE_ORDER,page,PAGE_NUM);
            }
        });
        initRecycle();
        statusOrder.setOnItemClickListener(R.id.tv_goShop, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLog.e("去逛逛");
            }
        });
    }

    private void initRecycle() {
        adapter = new OrderAdapter(mContext,orderBeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        //设置布局管理器
        recycleOrder.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycleOrder.setAdapter(adapter);
        recycleOrder.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,20
                ,mContext.getResources().getColor(R.color.c_F5F5F5)));
        //设置增加或删除条目的动画
        recycleOrder.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getOrder(RECEIVE_ORDER,page,PAGE_NUM);
    }


    /**
     * 获取订单列表成功
     * @param response
     */
    @Override
    public void getOrderListSuccess(OrderListBean response) {
        smartOrder.finishRefresh();
        smartOrder.finishLoadMore();
        if(page == 1){
            orderBeans.clear();
        }
        orderBeans.addAll(response);
        if(orderBeans.size() == 0){
            smartOrder.setEnableLoadMore(false);
            smartOrder.setEnableRefresh(false);
            statusOrder.showEmptyContent();
            return;
        }else{
            smartOrder.setEnableLoadMore(true);
            smartOrder.setEnableRefresh(true);
        }
        statusOrder.showContent();
        adapter.notifyDataSetChanged();
    }
}
