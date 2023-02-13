package com.cloudtimes.account.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.util.*

/**
 * 代理活动关系 ct_agent_activity
 *
 * @author 沈兵
 * @date 2023-02-02
 */
class CtAgentActivity {
    /** 活动ID  */
    var activityId: String? = null

    /** 代理用户ID  */
    var userId: String? = null
}