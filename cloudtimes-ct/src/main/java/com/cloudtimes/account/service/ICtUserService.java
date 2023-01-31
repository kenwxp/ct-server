package com.cloudtimes.account.service;

import java.util.List;

import com.cloudtimes.account.domain.CtUser;

/**
 * 用户Service接口
 *
 * @author 沈兵
 * @date 2023-01-17
 */
public interface ICtUserService {
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public CtUser selectCtUserById(Long id);

    /**
     * 通过用户登录帐号查询用户信息
     *
     * @param account
     * @return
     */
    public CtUser selectCtUserByAccount(String account);

    /**
     * 通过用户编码查询用户
     *
     * @param userCodes
     * @return
     */
    public CtUser selectCtUserByUserCode(String userCode);

    /**
     * 查询用户列表
     *
     * @param ctUser 用户
     * @return 用户集合
     */
    public List<CtUser> selectCtUserList(CtUser ctUser);

    /**
     * 新增用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    public int insertCtUser(CtUser ctUser);

    /**
     * 修改用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    public int updateCtUser(CtUser ctUser);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteCtUserByIds(Long[] ids);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteCtUserById(Long id);
}
