package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema(description = "返回参数")
@Data
public class GetFaceAuthInfoResp {
    @Schema(description = "刷脸凭证authInfo", required = true)
    private String authInfo;
}
