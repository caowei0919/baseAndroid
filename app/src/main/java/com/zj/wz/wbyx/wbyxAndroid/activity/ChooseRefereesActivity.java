package com.zj.wz.wbyx.wbyxAndroid.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.mvp.BaseMvpActivity;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.baseandroid.view.RecyclerViewSpacesItemDecoration;
import com.zj.wz.wbyx.baseandroid.view.StatusBarUtil;
import com.zj.wz.wbyx.wbyxAndroid.adapter.RefereesAdapter;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefereesBean;
import com.zj.wz.wbyx.wbyxAndroid.presenter.ChooseRefereesPresenter;
import com.zj.wz.wbyx.wbyxAndroid.view.ChooseRefereesView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: ChooseRefereesActivity
 * Author: 曹伟
 * Date: 2019/10/8 14:11
 * Description:选择推荐人
 */

public class ChooseRefereesActivity extends BaseMvpActivity<ChooseRefereesView, ChooseRefereesPresenter>
        implements ChooseRefereesView , RefereesAdapter.onItemClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle ;  //标题
    @BindView(R.id.linear_back)
    LinearLayout linearBack ;   //返回键
    @BindView(R.id.smart_referees)
    SmartRefreshLayout smartReferees ;
    @BindView(R.id.recycle_referees)
    RecyclerView recycleReferees ;  //推荐人列表

    private RefereesAdapter adapter ;   //推荐人列表适配器
    private List<RefereesBean.MarketerBean> marketerBeans = new ArrayList<>();
    private Dialog dialogN ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_referees;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void setupView() {
        StatusBarUtil.setStatusBarColor(this,mContext
                .getResources().getColor(R.color.c_F8F8F8));
        tvTitle.setText(mContext.getResources().getString(R.string.choose_referees));
        smartReferees.setEnableRefresh(false);
        smartReferees.setEnableLoadMore(false);
        initRecycle();
    }

    /**
     * 初始化recycleView
     */
    private void initRecycle() {
        adapter = new RefereesAdapter(mContext,marketerBeans,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this );
        //设置布局管理器
        recycleReferees.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper. VERTICAL);
        //设置Adapter
        recycleReferees.setAdapter(adapter);
        recycleReferees.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext
                ,LinearLayoutManager.HORIZONTAL
                ,20
                ,mContext.getResources().getColor(R.color.c_F8F8F8)));
        //设置增加或删除条目的动画
        recycleReferees.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        getPresenter().applyBuild();
    }

    @OnClick({R.id.linear_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.linear_back : //返回
                    finish();
                break;
        }
    }

    /**
     * 获取推荐人列表成功
     * @param response
     */
    @Override
    public void applyBuildSuccess(RefereesBean response) {
        marketerBeans.addAll(response.getMarketer());
        adapter.notifyDataSetChanged();
    }

    /**
     * item内按钮点击监听
     * @param bean
     */
    @Override
    public void onItemClick(RefereesBean.MarketerBean bean) {
        PLog.e("成为推荐人点击成功");
        showDialogCenter(bean);
    }

    /**
     * 选择弹框展示
     * @param bean
     */
    private void showDialogCenter(RefereesBean.MarketerBean bean) {
        if(null == dialogN){
            dialogN = new AlertDialog.Builder(mContext).create();
        }
        dialogN.show();
        dialogN.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.choose_referees_dialog);
        TextView tvCancel = dialogN.findViewById(R.id.tv_cancel) ;  //取消
        TextView tvSure = dialogN.findViewById(R.id.tv_sure);   //确定

        //取消操作
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
            }
        });

        //确定操作
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(bean);
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                    dialogN=null;
                }
                finish();
            }
        });
    }
}
