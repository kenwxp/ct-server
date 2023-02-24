package com.cloudtimes.app.controller.cash.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class OrderItemReq {
    @ApiModelProperty(value = "是否值守 0-否 1-是", required = true)
    private String isSupervise;
    @ApiModelProperty(value = "订单号 isSupervise 为1 必填")
    private String orderId;
    @ApiModelProperty(value = "商品id")
    private String goodId;
    @ApiModelProperty(value = "商品名")
    private String goodName;
    @ApiModelProperty(value = "商品类别id")
    private String categoryId;
    @ApiModelProperty(value = "商品类别名")
    private String categoryName;
    @ApiModelProperty(value = "数量")
    private int num;
    @ApiModelProperty(value = "进货价 单位：分/件")
    private int buyPrice;
    @ApiModelProperty(value = "售价 单位：分/件 ")
    private int sellPrice;
}
