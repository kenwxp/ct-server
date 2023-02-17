package com.cloudtimes.agent.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 代理活动对象 ct_agent_activity
 *
 * @author 沈兵
 * @date 2023-02-17
 */
class CtAgentActivity : BaseEntity() {
    /** 编号  */
    var id: String? = null

    /** 标题  */
    @Excel(name = "标题")
    var title: String? = null

    /** 内容  */
    @Excel(name = "内容")
    var content: String? = null

    /** 活动类型  */
    @Excel(name = "活动类型")
    var activityType: String? = null

    /** 是否启用  */
    @Excel(name = "是否启用")
    var isEnabled: String? = null

    /** 活动状态  */
    @Excel(name = "活动状态")
    var state: String? = null

    /** 是否删除  */
    var delFlag: String? = null

    /** 开始时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var startTime: Date? = null

    /** 结束时间  */
    @JsonFormat(pattern = "yyyy-MM-dd")
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
