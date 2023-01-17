package com.cloudtimes.resources.mapper;

import java.util.List;
import com.cloudtimes.resources.domain.CtMedia;

/**
 * 媒体信息Mapper接口
 * 
 * @author tank
 * @date 2023-01-17
 */
public interface CtMediaMapper 
{
    /**
     * 查询媒体信息
     * 
     * @param id 媒体信息主键
     * @return 媒体信息
     */
    public CtMedia selectCtMediaById(Long id);

    /**
     * 查询媒体信息列表
     * 
     * @param ctMedia 媒体信息
     * @return 媒体信息集合
     */
    public List<CtMedia> selectCtMediaList(CtMedia ctMedia);

    /**
     * 新增媒体信息
     * 
     * @param ctMedia 媒体信息
     * @return 结果
     */
    public int insertCtMedia(CtMedia ctMedia);

    /**
     * 修改媒体信息
     * 
     * @param ctMedia 媒体信息
     * @return 结果
     */
    public int updateCtMedia(CtMedia ctMedia);

    /**
     * 删除媒体信息
     * 
     * @param id 媒体信息主键
     * @return 结果
     */
    public int deleteCtMediaById(Long id);

    /**
     * 批量删除媒体信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtMediaByIds(Long[] ids);
}
