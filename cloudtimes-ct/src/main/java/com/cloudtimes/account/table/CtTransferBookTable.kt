package com.cloudtimes.account.table

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column
import java.math.BigDecimal

class CtTransferBookTable : AliasableSqlTable<CtTransferBookTable>("ct_transfer_book", ::CtTransferBookTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val payer = column<String>(name = "payer", jdbcType = JDBCType.OTHER)

    val payee = column<String>(name = "payee", jdbcType = JDBCType.OTHER)

    val amount = column<BigDecimal>(name = "amount", jdbcType = JDBCType.DECIMAL)

    val yearMonth = column<Int>(name = "year_month", jdbcType = JDBCType.INTEGER)

    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)
}