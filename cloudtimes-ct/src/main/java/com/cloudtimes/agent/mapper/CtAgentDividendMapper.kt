package com.cloudtimes.agent.mapper

import com.cloudtimes.agent.domain.CtAgentDividend
import com.cloudtimes.agent.dto.response.CtAgentDividendDto

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 分润配置Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@Mapper
interface CtAgentDividendMapper
    : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtAgentDividend>, CommonUpdateMapper {
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @Results(
        id = "CtAgentDividendResult", value = [
            Result(column = "id", property = "id", jdbcType = JdbcType.OTHER, id = true),
            Result(column = "user_id", property = "userId", jdbcType = JdbcType.OTHER),
            Result(column = "bill_amount", property = "billAmount", jdbcType = JdbcType.DECIMAL),
            Result(column = "dividend_ratio", property = "dividendRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "tax_ratio", property = "taxRatio", jdbcType = JdbcType.DECIMAL),
            Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.CHAR),
            Result(column = "create_date", property = "createDate", jdbcType = JdbcType.DATE),
            Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
        ]
    )
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentDividend>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    @ResultMap("CtAgentDividendResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentDividend?

    /**
     * 查询分润配置
     *
     * @param id 分润配置主键
     * @return 分润配置
     */
    fun selectCtAgentDividendById(id: String): CtAgentDividend?

    /**
     * 查询分润配置列表
     *
     * @param ctAgentDividend 分润配置
     * @return 分润配置集合
     */
    fun selectCtAgentDividendList(ctAgentDividend: CtAgentDividend): List<CtAgentDividend>

    fun selectCtAgentDividendListPlus(ctAgentDividend: CtAgentDividendDto): List<CtAgentDividendDto>

    /**
     * 新增分润配置
     *
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    fun insertCtAgentDividend(ctAgentDividend: CtAgentDividend): Int

    /**
     * 修改分润配置
     *
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    fun updateCtAgentDividend(ctAgentDividend: CtAgentDividend): Int

    /**
     * 删除分润配置
     *
     * @param id 分润配置主键
     * @return 结果
     */
    fun deleteCtAgentDividendById(id: String): Int

    /**
     * 批量删除分润配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentDividendByIds(ids: Array<String>): Int
}
