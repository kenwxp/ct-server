package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class ApproveOrderReq {
    @Schema(description = "订单编号", required = true)
    private String orderId;
    @Schema(description = "审核标志（1-通过 2-拒绝 3-取消订单）", required = true)
    private String option;
    @Schema(description = "备注", required = true)
    private String remark;
}
