package com.cloudtimes.account.service;

import java.util.List;
import com.cloudtimes.account.domain.CtUserAgent;

/**
 * 代理Service接口
 * 
 * @author 沈兵
 * @date 2023-02-07
 */
public interface ICtUserAgentService 
{
    /**
     * 查询代理
     * 
     * @param userId 代理主键
     * @return 代理
     */
    public CtUserAgent selectCtUserAgentByUserId(String userId);

    /**
     * 查询代理列表
     * 
     * @param ctUserAgent 代理
     * @return 代理集合
     */
    public List<CtUserAgent> selectCtUserAgentList(CtUserAgent ctUserAgent);

    /**
     * 新增代理
     * 
     * @param ctUserAgent 代理
     * @return 结果
     */
    public int insertCtUserAgent(CtUserAgent ctUserAgent);

    /**
     * 修改代理
     * 
     * @param ctUserAgent 代理
     * @return 结果
     */
    public int updateCtUserAgent(CtUserAgent ctUserAgent);

    /**
     * 批量删除代理
     * 
     * @param userIds 需要删除的代理主键集合
     * @return 结果
     */
    public int deleteCtUserAgentByUserIds(String[] userIds);

    /**
     * 删除代理信息
     * 
     * @param userId 代理主键
     * @return 结果
     */
    public int deleteCtUserAgentByUserId(String userId);
}
