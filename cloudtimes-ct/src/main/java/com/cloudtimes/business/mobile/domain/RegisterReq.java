package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;


@Schema(description = "请求参数")
@Data
@Slf4j
public class RegisterReq {
    @NotEmpty
    @Schema(description = "手机号", required = true)
    private String phone;
    @NotEmpty
    @Schema(description = "登录密码", required = true)
    private String password;
    @NotEmpty
    @Schema(description = "账户名", required = true)
    private String account;
    @NotEmpty
    @Schema(description = "昵称", required = true)
    private String nickName;
}
