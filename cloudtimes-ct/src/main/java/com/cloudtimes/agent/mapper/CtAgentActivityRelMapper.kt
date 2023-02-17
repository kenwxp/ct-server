package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentActivityRel

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper

/**
 * 代理活动关系Mapper接口
 *
 * @author tank
 * @date 2023-02-17
 */
@Mapper
interface CtAgentActivityRelMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentActivityRel>,
    CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentActivityRelResult", value = [
            Result(column = "activity_id", property = "activityId", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "activity_type", property = "activityType", jdbcType = JdbcType.CHAR)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentActivityRel>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentActivityRelResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentActivityRel?

    /**
     * 查询代理活动关系
     *
     * @param activityId 代理活动关系主键
     * @return 代理活动关系
     */
    fun selectCtAgentActivityRelByActivityId(activityId: String): CtAgentActivityRel

    /**
     * 查询代理活动关系列表
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 代理活动关系集合
     */
    fun selectCtAgentActivityRelList(ctAgentActivityRel: CtAgentActivityRel): List<CtAgentActivityRel>

    /**
     * 新增代理活动关系
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 结果
     */
    fun insertCtAgentActivityRel(ctAgentActivityRel: CtAgentActivityRel): Int

    /**
     * 修改代理活动关系
     *
     * @param ctAgentActivityRel 代理活动关系
     * @return 结果
     */
    fun updateCtAgentActivityRel(ctAgentActivityRel: CtAgentActivityRel): Int

    /**
     * 删除代理活动关系
     *
     * @param activityId 代理活动关系主键
     * @return 结果
     */
    fun deleteCtAgentActivityRelByActivityId(activityId: String): Int

    /**
     * 批量删除代理活动关系
     *
     * @param activityIds 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentActivityRelByActivityIds(activityIds: Array<String>): Int
}
