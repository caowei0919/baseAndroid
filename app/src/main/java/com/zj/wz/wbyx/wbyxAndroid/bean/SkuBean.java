package com.zj.wz.wbyx.wbyxAndroid.bean;

import android.support.annotation.Keep;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;

/**
 * Created by zan on 16/4/22.
 */
@Keep
public class SkuBean implements IPickerViewData, Serializable {

    public String flowId;
    public String flowName;
    public String flowKey;
    public int drawableRes;
    public boolean isSelect = false; //是否选择
    public String reservedParams;//预留备用参数

    public SkuBean() {
    }

    public SkuBean(String flowId, String flowName) {
        this.flowId = flowId;
        this.flowName = flowName;
    }

    public SkuBean(String flowId, String flowName, int drawableRes) {
        this.flowId = flowId;
        this.flowName = flowName;
        this.drawableRes = drawableRes;
    }

    public SkuBean(String flowId, String flowName, String reservedParams) {
        this.flowId = flowId;
        this.flowName = flowName;
        this.reservedParams = reservedParams;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getReservedParams() {
        return reservedParams;
    }

    public void setReservedParams(String reservedParams) {
        this.reservedParams = reservedParams;
    }

    @Override
    public String toString() {
        return "SkuBean{" +
                "flowId='" + flowId + '\'' +
                ", flowName='" + flowName + '\'' +
                ", flowKey='" + flowKey + '\'' +
                ", drawableRes=" + drawableRes +
                ", isSelect=" + isSelect +
                ", reservedParams='" + reservedParams + '\'' +
                '}';
    }

    /**
     * 实现 IPickerViewData 接口,用来显示在PickerView上面的字符串，
     * PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
     *
     * @return
     */
    @Override
    public String getPickerViewText() {
        return flowName;
    }
}
