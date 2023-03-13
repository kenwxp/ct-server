package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "返回参数")
@Data
@NoArgsConstructor
public class OrderItemResp {
    @Schema(description = "订单号")
    private String orderId;

    public OrderItemResp(String orderId) {
        this.orderId = orderId;
    }
}
