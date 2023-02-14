package com.cloudtimes.account.table

import java.sql.JDBCType

import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentActivityTable : AliasableSqlTable<CtAgentActivityTable>("ct_agent_activity", ::CtAgentActivityTable) {
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val activityId = column<String>(name = "activity_id", jdbcType = JDBCType.OTHER)

    /** 活动类型 */
    val activityType = column<String>(name = "activity_type", jdbcType = JDBCType.CHAR)
}

val agentActivityTable = CtAgentActivityTable()