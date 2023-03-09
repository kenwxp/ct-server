package com.cloudtimes.promotion.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtActivityTable : AliasableSqlTable<CtActivityTable>("ct_activity", ::CtActivityTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val activityName = column<String>(name = "activity_name", jdbcType = JDBCType.VARCHAR)

    val activityContent = column<String>(name = "activity_content", jdbcType = JDBCType.VARCHAR)

    val totalAmount = column<BigDecimal>(name = "total_amount", jdbcType = JDBCType.DECIMAL)

    val usedAmount = column<BigDecimal>(name = "used_amount", jdbcType = JDBCType.DECIMAL)

    val sourceType = column<String>(name = "source_type", jdbcType = JDBCType.CHAR)

    val termType = column<String>(name = "term_type", jdbcType = JDBCType.CHAR)

    val hasTimeInterval = column<String>(name = "has_time_interval", jdbcType = JDBCType.CHAR)

    val isEnable = column<String>(name = "is_enable", jdbcType = JDBCType.CHAR)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    val timeInterval = column<String>(name = "time_interval", jdbcType = JDBCType.VARCHAR)

    val startTime = column<Date>(name = "start_time", jdbcType = JDBCType.TIMESTAMP)

    val endTime = column<Date>(name = "end_time", jdbcType = JDBCType.TIMESTAMP)

    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)

    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}