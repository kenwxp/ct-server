package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Schema(description = "返回参数")
@Data
@Slf4j
public class GetAudioTemplateResp {
    @Schema(description = "模版编号")
    private String templateId;// uuid
    @Schema(description = "模版名")
    private String templateName;// 模版名
    @Schema(description = "媒体id")
    private String mediaUuid;// 媒体id
    @Schema(description = "媒体id")
    private String ossLink;// 媒体id
    @Schema(description = "创建时间")
    private String createTime;// 创建时间
    @Schema(description = "更新时间")
    private String updateTime;// 更新时间
}
