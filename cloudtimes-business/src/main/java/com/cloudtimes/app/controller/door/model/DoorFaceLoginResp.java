package com.cloudtimes.app.controller.door.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;



@Data
@Slf4j
@Schema(description = "小程序登录校验接口返回体")
public class DoorFaceLoginResp {
    @Schema(description = "后台登录token")
    private String accessToken;
}
