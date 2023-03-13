package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;


@Schema(description = "返回参数")
@Data
public class GetOrderDetailResp {
    @Schema(description = "订单编号")
    private String orderId;
    @Schema(description = "任务编号")
    private String taskId;
    @Schema(description = "门店ID")
    private String storeId;
    @Schema(description = "门店名称")
    private String storeName;
    @Schema(description = "购物编号")
    private String shoppingId;
    @Schema(description = "顾客编号")
    private String userId;
    @Schema(description = "顾客手机")
    private String userPhone;
    @Schema(description = "实付金额")
    private String actualAmount;
    @Schema(description = "商品总数")
    private String totalCount;
    @Schema(description = "支付方式 0-支付宝 1-微信 2-网银")
    private String paymentMode;
    @Schema(description = "支付流水号")
    private String paymentId;
    @Schema(description = "订单状态 0-未支付 1-支付中 2-支付完成 3-支付失败 4-已撤销")
    private String state;
    @Schema(description = "创建日期 yyyy-mm-dd hh:mm:ss")
    private String createTime;
    @Schema(description = "付款时间 yyyy-mm-dd hh:mm:ss")
    private String updateTime;
    // 订单清单物品
    @Schema(description = "物品编号")
    private List<OrderDetailData> detail;

}
