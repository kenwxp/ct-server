package com.cloudtimes.supervise.service.impl;

import com.cloudtimes.cache.CtCustomerServiceCache;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.supervise.domain.CtCustomerService;
import com.cloudtimes.supervise.mapper.CtCustomerServiceMapper;
import com.cloudtimes.supervise.service.ICtCustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 客服特性参数Service业务层处理
 *
 * @author tank
 * @date 2023-03-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtCustomerServiceServiceImpl implements ICtCustomerServiceService {
    @Autowired
    private CtCustomerServiceMapper sysCustomerServiceMapper;
    private final long SERVICE_DEPT_ID = 201;
    @Autowired
    private CtCustomerServiceCache ctCustomerServiceCache;

    /**
     * 查询客服特性参数
     *
     * @param id 客服特性参数主键
     * @return 客服特性参数
     */
    @Override
    public CtCustomerService selectCtCustomerServiceById(String id) {
        return sysCustomerServiceMapper.selectCtCustomerServiceById(id);
    }

    /**
     * 查询客服特性参数列表
     *
     * @param sysCustomerService 客服特性参数
     * @return 客服特性参数
     */
    @Override
    public List<CtCustomerService> selectCtCustomerServiceList(CtCustomerService sysCustomerService) {
        return sysCustomerServiceMapper.selectCtCustomerServiceList(sysCustomerService);
    }

    /**
     * 新增客服特性参数
     *
     * @param sysCustomerService 客服特性参数
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCtCustomerService(CtCustomerService sysCustomerService) {
        sysCustomerService.setCreateTime(DateUtils.getNowDate());
        int row = sysCustomerServiceMapper.insertCtCustomerService(sysCustomerService);
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
    public int updateCtCustomerService(CtCustomerService sysCustomerService) {
        CtCustomerService dbOne = sysCustomerServiceMapper.selectCtCustomerServiceById(sysCustomerService.getId());
        if (dbOne == null) {
            throw new ServiceException("客服不存在");
        }
        ctCustomerServiceCache.refreshOne(String.valueOf(dbOne.getServiceId()));
        sysCustomerService.setUpdateTime(DateUtils.getNowDate());
        return sysCustomerServiceMapper.updateCtCustomerService(sysCustomerService);
    }

    /**
     * 批量删除客服特性参数
     *
     * @param ids 需要删除的客服特性参数主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCtCustomerServiceByIds(String[] ids) {
        List<CtCustomerService> sysCustomerServices = sysCustomerServiceMapper.selectCtCustomerServiceByIds(ids);
        for (CtCustomerService customerService :
                sysCustomerServices) {
            if (customerService == null) {
                throw new ServiceException("客服不存在");
            }
            ctCustomerServiceCache.deleteMap(String.valueOf(customerService.getServiceId()));
        }
        return sysCustomerServiceMapper.deleteCtCustomerServiceByIds(ids);
    }

    /**
     * 删除客服特性参数信息
     *
     * @param id 客服特性参数主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCtCustomerServiceById(String id) {
        CtCustomerService customerService = sysCustomerServiceMapper.selectCtCustomerServiceById(id);
        if (customerService == null) {
            throw new ServiceException("客服不存在");
        }
        // 内存删除
        ctCustomerServiceCache.deleteMap(String.valueOf(customerService.getServiceId()));
        return sysCustomerServiceMapper.deleteCtCustomerServiceById(id);
    }


    /**
     * 同步客服特性参数
     *
     * @return 结果
     */
    @Transactional
    @Override
    public int syncCtCustomerService() {
        // 原始数据
        List<CtCustomerService> rawList = sysCustomerServiceMapper.selectCtCustomerServiceListFromDept(SERVICE_DEPT_ID);
        CtCustomerService query = new CtCustomerService();
        query.setDelFlag("0");
        // 已有数据
        List<CtCustomerService> currentList = sysCustomerServiceMapper.selectCtCustomerServiceList(query);
        for (CtCustomerService rawUser : rawList) {
            boolean exist = false;
            for (CtCustomerService existUser :
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
            sysCustomerServiceMapper.insertCtCustomerService(rawUser);
        }
        // 刷新内存
        ctCustomerServiceCache.refresh();
        return 1;
    }

}
