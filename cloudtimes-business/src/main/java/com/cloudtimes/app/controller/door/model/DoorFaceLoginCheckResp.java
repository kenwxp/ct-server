package com.cloudtimes.app.controller.door.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录校验接口返回体
 */

@Data
@Slf4j
@ApiModel(value = "DoorFaceLoginCheckResp", description = "登录校验接口返回体")
public class DoorFaceLoginCheckResp {
    /**
     * 是否新设备 0-否 1-是
     */
    @ApiModelProperty("是否新设备 0-否 1-是")
    private String isNew;

}
