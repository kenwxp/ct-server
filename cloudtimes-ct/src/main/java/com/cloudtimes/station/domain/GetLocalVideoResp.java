package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "返回参数")
@Data
public class GetLocalVideoResp {
    @Schema(description = "播放链接")
    private String url;
    @Schema(description = "视频播放token")
    private String token;
}