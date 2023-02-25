package com.cloudtimes.supervise.mapper

import com.cloudtimes.supervise.domain.CtOrder
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.SelectProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 购物订单Mapper接口
 *
 * @author wangxp
 * @date 2023-02-07
 */
interface CtOrderMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtOrder>, CommonUpdateMapper
{
    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    fun selectMany(selectStatement: SelectStatementProvider): List<CtOrder>

    @SelectProvider(type = SqlProviderAdapter::class, method = "select")
    fun selectOne(selectStatement: SelectStatementProvider): CtOrder?

    /**
     * 查询购物订单
     *
     * @param id 购物订单主键
     * @return 购物订单
     */
    fun selectCtOrderById(id: String): CtOrder?

    /**
     * 查询购物订单列表
     *
     * @param ctOrder 购物订单
     * @return 购物订单集合
     */
    fun selectCtOrderList(ctOrder: CtOrder): List<CtOrder>

    /**
     * 新增购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    fun insertCtOrder(ctOrder: CtOrder): Int

    /**
     * 修改购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    fun updateCtOrder(ctOrder: CtOrder): Int

    /**
     * 删除购物订单
     *
     * @param id 购物订单主键
     * @return 结果
     */
    fun deleteCtOrderById(id: String): Int

    /**
     * 批量删除购物订单
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtOrderByIds(ids: Array<String>): Int

    /**
     * 根据任务id或状态查询订单信息
     *
     * @param state
     * @return
     */
    fun selectCtOrderListByTask(@Param("taskId") taskId: String, @Param("state") state: String): List<CtOrder>
}