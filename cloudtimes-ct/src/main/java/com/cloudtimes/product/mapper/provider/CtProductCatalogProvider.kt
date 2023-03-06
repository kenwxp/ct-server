package com.cloudtimes.product.mapper.provider

import com.cloudtimes.common.enums.AgentType
import com.cloudtimes.product.table.CtProductCatalogTable
import com.cloudtimes.thirdpart.dto.request.RcygProductRecord


import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import java.math.BigDecimal

object CtProductCatalogProvider {
    private val oneHundred = BigDecimal("100.00")
    private val catalogTable = CtProductCatalogTable()

    fun findByBarCode(barcode: String): SelectStatementProvider {
        return select(catalogTable.allColumns()) {
            from(catalogTable)
            where {
                catalogTable.barcode isEqualTo barcode
            }
        }
    }

    fun insertRcygProduct(record: RcygProductRecord): GeneralInsertStatementProvider {
        val retailPrice = record.retailPrice.multiply(oneHundred) .toInt()
        val wholesalePrice = record.wholesalePrice.multiply(oneHundred) .toInt()

        return insertInto(catalogTable) {
            set(catalogTable.barcode) toValue record.barcode
            set(catalogTable.specification) toValue record.specification
            set(catalogTable.unit) toValue record.unit
            set(catalogTable.productName) toValue record.productName
            set(catalogTable.retailPrice) toValue retailPrice
            set(catalogTable.wholesalePrice) toValue wholesalePrice
            set(catalogTable.lifeSpanDays) toValue record.lifeSpanDays
        }
    }
}