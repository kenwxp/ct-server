package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class ShopIncomeSumResp {
    @Schema(description = "总营收 数字到分")
    private String totalIncome;
    @Schema(description = "付款单数 付款单数")
    private String payCount;
    @Schema(description = "客单价 客单价，数据到分")
    private String payPerCustomer;
    @Schema(description = "较上期 单位%")
    private String diffRate;
}
