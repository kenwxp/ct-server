package com.cloudtimes.supervise.mapper.provide

import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByMonth
import com.cloudtimes.supervise.table.CtOrderTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtOrderProvider {
    private val orderTable = CtOrderTable()
    fun selectMonthlyOrdersStmt(request: QueryOrdersByMonth): SelectStatementProvider {
        return select(orderTable.allColumns()) {
            from(orderTable)
            where { orderTable.yearMonth isEqualTo Integer.parseInt(request.yearMonth) }
            and { orderTable.storeId isEqualTo request.storeId }
            orderBy(orderTable.createTime)
        }
    }
}