package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "请求参数")
@Data
@Slf4j
public class OrderItemAddReq {
    @NotEmpty
    @Schema(description = "是否值守 0-否 1-是", required = true)
    private String isSupervise;
    @Schema(description = "订单号 isSupervise 为1 必填")
    private String orderId;
    @NotEmpty
    @Schema(description = "商品id")
    private String goodId;
    @NotEmpty
    @Schema(description = "商品名")
    private String goodName;
    @NotEmpty
    @Schema(description = "商品类别id")
    private String categoryId;
    @NotEmpty
    @Schema(description = "商品类别名")
    private String categoryName;
    @NotNull
    @Schema(description = "数量")
    private int num;
    @NotNull
    @Schema(description = "进货价 单位：分/件")
    private int buyPrice;
    @NotNull
    @Schema(description = "售价 单位：分/件 ")
    private int sellPrice;
}
