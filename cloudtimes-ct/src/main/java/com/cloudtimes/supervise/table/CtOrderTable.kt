package com.cloudtimes.supervise.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtOrderTable : AliasableSqlTable<CtOrderTable>("ct_order", ::CtOrderTable) {
    val yearMonth = column<Int>(name = "`year_month`", jdbcType = JDBCType.INTEGER)

    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val taskId = column<String>(name = "task_id", jdbcType = JDBCType.OTHER)

    val storeId = column<String>(name = "store_id", jdbcType = JDBCType.OTHER)

    val storeName = column<String>(name = "store_name", jdbcType = JDBCType.VARCHAR)

    val storeProvince = column<String>(name = "store_province", jdbcType = JDBCType.VARCHAR)

    val storeCity = column<String>(name = "store_city", jdbcType = JDBCType.VARCHAR)

    val agentId = column<String>(name = "agent_id", jdbcType = JDBCType.OTHER)

    val bossUserId = column<String>(name = "boss_user_id", jdbcType = JDBCType.OTHER)

    val shoppingId = column<String>(name = "shopping_id", jdbcType = JDBCType.OTHER)

    val staffCode = column<String>(name = "staff_code", jdbcType = JDBCType.VARCHAR)

    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    val isCompose = column<String>(name = "is_compose", jdbcType = JDBCType.CHAR)

    val moneyAmount = column<BigDecimal>(name = "money_amount", jdbcType = JDBCType.DECIMAL)

    val totalAmount = column<BigDecimal>(name = "total_amount", jdbcType = JDBCType.DECIMAL)

    val discountAmount = column<BigDecimal>(name = "discount_amount", jdbcType = JDBCType.DECIMAL)

    val deductionAmount = column<BigDecimal>(name = "deduction_amount", jdbcType = JDBCType.DECIMAL)

    val actualAmount = column<BigDecimal>(name = "actual_amount", jdbcType = JDBCType.DECIMAL)

    val itemCount = column<Int>(name = "item_count", jdbcType = JDBCType.INTEGER)

    val paymentCode = column<String>(name = "payment_code", jdbcType = JDBCType.VARCHAR)

    val paymentName = column<String>(name = "payment_name", jdbcType = JDBCType.VARCHAR)

    val paymentMode = column<String>(name = "payment_mode", jdbcType = JDBCType.CHAR)

    val paymentAction = column<String>(name = "payment_action", jdbcType = JDBCType.CHAR)

    val paymentId = column<String>(name = "payment_id", jdbcType = JDBCType.VARCHAR)

    val deviceCashId = column<String>(name = "device_cash_id", jdbcType = JDBCType.OTHER)

    val descText = column<String>(name = "desc_text", jdbcType = JDBCType.VARCHAR)

    val isExceptional = column<String>(name = "is_exceptional", jdbcType = JDBCType.CHAR)

    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)
}