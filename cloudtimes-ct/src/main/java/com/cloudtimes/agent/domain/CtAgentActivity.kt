package com.cloudtimes.agent.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 代理活动对象 ct_agent_activity
 *
 * @author 沈兵
 * @date 2023-02-17
 */
@ApiModel(value = "CtAgentActivity", description = "代理活动")
class CtAgentActivity : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "标题")
    @Excel(name = "标题")
    var title: String? = null

    @ApiModelProperty(value = "内容")
    @Excel(name = "内容")
    var content: String? = null

    @ApiModelProperty(value = "活动类型")
    @Excel(name = "活动类型")
    var activityType: String? = null

    @ApiModelProperty(value = "是否启用")
    @Excel(name = "是否启用")
    @get:JvmName("getIsEnabled")
    @set:JvmName("setIsEnabled")
    var isEnabled: String? = null

    @ApiModelProperty(value = "活动状态")
    @Excel(name = "活动状态")
    var state: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var startTime: Date? = null

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var endTime: Date? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("title", title)
            .append("content", content)
            .append("activityType", activityType)
            .append("isEnabled", isEnabled)
            .append("state", state)
            .append("delFlag", delFlag)
            .append("startTime", startTime)
            .append("endTime", endTime)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
