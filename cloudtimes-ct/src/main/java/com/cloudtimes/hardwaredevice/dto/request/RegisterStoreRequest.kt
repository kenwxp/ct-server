package com.cloudtimes.hardwaredevice.dto.request

import com.cloudtimes.common.annotation.NoArgs
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@NoArgs
data class RegisterStoreRequest(
    @ApiModelProperty(value = "店铺名称", required = true)
    @field:NotNull(message = "店铺名称不能为空")
    @field:NotEmpty(message = "店铺名称不能为空")
    val shopName: String,

    @ApiModelProperty(value = "店主名称", required = true)
    @field:NotNull(message = "店主名称不能为空")
    @field:NotEmpty(message = "店主名称不能为空")
    val shopKeeper: String,

    @ApiModelProperty(value = "微信UNION_ID", required = true)
    @field:NotEmpty(message =  "微信UNION_ID不能为空")
    @field:NotNull(message =  "微信UNION_ID不能为空")
    val wxUnionId: String,

    @ApiModelProperty(value = "手机号", required = true)
    @field:NotNull(message =  "手机号不能为空")
    @field:NotEmpty(message =  "手机号不能为空")
    val mobile: String,

    @ApiModelProperty(value = "地区码", required = true)
    @field:NotNull(message =  "地区码不能为空")
    @field:NotEmpty(message =  "地区码不能为空")
    val regionCode: String,

    @ApiModelProperty(value = "店铺地址", required = true)
    @field:NotNull(message =  "店铺地址不能为空")
    @field:NotEmpty(message =  "店铺地址不能为空")
    val address: String,

    @ApiModelProperty(value = "经度")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val longitude:  BigDecimal?,

    @ApiModelProperty(value = "纬度")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val latitude:  BigDecimal?,

    @ApiModelProperty(value = "邀请码")
    val inviteCode: String? = null,

    @ApiModelProperty(value = "手机验证码UUID", required = true)
    @field:NotNull(message =  "手机验证码UUID不能为空")
    @field:NotEmpty(message =  "手机验证码UUID不能为空")
    val verifyUUID: String,

    @ApiModelProperty(value = "手机验证码", required = true)
    @field:NotNull(message =  "手机验证码不能为空")
    @field:NotEmpty(message =  "手机验证码不能为空")
    val verifyCode: String? = null,
)