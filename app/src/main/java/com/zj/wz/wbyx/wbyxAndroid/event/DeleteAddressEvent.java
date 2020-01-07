package com.zj.wz.wbyx.wbyxAndroid.event;

import android.support.annotation.Keep;

/**
 * FileName: AddressEvent
 * Author: 曹伟
 * Date: 2019/10/21 13:51
 * Description:地址信息删除
 */
@Keep
public class DeleteAddressEvent {
    private String name ;   //宿舍名称
    private String id ; //宿舍id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeleteAddressEvent(String name, String id) {
        this.name = name ;
        this.id = id ;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
