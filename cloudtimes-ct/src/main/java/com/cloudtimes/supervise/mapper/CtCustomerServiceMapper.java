package com.cloudtimes.supervise.mapper;

import com.cloudtimes.supervise.domain.CtCustomerService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客服特性参数Mapper接口
 * 
 * @author wangxp
 * @date 2023-03-02
 */
public interface CtCustomerServiceMapper
{
    /**
     * 查询客服特性参数
     * 
     * @param id 客服特性参数主键
     * @return 客服特性参数
     */
    public CtCustomerService selectCtCustomerServiceById(String id);

    /**
     * 查询客服特性参数
     *
     * @param ids 客服特性参数主键
     * @return 客服特性参数
     */
    public List<CtCustomerService> selectCtCustomerServiceByIds(String[] ids);

    /**
     * 查询客服特性参数
     *
     * @param serviceId 客服特性参数主键
     * @return 客服特性参数
     */
    public CtCustomerService selectCtCustomerServiceByServiceId(@Param("serviceId") Long serviceId);
    /**
     * 查询客服特性参数列表
     * 
     * @param sysCustomerService 客服特性参数
     * @return 客服特性参数集合
     */
    public List<CtCustomerService> selectCtCustomerServiceList(CtCustomerService sysCustomerService);

    /**
     * 获取原始客服列表
     *
     * @param deptId
     * @return 客服特性参数集合
     */
    public List<CtCustomerService> selectCtCustomerServiceListFromDept(Long deptId);

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
     * 删除客服特性参数
     * 
     * @param id 客服特性参数主键
     * @return 结果
     */
    public int deleteCtCustomerServiceById(String id);

    /**
     * 批量删除客服特性参数
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtCustomerServiceByIds(String[] ids);
}
