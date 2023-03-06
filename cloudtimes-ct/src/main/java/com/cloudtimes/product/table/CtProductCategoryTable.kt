package com.cloudtimes.product.table

import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtProductCategoryTable : AliasableSqlTable<CtProductCategoryTable>("ct_product_category", ::CtProductCategoryTable) {
    val categoryCode = column<String>(name = "category_code", jdbcType = JDBCType.CHAR)

    val tier = column<String>(name = "tier", jdbcType = JDBCType.CHAR)

    val categoryName = column<String>(name = "category_name", jdbcType = JDBCType.VARCHAR)

    val comment = column<String>(name = "comment", jdbcType = JDBCType.VARCHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}