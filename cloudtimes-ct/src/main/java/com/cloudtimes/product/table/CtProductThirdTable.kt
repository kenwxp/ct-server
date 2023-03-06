package com.cloudtimes.product.table

import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtProductThirdTable : AliasableSqlTable<CtProductThirdTable>("ct_product_third", ::CtProductThirdTable) {
    val thirdCode = column<String>(name = "third_code", jdbcType = JDBCType.VARCHAR)

    val thirdProductId = column<String>(name = "third_product_id", jdbcType = JDBCType.VARCHAR)

    val productId = column<Object>(name = "product_id", jdbcType = JDBCType.OTHER)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}