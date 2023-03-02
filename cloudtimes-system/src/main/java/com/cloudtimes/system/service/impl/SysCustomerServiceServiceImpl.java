package com.cloudtimes.system.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.system.mapper.SysCustomerServiceMapper;
import com.cloudtimes.system.domain.SysCustomerService;
import com.cloudtimes.system.service.ISysCustomerServiceService;

/**
 * 客服特性参数Service业务层处理
 * 
 * @author tank
 * @date 2023-03-02
 */
@DataSource(DataSourceType.CT)
@Service
public class SysCustomerServiceServiceImpl implements ISysCustomerServiceService 
{
    @Autowired
    private SysCustomerServiceMapper sysCustomerServiceMapper;

    /**
     * 查询客服特性参数
     * 
     * @param id 客服特性参数主键
     * @return 客服特性参数
     */
    @Override
    public SysCustomerService selectSysCustomerServiceById(String id)
    {
        return sysCustomerServiceMapper.selectSysCustomerServiceById(id);
    }

    /**
     * 查询客服特性参数列表
     * 
     * @param sysCustomerService 客服特性参数
     * @return 客服特性参数
     */
    @Override
    public List<SysCustomerService> selectSysCustomerServiceList(SysCustomerService sysCustomerService)
    {
        return sysCustomerServiceMapper.selectSysCustomerServiceList(sysCustomerService);
    }

    /**
     * 新增客服特性参数
     * 
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    @Override
    public int insertSysCustomerService(SysCustomerService sysCustomerService)
    {
        sysCustomerService.setCreateTime(DateUtils.getNowDate());
        return sysCustomerServiceMapper.insertSysCustomerService(sysCustomerService);
    }

    /**
     * 修改客服特性参数
     * 
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    @Override
    public int updateSysCustomerService(SysCustomerService sysCustomerService)
    {
        sysCustomerService.setUpdateTime(DateUtils.getNowDate());
        return sysCustomerServiceMapper.updateSysCustomerService(sysCustomerService);
    }

    /**
     * 批量删除客服特性参数
     * 
     * @param ids 需要删除的客服特性参数主键
     * @return 结果
     */
    @Override
    public int deleteSysCustomerServiceByIds(String[] ids)
    {
        return sysCustomerServiceMapper.deleteSysCustomerServiceByIds(ids);
    }

    /**
     * 删除客服特性参数信息
     * 
     * @param id 客服特性参数主键
     * @return 结果
     */
    @Override
    public int deleteSysCustomerServiceById(String id)
    {
        return sysCustomerServiceMapper.deleteSysCustomerServiceById(id);
    }
}
