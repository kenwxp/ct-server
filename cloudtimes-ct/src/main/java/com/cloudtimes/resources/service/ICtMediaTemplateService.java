package com.cloudtimes.resources.service;

import java.util.List;

import com.cloudtimes.resources.domain.CtMediaTemplate;

/**
 * 媒体资源模板Service接口
 *
 * @author tank
 * @date 2023-01-17
 */
public interface ICtMediaTemplateService {
    /**
     * 查询媒体资源模板
     *
     * @param id 媒体资源模板主键
     * @return 媒体资源模板
     */
    public CtMediaTemplate selectCtMediaTemplateById(String id);

    /**
     * 查询媒体资源模板列表
     *
     * @param ctMediaTemplate 媒体资源模板
     * @return 媒体资源模板集合
     */
    public List<CtMediaTemplate> selectCtMediaTemplateList(CtMediaTemplate ctMediaTemplate);

    /**
     * 新增媒体资源模板
     *
     * @param ctMediaTemplate 媒体资源模板
     * @return 结果
     */
    public int insertCtMediaTemplate(CtMediaTemplate ctMediaTemplate);

    /**
     * 修改媒体资源模板
     *
     * @param ctMediaTemplate 媒体资源模板
     * @return 结果
     */
    public int updateCtMediaTemplate(CtMediaTemplate ctMediaTemplate);

    /**
     * 批量删除媒体资源模板
     *
     * @param ids 需要删除的媒体资源模板主键集合
     * @return 结果
     */
    public int deleteCtMediaTemplateByIds(String[] ids);

    /**
     * 删除媒体资源模板信息
     *
     * @param id 媒体资源模板主键
     * @return 结果
     */
    public int deleteCtMediaTemplateById(String id);
}
