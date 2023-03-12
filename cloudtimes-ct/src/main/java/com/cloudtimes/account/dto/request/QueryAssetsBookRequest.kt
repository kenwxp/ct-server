package com.cloudtimes.account.dto.request

import com.cloudtimes.common.core.domain.PageRequest
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Schema(description = "查询资产账簿请求")
class QueryAssetsBookRequest: PageRequest {
    @Schema(description = "用户编号", required = true)
    @field:NotEmpty(message =  "用户编号不能为空")
    @field:NotNull(message =  "用户编号不能为空")
    var userId: String = ""

    @Schema(description = "交易发生日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var yearMonth: String? = null

    @Schema(description = "账簿类型")
    var bookType: String? = null

    override var pageNum: Int = 1
    override var pageSize: Int = 10

    override fun toString(): String {
        return "QueryAssetsBookRequest(userId='$userId', yearMonth=$yearMonth, payState=$bookType, pageNum=$pageNum, pageSize=$pageSize)"
    }
}
