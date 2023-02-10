package com.cloudtimes.resources.service;

import java.util.List;
import com.cloudtimes.resources.domain.CtMedia;

/**
 * 媒体Service接口
 * 
 * @author tank
 * @date 2023-02-10
 */
public interface ICtMediaService 
{
    /**
     * 查询媒体
     * 
     * @param id 媒体主键
     * @return 媒体
     */
    public CtMedia selectCtMediaById(String id);

    /**
     * 查询媒体列表
     * 
     * @param ctMedia 媒体
     * @return 媒体集合
     */
    public List<CtMedia> selectCtMediaList(CtMedia ctMedia);

    /**
     * 新增媒体
     * 
     * @param ctMedia 媒体
     * @return 结果
     */
    public int insertCtMedia(CtMedia ctMedia);

    /**
     * 修改媒体
     * 
     * @param ctMedia 媒体
     * @return 结果
     */
    public int updateCtMedia(CtMedia ctMedia);

    /**
     * 批量删除媒体
     * 
     * @param ids 需要删除的媒体主键集合
     * @return 结果
     */
    public int deleteCtMediaByIds(String[] ids);

    /**
     * 删除媒体信息
     * 
     * @param id 媒体主键
     * @return 结果
     */
    public int deleteCtMediaById(String id);
}
