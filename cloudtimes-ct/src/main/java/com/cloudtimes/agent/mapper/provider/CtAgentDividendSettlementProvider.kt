package com.cloudtimes.agent.mapper.provider


import com.cloudtimes.agent.dto.request.AgentDividendRequest
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

import com.cloudtimes.agent.dto.request.StoreDividendRequest
import com.cloudtimes.agent.table.dividendSettlementTable
import com.cloudtimes.common.enums.VerifyState
import com.cloudtimes.common.utils.extension.toInt
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import java.util.Date

object CtAgentDividendSettlementProvider {
    fun selectStoreDividendStmt(request: StoreDividendRequest): SelectStatementProvider  {
        var yearMonth = request.yearMonth.toInt()

        return with(dividendSettlementTable) {
            select(dividendSettlementTable.allColumns()) {
                from(dividendSettlementTable)
                where { storeId isEqualTo request.storeId }
                and { dividendYearMonth isEqualToWhenPresent yearMonth }
                if (! request.verifyState.isNullOrEmpty()) {
                    and { verifyState isEqualTo request.verifyState!! }
                }
                orderBy(dividendYearMonth.descending())
            }
        }
    }

    fun selectAgentDividendDetailStmt(request: AgentDividendRequest): SelectStatementProvider {
        var yearMonth = request.yearMonth.toInt()

        return with(dividendSettlementTable) {
            select(dividendSettlementTable.allColumns()) {
                from(dividendSettlementTable)
                where { storeId isEqualTo request.storeId }
                and { dividendYearMonth isEqualTo  yearMonth!! }
            }
        }
    }

    fun agentApproveDividendStmt(request: AgentDividendRequest): UpdateStatementProvider {
        var yearMonth = request.yearMonth.toInt()

        return with(dividendSettlementTable) {
            update(dividendSettlementTable) {
                set(verifyState) equalTo VerifyState.Agent.code
                set(updateTime) equalTo Date()
                set(agentApprovedTime) equalTo Date()
                where { storeId isEqualTo request.storeId }
                and { dividendYearMonth isEqualTo  yearMonth!! }
            }
        }
    }
}
