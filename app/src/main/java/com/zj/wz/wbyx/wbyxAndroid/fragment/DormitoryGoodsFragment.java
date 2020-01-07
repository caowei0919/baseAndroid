package com.zj.wz.wbyx.wbyxAndroid.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.enlogy.statusview.StatusRelativeLayout;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpFragment;
import com.zj.wz.wbyx.wbyxAndroid.adapter.DormitoryGoodsAdapter;
import com.zj.wz.wbyx.wbyxAndroid.adapter.DormitoryLeftMenuAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryGoodsBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.DormitoryGoodsPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.DormitoryGoodsView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * FileName: DormitoryGoodsFragment
 * Author: 曹伟
 * Date: 2019/11/14 15:20
 * Description:宿舍小店商品
 */

public class DormitoryGoodsFragment extends BaseMvpFragment<DormitoryGoodsView
        , DormitoryGoodsPresenter> implements DormitoryGoodsView, DormitoryLeftMenuAdapter.OnItemClick, DormitoryGoodsAdapter.addToShopCarClick {

    @BindView(R.id.status_goods)
    StatusRelativeLayout statusGoods;
    @BindView(R.id.recycle_goodType)
    RecyclerView recycleGoodType ;  //商品标签
    @BindView(R.id.recycle_goods)
    RecyclerView recycleGoods ;   //商品展示

    private DormitoryLeftMenuAdapter dormitoryLeftMenuAdapter ; //左侧标签适配器
    private DormitoryGoodsAdapter dormitoryGoodsAdapter ;   //商品列表适配器
    private String shopId = "" ;

    //标签集合
    private List<DormitoryBean.ShoperBean.CategoryBean> categoryBeans = new ArrayList<>();  //菜单
    private List<DormitoryGoodsBean.GoodsBean> goodsBeans = new ArrayList<>();  //商品集合
    private LinearLayoutManager layoutManagerGoods ;
    private SuspensionDecoration mDecoration ;
    private String cateId = "" ;    //选中标签id
    private String tag = "" ;       //菜单选中标签

    public static DormitoryGoodsFragment newInstance() {
        Bundle args = new Bundle();
        DormitoryGoodsFragment fragment = new DormitoryGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_good;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void setupView(View rootView) {
/**
 * 左侧菜单栏
 */
        dormitoryLeftMenuAdapter
                = new DormitoryLeftMenuAdapter(mContext,categoryBeans,cateId,this);
        LinearLayoutManager layoutManagerLeft = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManagerLeft.setOrientation(OrientationHelper.VERTICAL);
        layoutManagerLeft.setSmoothScrollbarEnabled(true);
        layoutManagerLeft.setAutoMeasureEnabled(true);
        recycleGoodType.setHasFixedSize(true);
        recycleGoodType.setNestedScrollingEnabled(true);
        //设置布局管理器
        recycleGoodType.setLayoutManager(layoutManagerLeft);
        //设置Adapter
        recycleGoodType.setAdapter(dormitoryLeftMenuAdapter);

        /**
         * 商品展示
         */
        dormitoryGoodsAdapter = new DormitoryGoodsAdapter(mContext,goodsBeans,this);
        layoutManagerGoods = new LinearLayoutManager(mContext);
        //设置为垂直布局，这也是默认的
        layoutManagerGoods.setOrientation(OrientationHelper.VERTICAL);
        layoutManagerGoods.setSmoothScrollbarEnabled(true);
        layoutManagerGoods.setAutoMeasureEnabled(true);
        recycleGoods.setHasFixedSize(true);
        recycleGoods.setNestedScrollingEnabled(true);
        //设置布局管理器
        recycleGoods.setLayoutManager(layoutManagerGoods);
        mDecoration = new SuspensionDecoration(getActivity(), goodsBeans).setHeaderViewCount(categoryBeans.size());
        mDecoration.setColorTitleBg(mContext.getResources().getColor(R.color.c_FFFFFF));
        mDecoration.setTitleFontSize(36);
        mDecoration.setColorTitleFont(mContext.getResources().getColor(R.color.c_8F949C));
        mDecoration.setmTitleHeight((int) mContext.getResources().getDimension(R.dimen.H60));
        recycleGoods.addItemDecoration(mDecoration);
        //设置Adapter
        recycleGoods.setAdapter(dormitoryGoodsAdapter);
        recycleGoods.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = getTagByIndex(goodsBeans
                        .get(layoutManagerGoods.findFirstVisibleItemPosition()).getGood_type());
                if(position != -1)
                    dormitoryLeftMenuAdapter.setCateId(categoryBeans.get(position).getId());
            }
        });
    }

    /**
     * 根据页面显示第一个显示的完全的tag获取tag的position
     * @return
     * @param tag
     */
    private int getTagByIndex(String tag) {
        if(this.tag.equals(tag)){     //提高效率，当前tag已经相同，不在做集合遍历
            return -1 ;
        }
        for (int i = 0 ; i < categoryBeans.size() ; i++){
            if(tag.equals(categoryBeans.get(i).getName())){
                return i;
            }
        }
        return -1 ;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnEvent(DormitoryBean.ShoperBean shoperBean){
        categoryBeans.clear();
        if(shoperBean.getCategory() != null && shoperBean.getCategory().size() > 0){
            statusGoods.showContent();
            categoryBeans.addAll(shoperBean.getCategory());
            cateId = categoryBeans.get(0).getId() ;
            dormitoryLeftMenuAdapter.setCateId(cateId);
            shopId = shoperBean.getSid() ;
            getPresenter().getGoodsForShop(shopId);
        }else{
            statusGoods.showEmptyContent();
        }
    }

    @Override
    public void onItemClick(int position) {
        cateId = categoryBeans.get(position).getId() ;
        dormitoryLeftMenuAdapter.setCateId(cateId);
        tag = categoryBeans.get(position).getName() ;
    }

    /**
     * 获取宿舍小店商品成功
     * @param response
     */
    @Override
    public void getGoodsSuccess(DormitoryGoodsListBean response) {
        goodsBeans.clear();
        for (int i=0 ; i < response.size() ; i ++){
            for (DormitoryGoodsBean.GoodsBean bean : response.get(i).getGoods()) {
                bean.setGood_type(response.get(i).getTitle());
                goodsBeans.add(bean);
            }
        }
        dormitoryGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddToShopCar(View view, int position) {

    }
}
