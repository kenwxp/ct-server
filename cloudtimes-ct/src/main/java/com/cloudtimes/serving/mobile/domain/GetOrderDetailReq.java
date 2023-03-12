package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;


@Schema(description = "请求参数")
@Data
@Slf4j
public class GetOrderDetailReq {
    @NotEmpty
    @Schema(description = "订单编号", required = true)
    private String orderId;
}
