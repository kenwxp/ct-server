package com.cloudtimes.app.controller.door.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录校验接口返回体
 */

@Data
@Slf4j
@Schema(description = "登录校验接口返回体")
public class DoorFaceLoginCheckResp {
    /**
     * 是否新设备 0-否 1-是
     */
    @Schema(description = "是否新设备 0-否 1-是")
    private String isNew;

}
