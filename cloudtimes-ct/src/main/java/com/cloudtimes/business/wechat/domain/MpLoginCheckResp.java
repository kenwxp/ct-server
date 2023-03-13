package com.cloudtimes.business.wechat.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 小程序登录校验接口返回体
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "返回参数")
public class MpLoginCheckResp {
    /**
     * 是否新用户 0-否 1-是
     */
    @Schema(description = "是否新用户 0-否 1-是")
    private String isNew;

}
