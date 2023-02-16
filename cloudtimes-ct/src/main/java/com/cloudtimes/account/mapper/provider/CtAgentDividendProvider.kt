package com.cloudtimes.account.mapper.provider

import com.cloudtimes.account.domain.CtAgentDividend
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import com.cloudtimes.account.table.dividendTable
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import java.util.Date

object CtAgentDividendProvider {
    /** 查询代理配置参数 */
    fun selectManyByUserId(userId: String): SelectStatementProvider {
        return select(dividendTable.allColumns()) {
            from(dividendTable)
            where {
                dividendTable.userId isEqualTo userId
            }
            orderBy(dividendTable.billAmount)
        }
    }

    /** 根据上级代理插入下级代理配置参数 */
    fun insertOneWithParentConfig(parentDividend: CtAgentDividend, childUserId: String): GeneralInsertStatementProvider {
            return with(dividendTable) {
                insertInto(dividendTable) {
                    set(userId) toValue childUserId
                    set(parentUserId) toValue parentDividend.userId!!
                    set(compareType) toValue parentDividend.compareType!!
                    set(billAmount) toValue parentDividend.billAmount!!
                    set(taxRatio) toValue parentDividend.taxRatio!!
                }
        }
    }

    /** 更新代理分润比率 */
    fun updateDividendRatio(row: CtAgentDividend): UpdateStatementProvider {
        return with(dividendTable) {
            update(dividendTable) {
                set(dividendRatio) equalTo row.dividendRatio!!
                set(updateTime) equalTo Date()
                where { userId isEqualTo row.userId!! }
            }
        }
    }
}