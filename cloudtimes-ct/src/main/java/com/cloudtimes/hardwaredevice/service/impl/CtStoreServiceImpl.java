package com.cloudtimes.hardwaredevice.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.service.ICtStoreService;

/**
 * 门店Service业务层处理
 * 
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtStoreServiceImpl implements ICtStoreService 
{
    @Autowired
    private CtStoreMapper ctStoreMapper;

    /**
     * 查询门店
     * 
     * @param id 门店主键
     * @return 门店
     */
    @Override
    public CtStore selectCtStoreById(Long id)
    {
        return ctStoreMapper.selectCtStoreById(id);
    }

    /**
     * 查询门店列表
     * 
     * @param ctStore 门店
     * @return 门店
     */
    @Override
    public List<CtStore> selectCtStoreList(CtStore ctStore)
    {
        return ctStoreMapper.selectCtStoreList(ctStore);
    }

    /**
     * 新增门店
     * 
     * @param ctStore 门店
     * @return 结果
     */
    @Override
    public int insertCtStore(CtStore ctStore)
    {
        ctStore.setCreateTime(DateUtils.getNowDate());
        return ctStoreMapper.insertCtStore(ctStore);
    }

    /**
     * 修改门店
     * 
     * @param ctStore 门店
     * @return 结果
     */
    @Override
    public int updateCtStore(CtStore ctStore)
    {
        ctStore.setUpdateTime(DateUtils.getNowDate());
        return ctStoreMapper.updateCtStore(ctStore);
    }

    /**
     * 批量删除门店
     * 
     * @param ids 需要删除的门店主键
     * @return 结果
     */
    @Override
    public int deleteCtStoreByIds(Long[] ids)
    {
        return ctStoreMapper.deleteCtStoreByIds(ids);
    }

    /**
     * 删除门店信息
     * 
     * @param id 门店主键
     * @return 结果
     */
    @Override
    public int deleteCtStoreById(Long id)
    {
        return ctStoreMapper.deleteCtStoreById(id);
    }
}
