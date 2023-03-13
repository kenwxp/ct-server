package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "请求参数")
@Data
@Slf4j
public class ShopStatSumReq {
    @Schema(description = "门店id 门店查询类型为1时，必输", required = true)
    private String shopId;
    @NotEmpty
    @Schema(description = "门店查询类型（0-全部门店，1-具体门店）", required = true)
    private String searchType;
    @NotEmpty
    @Schema(description = "时间段类型（1-今天，2-昨天，3-本周，4-本月）", required = true)
    private String periodType;
}
