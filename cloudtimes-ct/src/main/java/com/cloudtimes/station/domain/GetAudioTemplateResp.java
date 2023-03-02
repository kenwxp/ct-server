package com.cloudtimes.station.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@ApiModel(description = "返回参数")
@Data
@Slf4j
public class GetAudioTemplateResp {
    @ApiModelProperty("模版编号")
    private String templateId;// uuid
    @ApiModelProperty("模版名")
    private String templateName;// 模版名
    @ApiModelProperty("媒体id")
    private String mediaUuid;// 媒体id
    @ApiModelProperty("媒体id")
    private String ossLink;// 媒体id
    @ApiModelProperty("创建时间")
    private String createTime;// 创建时间
    @ApiModelProperty("更新时间")
    private String updateTime;// 更新时间
}
