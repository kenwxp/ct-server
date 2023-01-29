package com.cloudtimes.resources.mapper;

import java.util.List;
import com.cloudtimes.resources.domain.CtMediaTemplate;

/**
 * 媒体资源模板Mapper接口
 * 
 * @author tank
 * @date 2023-01-17
 */
public interface CtMediaTemplateMapper 
{
    /**
     * 查询媒体资源模板
     * 
     * @param id 媒体资源模板主键
     * @return 媒体资源模板
     */
    public CtMediaTemplate selectCtMediaTemplateById(Long id);

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
     * 删除媒体资源模板
     * 
     * @param id 媒体资源模板主键
     * @return 结果
     */
    public int deleteCtMediaTemplateById(Long id);

    /**
     * 批量删除媒体资源模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtMediaTemplateByIds(Long[] ids);
}
