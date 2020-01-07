package com.zj.wz.wbyx.baseandroid.config;





import com.zj.wz.wbyx.wbyxAndroid.bean.AddShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.AddressBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.AdvListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ApplyBuildBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BaseBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BuildListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.BuyVipBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.CarFootBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ChooseSchool;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.DormitoryGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GoodSortListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.GroupBeans;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeDateBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.HomeGoodsOfAllListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.InviteCheckBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ListVersionBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.LoginBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MarketGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MemberCenterBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.MenuItemListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.NotDormitoryAddress;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderDetailBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.OrderListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefereesBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.RefundDetailsBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchGoodsListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SearchHistoryListBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SelectSchoolBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ServiceBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.ShopCarBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.SubjectBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.UpdataUserList;
import com.zj.wz.wbyx.wbyxAndroid.bean.UserBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.VipProducBean;
import com.zj.wz.wbyx.wbyxAndroid.bean.WareHouseListBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MyApi {

    /**
     * 获取引导页图片
     * @return
     */
    Observable<BaseBean<String>> getSplashImage();

    /**
     * 获取验证码
     * @param phoneNum 手机号
     * @return
     */
    @GET("/sms/send/reglog")
    Observable<BaseBean> sendMsg(@Query("phone") String phoneNum);

    /**
     * 登录or注册
     * @param mPhoneNum
     * @param mMessageCode
     */
    @FormUrlEncoded
    @POST("/login")
    Observable<BaseBean<LoginBean>> loginOrRegister(@Field("phone") String mPhoneNum ,
                                                    @Field("code") String mMessageCode);

    /**
     * 获取用户信息
     * @return
     */
    @GET("/user")
    Observable<BaseBean<UserBean>> getUser();

    /**
     * 获取会员中心信息
     */
    @GET("/member_center")
    Observable<BaseBean<MemberCenterBean>> getMemberCenter();

    /**
     * 获取个人资料用户展示的信息
     */
    @POST("/member_user")
    Observable<BaseBean<UpdataUserList>> getMemberUser();

//    /**
//     * 修改出生日期
//     * @param birthday
//     */
//    @POST("/member_user")
//    Observable<BaseBean<UpdataUserList>> updataBirthday(@Query("birthday") String birthday);
//
//    /**
//     * 修改性别
//     * @param codeGenderMale
//     * @return
//     */
//    @POST("/member_user")
//    Observable<BaseBean<UpdataUserList>> updataGender(@Query("sex") String codeGenderMale);

    /**
     * 修改用户信息
     * @param nick
     * @return
     */
    @POST("/member_user")
    Observable<BaseBean<UpdataUserList>> updataUserInfo(@Query("birthday") String birthday,
                                                        @Query("sex") String codeGenderMale,
                                                        @Query("nickname") String nick);

    /**
     * 查询邀请人
     * @param invitePhone
     * @return
     */
    @POST("/member_user")
    Observable<BaseBean<InviteCheckBean>> checkInvitePhone(@Query("invite_phone") String invitePhone);

    /**
     * 绑定邀请人
     * @param cellphone
     * @return
     */
    @POST("/member_user")
    Observable<BaseBean> BindInvite(@Query("invite_phone") String cellphone ,
                                    @Query("info") String info);

    /**
     * 查询申请状态
     * @param s_id
     * @param name
     * @param phone
     * @param address_detail
     * @return
     */
    @POST("/building_apply")
    Observable<BaseBean<ApplyBuildBean>> applyBuildStatus(@Query("s_id") String s_id,
                                                          @Query("name") String name,
                                                          @Query("phone") String phone,
                                                          @Query("address_detail") String address_detail);

    /**
     * 申请楼长
     * @param s_id
     * @param name
     * @param phone
     * @param address_detail
     * @return
     */
    @POST("/building_apply")
    Observable<BaseBean> applyBuild(@Query("s_id") String s_id,
                                    @Query("phone") String phone,
                                    @Query("name") String name,
                                    @Query("address_detail") String address_detail,
                                    @Query("cellphone") String cellphone);

    /**
     * 获取推荐人列表
     * @return
     */
    @POST("/building_apply")
    Observable<BaseBean<RefereesBean>> applyBuild();

    /**
     * 查询附近学校
     * @param type
     * @param current_city_name
     * @param area_id
     * @param school_id
     * @param page
     * @return
     */
    @POST("/user/chooseSchool")
    Observable<BaseBean<ChooseSchool>> chooseSchool(@Query("type") String type,
                                                    @Query("current_city_name") String current_city_name,
                                                    @Query("area_id") String area_id,
                                                    @Query("school_id") String school_id,
                                                    @Query("page") int page);

    /**
     * 查询附近学校
     * @param type
     * @param current_city_name
     * @param area_id
     * @param school_id
     * @param page
     * @return
     */
    @POST("/user/chooseSchool")
    Observable<BaseBean<BuildListBean>> chooseBuild(@Query("type") String type,
                                                    @Query("current_city_name") String current_city_name,
                                                    @Query("area_id") String area_id,
                                                    @Query("school_id") String school_id,
                                                    @Query("page") int page);

    /**
     * 根据关键搜索学校
     * @param keyWord
     * @param area_id
     * @param page
     * @return
     */
    @POST("/user/selectSchool")
    Observable<BaseBean<SelectSchoolBean>> searchSchool(@Query("keywords") String keyWord,
                                                        @Query("area_id") String area_id,
                                                        @Query("page") int page);

    /**
     * 检查版本
     * @return
     */
    @GET("/version")
    Observable<BaseBean<ListVersionBean>> checkVersion();

    /**
     * 获取服务条款或者常见问题
     * @param typeService  1：服务条款   2：常见问题
     */
    @POST("/severwrite")
    Observable<BaseBean<ServiceBean>> getServerWrite(@Query("type") int typeService);

    /**
     * 获取广告图
     * @param position_id 广告类别
     */
    @GET("/adv")
    Observable<BaseBean<AdvListBean>> getAdv(@Query("position_id") int position_id);

    /**
     * 添加地址
     * @param type 1 宿舍地址 2其他地址
     * @param address_info
     * @return
     */
    @POST("/user/addAddress")
    Observable<BaseBean<DormitoryAddress>> addDormitoryAddress(@Query("type") String type,
                                                               @Query("address_info") String address_info);

    /**
     * 添加地址
     * @param type 1 宿舍地址 2其他地址
     * @param address_info
     * @return
     */
    @POST("/user/addAddress")
    Observable<BaseBean<NotDormitoryAddress>> addNotDormitoryAddress(@Query("type") String type,
                                                                     @Query("address_info") String address_info);

    /**
     * 获取所有地址信息
     * @return
     */
    @GET("/user/address")
    Observable<BaseBean<AddressBean>> getAllAddress();

    /**
     * 修改宿舍地址
     * @param type
     * @param address_info
     * @param address_id
     * @return
     */
    @POST("/user/addAddress")
    Observable<BaseBean> upDateDormitory(@Query("type") String type,
                                         @Query("address_info") String address_info,
                                         @Query("address_id") String address_id);

    /**
     * 修改非宿舍地址
     * @param type
     * @param address_info
     * @param address_id
     * @return
     */
    @POST("/user/addAddress")
    Observable<BaseBean> upDateNotDormitoryAddress(@Query("type") String type,
                                                  @Query("address_info") String address_info,
                                                  @Query("address_id") String address_id);

    /**
     * 获取我的拼团列表
     * @param area_id
     * @return
     */
    @GET("/group_order")
    Observable<BaseBean<GroupBeans>> getGroupOrder(@Query("area_id") String area_id);

    /**
     * 获取订单列表
     * @param status
     * @param page
     * @param num
     * @return
     */
    @GET("/order")
    Observable<BaseBean<OrderListBean>> getOrder(@Query("status") int status,
                                                 @Query("page") int page,
                                                 @Query("num") int num);

    /**
     * 获取购物车角标
     * @param city_id
     * @return
     */
    @GET("/cart_footer")
    Observable<BaseBean<CarFootBean>> getCarFoot(@Query("city_id") String city_id);

    /**
     * 获取购物车列表
     * @param area_id 区域id
     */
    @GET("/cart")
    Observable<BaseBean<ShopCarBean>> getShopCarGoods(@Query("area_id") String area_id);

    /**
     * 批量删除购物车
     * @param carMap
     * @return
     */
    @POST("/delect_cart")
    Observable<BaseBean> deleteAllSelected(@QueryMap Map<String,Object> carMap);

    /**
     *上传用户选中和未选中状态
     * @param carMap
     * @return
     */
    @POST("/checked")
    Observable<BaseBean> putUserSelected(@QueryMap Map<String, Object> carMap);

    /**
     * 获取首页模块信息
     * @param area_id
     * @param modules
     */
    @GET("/index/{area_id}")
    Observable<BaseBean<HomeDateBean>> getHomeDate(@Path("area_id") String area_id,
                                                   @Query("modules") String modules);

    /**
     * 首页获取底部viewpage展示商品
     * @param adCode    区域编码
     * @param goodsType 商品类型
     * @param page  页码
     * @return
     */
    @GET("/home_goods")
    Observable<BaseBean<HomeGoodsOfAllListBean>> getHomeGoods(@Query("area_id") String adCode,
                                                              @Query("goods_type") String goodsType,
                                                              @Query("page") int page,
                                                              @Query("per_page") int pageNum);

    /**
     * 获取顶级分类
     * @param hot
     * @param type
     * @param area_id
     * @return
     */
    @GET("/menu")
    Observable<BaseBean<MenuItemListBean>> getMenu(@Query("hot") String hot,
                                                   @Query("type") String type,
                                                   @Query("area_id") String area_id);

    /**
     * 菜单项下商品分类
     * @param cateId    菜单id
     * @param area_id   区域id
     * @return
     */
    @GET("/goods_sort")
    Observable<BaseBean<GoodSortListBean>> getGoodsSort(@Query("cat_id") String cateId,
                                                        @Query("area_id") String area_id);

    /**
     * 获取商品列表
     * @param areaId  区域id
     * @param id      类型id
     * @param goodsType 商品类型  1  推荐   2 超市
     * @return
     */
    @GET("/goods_list")
    Observable<BaseBean<MarketGoodsListBean>> getGoodList(@Query("area_id") String areaId,
                                                          @Query("cat_id") String id,
                                                          @Query("goods_type") String goodsType);

    /**
     * 获取特殊商品列表
     * @param param
     * @return
     */
    @POST("/special_list")
    Observable<BaseBean<SubjectBean>> getSpecialList(@Body Map<String, String> param);

    /**
     * 获取宿舍小店信息
     * @return
     */
    @GET("/dorm")
    Observable<BaseBean<DormitoryBean>> getDormitor();

    /**
     * 根据店铺id获取宿舍小店商品
     * @param shopId
     */
    @GET("/shop/{shop_id}/goods")
    Observable<BaseBean<DormitoryGoodsListBean>> getGoodsForShop(@Path("shop_id") String shopId);

    /**
     * 获取商品详情
     * @return
     * @param map
     */
    @GET("/details")
    Observable<GoodDetailBean> getGoodsDetail(@QueryMap Map<String, String> map);

    @PUT("/collect")
    Observable<BaseBean> setCollect(@Body RequestBody body);

    /**
     * 获取当前用户可购买Vip套餐列表
     */
    @GET("/vip_product")
    Observable<BaseBean<VipProducBean>> getVipProduct();

    /**
     * 购买会员创建订单
     * @param vip_type  套餐id
     * @param pay_type  支付类型
     * @param ip    ip地址
     * @return
     */
    @FormUrlEncoded
    @POST("/user/buy-vip")
    Observable<BaseBean<BuyVipBean>> setBuyVip(@Field("vip_type") String vip_type,
                                               @Field("pay_type") String pay_type,
                                               @Field("ip") String ip);

    /**
     * 根据订单id获取订单详情
     * @param order_id
     * @return
     */
    @GET("/order/{id}")
    Observable<BaseBean<OrderDetailBean>> getOrderDetail(@Path("id") String order_id);

    @GET("/order/{id}")
    Observable<String> getDetailForReason(@Path("id")  String orderId);

    @POST("/order/refund/{id}")
    Observable<BaseBean> getRefund(@Path("id") String orderId,
                                   @Body RequestBody requestBody);


    /**
     * 店铺评分
     * @param id    订单id
     * @param score 评分
     * @param anonymous 是否匿名  1 匿名  0 不匿名
     * @return
     */
    @FormUrlEncoded
    @POST("/order/comment/shop")
    Observable<BaseBean> submitAppraise(@Field("id") String id,
                                        @Field("score") int score,
                                        @Field("anonymous") int anonymous);

    /**
     * 获取退款详情
     * @param order_id
     */
    @GET("/order/refund/{id}")
    Observable<BaseBean<RefundDetailsBean>> getRefundDetail(@Path("id") String order_id);

    /**
     * 取消退款
     * @param order_id
     * @return
     */
    @GET("/order/refund/{id}/cancel")
    Observable<BaseBean> cancelRefund(@Path("id") String order_id);

    /**
     * 检验微信授权
     * @param code
     * @return
     */
    @GET("/wx-login")
    Observable<BaseBean<LoginBean>> checkWxBind(@Query("wxcode") String code);

    /**
     * 微信授权绑定手机号
     * @param key
     * @param mPhoneNum
     * @param mMessageCode
     * @return
     */
    @FormUrlEncoded
    @POST("/wx-reg")
    Observable<BaseBean<LoginBean>> WxLogin(@Field("key") String key,
                                @Field("phone") String mPhoneNum ,
                                @Field("code") String mMessageCode);


    /**
     * 推荐商品加入购物车
     * @param params
     * @return
     */
    @GET("/recommend_cart")
    Observable<BaseBean<AddShopCarBean>> recommendAddCar(@QueryMap Map<String, Object> params);

    /**
     * 超市加入购物车
     * @param marketparams
     * @return
     */
    @GET("/dayCart")
    Observable<BaseBean<AddShopCarBean>> marketAddCar(@QueryMap Map<String, Object> marketparams);

    @POST("/search_log")
    Observable<BaseBean<SearchHistoryListBean>> getSearchHistory();

    @POST("/search_log")
    Observable<BaseBean> delSearchHistory(@Query("del_log") String del_log);

    /**
     * 搜索商品
     * @param content
     * @param sort_type
     * @param warehouse_id
     * @param direstion
     * @param page
     * @param per_page
     * @return
     */
    @POST("/search_log")
    Observable<BaseBean<SearchGoodsListBean>> searchGoods(@Query("content") String content,
                                                          @Query("sort_type") String sort_type,
                                                          @Query("warehouse_id") String warehouse_id,
                                                          @Query("direstion") String direstion,
                                                          @Query("page") int page,
                                                          @Query("per_page") int per_page);

    /**
     * 获取附近城市仓
     * @return
     */
    @GET("/dayLocation")
    Observable<BaseBean<WareHouseListBean>> getDayLocation(@Query("lng") String lng,
                                                           @Query("lat") String lat,
                                                           @Query("area_code") String area_code);
}

