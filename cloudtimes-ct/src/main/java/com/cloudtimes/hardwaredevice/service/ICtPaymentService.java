package com.cloudtimes.hardwaredevice.service;

import com.cloudtimes.hardwaredevice.domain.CtPayment;

import java.util.List;

/**
 * 支付渠道Service接口
 * 
 * @author tank
 * @date 2023-02-22
 */
public interface ICtPaymentService 
{
    /**
     * 查询支付渠道
     * 
     * @param id 支付渠道主键
     * @return 支付渠道
     */
    public CtPayment selectCtPaymentById(String id);

    /**
     * 查询支付渠道列表
     * 
     * @param ctPayment 支付渠道
     * @return 支付渠道集合
     */
    public List<CtPayment> selectCtPaymentList(CtPayment ctPayment);

    /**
     * 新增支付渠道
     * 
     * @param ctPayment 支付渠道
     * @return 结果
     */
    public int insertCtPayment(CtPayment ctPayment);

    /**
     * 修改支付渠道
     * 
     * @param ctPayment 支付渠道
     * @return 结果
     */
    public int updateCtPayment(CtPayment ctPayment);

    /**
     * 批量删除支付渠道
     * 
     * @param ids 需要删除的支付渠道主键集合
     * @return 结果
     */
    public int deleteCtPaymentByIds(String[] ids);

    /**
     * 删除支付渠道信息
     * 
     * @param id 支付渠道主键
     * @return 结果
     */
    public int deleteCtPaymentById(String id);
}
