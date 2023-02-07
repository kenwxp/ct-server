package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtAgentDividendMapper;
import com.cloudtimes.account.domain.CtAgentDividend;
import com.cloudtimes.account.service.ICtAgentDividendService;

/**
 * 分润配置Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-03
 */
@DataSource(DataSourceType.CT)
@Service
public class CtAgentDividendServiceImpl implements ICtAgentDividendService 
{
    @Autowired
    private CtAgentDividendMapper ctAgentDividendMapper;

    /**
     * 查询分润配置
     * 
     * @param id 分润配置主键
     * @return 分润配置
     */
    @Override
    public CtAgentDividend selectCtAgentDividendById(String id)
    {
        return ctAgentDividendMapper.selectCtAgentDividendById(id);
    }

    /**
     * 查询分润配置列表
     * 
     * @param ctAgentDividend 分润配置
     * @return 分润配置
     */
    @Override
    public List<CtAgentDividend> selectCtAgentDividendList(CtAgentDividend ctAgentDividend)
    {
        return ctAgentDividendMapper.selectCtAgentDividendList(ctAgentDividend);
    }

    /**
     * 新增分润配置
     * 
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    @Override
    public int insertCtAgentDividend(CtAgentDividend ctAgentDividend)
    {
        ctAgentDividend.setCreateTime(DateUtils.getNowDate());
        return ctAgentDividendMapper.insertCtAgentDividend(ctAgentDividend);
    }

    /**
     * 修改分润配置
     * 
     * @param ctAgentDividend 分润配置
     * @return 结果
     */
    @Override
    public int updateCtAgentDividend(CtAgentDividend ctAgentDividend)
    {
        ctAgentDividend.setUpdateTime(DateUtils.getNowDate());
        return ctAgentDividendMapper.updateCtAgentDividend(ctAgentDividend);
    }

    /**
     * 批量删除分润配置
     * 
     * @param ids 需要删除的分润配置主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentDividendByIds(String[] ids)
    {
        return ctAgentDividendMapper.deleteCtAgentDividendByIds(ids);
    }

    /**
     * 删除分润配置信息
     * 
     * @param id 分润配置主键
     * @return 结果
     */
    @Override
    public int deleteCtAgentDividendById(String id)
    {
        return ctAgentDividendMapper.deleteCtAgentDividendById(id);
    }
}
