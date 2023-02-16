package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "OrderPayReq", description = "订单支付请求体")
@Data
@Slf4j
public class OrderPayReq {
    private String isDuty;        // 是否云值守 0-否 1-是
    private int payType;        // 支付方式 1-扫码支付 2-刷脸支付
    private String payCode;     // 支付码（扫码支付时，为动态码，刷脸支付时为face_code）
    private String orderId;    // 订单序列号（首次调用为空）
    private int totalAmount;   // 总计预付金额，单位分
    private int totalNum;      // 物件总数
}
