package com.cloudtimes.product.table

import java.sql.JDBCType
import java.util.Date
import java.math.BigDecimal
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtShopPurchaseTable : AliasableSqlTable<CtShopPurchaseTable>("ct_shop_purchase", ::CtShopPurchaseTable) {
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    val shopNo = column<String>(name = "shop_no", jdbcType = JDBCType.VARCHAR)

    val supplier= column<String>(name = "supplier", jdbcType = JDBCType.CHAR)

    val orderingDate = column<Date>(name = "ordering_date", jdbcType = JDBCType.TIMESTAMP)

    val orderingNumber = column<String>(name = "ordering_number", jdbcType = JDBCType.VARCHAR)

    val totalPrice = column<BigDecimal>(name = "total_price", jdbcType = JDBCType.DECIMAL)

    val logisticsFee = column<BigDecimal>(name = "logistics_fee", jdbcType = JDBCType.DECIMAL)

    val deliveryTime = column<Date>(name = "delivery_time", jdbcType = JDBCType.TIMESTAMP)

    val takeTime = column<Date>(name = "take_time", jdbcType = JDBCType.TIMESTAMP)

    val trackingNumber = column<String>(name = "tracking_number", jdbcType = JDBCType.VARCHAR)

    val orderingStatus = column<String>(name = "ordering_status", jdbcType = JDBCType.CHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}