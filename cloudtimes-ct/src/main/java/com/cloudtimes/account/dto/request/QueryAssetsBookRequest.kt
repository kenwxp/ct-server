package com.cloudtimes.account.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@ApiModel(value = "QueryAssetsBookRequest", description = "查询资产账簿请求")
class QueryAssetsBookRequest: PageRequest {
    @ApiModelProperty(value = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @ApiModelProperty(value = "交易发生日期")
    var createDate: LocalDate? = null

    @ApiModelProperty(value = "账簿类型")
    var bookType: String? = null

    override var pageNum: Int = 1
    override var pageSize: Int = 10

    override fun toString(): String {
        return "QueryAssetsBookRequest(userId='$userId', applyDate=$createDate, payState=$bookType, pageNum=$pageNum, pageSize=$pageSize)"
    }
}