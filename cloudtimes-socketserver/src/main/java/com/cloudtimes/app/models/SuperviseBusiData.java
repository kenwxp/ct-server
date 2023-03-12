package com.cloudtimes.app.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SuperviseBusiData {
    @Schema(description = "是否订阅 0-否 1-是")
    private int subscribe;
    @Schema(description = "任务id 订阅获取订单列表是必填")
    private String taskId;
}
