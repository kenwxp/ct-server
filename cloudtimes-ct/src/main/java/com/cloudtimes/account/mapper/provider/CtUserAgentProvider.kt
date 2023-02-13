package com.cloudtimes.account.mapper.provider

import com.cloudtimes.account.domain.CtUserAgent
import com.cloudtimes.account.table.ctUserAgentTable
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import java.util.Date

object CtUserAgentProvider {
    fun selectById(id: String): SelectStatementProvider {
        return with(ctUserAgentTable) {
            select(ctUserAgentTable.allColumns()) {
                from(ctUserAgentTable)
                where {
                    userId isEqualTo id
                }
            }
        }
    }

    fun selectByIds(ids: List<String>): SelectStatementProvider {
        return with(ctUserAgentTable) {
            select(ctUserAgentTable.allColumns()) {
                from(ctUserAgentTable)
                where {
                    userId isIn ids
                }
            }
        }
    }

    /** 更新代理现金余额语句 */
    fun updateAgentCashStmt(row: CtUserAgent): UpdateStatementProvider {
        return with(ctUserAgentTable) {
            update(ctUserAgentTable) {
                set(cashAmount).equalTo(row.cashAmount!!)
                set(updateTime).equalTo(Date())
                where {
                    userId isEqualTo row.userId!!
                }
            }
        }
    }
}