package com.cloudtimes.promotion.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtUserDrawRelTable : AliasableSqlTable<CtUserDrawRelTable>("ct_user_draw_rel", ::CtUserDrawRelTable) {
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val activityId = column<String>(name = "activity_id", jdbcType = JDBCType.OTHER)

    val drawRuleId = column<String>(name = "draw_rule_id", jdbcType = JDBCType.OTHER)

    val exchangeState = column<String>(name = "exchange_state", jdbcType = JDBCType.CHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}