package com.cloudtimes.product.mapper.provider

import java.util.*

import com.cloudtimes.product.domain.CtShopPurchase
import com.cloudtimes.product.table.CtShopPurchaseTable
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

object CtShopPurchaseProvider {
    private val purchaseTable = CtShopPurchaseTable()

    fun insertNewPurchaseStmt(purchase: CtShopPurchase): GeneralInsertStatementProvider {
        return insertInto(purchaseTable) {
            set(purchaseTable.shopNo) toValue purchase.shopNo!!
            set(purchaseTable.supplier) toValue purchase.supplier!!
            set(purchaseTable.orderingNumber) toValue purchase.orderingNumber!!
            set(purchaseTable.takeTime) toValueWhenPresent purchase.takeTime
        }
    }

    fun selectPurchaseStmt(purchase: CtShopPurchase): SelectStatementProvider {
        return select(purchaseTable.allColumns()) {
            from(purchaseTable)
            where { purchaseTable.shopNo isEqualTo  purchase.shopNo!! }
            where { purchaseTable.supplier isEqualTo  purchase.supplier!! }
            where { purchaseTable.orderingNumber isEqualTo  purchase.orderingNumber!! }
        }
    }
}