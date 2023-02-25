package com.cloudtimes.app.controller.mobile.model;

import com.cloudtimes.common.core.domain.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@ApiModel(description = "请求参数")
@Data
@Slf4j
public class GetSuperviseListReq implements PageRequest {
    private int pageNum;
    private int pageSize;

    @Override
    public int getPageNum() {
        return 0;
    }

    @Override
    public void setPageNum(int i) {

    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public void setPageSize(int i) {

    }
}
