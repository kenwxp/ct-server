package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "返回参数")
@Data
public class GetOrderListResp {
    @ApiModelProperty("订单编号")
    private String orderId;
    @ApiModelProperty("任务编号")
    private String taskId;
    @ApiModelProperty("门店ID")
    private String storeId;
    @ApiModelProperty("门店名称")
    private String storeName;
    @ApiModelProperty("购物编号")
    private String shoppingId;
    @ApiModelProperty("顾客编号")
    private String userId;
    @ApiModelProperty("顾客手机")
    private String userPhone;
    @ApiModelProperty("实付金额")
    private String actualAmount;
    @ApiModelProperty("商品总数")
    private String totalCount;
    @ApiModelProperty("支付方式 0-支付宝 1-微信 2-网银")
    private String paymentMode;
    @ApiModelProperty("支付流水号")
    private String paymentId;
    @ApiModelProperty("订单状态 0-未支付 1-支付中 2-支付完成 3-支付失败 4-已撤销")
    private String state;
    @ApiModelProperty("创建日期 yyyy-mm-dd hh:mm:ss")
    private String createTime;
    @ApiModelProperty("付款时间 yyyy-mm-dd hh:mm:ss")
    private String updateTime;
    // 订单清单物品
    @ApiModelProperty("物品编号")
    private String itemId;
    @ApiModelProperty("物品名称")
    private String itemName;
    @ApiModelProperty("物品类别")
    private String itemTypeId;
    @ApiModelProperty("类别名称")
    private String itemTypeName;
    @ApiModelProperty("物品数量")
    private String itemCount;
    @ApiModelProperty("物品单价")
    private String itemPrice;
    @ApiModelProperty("物品进货价")
    private String itemPrimePrice;
    @ApiModelProperty("小计")
    private String itemSum;
    @ApiModelProperty("毛利率")
    private String profit;
}