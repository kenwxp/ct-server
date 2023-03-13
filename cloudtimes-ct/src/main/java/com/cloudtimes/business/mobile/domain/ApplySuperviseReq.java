package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "请求参数")
@Data
@Slf4j
public class ApplySuperviseReq {
    @NotEmpty
    @Schema(description = "店铺id", required = true)
    private String storeId;
    @NotEmpty
    @Schema(description = "操作标志 (0退出，1申请）", required = true)
    private String optFlag;
}
