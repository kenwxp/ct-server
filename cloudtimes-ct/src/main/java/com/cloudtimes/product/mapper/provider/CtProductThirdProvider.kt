package com.cloudtimes.product.mapper.provider

import com.cloudtimes.common.enums.ChannelType
import com.cloudtimes.product.domain.CtProductThird
import com.cloudtimes.product.table.CtProductCatalogTable
import com.cloudtimes.product.table.CtProductThirdTable
import com.cloudtimes.thirdpart.dto.request.RcygProductRecord
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

object CtProductThirdProvider {
    private val thirdTable = CtProductThirdTable()

    fun findByThirdAndBarcode(thirdCode: String, barcode: String): SelectStatementProvider {
        return select(thirdTable.allColumns()) {
            from(thirdTable)
            where { thirdTable.thirdCode isEqualTo thirdCode }
            and { thirdTable.barcode isEqualTo  barcode }
        }
    }

    fun insertThirdProduct(thirdProduct: CtProductThird): GeneralInsertStatementProvider {
        return insertInto(thirdTable) {
            set(thirdTable.thirdCode) toValue thirdProduct.thirdCode!!
            set(thirdTable.thirdProductId) toValue thirdProduct.thirdProductId!!
            set(thirdTable.barcode) toValue thirdProduct.barcode!!

        }
    }
}