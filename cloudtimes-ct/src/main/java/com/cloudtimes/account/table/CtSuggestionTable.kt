package com.cloudtimes.account.table

import java.lang.Object
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtSuggestionTable : AliasableSqlTable<CtSuggestionTable>("ct_suggestion", ::CtSuggestionTable) {
    /** 编号 */
    val id = column<Object>(name = "id", jdbcType = JDBCType.OTHER)

    /** 投诉人 */
    val userId = column<Object>(name = "user_id", jdbcType = JDBCType.OTHER)

    /** 联系方式(页面采集) */
    val contact = column<String>(name = "contact", jdbcType = JDBCType.VARCHAR)

    /** 投诉内容 */
    val content = column<String>(name = "content", jdbcType = JDBCType.VARCHAR)

    /** 处理员工工号 */
    val replyAccNo = column<String>(name = "reply_acc_no", jdbcType = JDBCType.VARCHAR)

    /** 处理时间 */
    val replyTime = column<Date>(name = "reply_time", jdbcType = JDBCType.TIMESTAMP)

    /** 处理批复 */
    val replyRemark = column<String>(name = "reply_remark", jdbcType = JDBCType.VARCHAR)

    /** 投诉渠道 */
    val channelType = column<String>(name = "channel_type", jdbcType = JDBCType.VARCHAR)

    /** 投诉/建议类型 */
    val suggestionType = column<String>(name = "suggestion_type", jdbcType = JDBCType.CHAR)

    /** 处理状态 */
    val processState = column<String>(name = "process_state", jdbcType = JDBCType.CHAR)

    /** 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /** 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)
}

val suggessionTable = CtSuggestionTable()