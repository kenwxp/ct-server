package com.cloudtimes.account.mapper;

import java.util.List;
import com.cloudtimes.account.domain.CtAgentDividend;

/**
 * 分润配置Mapper接口
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
public interface CtAgentDividendMapper 
{
    /**
     * 查询分润配置
     * 
     * @param id 分润配置主键
     * @return 分润配置
     */
    public CtAgentDividend selectCtAgentDividendById(String id);

    /**
     * 查询分润配置列表
     * 
     * @param ctAgentDividend 分润配置
     * @return 分润配置集合
     */
    public List<CtAgentDividend> selectCtAgentDividendList(CtAgentDividend ctAgentDividend);

    /**
     * 新增分润配置
     * 
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    public int insertCtAgentDividend(CtAgentDividend ctAgentDividend);

    /**
     * 修改分润配置
     * 
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    public int updateCtAgentDividend(CtAgentDividend ctAgentDividend);

    /**
     * 删除分润配置
     * 
     * @param id 分润配置主键
     * @return 结果
     */
    public int deleteCtAgentDividendById(String id);

    /**
     * 批量删除分润配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtAgentDividendByIds(String[] ids);
}
