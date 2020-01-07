package com.zj.wz.wbyx.wbyxAndroid.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.view.RatingBar;
import com.zj.wz.wbyx.wbyxAndroid.bean.AppraiseRequestBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName: AppraiseGoodAdapter
 * Author: 曹伟
 * Date: 2019/11/22 10:46
 * Description:
 */

public class AppraiseGoodAdapter extends RecyclerView.Adapter<AppraiseGoodAdapter.ViewHolder> {
    private Context mContext ;
    private List<OrderDetailBean.OrderBean.GoodsBean> goodsBeans = new ArrayList<>();
    private List<AppraiseRequestBean> appraiseRequestBeans = new ArrayList<>();
    private String order_id  ;

    public AppraiseGoodAdapter(Context context
            , List<OrderDetailBean.OrderBean.GoodsBean> goodsBeans,String order_id) {
        this.mContext = context;
        this.goodsBeans = goodsBeans;
        this.order_id = order_id ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appraise_goods, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppraiseRequestBean bean = new AppraiseRequestBean();
        bean.setId(order_id);
        Glide.with(mContext)
                .load(goodsBeans.get(position).getImg())
                .error(R.mipmap.img_default)
                .placeholder(R.mipmap.img_default)
                .dontAnimate()
                .into(holder.imgGoodsPicture);
        holder.tvName.setText(goodsBeans.get(position).getTitle());
        String specs = "" ;
        for (String str:goodsBeans.get(position).getSpecs()) {
            specs += str ;
        }
        holder.tvSku.setText(specs);
        /**
         * 商品评分
         */
        holder.ratingBarGood.setmOnStarChangeListener(new RatingBar.OnStarChangeListener() {
            @Override
            public void OnStarChanged(float selectedNumber, int position) {
                bean.setScore(position + 1 + "");
            }
        });
        /**
         * 评价
         */
        holder.edFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bean.setComment(s.toString().trim());
            }
        });

    }

    @Override
    public int getItemCount() {
        return goodsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_goodsPicture)
        ImageView imgGoodsPicture ; //商品图片
        @BindView(R.id.tv_name)
        TextView tvName ;   //标题
        @BindView(R.id.tv_sku)
        TextView tvSku ;    //规格
        @BindView(R.id.ratingBar_good)
        RatingBar ratingBarGood ;   //商品评分
        @BindView(R.id.ed_feedback)
        EditText edFeedback ;   //评论

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
