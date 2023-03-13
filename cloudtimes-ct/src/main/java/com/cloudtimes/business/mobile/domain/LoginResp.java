package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Schema(description = "返回参数")
@Data
@Slf4j
public class LoginResp {
    @Schema(description = "token")
    private String token;
    @Schema(description = "用户编号")
    private String id;
    @Schema(description = "用户名")
    private String name;
    @Schema(description = "手机号")
    private String phone;
}
