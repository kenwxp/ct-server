package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * 投诉建议 ct_suggestion
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@Schema(description = "投诉建议")
class CtSuggestion : BaseEntity() {
    @Schema(description = "编号")
    var id: String? = null

    @Schema(description = "投诉人", required = true)
    @NotNull(message = "投诉人不能为空")
    @NotEmpty(message = "投诉人不能为空")
    @Excel(name = "投诉人")
    var userId: String? = null

    @Schema(description = "联系方式(页面采集)")
    @Excel(name = "联系方式(页面采集)")
    var contact: String? = null

    @Schema(description = "投诉内容", required = true)
    @Excel(name = "投诉内容")
    @NotNull(message = "投诉内容不能为空")
    @NotEmpty(message = "投诉内容不能为空")
    var content: String? = null

    @Schema(description = "处理员工工号")
    @Excel(name = "处理员工工号")
    var replyAccNo: String? = null

    @Schema(description = "处理时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var replyTime: Date? = null

    @Schema(description = "处理批复")
    @Excel(name = "处理批复")
    var replyRemark: String? = null

    @Schema(description = "投诉渠道", required = true)
    @Excel(name = "投诉渠道")
    @NotNull(message = "投诉渠道不能为空")
    @NotEmpty(message = "投诉渠道不能为空")
    var channelType: String? = null

    @Schema(description = "投诉/建议类型", required = true)
    @Excel(name = "投诉/建议类型")
    @NotNull(message = "投诉建议类型不能为空")
    @NotEmpty(message = "投诉建议类型不能为空")
    var suggestionType: String? = null

    @Schema(description = "处理状态")
    @Excel(name = "处理状态")
    var processState: String? = null

    @Schema(description = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("userId", userId)
            .append("contact", contact)
            .append("content", content)
            .append("replyAccNo", replyAccNo)
            .append("replyTime", replyTime)
            .append("replyRemark", replyRemark)
            .append("channelType", channelType)
            .append("suggestionType", suggestionType)
            .append("processState", processState)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
