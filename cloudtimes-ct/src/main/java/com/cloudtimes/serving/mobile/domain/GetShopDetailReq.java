package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetShopDetailReq {
    @NotEmpty
    @ApiModelProperty(value = "店名id")
    private String shopId; // 店名
}
