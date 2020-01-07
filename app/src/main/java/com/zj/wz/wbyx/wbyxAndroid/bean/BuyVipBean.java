package com.zj.wz.wbyx.wbyxAndroid.bean;
import com.google.gson.annotations.SerializedName;

/**
 * FileName: BuyVipBean
 * Author: 曹伟
 * Date: 2019/11/20 11:55
 * Description:
 */

public class BuyVipBean {

    /**
     * appid : wx86a5e39484bec745
     * partnerid : 1526021671
     * prepayid : wx201151426398615558b3a7721159280800
     * timestamp : 1574221902
     * noncestr : 201911201151421f914ad0a5e633a9
     * package : Sign=WXPay
     * sign : BAD064931BEF13C1E6FADBE7EF7C541D
     * packagev : Sign=WXPay
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    private String timestamp;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String sign;
    private String packagev;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPackagev() {
        return packagev;
    }

    public void setPackagev(String packagev) {
        this.packagev = packagev;
    }

    //支付宝
    private String query ;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
