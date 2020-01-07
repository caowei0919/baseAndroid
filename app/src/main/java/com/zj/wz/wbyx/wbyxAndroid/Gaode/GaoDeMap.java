package com.zj.wz.wbyx.wbyxAndroid.Gaode;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.zj.wz.wbyx.baseandroid.config.Constant;
import com.zj.wz.wbyx.baseandroid.utils.PLog;
import com.zj.wz.wbyx.wbyxAndroid.event.LocationResultEvent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class GaoDeMap {

    public static GaoDeMap instance = null;

    public static GaoDeMap getInstance() {
        if (instance == null) {
            synchronized (GaoDeMap.class) {
                if (instance == null) {
                    instance = new GaoDeMap();
                }
            }
        }
        return instance;
    }

    public void init(final Application application) {

        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

            private int mCount = 0;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mCount++;
                if (mCount == 1) {
                    //进入前台
                    GaoDeMap.getInstance().stopMap();
                    GaoDeMap.getInstance().destroyMap();
                    GaoDeMap.getInstance().initMap(application);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mCount--;
                if (mCount == 0) {
                    //进入后台
                    //后台也不停止定位
                    //GaoDeMap.getInstance().stopMap();
                    //GaoDeMap.getInstance().destroyMap();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    public void initMap(Context context) {
        // 初始化定位
        mLocationClient = new AMapLocationClient(context);
        // 设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        // 初始化AMapLocationClientOption对象
        // 高德定位服务包含GPS和网络定位（Wi-Fi和基站定位）两种能力
        mLocationOption = new AMapLocationClientOption();
        // 设置定位模式:
        // AMapLocationMode.Hight_Accuracy，高精度模式:会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息
        // AMapLocationMode.Battery_Saving，低功耗模式:不会使用GPS和其他传感器，只会使用网络定位（Wi-Fi和基站定位）
        // AMapLocationMode.Device_Sensors，仅设备模式:不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位
        mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);

        // 获取一次定位结果：
        // 该方法默认为false表示使用默认的连续定位策略。
        mLocationOption.setOnceLocation(true);

        // 获取最近3s内精度最高的一次定位结果：
        // 设置setOnceLocationLatest(boolean b)
        // 接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        // false表示使用默认的连续定位策略
        mLocationOption.setOnceLocationLatest(true);

        // 设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);

        // 设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        // 设置是否强制刷新WIFI，默认为true，强制刷新。
        // mLocationOption.setWifiActiveScan(false);

        // 设置是否允许模拟位置,默认为false，不允许模拟位置
        // mLocationOption.setMockEnable(false);

        // 设置定位请求超时时间,单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);

        // 设置是否开启定位缓存机制,缓存机制默认开启。可以关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);

        // 启动定位,给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

    }

    // 声明定位回调监听器,用于接收异步返回的定位结果
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                // 当定位错误码类型为0时定位成功
                if (aMapLocation.getErrorCode() == 0) {
                    getMapLocation(aMapLocation);
                    // 发送定位成功提示
                    EventBus.getDefault().post(new LocationResultEvent(true));
                } else {
                    // ResetLoc
                    Constant.setMyLocation(new MyLocation());
                    EventBus.getDefault().post(new LocationResultEvent(false));
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因。
                    PLog.e("location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation
                            .getErrorInfo());
                }
            }
        }
    };

    public void getMapLocation(AMapLocation amapLocation) {
        MyLocation myLocation = new MyLocation();
        myLocation.setLatitude(amapLocation.getLatitude());
        myLocation.setLongitude(amapLocation.getLongitude());
        myLocation.setAddress(amapLocation.getAddress());
        myLocation.setCountryName(amapLocation.getCountry());
        myLocation.setProvinceName(amapLocation.getProvince());
        String cityName = "";
        if (amapLocation.getDistrict().endsWith("市")) {//先取县级市
            cityName = amapLocation.getDistrict();
        } else {
            if (amapLocation.getCity().endsWith("市")) {
                cityName = amapLocation.getCity();
            } else {
                cityName = amapLocation.getCity();
            }
        }
        myLocation.setCityName(cityName);
        myLocation.setDistrictName(amapLocation.getDistrict());
        myLocation.setStreetName(amapLocation.getStreet());
        myLocation.setCityCode(amapLocation.getCityCode());
        myLocation.setAdCode(amapLocation.getAdCode());
        myLocation.setPoiName(amapLocation.getPoiName());
        Constant.setMyLocation(myLocation);

        PLog.e("GaodeMap", "type = " + amapLocation.getLocationType());//获取当前定位结果来源,如网络定位结果.
        PLog.e("GaodeMap", "latitude=" + amapLocation.getLatitude());//获取纬度
        PLog.e("GaodeMap", "longitude=" + amapLocation.getLongitude());//获取经度
        PLog.e("GaodeMap", "accuracy = " + amapLocation.getAccuracy());//获取定位精度 单位:米
        PLog.e("GaodeMap", "" + amapLocation.getAddress());//地址，网络定位结果中会有地址信息，GPS定位不返回地址信息
        PLog.e("GaodeMap", "" + amapLocation.getCountry());//国家信息
        PLog.e("GaodeMap", "" + amapLocation.getProvince());//省信息
        PLog.e("GaodeMap", "" + amapLocation.getCity());//城市信息
        PLog.e("GaodeMap", "" + amapLocation.getDistrict());//城区信息
        PLog.e("GaodeMap", "" + amapLocation.getStreet());//街道信息
        PLog.e("GaodeMap", "" + amapLocation.getStreetNum());//街道门牌号信息
        PLog.e("GaodeMap", "" + amapLocation.getCityCode());//城市编码
        PLog.e("GaodeMap", "" + amapLocation.getAdCode());//地区编码
        PLog.e("GaodeMap", "" + amapLocation.getPoiName());//获取当前定位点的POI信息

        //获取定位时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date(amapLocation.getTime());
        PLog.e("GaodeMap", "" + df.format(date));
    }

    public interface LocationResult {
        void onLocationResult(String addressName);
    }

    public void startMap() {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stopLocation();
        }

        if (mLocationClient != null) {
            // ResetLoc
            Constant.setMyLocation(new MyLocation());
            mLocationClient.startLocation();//开始定位后
        }
    }

    public void stopMap() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        }
    }

    public void destroyMap() {
        if (mLocationClient != null) {
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    }

}
