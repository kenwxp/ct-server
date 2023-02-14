package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtUser

/**
 * 用户Service接口
 *
 * @author 沈兵
 * @date 2023-01-17
 */
interface ICtUserService {
    fun wxLoginOrCreateNewUser(loginUser: CtUser): CtUser

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    fun selectCtUserById(id: String): CtUser?

    /**
     * 通过用户登录帐号查询用户信息
     *
     * @param account
     * @return
     */
    fun selectCtUserByAccount(account: String): CtUser?

    /**
     * 通过openId查询用户
     *
     * @param wxOpenId
     * @return
     */
    fun selectCtUserByWxOpenId(wxOpenId: String): CtUser?

    /**
     * 查询用户列表
     *
     * @param ctUser 用户
     * @return 用户集合
     */
    fun selectCtUserList(ctUser: CtUser): List<CtUser>

    /**
     * 新增用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    fun insertCtUser(ctUser: CtUser): Int

    /**
     * 修改用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    fun updateCtUser(ctUser: CtUser): Int
}