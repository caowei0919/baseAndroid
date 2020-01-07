package com.zj.wz.wbyx.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zj.wz.wbyx.baseandroid.config.Constant;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	private final int success = 0;
	private final int failed = -1;
	private final int cancel = -2;

	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, Constant.APP_ID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq baseReq) {

	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) {

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode) {
				case 0:
//					ToastUtil.showToast("支付成功");
////					GBApplication.instance().sendBroadcast(new Intent(Constant.UPDATE_SHOP_CAR_ACTION));
////					GBApplication.instance().sendBroadcast(new Intent(Constant.PAY_SUCCESS).putExtra("payType","微信支付"));
//					EventBus.getDefault().post(new PayResultEvent("1"));
					break;
				case -1:
//					ToastUtil.showToast("支付失败");
//					EventBus.getDefault().post(new PayResultEvent("0"));
////					GBActivity.showMessageToast("支付失败");
////					GBApplication.instance().sendBroadcast(new Intent(Constant.UPDATE_SHOP_CAR_ACTION));
////					GBApplication.instance().sendBroadcast(new Intent(Constant.PAY_FAIL).putExtra("payType","微信支付"));
//					break;
//				case -2:
//					ToastUtil.showToast("取消支付");
//					EventBus.getDefault().post(new PayResultEvent("0"));
////					GBActivity.showMessageToast("取消");
////					GBApplication.instance().sendBroadcast(new Intent(Constant.PAY_FAIL));
//					break;
//				default:
//					EventBus.getDefault().post(new PayResultEvent("0"));
////					GBApplication.instance().sendBroadcast(new Intent(Constant.PAY_FAIL));
//					break;

			}
			finish();
		}
	}

}