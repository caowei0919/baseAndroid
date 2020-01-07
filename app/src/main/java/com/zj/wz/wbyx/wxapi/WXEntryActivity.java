package com.zj.wz.wbyx.wxapi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.event.WxLoginEvent;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity  extends Activity
        implements IWXAPIEventHandler{

    private IWXAPI api ;
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                PLog.e("BaseResp.ErrCode.ERR_OK" +  ",code ==== " + ((SendAuth.Resp) baseResp).code);
                EventBus.getDefault().post(new WxLoginEvent((SendAuth.Resp) baseResp));
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                PLog.e("BaseResp.ErrCode.ERR_USER_CANCEL");
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                PLog.e("BaseResp.ErrCode.ERR_AUTH_DENIED");
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                PLog.e("BaseResp.ErrCode.ERR_UNSUPPORT");
                break;
        }
        finish();

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constant.APP_ID,true);
        api.registerApp(Constant.APP_ID);
        try {
            boolean result =  api.handleIntent(getIntent(), this);
            if(!result){
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data,this);
    }
}
