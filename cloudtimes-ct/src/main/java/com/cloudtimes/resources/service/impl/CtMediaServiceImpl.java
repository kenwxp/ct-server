package com.cloudtimes.resources.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.resources.mapper.CtMediaMapper;
import com.cloudtimes.resources.domain.CtMedia;
import com.cloudtimes.resources.service.ICtMediaService;

/**
 * 媒体Service业务层处理
 * 
 * @author tank
 * @date 2023-02-10
 */
@DataSource(DataSourceType.CT)
@Service
public class CtMediaServiceImpl implements ICtMediaService 
{
    @Autowired
    private CtMediaMapper ctMediaMapper;

    /**
     * 查询媒体
     * 
     * @param id 媒体主键
     * @return 媒体
     */
    @Override
    public CtMedia selectCtMediaById(String id)
    {
        return ctMediaMapper.selectCtMediaById(id);
    }

    /**
     * 查询媒体列表
     * 
     * @param ctMedia 媒体
     * @return 媒体
     */
    @Override
    public List<CtMedia> selectCtMediaList(CtMedia ctMedia)
    {
        return ctMediaMapper.selectCtMediaList(ctMedia);
    }

    /**
     * 新增媒体
     * 
     * @param ctMedia 媒体
     * @return 结果
     */
    @Override
    public int insertCtMedia(CtMedia ctMedia)
    {
        ctMedia.setCreateTime(DateUtils.getNowDate());
        return ctMediaMapper.insertCtMedia(ctMedia);
    }

    /**
     * 修改媒体
     * 
     * @param ctMedia 媒体
     * @return 结果
     */
    @Override
    public int updateCtMedia(CtMedia ctMedia)
    {
        ctMedia.setUpdateTime(DateUtils.getNowDate());
        return ctMediaMapper.updateCtMedia(ctMedia);
    }

    /**
     * 批量删除媒体
     * 
     * @param ids 需要删除的媒体主键
     * @return 结果
     */
    @Override
    public int deleteCtMediaByIds(String[] ids)
    {
        return ctMediaMapper.deleteCtMediaByIds(ids);
    }

    /**
     * 删除媒体信息
     * 
     * @param id 媒体主键
     * @return 结果
     */
    @Override
    public int deleteCtMediaById(String id)
    {
        return ctMediaMapper.deleteCtMediaById(id);
    }
}
