package com.cloudtimes.serving.cash.service;

import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.product.domain.CtShopProduct;

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
}
