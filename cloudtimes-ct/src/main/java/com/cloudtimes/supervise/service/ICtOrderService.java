package com.cloudtimes.supervise.service;

import com.cloudtimes.supervise.domain.CtOrder;

import java.util.List;

/**
 * 购物订单Service接口
 *
 * @author tank
 * @date 2023-02-07
 */
public interface ICtOrderService {
    /**
     * 查询购物订单
     *
     * @param id 购物订单主键
     * @return 购物订单
     */
    public CtOrder selectCtOrderById(String id);

    /**
     * 查询购物订单列表
     *
     * @param ctOrder 购物订单
     * @return 购物订单集合
     */
    public List<CtOrder> selectCtOrderList(CtOrder ctOrder);

    /**
     * 新增购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    public int insertCtOrder(CtOrder ctOrder);

    /**
     * 修改购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    public int updateCtOrder(CtOrder ctOrder);

    /**
     * 批量删除购物订单
     *
     * @param ids 需要删除的购物订单主键集合
     * @return 结果
     */
    public int deleteCtOrderByIds(String[] ids);

    /**
     * 删除购物订单信息
     *
     * @param id 购物订单主键
     * @return 结果
     */
    public int deleteCtOrderById(String id);
}
