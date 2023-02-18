package com.cloudtimes.agent.mapper.provider

import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

import com.cloudtimes.agent.dto.request.ActivityDetailRequest
import com.cloudtimes.agent.table.CtAgentActivity1RuleTable
import com.cloudtimes.agent.table.CtAgentActivitySettlementTable

object CtAgentActivity1RuleProvider {
    private val rule1Table = CtAgentActivity1RuleTable().withAlias("rule1")
    private val settlementTable = CtAgentActivitySettlementTable().withAlias("st")

    fun selectAgentActivityDetailStmt(request: ActivityDetailRequest):  SelectStatementProvider {
       return select(
           rule1Table.allColumns(),
           // 结决算信息
           settlementTable.isAgentOk,
           settlementTable.isPlatformOk,
           settlementTable.isFulfilled,
           settlementTable.state.`as`("settlement_state"),
       ) {
           from (rule1Table)
           leftJoin(settlementTable) {
               on(settlementTable.activityRuleId) equalTo rule1Table.id
           }
           where { rule1Table.activityId isEqualTo  request.activityId!! }
           and {
               settlementTable.userId isEqualTo request.userId!!
               or { settlementTable.userId.isNull() }
           }
       }
    }
}