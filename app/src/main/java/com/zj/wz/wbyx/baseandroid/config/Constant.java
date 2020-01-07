package com.zj.wz.wbyx.baseandroid.config;

import android.content.Context;
import android.support.annotation.Keep;
import com.orhanobut.hawk.Hawk;
import com.zj.wz.wbyx.baseandroid.retrofit.helper.PreferencesHelper;
import com.zj.wz.wbyx.baseandroid.utils.AndroidUtils;
import com.zj.wz.wbyx.wbyxAndroid.Gaode.MyLocation;
import com.zj.wz.wbyx.wbyxAndroid.bean.UserBean;



@Keep
public class Constant {
    public static final int REQUEST_TAKE_PHOTO = 0x00001024 ;       //拍照
    public static final int REQUEST_OPEN_CAMERA = 0x00001023 ;      //相册
    public static final String CODE_GENDER_MALE = "1" ;             //表示男性
    public static final String CODE_GENDER_FEMALE = "0" ;           //表示女性


    public static final String USER_TOKEN = "user_token" ;  //用户token
    public static final String VIP_USER = "vip_user" ;   //是否为vip标识
    public static final String NEW_USER = "new_user" ;  //是否为新用户标识
    public static final String LAST_PHONE = "last_phone" ;  //最近一次登录手机号

    /**
     * 用户基础信息相关
     */
    public static final String USER_PHONE = "user_phone" ;  //用户手机号
    public static final String USER_DORM = "user_dorm"  ;   //宿舍地址
    public static final String USER_VIP = "user_vip" ;     //是否为vip
    public static final String USER_AVATAR = "user_avatar" ;    //用户头像
    public static final String USER_NICKNAME = "user_NICKNAME" ;    //用户昵称
    public static final String USER_EXP = "user_exp" ;
    public static final String USER_REG = "user_reg" ;  //用户注册时间
    public static final String USER_SC = "user_sc" ;
    public static final String USER_IS_LANDLORD = "user_is_landlord" ;
    public static final String USER_ECONOMIZE_PRICE = "user_economize_price" ;  //累计节约多少钱
    public static final String USER_REG_DAY = "user_reg_day" ;
    public static final String USER_IS_GUEST = "user_is_guest" ;    //是否为游客
    public static final String USER_HAS_LOGIN = "user_has_login" ;  //用户是否处于登录状态
    public static final String USER_LOCATION = "user_location" ;    //位置信息
    /**
     * 常见的日期格式
     */
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd" ;
    public static final String DATE_YYYYMMDD = "yyyyMMdd" ;
    public static final String DATE_MMDD = "MM-dd" ;
    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss" ;

    /**
     * 倒计时时间格式
     */
    public static final String TEXT_FORMAT_COUNT_DOWN = "%dS";
    public static final long COUNT_DOWN_TIME = 60 ; //倒计时时长
    public static final int PERMISSIONS_CODE = 0x1001;  //请求权限code
    public static int INSTALL_APK = 0x00008;    //安装apk

    /**
     * 1:服务条款
     * 2：常见问题
     */
    public static int TYPE_SERVICE = 1;
    public static int TYPE_WRITE = 2;

    /**
     * 首页viewpage展示商品类型
     * 0 全部
     * 1 窝边超市
     * 2 窝边推荐
     */
    public static String HOME_GOODS_ALL = "0" ;
    public static String HOME_GOODS_MARKET = "1" ;
    public static String HOME_GOODS_RECOMMEND = "2" ;

    /**
     * 商品类型
     */
    public final static String TYPE_GOODS_MARKET = "2" ;
    public final static String TYPE_GOODS_RECOMMEND = "1" ;

    /**
     * 广告图
     */
    public static int TYPE_SHARE = 2 ;      //分享有礼
    public static int TYPE_MARKET = 6 ;     //窝边超市
    public static int TYPE_RECOMMEND = 5 ;      //推荐超市

    public static int PAGE_NUM = 10 ;   //分页每页查询数量

    /**
     * 订单查询状态
     */
    public static int ALL_ORDER = 0 ;   //全部订单
    public static int SEND_ORDER = 2;    //待发货订单
    public static int RECEIVE_ORDER = 3;    //待收货订单
    public static int PAYMENT_ORDER = 4 ;   //待付款订单
    public static int EVALUATE_ORDER = 6 ;  //待评价订单
    public static int AFTER_SALES_ORDER = 7 ;  //售后订单

    /**
     * 首页相关请求参数
     * 启动页boot,轮播图banners,分类栏categories,模块区recom
     * ,限时抢购flash,超值拼团groupon,商品专题subjects,最新推荐new
     */
    public static String HOME_BANNER = "banners" ;
    public static String HOME_CATEGORIES = "categories" ;
    public static String HOME_RECOM = "recom" ;
    public static String HOME_FLASH = "flash" ;
    public static String HOME_GROUPON = "groupon" ;
    public static String HOME_SUBJECTS = "subjects" ;
    public static String HOME_NEW = "new" ;

    public static String APP_ID = "wx86a5e39484bec745" ;


    public static Context getContext() {
        return AndroidUtils.getContext();
    }

    /**
     * 保存用户的token
     * @param token
     */
    public static void savaToken (String token){
        PreferencesHelper.put(USER_TOKEN,token);
    }

    /**
     * 获取用户的token
     * @return
     */
    public static String getUserToken(){
        return PreferencesHelper.get(USER_TOKEN,"");
    }

    /**
     * 保存用户是否为vip状态
     * @param isVip
     */
    public static void setVipUser(boolean isVip){
        PreferencesHelper.put(VIP_USER,isVip);
    }

    /**
     * 返回用户是否为vip
     * @return
     */
    public static boolean isVipUser(){
        return PreferencesHelper.get(VIP_USER,false);
    }

    /**
     * 保存用户是否为新用户状态
     * @param isNew
     */
    public static void setNewUser(boolean isNew){
        PreferencesHelper.put(NEW_USER,isNew);
    }

    public static boolean isLandlordUser(){
        return PreferencesHelper.get(USER_IS_LANDLORD,false);
    }

    /**
     * 返回用户是否为新用户
     * @return
     */
    public static boolean isNewUser(){
        return PreferencesHelper.get(NEW_USER,false);
    }

    /**
     * 保存最近一次登录手机号
     * @param phone
     */
    public static void setLastPhone(String phone){
        PreferencesHelper.put(LAST_PHONE,phone);
    }

    /**
     * 获取最近一次登录手机号
     * @return
     */
    public static String getLastPhone(){
        return PreferencesHelper.get(LAST_PHONE,"");
    }

    /**
     * 用户是否处于登录状态
     */
    public static boolean hasLogin(){
        return PreferencesHelper.get(USER_HAS_LOGIN,false);
    }

    public static void setLoginStatus(boolean hasLogin){
        PreferencesHelper.put(USER_HAS_LOGIN,hasLogin);
    }

    /**
     * 获取用户基础信息
     * @return
     */
    public static UserBean getUser(){
        UserBean user = new UserBean();
        user.setAvatar(PreferencesHelper.get(USER_AVATAR,""));
        user.setPhone(PreferencesHelper.get(USER_PHONE,""));
        user.setDorm(PreferencesHelper.get(USER_DORM,""));
        user.setEconomize_price(PreferencesHelper.get(USER_ECONOMIZE_PRICE,""));
        user.setExp_time(PreferencesHelper.get(USER_EXP,""));
        user.setIs_guest(PreferencesHelper.get(USER_IS_GUEST,""));
        user.setReg_time(PreferencesHelper.get(USER_REG,""));
        user.setReg_day(PreferencesHelper.get(USER_REG_DAY,0));
        user.setIs_vip(PreferencesHelper.get(USER_VIP,0));
        user.setNickname(PreferencesHelper.get(USER_NICKNAME,""));
        user.setSc(PreferencesHelper.get(USER_SC,0));
        user.setIs_landlord(PreferencesHelper.get(USER_IS_LANDLORD,0));
        return user ;
    }

    public static void saveUser(UserBean userBean){
        PreferencesHelper.put(USER_PHONE,userBean.getPhone());
        PreferencesHelper.put(USER_AVATAR,userBean.getAvatar());
        PreferencesHelper.put(USER_DORM,userBean.getDorm());
        PreferencesHelper.put(USER_ECONOMIZE_PRICE,userBean.getEconomize_price());
        PreferencesHelper.put(USER_EXP,userBean.getExp_time());
        PreferencesHelper.put(USER_IS_GUEST,userBean.getIs_guest());
        PreferencesHelper.put(USER_REG,userBean.getReg_time());
        PreferencesHelper.put(USER_REG_DAY,userBean.getReg_day());
        PreferencesHelper.put(USER_VIP,userBean.getIs_vip());
        PreferencesHelper.put(USER_NICKNAME,userBean.getNickname());
        PreferencesHelper.put(USER_SC,userBean.getSc());
        PreferencesHelper.put(USER_IS_LANDLORD,userBean.getIs_landlord());
    }

    /**
     * 登出
     */
    public static void louginOut() {
        saveUser(new UserBean());
        setLoginStatus(false);
        savaToken("");

    }

    /**
     * 保存定位信息
     * @param myLocation
     */
    public static void setMyLocation(MyLocation myLocation) {
        Hawk.put(USER_LOCATION,myLocation);
    }

    /**
     * 获取位置信息
     * @return
     */
    public static MyLocation getMyLocation() {
        return Hawk.get(USER_LOCATION, new MyLocation());
    }
}