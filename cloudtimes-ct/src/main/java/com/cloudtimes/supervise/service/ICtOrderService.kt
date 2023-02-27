package com.cloudtimes.supervise.service

import com.cloudtimes.agent.dto.response.OrderMonthlyStats
import com.cloudtimes.agent.dto.response.StoreOrderDetail
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByDate
import com.cloudtimes.hardwaredevice.dto.request.QueryOrdersByMonth
import com.cloudtimes.supervise.domain.CtOrder

/**
 * 购物订单Service接口
 *
 * @author tank
 * @date 2023-02-07
 */
interface ICtOrderService {
    /** 查询门店每月订单统计 */
    fun selectMonthlyOrderStats(request: QueryOrdersByMonth): List<OrderMonthlyStats>

    /** 查询门店每日订单 */
    fun selectDailyOrders(request: QueryOrdersByDate): List<StoreOrderDetail>

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
     * 批量删除购物订单
     *
     * @param ids 需要删除的购物订单主键集合
     * @return 结果
     */
    fun deleteCtOrderByIds(ids: Array<String>): Int

    /**
     * 删除购物订单信息
     *
     * @param id 购物订单主键
     * @return 结果
     */
    fun deleteCtOrderById(id: String): Int
}