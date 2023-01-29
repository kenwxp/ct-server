package com.cloudtimes.resources.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.resources.mapper.CtMediaTemplateMapper;
import com.cloudtimes.resources.domain.CtMediaTemplate;
import com.cloudtimes.resources.service.ICtMediaTemplateService;

/**
 * 媒体资源模板Service业务层处理
 * 
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtMediaTemplateServiceImpl implements ICtMediaTemplateService 
{
    @Autowired
    private CtMediaTemplateMapper ctMediaTemplateMapper;

    /**
     * 查询媒体资源模板
     * 
     * @param id 媒体资源模板主键
     * @return 媒体资源模板
     */
    @Override
    public CtMediaTemplate selectCtMediaTemplateById(Long id)
    {
        return ctMediaTemplateMapper.selectCtMediaTemplateById(id);
    }

    /**
     * 查询媒体资源模板列表
     * 
     * @param ctMediaTemplate 媒体资源模板
     * @return 媒体资源模板
     */
    @Override
    public List<CtMediaTemplate> selectCtMediaTemplateList(CtMediaTemplate ctMediaTemplate)
    {
        return ctMediaTemplateMapper.selectCtMediaTemplateList(ctMediaTemplate);
    }

    /**
     * 新增媒体资源模板
     * 
     * @param ctMediaTemplate 媒体资源模板
     * @return 结果
     */
    @Override
    public int insertCtMediaTemplate(CtMediaTemplate ctMediaTemplate)
    {
        ctMediaTemplate.setCreateTime(DateUtils.getNowDate());
        return ctMediaTemplateMapper.insertCtMediaTemplate(ctMediaTemplate);
    }

    /**
     * 修改媒体资源模板
     * 
     * @param ctMediaTemplate 媒体资源模板
     * @return 结果
     */
    @Override
    public int updateCtMediaTemplate(CtMediaTemplate ctMediaTemplate)
    {
        ctMediaTemplate.setUpdateTime(DateUtils.getNowDate());
        return ctMediaTemplateMapper.updateCtMediaTemplate(ctMediaTemplate);
    }

    /**
     * 批量删除媒体资源模板
     * 
     * @param ids 需要删除的媒体资源模板主键
     * @return 结果
     */
    @Override
    public int deleteCtMediaTemplateByIds(Long[] ids)
    {
        return ctMediaTemplateMapper.deleteCtMediaTemplateByIds(ids);
    }

    /**
     * 删除媒体资源模板信息
     * 
     * @param id 媒体资源模板主键
     * @return 结果
     */
    @Override
    public int deleteCtMediaTemplateById(Long id)
    {
        return ctMediaTemplateMapper.deleteCtMediaTemplateById(id);
    }
}
