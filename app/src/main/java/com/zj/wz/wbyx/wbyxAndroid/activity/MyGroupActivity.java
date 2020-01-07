package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.GroupAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.GroupBeans;
import com.zj.wz.wbyx.wbyxAndroid.bean.GroupItemBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.MyGroupPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.MyGroupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: MyGroupActivity
 * Author: 曹伟
 * Date: 2019/10/9 14:07
 * Description:我的拼团
 */

public class MyGroupActivity extends BaseMvpActivity<MyGroupView, MyGroupPresenter>
        implements MyGroupView {

    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.recycle_group)
    RecyclerView recycleGroup ;  //拼团
    @BindView(R.id.smart_group)
    SmartRefreshLayout smartGroup ;

    private int page = 1 ;  //页码
    private GroupAdapter adapter ;  //拼团适配器
    private List<GroupItemBean> groupItemBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_my_group;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.order_for_group));
        initRecycle();

        //刷新加载更多操作
        smartGroup.setOnLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1 ;
            }
        });
    }

    private void initRecycle() {
        adapter = new GroupAdapter(mContext,groupItemBeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        //设置布局管理器
        recycleGroup.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycleGroup.setAdapter(adapter);
        recycleGroup.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext,
                LinearLayoutManager.HORIZONTAL
                ,10
                ,mContext.getResources().getColor(R.color.c_F1F1F1)));
        //设置增加或删除条目的动画
        recycleGroup.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().getGroupOrder(Constant.getMyLocation().adCode);
    }

    @OnClick({R.id.linear_back})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;
        }
    }

    @Override
    public void getGroupSuccess(GroupBeans response) {
        groupItemBeans.addAll(response);
        adapter.notifyDataSetChanged();
    }
}
