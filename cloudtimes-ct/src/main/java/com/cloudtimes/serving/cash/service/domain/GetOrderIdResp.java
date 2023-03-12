package com.cloudtimes.serving.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class GetOrderIdResp {
    @Schema(description = "新单号", required = true)
    private String orderId;
    @Schema(description = "顾客手机号", required = true)
    private String customerPhone;
}
