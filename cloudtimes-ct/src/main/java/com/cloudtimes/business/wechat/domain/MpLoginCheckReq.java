package com.cloudtimes.business.wechat.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 小程序登录校验请求体
 */
@Schema(description = "请求参数")
@Data
public class MpLoginCheckReq {
    @NotEmpty
    @Schema(description = "微信登录code", required = true)
    private String loginCode;
}
