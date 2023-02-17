package com.cloudtimes.agent.table

import java.lang.Object
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentActivityRuleStoreRelTable : AliasableSqlTable<CtAgentActivityRuleStoreRelTable>(
    "ct_agent_activity_rule_store_rel",
    ::CtAgentActivityRuleStoreRelTable
) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val activityRuleId = column<String>(name = "activity_rule_id", jdbcType = JDBCType.OTHER)

    val storeId = column<String>(name = "store_id", jdbcType = JDBCType.OTHER)

    val storeOnlineDate = column<Date>(name = "store_online_date", jdbcType = JDBCType.DATE)

    val regionCode = column<String>(name = "region_code", jdbcType = JDBCType.VARCHAR)

    val parentAgentId = column<String>(name = "parent_agent_id", jdbcType = JDBCType.OTHER)

    val agentId = column<String>(name = "agent_id", jdbcType = JDBCType.OTHER)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}
