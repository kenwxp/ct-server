package com.cloudtimes.business.mobile.service;

import com.cloudtimes.business.mobile.domain.*;

import java.util.List;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
public interface ICtShopBossBusinessService {
    /**
     * 修改密码
     *
     * @param userId 用户id
     * @param param  请求参数
     * @return
     */
    public void changePassword(String userId, ChangePasswordReq param);

    /**
     * 申请或取消与值守方法
     *
     * @param userId
     * @param param
     * @return
     */
    public void applySupervise(String userId, ApplySuperviseReq param);

    /**
     * 查询门店列表
     *
     * @param userId
     * @param param
     * @return
     */
    public List<GetShopListResp> getShopList(String userId, GetShopListReq param);

    /**
     * 查询门店详情
     *
     * @param userId
     * @param param
     * @return
     */
    public GetShopDetailResp getShopDetail(String userId, GetShopDetailReq param);

    /**
     * 查询门店访问人数排行
     *
     * @return
     */
    public List<GetShopRankResp> getShopRank(String userId);

    /**
     * 店家开门
     *
     * @param userId
     * @param param
     */
    public void getShopOpenDoor(String userId, GetShopDetailReq param);

    /**
     * 查询门店设备列表
     *
     * @param userId
     * @param param
     * @return
     */
    public List<GetDeviceListResp> getDeviceList(String userId, GetDeviceListReq param);

    /**
     * 查询门店值守日志列表
     *
     * @param userId
     * @param param
     * @return
     */
    public List<GetSuperviseListResp> getSuperviseList(String userId, GetSuperviseListReq param);

    /**
     * 门店访问量汇总统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    public ShopVisitSumResp getStatVisitSum(String userId, ShopStatSumReq param);

    /**
     * 门店访问量图表统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    public List<ShopStatChartResp> getStatVisitChart(String userId, ShopStatChartReq param);

    /**
     * 门店营收汇总统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    public ShopIncomeSumResp getStatIncomeSum(String userId, ShopStatSumReq param);

    /**
     * 门店营收图表统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    public List<ShopStatChartResp> getStatIncomeChart(String userId, ShopStatChartReq param);

    /**
     * 门店24小时访问量及销售额统计
     *
     * @param userId
     * @param param
     * @return
     */
    public ShopStat24hSumResp getStatIncomeSum(String userId, GetShopDetailReq param);

    /**
     * 查询订单列表
     *
     * @param userId
     * @param param
     * @return
     */
    public List<GetOrderListResp> getOrderList(String userId, GetOrderListReq param);

    /**
     * 查询订单详情
     *
     * @param userId
     * @param param
     * @return
     */
    public GetOrderDetailResp getOrderDetail(String userId, GetOrderDetailReq param);


    /**
     * 获取订单本地回放
     *
     * @param userId
     * @param param
     * @return
     */
    public GetOrderLocalVideoResp getOrderLocalVideo(String userId, GetOrderLocalVideoReq param);

    /**
     * 更新商品
     *
     * @param userId
     * @param param
     */
    public void syncProduct(String userId, SyncProductReq param);
}
