package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Schema(description = "请求参数")
@Data
public class SyncProductReq {
    @NotEmpty
    @Schema(description = "门店id", required = true)
    private String shopId;
}
