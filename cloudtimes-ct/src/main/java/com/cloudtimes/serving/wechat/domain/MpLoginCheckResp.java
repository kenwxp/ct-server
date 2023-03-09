package com.cloudtimes.serving.wechat.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 小程序登录校验接口返回体
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "返回参数")
public class MpLoginCheckResp {
    /**
     * 是否新用户 0-否 1-是
     */
    @ApiModelProperty("是否新用户 0-否 1-是")
    private String isNew;

}
