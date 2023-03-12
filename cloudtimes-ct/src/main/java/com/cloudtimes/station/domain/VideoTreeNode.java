package com.cloudtimes.station.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "返回参数")
@Data
public class VideoTreeNode {
    @Schema(description = "id")
    private String id;// 103,
    @Schema(description = "标签")
    private String label;// "监控1",
    @Schema(description = "播放链接")
    private String videoUrl;// "ezopen://open.ys7.com/J58089461/1.live",
    @Schema(description = "token")
    private String token;// "at.23p3g7r25llrlgpr5ijvsdbf45e3wyof-5c0aq0zkd9-1a7s6ld-kzf0tlnb7"
    @Schema(description = "子节点")
    private List<VideoTreeNode> children;
}
