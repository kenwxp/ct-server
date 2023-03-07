package com.cloudtimes.serving.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetSuperviseListReq {
    @NotNull
    @ApiModelProperty(value = "页码")
    private int pageNum;
    @NotNull
    @ApiModelProperty(value = "每页条数")
    private int pageSize;
}
