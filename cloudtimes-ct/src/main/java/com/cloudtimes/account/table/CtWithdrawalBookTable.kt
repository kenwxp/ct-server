package com.cloudtimes.account.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column
import java.time.LocalDate
import java.time.LocalDateTime

class CtWithdrawalBookTable : AliasableSqlTable<CtWithdrawalBookTable>("ct_withdrawal_book", ::CtWithdrawalBookTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val userType = column<String>(name = "user_type", jdbcType = JDBCType.CHAR)

    val amount = column<BigDecimal>(name = "amount", jdbcType = JDBCType.DECIMAL)

    val paySerial = column<String>(name = "pay_serial", jdbcType = JDBCType.VARCHAR)

    val thirdSerial = column<String>(name = "third_serial", jdbcType = JDBCType.VARCHAR)

    val payState = column<String>(name = "pay_state", jdbcType = JDBCType.CHAR)

    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)

    val applyDate = column<LocalDate>(name = "apply_date", jdbcType = JDBCType.DATE)

    val payDate = column<LocalDate>(name = "pay_date", jdbcType = JDBCType.DATE)

    val payTime = column<LocalDateTime>(name = "pay_time", jdbcType = JDBCType.DATE)

    val operator = column<String>(name = "operator", jdbcType = JDBCType.VARCHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)
}