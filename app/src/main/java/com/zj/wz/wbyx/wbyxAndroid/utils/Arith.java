package com.zj.wz.wbyx.wbyxAndroid.utils;

import java.math.BigDecimal;

/**
 * FileName: Arith
 * Author: 曹伟
 * Date: 2019/10/25 16:17
 * Description:BigDecimal类和以BigDecimal类为基础定义类Arith工具类
 */

public class Arith {
    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;
    //构造器私有，让这个类不能实例化
    private Arith(){}
    //提供精确的加法运算
    public static BigDecimal add(BigDecimal v1, BigDecimal v2){
        BigDecimal b1 = v1;
        BigDecimal b2 = v2;
        return b1.add(b2);
    }
    //提供精确的减法运算
    public static double sub(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }
    //提供精确的乘法运算
    public static double mul(double v1, BigDecimal v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = v2;
        return b1.multiply(b2).doubleValue();
    }
    //提供精确的除法运算
    public static double div(double v1,double v2){
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2).doubleValue();
    }
}
