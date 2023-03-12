package com.cloudtimes.product.table

import java.sql.JDBCType
import java.util.*
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtShopProductTable : AliasableSqlTable<CtShopProductTable>("ct_shop_product", ::CtShopProductTable) {
    val id = column<Object>(name = "id", jdbcType = JDBCType.OTHER)

    val shopNo = column<String>(name = "shop_no", jdbcType = JDBCType.VARCHAR)

    val categoryCode = column<Object>(name = "category_code", jdbcType = JDBCType.OTHER)

    val barcode = column<String>(name = "barcode", jdbcType = JDBCType.VARCHAR)

    val productName = column<String>(name = "product_name", jdbcType = JDBCType.VARCHAR)

    val englishName = column<String>(name = "english_name", jdbcType = JDBCType.VARCHAR)

    val brand = column<String>(name = "brand", jdbcType = JDBCType.VARCHAR)

    val label = column<String>(name = "label", jdbcType = JDBCType.VARCHAR)

    val supplier = column<String>(name = "supplier", jdbcType = JDBCType.VARCHAR)

    val specification = column<String>(name = "specification", jdbcType = JDBCType.VARCHAR)

    val unit = column<String>(name = "unit", jdbcType = JDBCType.VARCHAR)

    val weight = column<String>(name = "weight", jdbcType = JDBCType.VARCHAR)

    val color = column<String>(name = "color", jdbcType = JDBCType.VARCHAR)

    val size = column<String>(name = "size", jdbcType = JDBCType.VARCHAR)

    val style = column<String>(name = "style", jdbcType = JDBCType.VARCHAR)

    val purchasePrice = column<Long>(name = "purchase_price", jdbcType = JDBCType.INTEGER)

    val retailPrice = column<Long>(name = "retail_price", jdbcType = JDBCType.INTEGER)

    val wholesalePrice = column<Long>(name = "wholesale_price", jdbcType = JDBCType.INTEGER)

    val vipPrice = column<Long>(name = "vip_price", jdbcType = JDBCType.INTEGER)

    val stock = column<Long>(name = "stock", jdbcType = JDBCType.INTEGER)

    val maxStock = column<Long>(name = "max_stock", jdbcType = JDBCType.INTEGER)

    val minStock = column<Long>(name = "min_stock", jdbcType = JDBCType.INTEGER)

    val totalSold = column<Long>(name = "total_sold", jdbcType = JDBCType.INTEGER)

    val totalSupplied = column<Long>(name = "total_supplied", jdbcType = JDBCType.INTEGER)

    val pictureUrl = column<String>(name = "picture_url", jdbcType = JDBCType.VARCHAR)

    val productionDate = column<Date>(name = "production_date", jdbcType = JDBCType.DATE)

    val qualityPeriod = column<String>(name = "quality_period", jdbcType = JDBCType.VARCHAR)

    val remarks = column<String>(name = "remarks", jdbcType = JDBCType.VARCHAR)

    val isEnable = column<String>(name = "is_enable", jdbcType = JDBCType.CHAR)

    val isEnableVipPrice = column<String>(name = "is_enable_vip_price", jdbcType = JDBCType.CHAR)

    val isEnablePoints = column<String>(name = "is_enable_points", jdbcType = JDBCType.CHAR)

    val isDeleted = column<String>(name = "is_deleted", jdbcType = JDBCType.CHAR)

    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}