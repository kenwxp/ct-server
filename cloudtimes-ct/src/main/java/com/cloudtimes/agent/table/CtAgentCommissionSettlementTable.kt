package com.cloudtimes.agent.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtAgentCommissionSettlementTable : AliasableSqlTable<CtAgentCommissionSettlementTable>(
    "ct_agent_commission_settlement",
    ::CtAgentCommissionSettlementTable
) {
    /* 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /* 门店编号 */
    val storeId = column<String>(name = "store_id", jdbcType = JDBCType.OTHER)

    /* 代理会员编号(开发门店者) */
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    /* 上级代理编号 */
    val parentUserId = column<String>(name = "parent_user_id", jdbcType = JDBCType.OTHER)

    /* 开店费用/合同价格 */
    val saleAmount = column<BigDecimal>(name = "sale_amount", jdbcType = JDBCType.DECIMAL)

    /* 下级成本价格 */
    val costPrice = column<BigDecimal>(name = "cost_price", jdbcType = JDBCType.DECIMAL)

    /* 上级成本价格 */
    val parentCostPrice = column<BigDecimal>(name = "parent_cost_price", jdbcType = JDBCType.DECIMAL)

    /** 手续费费率 */
    val taxRatio = column<BigDecimal>(name = "tax_ratio", jdbcType = JDBCType.DECIMAL)

    /** 结算金额 */
    val amount = column<BigDecimal>(name = "amount", jdbcType = JDBCType.DECIMAL)

    /** 手续费金额 */
    val taxAmount = column<BigDecimal>(name = "tax_amount", jdbcType = JDBCType.DECIMAL)

    /** 稅前结算金额 */
    val beforeTaxAmount = column<BigDecimal>(name = "before_tax_amount", jdbcType = JDBCType.DECIMAL)

    /** 上级结算金额 */
    val parentAmount = column<BigDecimal>(name = "parent_amount", jdbcType = JDBCType.DECIMAL)

    /** 上级手续费金额 */
    val parentTaxAmount = column<BigDecimal>(name = "parent_tax_amount", jdbcType = JDBCType.DECIMAL)

    /** 上级稅前结算金额 */
    val parentBeforeTaxAmount = column<BigDecimal>(name = "parent_before_tax_amount", jdbcType = JDBCType.DECIMAL)

    /* 店铺是否上线 */
    val isStoreOnline = column<String>(name = "is_store_online", jdbcType = JDBCType.CHAR)

    /* 代理是否审核 */
    val isAgentOk = column<String>(name = "is_agent_ok", jdbcType = JDBCType.CHAR)

    /* 平台是否审核 */
    val isPlatformOk = column<String>(name = "is_platform_ok", jdbcType = JDBCType.CHAR)

    /* 结算状态 */
    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    /* 备注 */
    val remark = column<String>(name = "remark", jdbcType = JDBCType.VARCHAR)

    /* 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    /* 平台审核时间 */
    val platformApprovedTime = column<Date>(name = "platform_approved_time", jdbcType = JDBCType.TIMESTAMP)

    /* 代理审核时间 */
    val agentApprovedTime = column<Date>(name = "agent_approved_time", jdbcType = JDBCType.TIMESTAMP)

    /* 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /* 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /* 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}

val commissionSettlementTable = CtAgentCommissionSettlementTable()
