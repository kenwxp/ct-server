package com.cloudtimes.serving.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class OrderPayStatusResp {
    @Schema(description = "支付状态 0-待支付 1-支付中 2-支付成功 3-支付失败", required = true)
    private String status;

    public OrderPayStatusResp(String status) {
        this.status = status;
    }
}
