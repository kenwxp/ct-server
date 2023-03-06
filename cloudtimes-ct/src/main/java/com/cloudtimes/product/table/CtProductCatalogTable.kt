package com.cloudtimes.product.table

import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtProductCatalogTable : AliasableSqlTable<CtProductCatalogTable>("ct_product_catalog", ::CtProductCatalogTable) {
    val id = column<Object>(name = "id", jdbcType = JDBCType.OTHER)

    val barcode = column<String>(name = "barcode", jdbcType = JDBCType.VARCHAR)

    val productName = column<String>(name = "product_name", jdbcType = JDBCType.VARCHAR)

    val englishName = column<String>(name = "english_name", jdbcType = JDBCType.VARCHAR)

    val productBrand = column<String>(name = "product_brand", jdbcType = JDBCType.VARCHAR)

    val productCode = column<String>(name = "product_code", jdbcType = JDBCType.VARCHAR)

    val manufacturer = column<String>(name = "manufacturer", jdbcType = JDBCType.VARCHAR)

    val originCountry = column<String>(name = "origin_country", jdbcType = JDBCType.VARCHAR)

    val specification = column<String>(name = "specification", jdbcType = JDBCType.VARCHAR)

    val unit = column<String>(name = "unit", jdbcType = JDBCType.VARCHAR)

    val weight = column<String>(name = "weight", jdbcType = JDBCType.VARCHAR)

    val listingDate = column<Date>(name = "listing_date", jdbcType = JDBCType.DATE)

    val retailPrice = column<Int>(name = "retail_price", jdbcType = JDBCType.INTEGER)

    val pictureUrl = column<String>(name = "picture_url", jdbcType = JDBCType.VARCHAR)

    val remarks = column<String>(name = "remarks", jdbcType = JDBCType.VARCHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}