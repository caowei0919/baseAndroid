package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.wbyxAndroid.adapter.BannerGoodDetailViewHolder;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.GoodsDetailPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.GoodsDetailView;

/**
 * FileName: GoodsDetailActivity
 * Author: 曹伟
 * Date: 2019/11/8 21:35
 * Description:商品详情
 */

public class GoodsDetailActivity extends BaseMvpActivity<GoodsDetailView,GoodsDetailPresenter>
        implements GoodsDetailView {
    @BindView(R.id.tv_title)
    TextView tvTitle ;      //标题
    @BindView(R.id.tv_status)
    TextView tvStatus ;     //商品状态（下架，抢光显示）
    @BindView(R.id.tv_toConnect)
    TextView tvToConnect ;  //收藏、取消收藏
    @BindView(R.id.tv_addToShopCar)
    TextView tvAddToShopCar ;   //加入购物车
    @BindView(R.id.tv_buyNow)
    TextView tvBuyNow ; //  立即购买
    @BindView(R.id.mzbanner_detail)
    MZBannerView mzbannerDetail ;   //轮播图
    @BindView(R.id.tv_index)
    TextView tvIndex ;  //轮播图index
    @BindView(R.id.tv_vipPrice)
    TextView tvVipPrice ;   //vip价格
    @BindView(R.id.tv_commonPrice)
    TextView tvCommonPrice ;    //零售价
    @BindView(R.id.tv_toBecomeVip)
    TextView tvToBecomeVip ;    //立即开通vip
    @BindView(R.id.layout_toBecomeVip)
    RelativeLayout layoutToBecomeVip ;  //开通会员提醒
    @BindView(R.id.tv_goodsType)
    TextView tvGoodsType ;      //商品类型  1推荐  2 超市
    @BindView(R.id.tv_goodsName)
    TextView tvGoodsName ;  //商品名称
    @BindView(R.id.tv_courierPrice)
    TextView tvCourierPrice ;   //快递费
    @BindView(R.id.tv_inventory)
    TextView tvInventory ;  //库存
    @BindView(R.id.tv_comment)
    TextView tvComment ;    //评论数
    @BindView(R.id.tv_checkAll)
    TextView tvCheckAll ;   //查看全部
    @BindView(R.id.tv_noEvaluation)
    TextView tvNoEvaluation ;   //暂无评价
    @BindView(R.id.layout_evaluation)
    RelativeLayout layoutEvaluation ;   //评价
    @BindView(R.id.img_avatar)
    CircleImageView imgAvatar ;         //用户头像
    @BindView(R.id.tv_userName)
    TextView tvUserName ;   //昵称
    @BindView(R.id.tv_evaluationContent)
    TextView tvEvaluationContent ;  //评价详情
    @BindView(R.id.tv_addTime)
    TextView tvAddTime ;    //添加时间
    @BindView(R.id.web_html)
    WebView webHtml ;


    private String goods_type = "" ;    //商品类型 1 推荐  2 当日达
    private String goods_id = "" ;  //商品id
    private String warehouse_id = "" ;  //仓库id 窝边超市需要
    private String inlet = "" ; //入口   group: 拼团 common:普通商品 limit :限时购
    private Map<String,String> map = new HashMap<>();
    private List<GoodDetailBean.ImgBean> imgBeans = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        tvTitle.setText(mContext.getResources().getString(R.string.goods_detail));
        layoutToBecomeVip.setVisibility(Constant.isVipUser() ? View.GONE : View.VISIBLE);
        WebSettings webSettings = webHtml.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webHtml.getSettings().setUseWideViewPort(true);
        webHtml.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        if(null != getIntent()){
            goods_type = TextUtils.isEmpty(getIntent().getStringExtra("goods_type"))
                    ? "" : getIntent().getStringExtra("goods_type") ;
            goods_id = TextUtils.isEmpty(getIntent().getStringExtra("goods_id") )
                    ? "" : getIntent().getStringExtra("goods_id") ;
            warehouse_id = TextUtils.isEmpty(getIntent().getStringExtra("warehouse_id") )
                    ? "" : getIntent().getStringExtra("warehouse_id") ;
            inlet = TextUtils.isEmpty(getIntent().getStringExtra("inlet") )
                    ? "common" : getIntent().getStringExtra("inlet") ;
            map.put("goods_type",goods_type);
            map.put("goods_id",goods_id);
            map.put("warehouse_id",warehouse_id);
            map.put("inlet",inlet);
            getPresenter().getGoodsDetail(map);
        }
    }

    @OnClick({R.id.linear_back,R.id.tv_toHome,R.id.tv_toShopCar,R.id.tv_toConnect
            ,R.id.tv_toBecomeVip})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.linear_back :     //返回
                    finish();
                break;

            case R.id.tv_toHome :   //首页

                break;

            case R.id.tv_toShopCar :    //购物车

                break;

            case R.id.tv_toConnect :    //收藏、取消收藏
                    getPresenter().setCollect(goods_id,goods_type,tvToConnect.isSelected()
                            ? "del" : "add",warehouse_id);
                break;

            case R.id.tv_toBecomeVip :  //开通会员

                break;
        }
    }

    @Override
    public void getGoodDetailSucccess(GoodDetailBean baseBean) {
        tvToConnect.setSelected(baseBean.getIs_collect().equals("1"));
        //库存为0
        if(baseBean.getDetails().getStore_nums().equals("0")){
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText(mContext.getResources()
                    .getString(R.string.the_goods_have_been_sold_out));
            tvAddToShopCar.setEnabled(false);
            tvBuyNow.setEnabled(false);
        }
        if(baseBean.getDetails().getStatus().equals("2")){  //下架
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText(mContext.getResources()
                    .getString(R.string.the_goods_have_been_taken_off_the_shelves));
            tvAddToShopCar.setEnabled(false);
            tvBuyNow.setEnabled(false);
        }
        imgBeans.clear();
        imgBeans.addAll(baseBean.getImg());
        tvIndex.setText("1/" + imgBeans.size());
        mzbannerDetail.setPages(imgBeans, new MZHolderCreator<BannerGoodDetailViewHolder>() {
            @Override
            public BannerGoodDetailViewHolder createViewHolder() {
                return new BannerGoodDetailViewHolder(mContext);
            }
        });
        mzbannerDetail.addPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvIndex.setText((position + 1) + "/" + imgBeans.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tvVipPrice.setText(mContext.getResources().getString(R.string.rmb)
                + baseBean.getDetails().getVip_price());
        tvCommonPrice.setText(mContext.getResources().getString(R.string.rmb)
                + baseBean.getDetails().getSell_price());
        if(baseBean.getDetails().getGoods_type().equals("1")){  //窝边推荐
            tvGoodsType.setText(mContext.getResources().getString(R.string.nest_side_recommended));
            tvCourierPrice.setText(mContext.getResources()
                    .getString(R.string.express_free_shipping));
        }else{      //窝边超市
            tvGoodsType.setText(mContext.getResources().getString(R.string.around_the_supermarket));
            tvCourierPrice.setText(mContext.getResources()
                    .getString(R.string.notice_city));
        }
        tvGoodsName.setText(baseBean.getDetails().getName());
        tvInventory.setText(mContext.getResources().getString(R.string.inventory)
                + baseBean.getDetails().getStore_nums());
        tvComment.setText(mContext.getResources().getString(R.string.evaluation_for_good)
                + "(" + baseBean.getDetails().getComments() + ")");
        if(baseBean.getDetails().getComments().equals("0")){    //0无评价   非0表示有评价
            tvCheckAll.setVisibility(View.GONE);
            tvNoEvaluation.setVisibility(View.VISIBLE);
            layoutEvaluation.setVisibility(View.GONE);
        }else{
            tvNoEvaluation.setVisibility(View.GONE);
            tvCheckAll.setVisibility(View.VISIBLE);
            layoutEvaluation.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(baseBean.getComment().getAvatar())
                    .error(R.mipmap.img_default)
                    .placeholder(R.mipmap.img_default)
                    .dontAnimate()
                    .into(imgAvatar);
            tvUserName.setText(baseBean.getComment().getNickname());
            tvEvaluationContent.setText(baseBean.getComment().getEvaluate());
            tvAddTime.setText(baseBean.getComment().getAddtime());
        }

        String content =baseBean.getDetails().getDetails();
        content = content.replace("<img"
                , "<img style=\"display:        ;width:100%;\"");
        webHtml.loadDataWithBaseURL(null, content
                , "text/html", "utf-8", null);
    }

    /**
     * 收藏或者取消收藏成功
     */
    @Override
    public void setCollectSuccess() {
        tvToConnect.setSelected(!tvToConnect.isSelected());
    }
}
