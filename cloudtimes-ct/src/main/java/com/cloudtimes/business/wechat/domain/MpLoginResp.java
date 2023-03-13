package com.cloudtimes.business.wechat.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "返回参数")
public class MpLoginResp {
    @Schema(description = "用户编号")
    private String userId;
    @Schema(description = "后台登录token")
    private String accessToken;

}
