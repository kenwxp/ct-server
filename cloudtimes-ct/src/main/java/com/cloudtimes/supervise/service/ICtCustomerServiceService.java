package com.cloudtimes.supervise.service;


import com.cloudtimes.supervise.domain.CtCustomerService;

import java.util.List;

/**
 * 客服特性参数Service接口
 *
 * @author wangxp
 * @date 2023-03-03
 */
public interface ICtCustomerServiceService {
    /**
     * 查询客服特性参数
     *
     * @param id 客服特性参数主键
     * @return 客服特性参数
     */
    public CtCustomerService selectCtCustomerServiceById(String id);

    /**
     * 查询客服特性参数列表
     *
     * @param sysCustomerService 客服特性参数
     * @return 客服特性参数集合
     */
    public List<CtCustomerService> selectCtCustomerServiceList(CtCustomerService sysCustomerService);

    /**
     * 新增客服特性参数
     *
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    public int insertCtCustomerService(CtCustomerService sysCustomerService);

    /**
     * 修改客服特性参数
     *
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    public int updateCtCustomerService(CtCustomerService sysCustomerService);

    /**
     * 批量删除客服特性参数
     *
     * @param ids 需要删除的客服特性参数主键集合
     * @return 结果
     */
    public int deleteCtCustomerServiceByIds(String[] ids);

    /**
     * 删除客服特性参数信息
     *
     * @param id 客服特性参数主键
     * @return 结果
     */
    public int deleteCtCustomerServiceById(String id);

    /**
     * 同步客服特性参数
     *
     * @return 结果
     */
    public int syncCtCustomerService();
}
