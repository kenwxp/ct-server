package com.cloudtimes.account.mapper

import com.cloudtimes.account.domain.CtAgentActivity1

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
 * 代理活动1Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-13
 */
@Mapper
interface CtAgentActivity1Mapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentActivity1>,
    CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivity1Result", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            Result(column = "activity_type", property = "activityType", jdbcType = JdbcType.CHAR),
            Result(column = "store_count", property = "storeCount", jdbcType = JdbcType.INTEGER),
            Result(column = "reward_money", property = "rewardMoney", jdbcType = JdbcType.DECIMAL),
            Result(column = "tax_ratio", property = "taxRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "time_span", property = "timeSpan", jdbcType = JdbcType.INTEGER),
            Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            Result(column = "is_enabled", property = "isEnabled", jdbcType = JdbcType.CHAR),
            Result(column = "state", property = "state", jdbcType = JdbcType.CHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "start_time", property = "startTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "end_time", property = "endTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivity1>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivity1Result")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivity1?

    /**
     * 查询代理活动1
     *
     * @param id 代理活动1主键
     * @return 代理活动1
     */
    fun selectCtAgentActivity1ById(id: String): CtAgentActivity1?

    /**
     * 查询代理活动1列表
     *
     * @param ctAgentActivity1 代理活动1
     * @return 代理活动1集合
     */
    fun selectCtAgentActivity1List(ctAgentActivity1: CtAgentActivity1): List<CtAgentActivity1>

    /**
     * 新增代理活动1
     *
     * @param ctAgentActivity1 代理活动1
     * @return 结果
     */
    fun insertCtAgentActivity1(ctAgentActivity1: CtAgentActivity1): Int

    /**
     * 修改代理活动1
     *
     * @param ctAgentActivity1 代理活动1
     * @return 结果
     */
    fun updateCtAgentActivity1(ctAgentActivity1: CtAgentActivity1): Int

    /**
     * 删除代理活动1
     *
     * @param id 代理活动1主键
     * @return 结果
     */
    fun deleteCtAgentActivity1ById(id: String): Int

    /**
     * 批量删除代理活动1
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentActivity1ByIds(ids: Array<String>): Int
}