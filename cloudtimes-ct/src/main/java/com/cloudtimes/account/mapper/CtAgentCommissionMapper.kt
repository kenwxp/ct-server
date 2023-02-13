package com.cloudtimes.account.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

import com.cloudtimes.account.domain.CtAgentCommission

/**
 * 代理销售佣金设置Mapper接口
 *
 * @author 沈兵
 * @date 2023-02-03
 */
@Mapper
interface CtAgentCommissionMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CtAgentCommissionResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.OTHER, id=true),
        Result(column="user_id", property="userId", jdbcType=JdbcType.OTHER),
        Result(column="parent_user_id", property="parentUserId", jdbcType=JdbcType.OTHER),
        Result(column="cost_price", property="costPrice", jdbcType=JdbcType.DECIMAL),
        Result(column="tax_ratio", property="taxRatio", jdbcType=JdbcType.DECIMAL),
        Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        Result(column="del_flag", property="delFlag", jdbcType=JdbcType.CHAR),
        Result(column="create_date", property="createDate", jdbcType=JdbcType.DATE),
        Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<CtAgentCommission>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CtAgentCommissionResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtAgentCommission?

    /**
     * 查询代理销售佣金设置
     *
     * @param id 代理销售佣金设置主键
     * @return 代理销售佣金设置
     */
    fun selectCtAgentCommissionById(id: String): CtAgentCommission?

    /**
     * 查询代理销售佣金设置列表
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 代理销售佣金设置集合
     */
    fun selectCtAgentCommissionList(ctAgentCommission: CtAgentCommission): List<CtAgentCommission>

    /**
     * 新增代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    fun insertCtAgentCommission(ctAgentCommission: CtAgentCommission): Int

    /**
     * 修改代理销售佣金设置
     *
     * @param ctAgentCommission 代理销售佣金设置
     * @return 结果
     */
    fun updateCtAgentCommission(ctAgentCommission: CtAgentCommission): Int

    /**
     * 删除代理销售佣金设置
     *
     * @param id 代理销售佣金设置主键
     * @return 结果
     */
    fun deleteCtAgentCommissionById(id: String): Int

    /**
     * 批量删除代理销售佣金设置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtAgentCommissionByIds(ids: Array<String>): Int
}