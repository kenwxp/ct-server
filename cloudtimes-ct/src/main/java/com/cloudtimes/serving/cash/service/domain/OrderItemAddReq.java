package com.cloudtimes.serving.cash.service.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class OrderItemAddReq {
    @NotEmpty
    @ApiModelProperty(value = "是否值守 0-否 1-是", required = true)
    private String isSupervise;
    @ApiModelProperty(value = "订单号 isSupervise 为1 必填")
    private String orderId;
    @NotEmpty
    @ApiModelProperty(value = "商品id")
    private String goodId;
    @NotEmpty
    @ApiModelProperty(value = "商品名")
    private String goodName;
    @NotEmpty
    @ApiModelProperty(value = "商品类别id")
    private String categoryId;
    @NotEmpty
    @ApiModelProperty(value = "商品类别名")
    private String categoryName;
    @NotNull
    @ApiModelProperty(value = "数量")
    private int num;
    @NotNull
    @ApiModelProperty(value = "进货价 单位：分/件")
    private int buyPrice;
    @NotNull
    @ApiModelProperty(value = "售价 单位：分/件 ")
    private int sellPrice;
}
