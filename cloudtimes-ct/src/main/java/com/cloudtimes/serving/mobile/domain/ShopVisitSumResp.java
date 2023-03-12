package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class ShopVisitSumResp {
    @Schema(description = "总客流")
    private String totalVisit;
    @Schema(description = "付款客流")
    private String payVisit;
    @Schema(description = "进入频率")
    private String visitFrequency;
    @Schema(description = "较上期 单位% ")
    private String diffRate;
}
