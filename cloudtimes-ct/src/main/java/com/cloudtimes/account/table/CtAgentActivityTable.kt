package com.cloudtimes.account.table

import java.sql.JDBCType

import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentActivityTable : AliasableSqlTable<CtAgentActivityTable>("ct_agent_activity", ::CtAgentActivityTable) {
    val userId = column<Object>(name = "user_id", jdbcType = JDBCType.OTHER)

    val activityId = column<Object>(name = "activity_id", jdbcType = JDBCType.OTHER)
}
