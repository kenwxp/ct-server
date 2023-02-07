package com.cloudtimes.supervise.service;

import com.cloudtimes.supervise.domain.CtCashOrder;

import java.util.List;

/**
 * 取现订单Service接口
 * 
 * @author wangxp
 * @date 2023-02-07
 */
public interface ICtCashOrderService 
{
    /**
     * 查询取现订单
     * 
     * @param id 取现订单主键
     * @return 取现订单
     */
    public CtCashOrder selectCtCashOrderById(String id);

    /**
     * 查询取现订单列表
     * 
     * @param ctCashOrder 取现订单
     * @return 取现订单集合
     */
    public List<CtCashOrder> selectCtCashOrderList(CtCashOrder ctCashOrder);

    /**
     * 新增取现订单
     * 
     * @param ctCashOrder 取现订单
     * @return 结果
     */
    public int insertCtCashOrder(CtCashOrder ctCashOrder);

    /**
     * 修改取现订单
     * 
     * @param ctCashOrder 取现订单
     * @return 结果
     */
    public int updateCtCashOrder(CtCashOrder ctCashOrder);

    /**
     * 批量删除取现订单
     * 
     * @param ids 需要删除的取现订单主键集合
     * @return 结果
     */
    public int deleteCtCashOrderByIds(String[] ids);

    /**
     * 删除取现订单信息
     * 
     * @param id 取现订单主键
     * @return 结果
     */
    public int deleteCtCashOrderById(String id);
}
