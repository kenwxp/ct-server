package com.cloudtimes.serving.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "返回参数")
@Data
public class GetShopRankResp {
    @Schema(description = "门店名")
    private String shopName;
    @Schema(description = "在店人数")
    private String visitNum;
}
