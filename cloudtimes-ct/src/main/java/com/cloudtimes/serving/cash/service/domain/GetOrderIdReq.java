package com.cloudtimes.serving.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;

@Schema(description = "请求参数")
@Data
@Slf4j
public class GetOrderIdReq {
    @NotEmpty
    @Schema(description = "人脸识别token", required = true)
    private String token;

}
