package com.cloudtimes.agent.table

import java.lang.Object
import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentActivityRelTable :
    AliasableSqlTable<CtAgentActivityRelTable>("ct_agent_activity_rel", ::CtAgentActivityRelTable) {
    val activityId = column<String>(name = "activity_id", jdbcType = JDBCType.OTHER)

    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val activityType = column<String>(name = "activity_type", jdbcType = JDBCType.CHAR)
}

val agentActivityRelTable = CtAgentActivityRelTable()
