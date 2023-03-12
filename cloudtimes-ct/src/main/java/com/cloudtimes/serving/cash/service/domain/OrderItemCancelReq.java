package com.cloudtimes.serving.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "请求参数")
@Data
@Slf4j
public class OrderItemCancelReq {
    @NotEmpty
    @Schema(description = "订单号 isSupervise 为1 必填")
    private String orderId;
}
