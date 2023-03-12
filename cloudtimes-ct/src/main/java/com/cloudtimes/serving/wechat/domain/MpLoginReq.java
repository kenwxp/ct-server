package com.cloudtimes.serving.wechat.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 小程序登录请求体
 */
@Schema(description = "请求参数")
@Data
public class MpLoginReq {
    /**
     * 微信登录code
     */
    @NotEmpty
    @Schema(description = "微信登录code", required = true)
    private String loginCode;
    /**
     * 获取手机号code
     */
    @Schema(description = "获取手机号code", required = true)
    private String phoneCode;

}
