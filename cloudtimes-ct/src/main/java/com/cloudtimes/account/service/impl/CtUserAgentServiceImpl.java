package com.cloudtimes.account.service.impl;

import java.util.List;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtUserAgentMapper;
import com.cloudtimes.account.domain.CtUserAgent;
import com.cloudtimes.account.service.ICtUserAgentService;

/**
 * 代理Service业务层处理
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
@DataSource(DataSourceType.CT)
@Service
public class CtUserAgentServiceImpl implements ICtUserAgentService 
{
    @Autowired
    private CtUserAgentMapper ctUserAgentMapper;

    /**
     * 查询代理
     * 
     * @param userId 代理主键
     * @return 代理
     */
    @Override
    public CtUserAgent selectCtUserAgentByUserId(String userId)
    {
        return ctUserAgentMapper.selectCtUserAgentByUserId(userId);
    }

    /**
     * 查询代理列表
     * 
     * @param ctUserAgent 代理
     * @return 代理
     */
    @Override
    public List<CtUserAgent> selectCtUserAgentList(CtUserAgent ctUserAgent)
    {
        return ctUserAgentMapper.selectCtUserAgentList(ctUserAgent);
    }

    /**
     * 新增代理
     * 
     * @param ctUserAgent 代理
     * @return 结果
     */
    @Override
    public int insertCtUserAgent(CtUserAgent ctUserAgent)
    {
        ctUserAgent.setCreateTime(DateUtils.getNowDate());
        return ctUserAgentMapper.insertCtUserAgent(ctUserAgent);
    }

    /**
     * 修改代理
     * 
     * @param ctUserAgent 代理
     * @return 结果
     */
    @Override
    public int updateCtUserAgent(CtUserAgent ctUserAgent)
    {
        ctUserAgent.setUpdateTime(DateUtils.getNowDate());
        return ctUserAgentMapper.updateCtUserAgent(ctUserAgent);
    }

    /**
     * 批量删除代理
     * 
     * @param userIds 需要删除的代理主键
     * @return 结果
     */
    @Override
    public int deleteCtUserAgentByUserIds(String[] userIds)
    {
        return ctUserAgentMapper.deleteCtUserAgentByUserIds(userIds);
    }

    /**
     * 删除代理信息
     * 
     * @param userId 代理主键
     * @return 结果
     */
    @Override
    public int deleteCtUserAgentByUserId(String userId)
    {
        return ctUserAgentMapper.deleteCtUserAgentByUserId(userId);
    }
}
