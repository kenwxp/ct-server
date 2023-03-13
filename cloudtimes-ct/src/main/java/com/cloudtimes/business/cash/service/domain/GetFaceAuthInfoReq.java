package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Schema(description = "请求参数")
@Data
public class GetFaceAuthInfoReq {
    @NotEmpty
    @Schema(description = "刷脸rawdata", required = true)
    private String rawdata;
    @NotEmpty
    @Schema(description = "凭证类型 1-微信凭证 2-收钱吧凭证", required = true)
    private String authType;
}
