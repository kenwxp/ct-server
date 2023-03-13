package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "请求参数")
@Data
@Slf4j
public class ChangePasswordReq {
    @NotEmpty
    @Schema(description = "新密码", required = true)
    private String passwordNew;
    @NotEmpty
    @Schema(description = "旧密码", required = true)
    private String passwordOld;
}
