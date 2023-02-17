package com.cloudtimes.serving.cash.service;

import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.serving.cash.service.domain.VoiceTokenData;

import java.util.List;
import java.util.Map;

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
     * @param deviceId
     * @param token
     * @return map
     * orderId
     * phone
     */
    public Map<String, String> getOrderId(String deviceId, String token);

    /**
     * 获取商品列表
     *
     * @param deviceId
     * @return
     */
    public List<CtShopProduct> getProductList(String deviceId);

    /**
     * 获取语音token相关信息
     *
     * @param deviceId
     * @return
     */
    public VoiceTokenData getVoiceToken(String deviceId);

    /**
     * 订单添加商品
     *
     * @param orderId
     * @param isSupervise
     * @param goodId
     * @param goodName
     * @param num
     * @param buyPrice
     * @param sellPrice
     */
    public String addOrderItem(String deviceId, String orderId, String isSupervise, String goodId, String goodName, String categoryId, String categoryName, int num, int buyPrice, int sellPrice);

    /**
     * 订单删除商品
     *
     * @param orderId
     * @param goodId
     * @param num
     */
    public void deleteOrderItem(String deviceId, String orderId, String goodId, int num);

    /**
     * 取消订单
     *
     * @param orderId
     */
    public void cancelOrder(String deviceId, String orderId);
}
