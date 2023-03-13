package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Schema(description = "请求参数")
@Data
@Slf4j
public class ShopStatChartReq {
    @Schema(description = "门店id 门店查询类型为1时，必输", required = true)
    private String shopId;
    @Schema(description = "门店查询类型（0-全部门店，1-具体门店）", required = true)
    private String searchType;
    @Schema(description = "步幅类型（0-时，1-日，2-周，3-月）", required = true)
    private String stepType;
}
