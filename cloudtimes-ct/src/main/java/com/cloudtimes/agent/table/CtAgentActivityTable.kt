package com.cloudtimes.agent.table

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.util.kotlin.elements.column
import org.mybatis.dynamic.sql.AliasableSqlTable

class CtAgentActivityTable : AliasableSqlTable<CtAgentActivityTable>("ct_agent_activity", ::CtAgentActivityTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val title = column<String>(name = "title", jdbcType = JDBCType.VARCHAR)

    val content = column<String>(name = "content", jdbcType = JDBCType.VARCHAR)

    val activityType = column<String>(name = "activity_type", jdbcType = JDBCType.VARCHAR)

    val state = column<String>(name = "state", jdbcType = JDBCType.VARCHAR)

    val isEnabled = column<String>(name = "is_enabled", jdbcType = JDBCType.CHAR)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    val startTime = column<Date>(name = "start_time", jdbcType = JDBCType.TIMESTAMP)

    val endTime = column<Date>(name = "end_time", jdbcType = JDBCType.TIMESTAMP)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}

var activityTable = CtAgentActivityTable()
