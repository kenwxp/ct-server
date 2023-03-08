package com.cloudtimes.hardwaredevice.mapper;

import com.cloudtimes.hardwaredevice.domain.CtPayment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 支付渠道Mapper接口
 *
 * @author tank
 * @date 2023-02-22
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
     * 查询支付渠道参数
     *
     * @param payeeId
     * @param payeeType
     * @param payWay
     * @return
     */
    public CtPayment selectCtPaymentByUniqueKey(@Param("payeeId") String payeeId, @Param("payeeType") String payeeType, @Param("payWay") String payWay);

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


    public int deleteCtPaymentByUniqueKey(CtPayment ctPayment);
}
