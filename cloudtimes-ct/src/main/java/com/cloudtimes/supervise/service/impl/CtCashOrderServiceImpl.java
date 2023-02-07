package com.cloudtimes.supervise.service.impl;

import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.supervise.domain.CtCashOrder;
import com.cloudtimes.supervise.mapper.CtCashOrderMapper;
import com.cloudtimes.supervise.service.ICtCashOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 取现订单Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
public class CtCashOrderServiceImpl implements ICtCashOrderService {
    @Autowired
    private CtCashOrderMapper ctCashOrderMapper;

    /**
     * 查询取现订单
     *
     * @param id 取现订单主键
     * @return 取现订单
     */
    @Override
    public CtCashOrder selectCtCashOrderById(String id) {
        return ctCashOrderMapper.selectCtCashOrderById(id);
    }

    /**
     * 查询取现订单列表
     *
     * @param ctCashOrder 取现订单
     * @return 取现订单
     */
    @Override
    public List<CtCashOrder> selectCtCashOrderList(CtCashOrder ctCashOrder) {
        return ctCashOrderMapper.selectCtCashOrderList(ctCashOrder);
    }

    /**
     * 新增取现订单
     *
     * @param ctCashOrder 取现订单
     * @return 结果
     */
    @Override
    public int insertCtCashOrder(CtCashOrder ctCashOrder) {
        ctCashOrder.setCreateTime(DateUtils.getNowDate());
        return ctCashOrderMapper.insertCtCashOrder(ctCashOrder);
    }

    /**
     * 修改取现订单
     *
     * @param ctCashOrder 取现订单
     * @return 结果
     */
    @Override
    public int updateCtCashOrder(CtCashOrder ctCashOrder) {
        ctCashOrder.setUpdateTime(DateUtils.getNowDate());
        return ctCashOrderMapper.updateCtCashOrder(ctCashOrder);
    }

    /**
     * 批量删除取现订单
     *
     * @param ids 需要删除的取现订单主键
     * @return 结果
     */
    @Override
    public int deleteCtCashOrderByIds(String[] ids) {
        return ctCashOrderMapper.deleteCtCashOrderByIds(ids);
    }

    /**
     * 删除取现订单信息
     *
     * @param id 取现订单主键
     * @return 结果
     */
    @Override
    public int deleteCtCashOrderById(String id) {
        return ctCashOrderMapper.deleteCtCashOrderById(id);
    }
}
