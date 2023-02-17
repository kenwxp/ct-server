package com.cloudtimes.agent.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

/**
 * 代理活动关系对象 ct_agent_activity_rel
 *
 * @author tank
 * @date 2023-02-17
 */
class CtAgentActivityRel : BaseEntity() {
    /** 活动ID  */
    var activityId: String? = null

    /** 用户ID  */
    var userId: String? = null

    /** 活动类型  */
    @Excel(name = "活动类型")
    var activityType: String? = null
    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", activityId)
            .append("userId", userId)
            .append("activityType", activityType)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
