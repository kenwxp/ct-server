package com.cloudtimes.account.mapper.provider

import com.cloudtimes.account.dto.request.QueryAgentWithdrawalRequest
import com.cloudtimes.account.table.CtWithdrawalBookTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert

object CtWithdrawalBookProvider {
    private val withdrawalBookTable = CtWithdrawalBookTable();

    fun selectAgentWithdrawList(request: QueryAgentWithdrawalRequest): SelectStatementProvider {
        return select(withdrawalBookTable.allColumns()) {
            from (withdrawalBookTable)
            where { withdrawalBookTable.userId isEqualTo request.userId }
            and { withdrawalBookTable.applyDate isEqualToWhenPresent request.applyDate }
            and { withdrawalBookTable.payState isEqualToWhenPresent request.payState }
            orderBy(withdrawalBookTable.createTime.descending())
        }
    }
}