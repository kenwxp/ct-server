package com.cloudtimes.supervise.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.supervise.mapper.CtTaskMapper;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.service.ICtTaskService;

/**
 * 值守任务Service业务层处理
 * 
 * @author tank
 * @date 2023-01-18
 */
@DataSource(DataSourceType.CT)
@Service
public class CtTaskServiceImpl implements ICtTaskService 
{
    @Autowired
    private CtTaskMapper ctTaskMapper;

    /**
     * 查询值守任务
     * 
     * @param id 值守任务主键
     * @return 值守任务
     */
    @Override
    public CtTask selectCtTaskById(Long id)
    {
        return ctTaskMapper.selectCtTaskById(id);
    }

    /**
     * 查询值守任务列表
     * 
     * @param ctTask 值守任务
     * @return 值守任务
     */
    @Override
    public List<CtTask> selectCtTaskList(CtTask ctTask)
    {
        return ctTaskMapper.selectCtTaskList(ctTask);
    }

    /**
     * 新增值守任务
     * 
     * @param ctTask 值守任务
     * @return 结果
     */
    @Override
    public int insertCtTask(CtTask ctTask)
    {
        ctTask.setCreateTime(DateUtils.getNowDate());
        return ctTaskMapper.insertCtTask(ctTask);
    }

    /**
     * 修改值守任务
     * 
     * @param ctTask 值守任务
     * @return 结果
     */
    @Override
    public int updateCtTask(CtTask ctTask)
    {
        ctTask.setUpdateTime(DateUtils.getNowDate());
        return ctTaskMapper.updateCtTask(ctTask);
    }

    /**
     * 批量删除值守任务
     * 
     * @param ids 需要删除的值守任务主键
     * @return 结果
     */
    @Override
    public int deleteCtTaskByIds(Long[] ids)
    {
        return ctTaskMapper.deleteCtTaskByIds(ids);
    }

    /**
     * 删除值守任务信息
     * 
     * @param id 值守任务主键
     * @return 结果
     */
    @Override
    public int deleteCtTaskById(Long id)
    {
        return ctTaskMapper.deleteCtTaskById(id);
    }
}
