package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class ShopStat24hSumResp {
    @Schema(description = "访问量")
    private String visit;
    @Schema(description = "营收额")
    private String income;
}
