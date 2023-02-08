package com.cloudtimes.app.controller.mobile.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "ChangePasswordReq", description = "修改密码请求参数")
@Data
@Slf4j
public class ApplySuperviseReq {
    @ApiModelProperty(value = "店铺id", required = true)
    private String storeId;
    @ApiModelProperty(value = "操作标志 (0退出，1申请）", required = true)
    private String optFlag;
}
