package com.cloudtimes.product.mapper.provider

import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.hardwaredevice.table.CtStoreTable
import com.cloudtimes.product.domain.CtProductThird
import com.cloudtimes.product.table.CtProductThirdTable
import com.cloudtimes.product.table.CtShopProductTable

import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.elements.subtract
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto

object CtShopProductProvider {
    private val shopProductTable = CtShopProductTable()
    private val storeTable = CtStoreTable()
    private val thirdProductTable = CtProductThirdTable()

    fun findSuggestProductsStmt(customerId: String): SelectStatementProvider {
        return select(
            thirdProductTable.thirdProductId,
            subtract(shopProductTable.totalSold, shopProductTable.totalSupplied).`as`("suggestion_count")
        ) {
            from(shopProductTable)
            join(thirdProductTable) {
                on(thirdProductTable.barcode) equalTo shopProductTable.barcode
            }
            where {
                shopProductTable.shopNo isEqualTo {
                    select(storeTable.storeNo) {
                        from(storeTable)
                        where { storeTable.rcygStorePhone isEqualTo customerId }
                    }
                }
            }
            and { thirdProductTable.thirdCode isEqualTo ChannelType.THIRD_RCYG.code }
            and { subtract(shopProductTable.totalSold, shopProductTable.totalSupplied) isGreaterThan 0 }
        }
    }
}