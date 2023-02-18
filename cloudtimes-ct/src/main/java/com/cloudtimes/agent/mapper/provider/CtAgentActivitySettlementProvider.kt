package com.cloudtimes.agent.mapper.provider

import com.cloudtimes.agent.table.CtAgentActivitySettlementTable
import com.cloudtimes.common.enums.YesNoState
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import java.util.Date

object CtAgentActivitySettlementProvider {
    private val settlementTable = CtAgentActivitySettlementTable()

    fun selectByKey(ruleId: String, agentId: String):SelectStatementProvider {
        return select(settlementTable.allColumns()) {
            from (settlementTable)
            where { settlementTable.userId isEqualTo  agentId }
            and { settlementTable.activityRuleId isEqualTo ruleId}
        }
    }

    fun updateFulfilledByKey(ruleId: String, agentId: String): UpdateStatementProvider {
        return update(settlementTable) {
            set(settlementTable.isFulfilled) equalTo YesNoState.Yes.code
            set(settlementTable.updateTime) equalTo Date()
            where { settlementTable.userId isEqualTo  agentId }
            and { settlementTable.activityRuleId isEqualTo ruleId}
        }
    }
}