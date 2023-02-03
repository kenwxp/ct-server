package com.cloudtimes.account.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloudtimes.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.service.ICtUserService;

/**
 * 用户Service业务层处理
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
public class CtUserServiceImpl implements ICtUserService {
    @Autowired
    private CtUserMapper ctUserMapper;

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public CtUser selectCtUserById(String id) {
        return ctUserMapper.selectCtUserById(id);
    }

    @Override
    public CtUser selectCtUserByAccount(String account) {
        return ctUserMapper.selectCtUserByAccount(account);
    }

    @Override
    public CtUser selectCtUserByWxOpenId(String wxOpenId) {
        return ctUserMapper.selectCtUserByWxOpenId(wxOpenId);
    }

    /**
     * 查询用户列表
     *
     * @param ctUser 用户
     * @return 用户
     */
    @Override
    public List<CtUser> selectCtUserList(CtUser ctUser) {
        return ctUserMapper.selectCtUserList(ctUser);
    }

    /**
     * 新增用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    @DataSource(DataSourceType.SHARDING)
    @Override
    public int insertCtUser(CtUser ctUser) {
        ctUser.setCreateTime(DateUtils.getNowDate());
        return ctUserMapper.insertCtUser(ctUser);
    }

    /**
     * 修改用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    @Override
    public int updateCtUser(CtUser ctUser) {
        ctUser.setUpdateTime(DateUtils.getNowDate());
        return ctUserMapper.updateCtUser(ctUser);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteCtUserByIds(Long[] ids) {
        return ctUserMapper.deleteCtUserByIds(ids);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteCtUserById(Long id) {
        return ctUserMapper.deleteCtUserById(id);
    }

}
