package com.cloudtimes.product.mapper.provider

import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.common.enums.YesNoState
import com.cloudtimes.hardwaredevice.table.CtStoreTable
import com.cloudtimes.product.domain.CtProductThird
import com.cloudtimes.product.domain.CtShopProduct
import com.cloudtimes.product.table.CtProductThirdTable
import com.cloudtimes.product.table.CtShopProductTable

import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.elements.add
import org.mybatis.dynamic.sql.util.kotlin.elements.constant
import org.mybatis.dynamic.sql.util.kotlin.elements.subtract
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import java.math.BigDecimal

object CtShopProductProvider {
    private val shopProductTable = CtShopProductTable()
    private val storeTable = CtStoreTable()
    private val thirdProductTable = CtProductThirdTable()

    fun insertNewProductStmt(product: CtShopProduct): GeneralInsertStatementProvider {
        return insertInto(shopProductTable) {
            set(shopProductTable.shopNo) toValue product.shopNo!!
            set(shopProductTable.barcode) toValue product.barcode!!
            set(shopProductTable.productName) toValue product.productName!!
            set(shopProductTable.purchasePrice) toValueWhenPresent  product.purchasePrice
            set(shopProductTable.retailPrice) toValueWhenPresent  product.retailPrice
            set(shopProductTable.isEnable) toValue YesNoState.Yes.code
        }
    }

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

    fun findExistBarcodesStmt(shopNo: String, candidateBarCodes: List<String>): SelectStatementProvider {
        return select(shopProductTable.barcode) {
            from(shopProductTable)
            where { shopProductTable.shopNo isEqualTo shopNo  }
            and { shopProductTable.barcode isIn candidateBarCodes}
        }
    }

    fun updatePurchaseProductStmt(shopProduct: CtShopProduct): UpdateStatementProvider {
        val incrementStock = constant<Int>(shopProduct.stockDelta.toString())

        return update(shopProductTable) {
            set(shopProductTable.totalSupplied) equalTo add(shopProductTable.totalSupplied, incrementStock)
            set(shopProductTable.stock) equalTo add(shopProductTable.stock, incrementStock)
            where { shopProductTable.shopNo isEqualTo shopProduct.shopNo!!  }
            and { shopProductTable.barcode isEqualTo shopProduct.barcode!!  }
        }
    }
}