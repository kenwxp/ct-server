package com.cloudtimes.serving.wechat.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Schema(description = "请求参数")
@Data
public class ScanCodeReq {
    @NotEmpty
    @Schema(description = "门店编号（非主键）", required = true)
    private String shopId;
    @Schema(description = "动态码内容", required = true)
    private String dynamicCode;
    @Schema(description = "设备id", required = true)
    private String did;
}
