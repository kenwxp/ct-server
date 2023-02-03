package com.cloudtimes.account.mapper;

import java.util.List;

import com.cloudtimes.account.domain.CtUser;

/**
 * 用户Mapper接口
 *
 * @author 沈兵
 * @date 2023-01-17
 */
public interface CtUserMapper {
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public CtUser selectCtUserById(String id);

    /**
     * 通过登录帐号查询用户
     *
     * @param account
     * @return
     */
    public CtUser selectCtUserByAccount(String account);

    /**
     * 通过wxOpenId查询用户
     *
     * @param wxOpenId
     * @return
     */
    public CtUser selectCtUserByWxOpenId(String wxOpenId);

    /**
     * 通过wxUnionId查询用户
     *
     * @param wxUnionId
     * @return
     */
    public CtUser selectCtUserByWxUnionId(String wxUnionId);

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
     * 删除用户
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteCtUserById(Long id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCtUserByIds(Long[] ids);
}
