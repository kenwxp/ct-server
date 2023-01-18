package com.cloudtimes.supervise.mapper;

import java.util.List;
import com.cloudtimes.supervise.domain.CtTask;

/**
 * 值守任务Mapper接口
 * 
 * @author tank
 * @date 2023-01-18
 */
public interface CtTaskMapper 
{
    /**
     * 查询值守任务
     * 
     * @param id 值守任务主键
     * @return 值守任务
     */
    public CtTask selectCtTaskById(Long id);

    /**
     * 查询值守任务列表
     * 
     * @param ctTask 值守任务
     * @return 值守任务集合
     */
    public List<CtTask> selectCtTaskList(CtTask ctTask);

    /**
     * 新增值守任务
     * 
     * @param ctTask 值守任务
     * @return 结果
     */
    public int insertCtTask(CtTask ctTask);

    /**
     * 修改值守任务
     * 
     * @param ctTask 值守任务
     * @return 结果
     */
    public int updateCtTask(CtTask ctTask);

    /**
     * 删除值守任务
     * 
     * @param id 值守任务主键
     * @return 结果
     */
    public int deleteCtTaskById(Long id);

    /**
     * 批量删除值守任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtTaskByIds(Long[] ids);
}
