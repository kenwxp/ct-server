package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "请求参数")
@Data
@Slf4j
public class ApplySuperviseReq {
    @NotEmpty
    @ApiModelProperty(value = "店铺id", required = true)
    private String storeId;
    @NotEmpty
    @ApiModelProperty(value = "操作标志 (0退出，1申请）", required = true)
    private String optFlag;
}
