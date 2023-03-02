package com.cloudtimes.serving.mobile.domain;

import com.cloudtimes.common.core.domain.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetSuperviseListReq {
    @ApiModelProperty(value = "页码")
    private int pageNum;
    @ApiModelProperty(value = "每页条数")
    private int pageSize;
}
