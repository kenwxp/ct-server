package com.cloudtimes.station.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "返回参数")
@Data
public class VideoTreeNode {
    @ApiModelProperty("id")
    private String id;// 103,
    @ApiModelProperty("标签")
    private String label;// "监控1",
    @ApiModelProperty("播放链接")
    private String videoUrl;// "ezopen://open.ys7.com/J58089461/1.live",
    @ApiModelProperty("token")
    private String token;// "at.23p3g7r25llrlgpr5ijvsdbf45e3wyof-5c0aq0zkd9-1a7s6ld-kzf0tlnb7"
    @ApiModelProperty("子节点")
    private List<VideoTreeNode> children;
}
