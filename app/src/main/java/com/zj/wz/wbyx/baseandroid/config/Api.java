package com.zj.wz.wbyx.baseandroid.config;

public class Api {

    public static final String CODE_NEED_BIND = "401";      //微信授权登录需要绑定手机号
    public static boolean isDevelop = false;
    public static final String CODE_SUCCESS = "200" ;     //请求成功
    public static final String CODE_TOKEN_FAILURE = "300" ;   //token失效 code 300,301
    public static final String CODE_FAILED_TOAST = "100" ;    //请求错误，弹出吐司
    public static final String CODE_HAS_NO_DORMITOR = "601" ;   //无宿舍地址
    public static final String CODE_HAS_NO_SHOP = "602" ;   //无宿舍小店


    /**
     * 接口地址
     */
    public static String PHP_BASE_URL = "";
    //测试
    public static final String BASE_URL_TEST = "http://testapp.wobianmall.com";
    //正式
    public static final String BASE_URL_PRODUCT = "http://app.wobianmall.com";


    /**
     * environment切换环境：
     * 1：测试环境；
     * 2：预发布环境；
     * 3: 线上环境;
     */
    public enum AppEnviron {
        DEV_TEST_ENV(1),
        REL_PRE_ENV(2);

        private int value;

        AppEnviron(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static AppEnviron of(int intValue) {

            switch (intValue) {

                case 1:
                    return DEV_TEST_ENV;

                default:
                    return REL_PRE_ENV;
            }
        }
    }

    public static void setEnviroment(int env) {

        switch (AppEnviron.of(env)) {

            case DEV_TEST_ENV:
                isDevelop = true;
                PHP_BASE_URL = BASE_URL_TEST;
                break;

            case REL_PRE_ENV:
                isDevelop = false;
                PHP_BASE_URL = BASE_URL_PRODUCT;
                break;

            default:
                break;
        }
    }

    public static class HEADER {

        //token
        public static final String HEADER_TOKEN = "token";
        //数字签名
        public static final String HEADER_SIGN = "sign";
        /**
         * 设备类型
         */
        public static final String HEADER_OS = "device";
        public static final String VALUE_OS = "android";

        /**
         * app版本
         */
        public static final String HEADER_VERSION = "app";
        /**
         * 设备唯一编号
         */
        public static final String HEADER_DEVICEID = "deviceid";
        /**
         * 手机系统版本号
         */
        public static final String SYSTEMVERSION = "os";
    }
}
