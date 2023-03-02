package com.cloudtimes.system.service;

import java.util.List;
import com.cloudtimes.system.domain.SysCustomerService;

/**
 * 客服特性参数Service接口
 * 
 * @author tank
 * @date 2023-03-02
 */
public interface ISysCustomerServiceService 
{
    /**
     * 查询客服特性参数
     * 
     * @param id 客服特性参数主键
     * @return 客服特性参数
     */
    public SysCustomerService selectSysCustomerServiceById(String id);

    /**
     * 查询客服特性参数列表
     * 
     * @param sysCustomerService 客服特性参数
     * @return 客服特性参数集合
     */
    public List<SysCustomerService> selectSysCustomerServiceList(SysCustomerService sysCustomerService);

    /**
     * 新增客服特性参数
     * 
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    public int insertSysCustomerService(SysCustomerService sysCustomerService);

    /**
     * 修改客服特性参数
     * 
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    public int updateSysCustomerService(SysCustomerService sysCustomerService);

    /**
     * 批量删除客服特性参数
     * 
     * @param ids 需要删除的客服特性参数主键集合
     * @return 结果
     */
    public int deleteSysCustomerServiceByIds(String[] ids);

    /**
     * 删除客服特性参数信息
     * 
     * @param id 客服特性参数主键
     * @return 结果
     */
    public int deleteSysCustomerServiceById(String id);
}