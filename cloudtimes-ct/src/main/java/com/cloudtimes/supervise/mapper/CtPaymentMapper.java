package com.cloudtimes.supervise.mapper;

import com.cloudtimes.supervise.domain.CtPayment;

import java.util.List;

/**
 * 支付渠道Mapper接口
 *
 * @author wangxp
 * @date 2023-02-07
 */
public interface CtPaymentMapper {
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
     * 删除支付渠道
     *
     * @param id 支付渠道主键
     * @return 结果
     */
    public int deleteCtPaymentById(String id);

    /**
     * 批量删除支付渠道
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtPaymentByIds(String[] ids);
}
