package com.cloudtimes.promotion.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtLuckyDrawRuleTable : AliasableSqlTable<CtLuckyDrawRuleTable>("ct_lucky_draw_rule", ::CtLuckyDrawRuleTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val activityId = column<String>(name = "activity_id", jdbcType = JDBCType.OTHER)

    val seqno = column<Int>(name = "seqno", jdbcType = JDBCType.INTEGER)

    val pic = column<String>(name = "pic", jdbcType = JDBCType.VARCHAR)

    val winRatio = column<BigDecimal>(name = "win_ratio", jdbcType = JDBCType.DECIMAL)

    val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

    val reward = column<BigDecimal>(name = "reward", jdbcType = JDBCType.DECIMAL)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}