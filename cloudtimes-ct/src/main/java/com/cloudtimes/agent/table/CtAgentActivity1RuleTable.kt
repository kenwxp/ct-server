package com.cloudtimes.agent.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentActivity1RuleTable :
    AliasableSqlTable<CtAgentActivity1RuleTable>("ct_agent_activity1_rule", ::CtAgentActivity1RuleTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val activityId = column<String>(name = "activity_id", jdbcType = JDBCType.OTHER)

    val title = column<String>(name = "title", jdbcType = JDBCType.VARCHAR)

    val content = column<String>(name = "content", jdbcType = JDBCType.VARCHAR)

    val activityType = column<String>(name = "activity_type", jdbcType = JDBCType.CHAR)

    val storeCount = column<Int>(name = "store_count", jdbcType = JDBCType.INTEGER)

    val rewardMoney = column<BigDecimal>(name = "reward_money", jdbcType = JDBCType.DECIMAL)

    val taxRatio = column<BigDecimal>(name = "tax_ratio", jdbcType = JDBCType.DECIMAL)

    val timeSpan = column<Int>(name = "time_span", jdbcType = JDBCType.INTEGER)

    val operator = column<String>(name = "operator", jdbcType = JDBCType.VARCHAR)

    val isEnabled = column<String>(name = "is_enabled", jdbcType = JDBCType.CHAR)

    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    val startTime = column<Date>(name = "start_time", jdbcType = JDBCType.TIMESTAMP)

    val endTime = column<Date>(name = "end_time", jdbcType = JDBCType.TIMESTAMP)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}
