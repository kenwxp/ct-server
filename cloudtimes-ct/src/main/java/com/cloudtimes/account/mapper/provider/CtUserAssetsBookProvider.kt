package com.cloudtimes.account.mapper.provider

import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select

import com.cloudtimes.account.domain.CtUserAssetsBook
import com.cloudtimes.account.dto.request.QueryAssetsBookRequest
import com.cloudtimes.account.table.ctUserAssetsBookTable

object CtUserAssetsBookProvider {
    fun insertOne(row: CtUserAssetsBook): GeneralInsertStatementProvider {
        return with(ctUserAssetsBookTable) {
            insertInto(ctUserAssetsBookTable) {
                set(transferId) toValueWhenPresent  row.transferId
                set(yearMonth) toValueWhenPresent row.yearMonth
                set(userId) toValueWhenPresent row.userId
                set(cardId) toValueWhenPresent row.cardId
                set(bookType) toValueWhenPresent row.bookType
                set(operateType) toValueWhenPresent row.operateType
                set(assetsType) toValueWhenPresent row.assetsType
                set(beforeAmount) toValueWhenPresent row.beforeAmount
                set(alterAmount) toValueWhenPresent row.alterAmount
                set(amount) toValueWhenPresent row.amount
                set(beforeTaxAmount) toValueWhenPresent row.beforeTaxAmount
                set(createDate) toValueWhenPresent row.createDate
                set(createTime) toValueWhenPresent row.createTime
                set(updateTime) toValueWhenPresent row.updateTime
                set(delFlag) toValueWhenPresent row.delFlag
                set(remark) toValueWhenPresent row.remark
            }
        }
    }

    fun selectByPrimaryKey(id_: String, yearMonth_: Int? = null): SelectStatementProvider {
        return with(ctUserAssetsBookTable) {
            select(ctUserAssetsBookTable.allColumns()) {
                from(ctUserAssetsBookTable)
                where {
                    id isEqualTo id_
                    yearMonth isEqualToWhenPresent yearMonth_
                }
            }
        }
    }

    /** 按用户查询资产登记簿 */
    fun selectByUserStmt(request: QueryAssetsBookRequest): SelectStatementProvider {
        return with(ctUserAssetsBookTable) {
            select(ctUserAssetsBookTable.allColumns()) {
                from(ctUserAssetsBookTable)
                where { userId isEqualTo request.userId }
                and {createDate isEqualToWhenPresent request.createDate}
                and { bookType isEqualToWhenPresent request.bookType}
                orderBy(createTime.descending())
            }
        }
    }

    fun selectMany(row: CtUserAssetsBook): SelectStatementProvider {
        return with(ctUserAssetsBookTable) {
            select(ctUserAssetsBookTable.allColumns()) {
                from(ctUserAssetsBookTable)
                where {
                    id isEqualToWhenPresent row.id
                    yearMonth isEqualToWhenPresent row.yearMonth
                    userId isEqualToWhenPresent row.userId
                    cardId isEqualToWhenPresent row.cardId
                    bookType isEqualToWhenPresent row.bookType
                    operateType isEqualToWhenPresent row.operateType
                    assetsType isEqualToWhenPresent row.assetsType
                    beforeAmount isEqualToWhenPresent row.beforeAmount
                    alterAmount isEqualToWhenPresent row.alterAmount
                    amount isEqualToWhenPresent row.amount
                    createDate isEqualToWhenPresent row.createDate
                    createTime isEqualToWhenPresent row.createTime
                    updateTime isEqualToWhenPresent row.updateTime
                    delFlag isEqualToWhenPresent row.delFlag
                    remark isEqualToWhenPresent row.remark
                }
            }
        }
    }
}