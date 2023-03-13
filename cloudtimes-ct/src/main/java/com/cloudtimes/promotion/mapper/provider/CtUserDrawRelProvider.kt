package com.cloudtimes.promotion.mapper.provider

import com.cloudtimes.promotion.domain.CtUserDrawRel
import com.cloudtimes.promotion.table.CtLuckyDrawRuleTable
import com.cloudtimes.promotion.table.CtUserDrawRelTable
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto

object CtUserDrawRelProvider {
    private val ruleTable = CtLuckyDrawRuleTable()
    private val drawTable = CtUserDrawRelTable()

    fun findDrawRuleByIdStmt(activityId: String, userId: String): SelectStatementProvider {
        return select(ruleTable.allColumns()) {
            from(ruleTable)
            join(drawTable) {
                on(ruleTable.id) equalTo drawTable.drawRuleId
                and(ruleTable.activityId) equalTo drawTable.activityId
            }
            where { drawTable.activityId isEqualTo activityId }
            and { drawTable.userId isEqualTo userId}
        }
    }

    fun findDrawByIdStmt(activityId: String, userId: String): SelectStatementProvider {
        return select(drawTable.allColumns()) {
            from(drawTable)
            join(ruleTable) {
                on(ruleTable.id) equalTo drawTable.drawRuleId
                and(ruleTable.activityId) equalTo drawTable.activityId
            }
            where { drawTable.activityId isEqualTo activityId }
            and { drawTable.userId isEqualTo userId}
        }
    }

    fun newDrawStmt(draw: CtUserDrawRel): GeneralInsertStatementProvider {
        return insertInto(drawTable) {
            set(drawTable.activityId) toValueWhenPresent draw.activityId
            set(drawTable.drawRuleId) toValueWhenPresent draw.drawRuleId
            set(drawTable.userId) toValueWhenPresent draw.userId
        }
    }
}