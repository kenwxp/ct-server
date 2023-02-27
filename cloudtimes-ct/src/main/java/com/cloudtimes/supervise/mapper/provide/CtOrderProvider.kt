package com.cloudtimes.supervise.mapper.provide

import com.cloudtimes.common.enums.OrderState
import com.cloudtimes.common.utils.extension.toYearMonth
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByDate
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByMonth
import com.cloudtimes.supervise.table.CtOrderTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.select.aggregate.Count;
import org.mybatis.dynamic.sql.select.aggregate.Sum;

object CtOrderProvider {
    private val orderTable = CtOrderTable()
    fun selectMonthlyOrderStatsStmt(request: QueryOrdersByMonth): SelectStatementProvider {
        return select(
            orderTable.createDate.`as`("tran_date"),
            Count.of(orderTable.id).`as`("total_count"),
            Sum.of(orderTable.actualAmount).`as`("total_amount"),
        ) {
            from(orderTable)
            where { orderTable.yearMonth isEqualTo request.yearMonth.toInt()}
            and { orderTable.storeId isEqualTo request.storeId }
            and { orderTable.state isEqualTo OrderState.Success.code }
            groupBy(orderTable.createDate)
            orderBy(orderTable.createDate)
        }
    }

    fun selectOrdersByDateStmt(request: QueryOrdersByDate): SelectStatementProvider {
        return select(
            orderTable.id,
            orderTable.actualAmount,
            orderTable.createDate.`as`("tran_date"),
            orderTable.createTime,
            orderTable.paymentCode
        ) {
            from(orderTable)
            where { orderTable.yearMonth isEqualTo request.tranDate.toYearMonth() }
            and { orderTable.storeId isEqualTo request.storeId }
            and { orderTable.createDate isEqualTo request.tranDate }
            and { orderTable.state isEqualTo OrderState.Success.code }
            orderBy(orderTable.createTime)
        }
    }
}