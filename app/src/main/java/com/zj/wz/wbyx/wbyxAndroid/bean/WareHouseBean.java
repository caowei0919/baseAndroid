package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

/**
 * FileName: WareHouseBean
 * Author: 曹伟
 * Date: 2019/11/29 14:31
 * Description:城市仓信息
 */
@Keep
public class WareHouseBean {

    /**
     * warehouse_id : 6
     * warehouse_name : 测试仓
     * address : 310000,310100
     */

    private String warehouse_id;
    private String warehouse_name;
    private String address;

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
