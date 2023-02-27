package com.cloudtimes.agent.table

import java.lang.Object
import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentActivitySettlementTable : AliasableSqlTable<CtAgentActivitySettlementTable>(
    "ct_agent_activity_settlement",
    ::CtAgentActivitySettlementTable
) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val activityType = column<String>(name = "activity_type", jdbcType = JDBCType.CHAR)

    val activityRuleId = column<String>(name = "activity_rule_id", jdbcType = JDBCType.OTHER)

    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val amount = column<BigDecimal>(name = "amount", jdbcType = JDBCType.DECIMAL)

    val taxRatio = column<BigDecimal>(name = "tax_ratio", jdbcType = JDBCType.DECIMAL)

    val taxAmount = column<BigDecimal>(name = "tax_amount", jdbcType = JDBCType.DECIMAL)

    val beforeTaxAmount = column<BigDecimal>(name = "before_tax_amount", jdbcType = JDBCType.DECIMAL)

    val platformApprovedTime = column<Date>(name = "platform_approved_time", jdbcType = JDBCType.TIMESTAMP)

    val agentApprovedTime = column<Date>(name = "agent_approved_time", jdbcType = JDBCType.TIMESTAMP)

    val isFulfilled = column<String>(name = "is_fulfilled", jdbcType = JDBCType.CHAR)

    val verifyState = column<String>(name = "verify_state", jdbcType = JDBCType.CHAR)

    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    val operator = column<String>(name = "operator", jdbcType = JDBCType.VARCHAR)

    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)

    val fulfilledDate = column<Date>(name = "fulfilled_date", jdbcType = JDBCType.DATE)

    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    val fulfilledStores = column<String>(name = "fulfilled_stores", jdbcType = JDBCType.LONGVARCHAR)
}
