package com.cloudtimes.supervise.service.impl

import com.cloudtimes.agent.dto.response.OrderMonthlyStats
import com.cloudtimes.agent.dto.response.StoreOrderDetail
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByDate
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByMonth
import com.cloudtimes.supervise.domain.CtOrder
import com.cloudtimes.supervise.mapper.CtOrderMapper
import com.cloudtimes.supervise.mapper.provide.CtOrderProvider
import com.cloudtimes.supervise.service.ICtOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 购物订单Service业务层处理
 *
 * @author tank
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
class CtOrderServiceImpl : ICtOrderService {
    @Autowired
    private lateinit var orderMapper: CtOrderMapper

    /** 查询门店每月订单 */
    override fun selectMonthlyOrderStats(request: QueryOrdersByMonth): List<OrderMonthlyStats> {
        return orderMapper.selectMonthlyOrderStats(CtOrderProvider.selectMonthlyOrderStatsStmt(request))
            .filter { it.tranDate != null}
    }

    /** 查询门店每日订单 */
    override fun selectDailyOrders(request: QueryOrdersByDate): List<StoreOrderDetail> {
        return orderMapper.selectAgentStoreOrders(CtOrderProvider.selectOrdersByDateStmt(request))
    }

    /**
     * 查询购物订单
     *
     * @param id 购物订单主键
     * @return 购物订单
     */
    override fun selectCtOrderById(id: String): CtOrder? {
        return orderMapper.selectCtOrderById(id)
    }

    /**
     * 查询购物订单列表
     *
     * @param ctOrder 购物订单
     * @return 购物订单
     */
    override fun selectCtOrderList(ctOrder: CtOrder): List<CtOrder> {
        return orderMapper.selectCtOrderList(ctOrder)
    }

    /**
     * 新增购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    override fun insertCtOrder(ctOrder: CtOrder): Int {
        ctOrder.createTime = DateUtils.getNowDate()
        return orderMapper.insertCtOrder(ctOrder)
    }

    /**
     * 修改购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    override fun updateCtOrder(ctOrder: CtOrder): Int {
        ctOrder.updateTime = DateUtils.getNowDate()
        return orderMapper.updateCtOrder(ctOrder)
    }

    /**
     * 批量删除购物订单
     *
     * @param ids 需要删除的购物订单主键
     * @return 结果
     */
    override fun deleteCtOrderByIds(ids: Array<String>): Int {
        return orderMapper.deleteCtOrderByIds(ids)
    }

    /**
     * 删除购物订单信息
     *
     * @param id 购物订单主键
     * @return 结果
     */
    override fun deleteCtOrderById(id: String): Int {
        return orderMapper.deleteCtOrderById(id)
    }
}