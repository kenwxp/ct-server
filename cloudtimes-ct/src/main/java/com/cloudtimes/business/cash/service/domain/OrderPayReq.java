package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "请求参数")
@Data
@Slf4j
public class OrderPayReq {
    @NotNull
    @Schema(description = "支付方式 0-扫码支付 1-刷脸支付", required = true)
    private int payType;
    @NotEmpty
    @Schema(description = "支付码（扫码支付时，为动态码，刷脸支付时为face_code）", required = true)
    private String payCode;
    @NotEmpty
    @Schema(description = "订单序列号", required = true)
    private String orderId;
    @NotNull
    @Schema(description = "总计预付金额，单位分", required = true)
    private int totalAmount;
    @NotNull
    @Schema(description = "物件总数", required = true)
    private int totalNum;
}
