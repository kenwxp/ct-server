package com.cloudtimes.business.mobile.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "返回参数")
@Data
public class GetOrderLocalVideoResp {
    @Schema(description = "视频播放url")
    private String url;
    @Schema(description = "视频播放token")
    private String token;
}