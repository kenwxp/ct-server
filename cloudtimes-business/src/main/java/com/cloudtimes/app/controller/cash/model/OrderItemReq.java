package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "AddOrderItemResp", description = "订单商品新增接口")
@Data
@Slf4j
public class OrderItemReq {
    private String isDuty;     // 是否值守 0-否 1-是
    private String orderId; // 	订单序列号（首次调用为空）
    private String goodId;     // 	商品id
    private String goodName;   // 	商品名
    private String categoryId;     // 	商品类别id
    private String categoryName;   // 	商品类别名
    private int num;        // 	数量
    private int buyPrice;   // 	单价 单位：分/件
    private int sellPrice;  // 	单价 单位：分/件
}
