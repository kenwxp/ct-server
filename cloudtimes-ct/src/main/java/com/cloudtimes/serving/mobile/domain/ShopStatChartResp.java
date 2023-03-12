package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "返回参数")
@Data
@Slf4j
public class ShopStatChartResp {
    @Schema(description = "标签")
    private String label;
    @Schema(description = "值")
    private String value;
}
