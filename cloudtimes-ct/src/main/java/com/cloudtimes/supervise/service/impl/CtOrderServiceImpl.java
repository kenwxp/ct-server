package com.cloudtimes.supervise.service.impl;

import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.service.ICtOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物订单Service业务层处理
 *
 * @author tank
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
public class CtOrderServiceImpl implements ICtOrderService {
    @Autowired
    private CtOrderMapper ctOrderMapper;

    /**
     * 查询购物订单
     *
     * @param id 购物订单主键
     * @return 购物订单
     */
    @Override
    public CtOrder selectCtOrderById(String id) {
        return ctOrderMapper.selectCtOrderById(id);
    }

    /**
     * 查询购物订单列表
     *
     * @param ctOrder 购物订单
     * @return 购物订单
     */
    @Override
    public List<CtOrder> selectCtOrderList(CtOrder ctOrder) {
        return ctOrderMapper.selectCtOrderList(ctOrder);
    }

    /**
     * 新增购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    @Override
    public int insertCtOrder(CtOrder ctOrder) {
        ctOrder.setCreateTime(DateUtils.getNowDate());
        return ctOrderMapper.insertCtOrder(ctOrder);
    }

    /**
     * 修改购物订单
     *
     * @param ctOrder 购物订单
     * @return 结果
     */
    @Override
    public int updateCtOrder(CtOrder ctOrder) {
        ctOrder.setUpdateTime(DateUtils.getNowDate());
        return ctOrderMapper.updateCtOrder(ctOrder);
    }

    /**
     * 批量删除购物订单
     *
     * @param ids 需要删除的购物订单主键
     * @return 结果
     */
    @Override
    public int deleteCtOrderByIds(String[] ids) {
        return ctOrderMapper.deleteCtOrderByIds(ids);
    }

    /**
     * 删除购物订单信息
     *
     * @param id 购物订单主键
     * @return 结果
     */
    @Override
    public int deleteCtOrderById(String id) {
        return ctOrderMapper.deleteCtOrderById(id);
    }
}
