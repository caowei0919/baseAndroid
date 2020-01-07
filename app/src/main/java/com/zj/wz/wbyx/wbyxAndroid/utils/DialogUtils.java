package com.zj.wz.wbyx.wbyxAndroid.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zj.wz.wbyx.R;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.event.UpdateVersionEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * FileName: DialogUtils
 * Author: 曹伟
 * Date: 2019/10/11 9:51
 * Description:弹出框工具
 */

public class DialogUtils {

    /**
     * 版本更新弹窗
     */
    public static void showVersionDialog(Context mContext) {
        Dialog dialogN= new AlertDialog.Builder(mContext).create();
        dialogN.show();
        dialogN.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.check_version_dialog);
        TextView tvCancel = dialogN.findViewById(R.id.tv_cancel);  //取消
        Button btnUpdate = dialogN.findViewById(R.id.btn_update);   //确定
        //取消操作
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
            }
        });

        //确定操作
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
                EventBus.getDefault().post(new UpdateVersionEvent());
            }
        });
    }

    /**
     * 显示在屏幕中间dialog
     * @param mContext
     */
    public static void showCenterDialog(Context mContext,String title) {
        Dialog dialogN = new AlertDialog.Builder(mContext).create();
        dialogN.show();
        dialogN.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.choose_referees_dialog);
        TextView tvTitle = dialogN.findViewById(R.id.tv_title) ;    //标题
        tvTitle.setText(title);
        tvTitle.setTextColor(mContext.getResources().getColor(R.color.c_000000));
        tvTitle.setTextSize(16);
        TextView tvCancel = dialogN.findViewById(R.id.tv_cancel) ;  //取消
        tvCancel.setText(mContext.getResources().getString(R.string.cancel));
        TextView tvSure = dialogN.findViewById(R.id.tv_sure);   //确定
        tvSure.setText(mContext.getResources().getString(R.string.sure));

        //取消操作
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
            }
        });

        //确定操作
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
            }
        });
    }

    /**
     * 显示在屏幕中间dialog
     * @param mContext
     */
    public static void showCenterDialog(Context mContext,String title,View.OnClickListener listener
            ,View.OnClickListener cancelListener) {
        Dialog dialogN = new AlertDialog.Builder(mContext).create();
        dialogN.show();
        dialogN.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.choose_referees_dialog);
        TextView tvTitle = dialogN.findViewById(R.id.tv_title) ;    //标题
        tvTitle.setText(title);
        tvTitle.setTextColor(mContext.getResources().getColor(R.color.c_000000));
        tvTitle.setTextSize(16);
        TextView tvCancel = dialogN.findViewById(R.id.tv_cancel) ;  //取消
        tvCancel.setText(mContext.getResources().getString(R.string.cancel));
        TextView tvSure = dialogN.findViewById(R.id.tv_sure);   //确定
        tvSure.setText(mContext.getResources().getString(R.string.sure));

        //取消操作
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListener.onClick(v);
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
            }
        });

        //确定操作
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
            }
        });
    }

    /**
     * 显示在屏幕中间dialog
     * @param mContext
     * @param event 消息传递事件
     */
    public static void showCenterDialog(Context mContext,String title,Object event) {
        Dialog dialogN = new AlertDialog.Builder(mContext).create();
        dialogN.show();
        dialogN.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = dialogN.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.choose_referees_dialog);
        TextView tvTitle = dialogN.findViewById(R.id.tv_title) ;    //标题
        tvTitle.setText(title);
        tvTitle.setTextColor(mContext.getResources().getColor(R.color.c_000000));
        tvTitle.setTextSize(16);
        TextView tvCancel = dialogN.findViewById(R.id.tv_cancel) ;  //取消
        tvCancel.setText(mContext.getResources().getString(R.string.cancel));
        TextView tvSure = dialogN.findViewById(R.id.tv_sure);   //确定
        tvSure.setText(mContext.getResources().getString(R.string.sure));

        //取消操作
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
            }
        });

        //确定操作
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogN.isShowing()){
                    dialogN.dismiss();
                }
                EventBus.getDefault().post(event);
            }
        });
    }

    public static void showCleanSuccess(Context mContext) {
        Dialog clearDialog = new AlertDialog.Builder(mContext).create();
        clearDialog.show();
        clearDialog.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = clearDialog.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.clear_cache_dialog);
        TextView tvSure = clearDialog.findViewById(R.id.tv_sure);   //确定

        //确定操作
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clearDialog.isShowing()){
                    clearDialog.dismiss();
                }
            }
        });
    }

    public static void ShouSureDialog(Context mContext, String tvSureText, String title
            , View.OnClickListener listener) {
        Dialog clearDialog = new AlertDialog.Builder(mContext).create();
        clearDialog.show();
        clearDialog.setCanceledOnTouchOutside(false);
        //让dialog显示到屏幕的中间
        Window window = clearDialog.getWindow();
        window.setGravity(Gravity.CENTER);//设置对话框弹出的位置
        window.setDimAmount(0.2f);
        //把要显示的布局加到dialog中
        window.setContentView(R.layout.sure_dialog);
        TextView tvSure = clearDialog.findViewById(R.id.tv_sure);   //确定
        tvSure.setText(tvSureText);
        TextView tvTitle = clearDialog.findViewById(R.id.tv_title) ;    //标题
        tvTitle.setText(title);

        //确定操作
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clearDialog.isShowing()){
                    clearDialog.dismiss();
                }
                listener.onClick(v);
                PLog.e("ShouSureDialog");
            }
        });
    }
}
