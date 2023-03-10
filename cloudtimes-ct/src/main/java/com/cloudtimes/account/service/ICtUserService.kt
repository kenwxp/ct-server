package com.cloudtimes.account.service

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.dto.request.VerifyRealNameRequest
import com.cloudtimes.account.dto.response.CtUserDto
import com.cloudtimes.agent.dto.request.AgentRegisterRequest
import com.cloudtimes.agent.dto.response.InviteResponse
import com.cloudtimes.common.enums.AppType

/**
 * 用户Service接口
 *
 * @author 沈兵
 * @date 2023-01-17
 */
interface ICtUserService {
    /** 微信登陆或创建新用户 */
    fun wxLoginOrCreateNewUser(loginUser: CtUser, appType: AppType, appUserId: String): CtUser

    /** 代理用户注册 */
    fun agentRegister(request: AgentRegisterRequest): CtUser

    /** 实名认证 */
    fun verifyRealName(request: VerifyRealNameRequest): Int

    /** 根据SSN查询用户信息 */
    fun selectCtUserBySsn(ssn: String): CtUser?

    /** 代理邀请 */
    fun inviteAgent(request: QueryByUserIdRequest): InviteResponse

    /** 门店邀请 */
    fun inviteStore(request: QueryByUserIdRequest): InviteResponse

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

    fun selectCtUserListPlus(ctUser: CtUser): List<CtUserDto>

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