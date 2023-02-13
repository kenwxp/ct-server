package com.cloudtimes.account.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date

import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtUserAssetsBookTable : AliasableSqlTable<CtUserAssetsBookTable>("ct_user_assets_book", ::CtUserAssetsBookTable) {
    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 簿记年月 */
    val yearMonth = column<Int>(name = "`year_month`", jdbcType = JDBCType.INTEGER)

    /** 用户编号 */
    val userId = column<String>(name = "user_id", jdbcType = JDBCType.OTHER)

    /** 卡劵编号 */
    val cardId = column<String>(name = "card_id", jdbcType = JDBCType.OTHER)

    /** 簿记类型 */
    val bookType = column<String>(name = "book_type", jdbcType = JDBCType.CHAR)

    /** 操作类型 */
    val operateType = column<String>(name = "operate_type", jdbcType = JDBCType.CHAR)

    /** 资产类型 */
    val assetsType = column<String>(name = "assets_type", jdbcType = JDBCType.CHAR)

    /** 操作前额度 */
    val beforeAmount = column<BigDecimal>(name = "before_amount", jdbcType = JDBCType.DECIMAL)

    /** 操作后额度 */
    val alterAmount = column<BigDecimal>(name = "alter_amount", jdbcType = JDBCType.DECIMAL)

    /** 操作额度/实际到账金额 */
    val amount = column<BigDecimal>(name = "amount", jdbcType = JDBCType.DECIMAL)

    /** 手续费费率 */
    val taxRatio = column<BigDecimal>(name = "tax_ratio", jdbcType = JDBCType.DECIMAL)

    /** 手续费金额 */
    val taxAmount = column<BigDecimal>(name = "tax_amount", jdbcType = JDBCType.DECIMAL)

    /** 税前金额 */
    val beforeTaxAmount = column<BigDecimal>(name = "before_tax_amount", jdbcType = JDBCType.DECIMAL)

    /** 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /** 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    /** 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    /** 备注 */
    val remark = column<String>(name = "remark", jdbcType = JDBCType.LONGVARCHAR)
}

val ctUserAssetsBookTable = CtUserAssetsBookTable()