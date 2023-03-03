package com.cloudtimes.system.service.impl;

import java.util.List;

import com.cloudtimes.cache.SysCustomerServiceCache;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.system.mapper.SysCustomerServiceMapper;
import com.cloudtimes.system.domain.SysCustomerService;
import com.cloudtimes.system.service.ISysCustomerServiceService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客服特性参数Service业务层处理
 *
 * @author tank
 * @date 2023-03-03
 */
@DataSource(DataSourceType.CT)
@Service
public class SysCustomerServiceServiceImpl implements ISysCustomerServiceService {
    @Autowired
    private SysCustomerServiceMapper sysCustomerServiceMapper;
    private final long SERVICE_DEPT_ID = 201;
    @Autowired
    private SysCustomerServiceCache ctCustomerServiceCache;

    /**
     * 查询客服特性参数
     *
     * @param id 客服特性参数主键
     * @return 客服特性参数
     */
    @Override
    public SysCustomerService selectSysCustomerServiceById(String id) {
        return sysCustomerServiceMapper.selectSysCustomerServiceById(id);
    }

    /**
     * 查询客服特性参数列表
     *
     * @param sysCustomerService 客服特性参数
     * @return 客服特性参数
     */
    @Override
    public List<SysCustomerService> selectSysCustomerServiceList(SysCustomerService sysCustomerService) {
        return sysCustomerServiceMapper.selectSysCustomerServiceList(sysCustomerService);
    }

    /**
     * 新增客服特性参数
     *
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysCustomerService(SysCustomerService sysCustomerService) {
        sysCustomerService.setCreateTime(DateUtils.getNowDate());
        int row = sysCustomerServiceMapper.insertSysCustomerService(sysCustomerService);
        // 刷新
        ctCustomerServiceCache.refreshOne(sysCustomerService.getId());
        return row;
    }

    /**
     * 修改客服特性参数
     *
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysCustomerService(SysCustomerService sysCustomerService) {
        SysCustomerService dbOne = sysCustomerServiceMapper.selectSysCustomerServiceById(sysCustomerService.getId());
        if (dbOne == null) {
            throw new ServiceException("客服不存在");
        }
        ctCustomerServiceCache.refreshOne(String.valueOf(dbOne.getServiceId()));
        sysCustomerService.setUpdateTime(DateUtils.getNowDate());
        return sysCustomerServiceMapper.updateSysCustomerService(sysCustomerService);
    }

    /**
     * 批量删除客服特性参数
     *
     * @param ids 需要删除的客服特性参数主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysCustomerServiceByIds(String[] ids) {
        List<SysCustomerService> sysCustomerServices = sysCustomerServiceMapper.selectSysCustomerServiceByIds(ids);
        for (SysCustomerService customerService :
                sysCustomerServices) {
            if (customerService == null) {
                throw new ServiceException("客服不存在");
            }
            ctCustomerServiceCache.deleteMap(String.valueOf(customerService.getServiceId()));
        }
        return sysCustomerServiceMapper.deleteSysCustomerServiceByIds(ids);
    }

    /**
     * 删除客服特性参数信息
     *
     * @param id 客服特性参数主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysCustomerServiceById(String id) {
        SysCustomerService customerService = sysCustomerServiceMapper.selectSysCustomerServiceById(id);
        if (customerService == null) {
            throw new ServiceException("客服不存在");
        }
        // 内存删除
        ctCustomerServiceCache.deleteMap(String.valueOf(customerService.getServiceId()));
        return sysCustomerServiceMapper.deleteSysCustomerServiceById(id);
    }


    /**
     * 同步客服特性参数
     *
     * @return 结果
     */
    @Transactional
    @Override
    public int syncSysCustomerService() {
        // 原始数据
        List<SysCustomerService> rawList = sysCustomerServiceMapper.selectSysCustomerServiceListFromDept(SERVICE_DEPT_ID);
        SysCustomerService query = new SysCustomerService();
        query.setDelFlag("0");
        // 已有数据
        List<SysCustomerService> currentList = sysCustomerServiceMapper.selectSysCustomerServiceList(query);
        for (SysCustomerService rawUser : rawList) {
            boolean exist = false;
            for (SysCustomerService existUser :
                    currentList) {
                if (rawUser.getServiceId() == existUser.getServiceId()) {
                    // 存在
                    exist = true;
                    break;
                }
            }
            if (exist) {
                continue;
            }
            // 新客服
            rawUser.setLevel("0");
            rawUser.setMaxAcceptTask(10L);
            rawUser.setMaxAcceptOrder(50L);
            rawUser.setDelFlag("0");
            rawUser.setCreateTime(DateUtils.getNowDate());
            rawUser.setUpdateTime(DateUtils.getNowDate());
            sysCustomerServiceMapper.insertSysCustomerService(rawUser);
        }
        // 刷新内存
        ctCustomerServiceCache.refresh();
        return 1;
    }

}
