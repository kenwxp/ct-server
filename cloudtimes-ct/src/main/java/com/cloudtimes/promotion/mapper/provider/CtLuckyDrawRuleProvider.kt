package com.cloudtimes.promotion.mapper.provider

import com.cloudtimes.promotion.table.CtLuckyDrawRuleTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider

object CtLuckyDrawRuleProvider {
    private val ruleTable = CtLuckyDrawRuleTable()

    fun findRulesByActivityIdStmt(activityId: String) : SelectStatementProvider {
        return select(ruleTable.allColumns()) {
            from(ruleTable)
            where {
                ruleTable.activityId isEqualTo activityId
            }
        }
    }
}