package com.cloudtimes.app.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SuperviseBusiData {
    @ApiModelProperty("是否订阅 0-否 1-是")
    private int subscribe;
    @ApiModelProperty("任务id 订阅获取订单列表是必填")
    private String taskId;
}
