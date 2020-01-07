package com.zj.wz.wbyx.wbyxAndroid.bean;
import android.support.annotation.Keep;


import java.util.List;

/**
 * FileName: AddressBean
 * Author: 曹伟
 * Date: 2019/10/18 15:00
 * Description:所有地址
 */
@Keep
public class AddressBean {

    private List<DormitoryAddress> shop_address;
    private List<NotDormitoryAddress> else_address;

    public List<DormitoryAddress> getShop_address() {
        return shop_address;
    }

    public void setShop_address(List<DormitoryAddress> shop_address) {
        this.shop_address = shop_address;
    }

    public List<NotDormitoryAddress> getElse_address() {
        return else_address;
    }

    public void setElse_address(List<NotDormitoryAddress> else_address) {
        this.else_address = else_address;
    }
}
