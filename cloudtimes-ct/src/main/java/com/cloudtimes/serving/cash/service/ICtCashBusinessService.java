package com.cloudtimes.serving.cash.service;

import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.serving.cash.service.domain.*;

import java.util.List;

public interface ICtCashBusinessService {
    /**
     * 获取刷脸凭证
     *
     * @param deviceId 设备id
     * @param rawdata  rawdata
     * @param authType 凭证类型 1-微信 2-收钱吧
     * @return
     */
    public AuthInfoData getFaceAuthInfo(String deviceId, String rawdata, String authType);

    /**
     * 获取单号和顾客信息
     *
     * @param info
     * @return map
     * orderId
     * phone
     */
    public GetOrderIdResp getOrderId(String deviceId, GetOrderIdReq info);

    /**
     * 获取商品列表
     *
     * @param deviceId
     * @return
     */
    public List<GetProductListResp> getProductList(String deviceId);

    /**
     * 获取语音token相关信息
     *
     * @param deviceId
     * @return
     */
    public GetVoiceTokenResp getVoiceToken(String deviceId);

    /**
     * 订单添加商品
     */
    public OrderItemResp addOrderItem(String deviceId, OrderItemAddReq info);

    /**
     * 订单删除商品
     */
    public void deleteOrderItem(String deviceId, OrderItemDeleteReq info);

    /**
     * 取消订单
     */
    public void cancelOrder(String deviceId, OrderItemCancelReq info);

    /**
     * 支付订单
     *
     */
    public String payOrder(String deviceId, OrderPayReq info);

    /**
     * 查询订单状态
     * 0-超时未确认 1-确认成功 2-确认失败 3-订单异常
     *
     * @param orderId
     */
    public int payOrderStatus(String deviceId, String orderId);

    public String genDynamicQrCodeUrl(String deviceId, String storeNo);

}
