package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtSuggestion

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 投诉建议Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-14
 */
@Mapper
interface CtSuggestionMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtSuggestion>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CtSuggestionResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.OTHER, id=true),
        Result(column="user_id", property="userId", jdbcType=JdbcType.OTHER),
        Result(column="contact", property="contact", jdbcType=JdbcType.VARCHAR),
        Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        Result(column="reply_acc_no", property="replyAccNo", jdbcType=JdbcType.VARCHAR),
        Result(column="reply_time", property="replyTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="reply_remark", property="replyRemark", jdbcType=JdbcType.VARCHAR),
        Result(column="channel_type", property="channelType", jdbcType=JdbcType.VARCHAR),
        Result(column="suggestion_type", property="suggestionType", jdbcType=JdbcType.CHAR),
        Result(column="process_state", property="processState", jdbcType=JdbcType.CHAR),
        Result(column="create_date", property="createDate", jdbcType=JdbcType.DATE),
        Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<CtSuggestion>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CtSuggestionResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtSuggestion?

    /**
     * 查询投诉建议
     *
     * @param id 投诉建议主键
     * @return 投诉建议
     */
    fun selectCtSuggestionById(id: String): CtSuggestion?

    /**
     * 查询投诉建议列表
     *
     * @param ctSuggestion 投诉建议
     * @return 投诉建议集合
     */
    fun selectCtSuggestionList(ctSuggestion: CtSuggestion): List<CtSuggestion>

    /**
     * 新增投诉建议
     *
     * @param ctSuggestion 投诉建议
     * @return 结果
     */
    fun insertCtSuggestion(ctSuggestion: CtSuggestion): Int

    /**
     * 修改投诉建议
     *
     * @param ctSuggestion 投诉建议
     * @return 结果
     */
    fun updateCtSuggestion(ctSuggestion: CtSuggestion): Int
}