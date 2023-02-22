package com.cloudtimes.supervise.service.impl;


import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.hardwaredevice.domain.CtPayment;
import com.cloudtimes.hardwaredevice.mapper.CtPaymentMapper;
import com.cloudtimes.hardwaredevice.service.ICtPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;

import java.util.List;


/**
 * 支付渠道Service业务层处理
 * 
 * @author tank
 * @date 2023-02-22
 */
@DataSource(DataSourceType.CT)
@Service
public class CtPaymentServiceImpl implements ICtPaymentService
{
    @Autowired
    private CtPaymentMapper ctPaymentMapper;

    /**
     * 查询支付渠道
     * 
     * @param id 支付渠道主键
     * @return 支付渠道
     */
    @Override
    public CtPayment selectCtPaymentById(String id)
    {
        return ctPaymentMapper.selectCtPaymentById(id);
    }

    /**
     * 查询支付渠道列表
     * 
     * @param ctPayment 支付渠道
     * @return 支付渠道
     */
    @Override
    public List<CtPayment> selectCtPaymentList(CtPayment ctPayment)
    {
        return ctPaymentMapper.selectCtPaymentList(ctPayment);
    }

    /**
     * 新增支付渠道
     * 
     * @param ctPayment 支付渠道
     * @return 结果
     */
    @Override
    public int insertCtPayment(CtPayment ctPayment)
    {
        ctPayment.setCreateTime(DateUtils.getNowDate());
        return ctPaymentMapper.insertCtPayment(ctPayment);
    }

    /**
     * 修改支付渠道
     * 
     * @param ctPayment 支付渠道
     * @return 结果
     */
    @Override
    public int updateCtPayment(CtPayment ctPayment)
    {
        ctPayment.setUpdateTime(DateUtils.getNowDate());
        return ctPaymentMapper.updateCtPayment(ctPayment);
    }

    /**
     * 批量删除支付渠道
     * 
     * @param ids 需要删除的支付渠道主键
     * @return 结果
     */
    @Override
    public int deleteCtPaymentByIds(String[] ids)
    {
        return ctPaymentMapper.deleteCtPaymentByIds(ids);
    }

    /**
     * 删除支付渠道信息
     * 
     * @param id 支付渠道主键
     * @return 结果
     */
    @Override
    public int deleteCtPaymentById(String id)
    {
        return ctPaymentMapper.deleteCtPaymentById(id);
    }
}
