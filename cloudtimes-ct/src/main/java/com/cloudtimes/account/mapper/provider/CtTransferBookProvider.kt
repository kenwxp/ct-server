package com.cloudtimes.account.mapper.provider

import com.cloudtimes.account.domain.CtTransferBook
import com.cloudtimes.account.table.CtTransferBookTable
import com.cloudtimes.account.table.CtUserTable

import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.select


object CtTransferBookProvider {
    private val userTable1: CtUserTable = CtUserTable().withAlias("u1")
    private val userTable2: CtUserTable = CtUserTable().withAlias("u2")
    private val transferTable: CtTransferBookTable = CtTransferBookTable().withAlias("tsf")

    fun insertOne(row: CtTransferBook) : GeneralInsertStatementProvider {
        return with(transferTable) {
            insertInto(transferTable) {
                set(payer) toValueWhenPresent  row.payer
                set(payee) toValueWhenPresent  row.payee
                set(yearMonth) toValueWhenPresent  row.yearMonth
                set(amount) toValueWhenPresent  row.amount
                set(remark) toValueWhenPresent  row.remark
            }
        }
    }

    fun selectTransferRecordsStmt(userId: String) : SelectStatementProvider {
        return select(
            transferTable.id.`as`("transferId"),
            transferTable.createTime.`as`("transferTime"),
            transferTable.remark.`as`("transferRemark"),
            transferTable.amount.`as`("transferAmount"),

            userTable1.id.`as`("payerUserId"),
            userTable1.nickName.`as`("payerNickName"),
            userTable1.realName.`as`("payerRealName"),
            userTable1.mobile.`as`("payerMobile"),

            userTable2.id.`as`("payeeUserId"),
            userTable2.nickName.`as`("payeeNickName"),
            userTable2.realName.`as`("payeeRealName"),
            userTable2.mobile.`as`("payeeMobile"),
        )  {
            from (transferTable)
            join(userTable1) { on(userTable1.id) equalTo transferTable.payer}
            join(userTable2) { on(userTable2.id) equalTo transferTable.payee}
            where {
                transferTable.payer isEqualTo userId
            }
            // :TODO: mybatis orderBy语句有bug，暂时不按日期倒序
            // orderBy(transferTable.createTime.descending())
        }
    }
}