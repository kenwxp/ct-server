package com.cloudtimes.hardwaredevice.dto.request

import com.cloudtimes.common.annotation.NoArgs
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@NoArgs
data class RegisterStoreRequest(
    @Schema(description = "店铺名称", required = true)
    @field:NotNull(message = "店铺名称不能为空")
    @field:NotEmpty(message = "店铺名称不能为空")
    val shopName: String,

    @Schema(description = "店主名称", required = true)
    @field:NotNull(message = "店主名称不能为空")
    @field:NotEmpty(message = "店主名称不能为空")
    val shopKeeper: String,

    @Schema(description = "微信UNION_ID", required = true)
    @field:NotEmpty(message =  "微信UNION_ID不能为空")
    @field:NotNull(message =  "微信UNION_ID不能为空")
    val wxUnionId: String,

    @Schema(description = "手机号", required = true)
    @field:NotNull(message =  "手机号不能为空")
    @field:NotEmpty(message =  "手机号不能为空")
    val mobile: String,

    @Schema(description = "地区码", required = true)
    @field:NotNull(message =  "地区码不能为空")
    @field:NotEmpty(message =  "地区码不能为空")
    val regionCode: String,

    @Schema(description = "店铺地址", required = true)
    @field:NotNull(message =  "店铺地址不能为空")
    @field:NotEmpty(message =  "店铺地址不能为空")
    val address: String,

    @Schema(description = "经度")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val longitude:  BigDecimal?,

    @Schema(description = "纬度")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val latitude:  BigDecimal?,

    @Schema(description = "邀请码")
    val inviteCode: String? = null,

    @Schema(description = "手机验证码UUID", required = true)
    @field:NotNull(message =  "手机验证码UUID不能为空")
    @field:NotEmpty(message =  "手机验证码UUID不能为空")
    val verifyUUID: String,

    @Schema(description = "手机验证码", required = true)
    @field:NotNull(message =  "手机验证码不能为空")
    @field:NotEmpty(message =  "手机验证码不能为空")
    val verifyCode: String? = null,
)
