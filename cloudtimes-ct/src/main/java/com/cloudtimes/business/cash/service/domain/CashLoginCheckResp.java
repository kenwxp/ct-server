package com.cloudtimes.business.cash.service.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 登录校验接口返回体
 */

@Data
@Slf4j
@Schema(description = "返回参数")
public class CashLoginCheckResp {
    /**
     * 是否新设备 0-否 1-是
     */
    @Schema(description = "是否新设备 0-否 1-是")
    private String isNew;

}
