package com.cloudtimes.resources.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.resources.mapper.CtRegionMapper;
import com.cloudtimes.resources.domain.CtRegion;
import com.cloudtimes.resources.service.ICtRegionService;

/**
 * 地区信息Service业务层处理
 * 
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtRegionServiceImpl implements ICtRegionService 
{
    @Autowired
    private CtRegionMapper ctRegionMapper;

    /**
     * 查询地区信息
     * 
     * @param id 地区信息主键
     * @return 地区信息
     */
    @Override
    public CtRegion selectCtRegionById(Long id)
    {
        return ctRegionMapper.selectCtRegionById(id);
    }

    /**
     * 查询地区信息列表
     * 
     * @param ctRegion 地区信息
     * @return 地区信息
     */
    @Override
    public List<CtRegion> selectCtRegionList(CtRegion ctRegion)
    {
        return ctRegionMapper.selectCtRegionList(ctRegion);
    }

    /**
     * 新增地区信息
     * 
     * @param ctRegion 地区信息
     * @return 结果
     */
    @Override
    public int insertCtRegion(CtRegion ctRegion)
    {
        ctRegion.setCreateTime(DateUtils.getNowDate());
        return ctRegionMapper.insertCtRegion(ctRegion);
    }

    /**
     * 修改地区信息
     * 
     * @param ctRegion 地区信息
     * @return 结果
     */
    @Override
    public int updateCtRegion(CtRegion ctRegion)
    {
        ctRegion.setUpdateTime(DateUtils.getNowDate());
        return ctRegionMapper.updateCtRegion(ctRegion);
    }

    /**
     * 批量删除地区信息
     * 
     * @param ids 需要删除的地区信息主键
     * @return 结果
     */
    @Override
    public int deleteCtRegionByIds(Long[] ids)
    {
        return ctRegionMapper.deleteCtRegionByIds(ids);
    }

    /**
     * 删除地区信息信息
     * 
     * @param id 地区信息主键
     * @return 结果
     */
    @Override
    public int deleteCtRegionById(Long id)
    {
        return ctRegionMapper.deleteCtRegionById(id);
    }
}
