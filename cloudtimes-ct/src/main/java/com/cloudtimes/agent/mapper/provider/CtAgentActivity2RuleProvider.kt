package com.cloudtimes.agent.mapper.provider

import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

import com.cloudtimes.agent.dto.request.ActivityDetailRequest
import com.cloudtimes.agent.table.CtAgentActivity2RuleTable

object CtAgentActivity2RuleProvider {
    private val rule2Table = CtAgentActivity2RuleTable().withAlias("rule2")
    fun selectAgentActivityDetailStmt(request: ActivityDetailRequest): SelectStatementProvider {
        return select(rule2Table.allColumns()) {
            from(rule2Table)
            where { rule2Table.activityId isEqualTo request.activityId!! }
        }
    }
}