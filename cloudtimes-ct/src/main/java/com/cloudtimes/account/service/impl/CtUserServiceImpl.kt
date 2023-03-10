package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.domain.CtUserAppRel
import com.cloudtimes.account.dto.request.QueryByUserIdRequest
import com.cloudtimes.account.dto.request.VerifyRealNameRequest
import com.cloudtimes.account.dto.response.CtUserDto
import com.cloudtimes.account.mapper.CtUserAppRelMapper
import com.cloudtimes.account.mapper.CtUserMapper
import com.cloudtimes.account.mapper.provider.CtUserAppRelProvider
import com.cloudtimes.account.mapper.provider.CtUserProvider
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.agent.domain.CtUserAgent
import com.cloudtimes.agent.dto.request.AgentRegisterRequest
import com.cloudtimes.agent.dto.response.InviteResponse
import com.cloudtimes.agent.mapper.CtUserAgentMapper
import com.cloudtimes.agent.mapper.provider.CtUserAgentProvider
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.*
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.supervise.domain.CtEvents
import com.cloudtimes.supervise.mapper.CtEventsMapper
import com.cloudtimes.system.service.ISysConfigService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户Service业务层处理
 *
 * @author 沈兵
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtUserServiceImpl : ICtUserService {
    @Autowired
    private lateinit var userMapper: CtUserMapper

    @Autowired
    private lateinit var userAppMapper: CtUserAppRelMapper

    @Autowired
    private lateinit var agentMapper: CtUserAgentMapper

    @Autowired
    private lateinit var eventsMapper: CtEventsMapper

    @Autowired
    private lateinit var configService: ISysConfigService

    override fun wxLoginOrCreateNewUser(loginUser: CtUser, appType: AppType, appUserId: String): CtUser {
        val existUser = userMapper.selectOne(CtUserProvider.selectUserByUnionId(loginUser.wxUnionId!!))

        if (existUser != null) {
            val existUserApp = userAppMapper.selectOne(CtUserAppRelProvider.selectOneStmt(existUser.id!!, appType))
            if (existUserApp == null) {
                userAppMapper.generalInsert(CtUserAppRelProvider.insertStmt(existUser.id!!, appType,  appUserId))
            }

            return existUser
        } else {
            userMapper.insert(CtUserProvider.insertWxUser(loginUser))
            userAppMapper.generalInsert(CtUserAppRelProvider.insertStmt(loginUser.id!!, appType,  appUserId))
            return loginUser
        }
    }

    override fun agentRegister(request: AgentRegisterRequest): CtUser {
        // Step 1. 查询用户信息
        val existUser = userMapper.selectOne(CtUserProvider.selectUserByUnionId(request.wxUnionId!!))
            ?: throw ServiceException("数据库异常，查询微信用户失败")

        // Step 2. 登记为代理
        val newAgent = CtUserAgent().apply {
            userId = existUser.id
            nickName = existUser.nickName
            agentType = existUser.agentType
        }
        if (!request.inviteCode.isNullOrEmpty()) {
            val parentAgent = userMapper.selectOne(CtUserProvider.selectUserByInviteCode(request.inviteCode!!))
                ?: throw ServiceException("查询邀请人失败")
            // 普通代理可以邀请，邀请的人归属公司
            if (parentAgent.agentType != AgentType.GeneralAgent.code) {
                request.agentType = AgentType.SubAgent.code
                newAgent.parentUserId = parentAgent.id
                newAgent.agentType = AgentType.SubAgent.code
            }
        }
        userMapper.update(CtUserProvider.agentRegister(request))
        agentMapper.generalInsert(CtUserAgentProvider.createAgent(newAgent))

        // Step 3. 登记事件表
        val events = CtEvents().apply {
            eventType = EventType.CustomerServiceMessage.code
            sender = existUser.id
            userType = UserType.Agent.code
            senderName = existUser.nickName
            eventName = "代理注册"
            content = "新用户 [${request.mobile}] 申请注册为代理，请联系审核"
            validDays = 30
            sourceType = ChannelType.WEB.code
        }
        eventsMapper.insertCtEvents(events)

        return existUser
    }

    override fun verifyRealName(request: VerifyRealNameRequest): Int {
        return userMapper.update(CtUserProvider.updateRealName(request))
    }

    override fun selectCtUserBySsn(ssn: String): CtUser? {
        return userMapper.selectOne(CtUserProvider.selectUserBySsn(ssn))
    }

    /** 代理邀请 */
    override fun inviteAgent(request: QueryByUserIdRequest): InviteResponse {
        // Step 1. 查询用户信息
        val userId = request.userId
        userMapper.selectOne(CtUserProvider.selectUserById(userId))
            ?: throw ServiceException("数据库异常，查询微信用户失败")

        // Step 2. 查询邀请地址
        val configUrl: String? = configService.selectConfigByKey("ct_invite_agent_url")
        if (configUrl.isNullOrEmpty()) {
            throw ServiceException("代理邀请地址参数未配置!")
        }

        // Step 3. 返回邀请码和和邀请地址
        val inviteCode = userId.substring(0, 8).uppercase()
        val inviteUrl = "$configUrl?ty=${UserType.Agent.code}&ic=${inviteCode}"
        return InviteResponse(inviteCode = inviteCode, inviteUrl = inviteUrl)
    }

    /** 门店邀请 */
    override fun inviteStore(request: QueryByUserIdRequest): InviteResponse {
        // Step 1. 查询用户信息
        val userId = request.userId
        val user = userMapper.selectOne(CtUserProvider.selectUserById(userId))
            ?: throw ServiceException("数据库异常，查询微信用户失败")

        // Step 2. 查询邀请地址
        val configUrl: String? = configService.selectConfigByKey("ct_invite_store_url")
        if (configUrl.isNullOrEmpty()) {
            throw ServiceException("店铺邀请地址参数未配置!")
        }

        // Step 3. 返回邀请码和和邀请地址
        val inviteCode = userId.substring(0, 8).uppercase()
        val inviteUrl = "$configUrl?ty=${UserType.Shopkeeper.code}&ic=$inviteCode"
        return InviteResponse(inviteCode = inviteCode, inviteUrl = inviteUrl)
    }

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    override fun selectCtUserById(id: String): CtUser? {
        return userMapper.selectCtUserById(id)
    }

    override fun selectCtUserByAccount(account: String): CtUser? {
        return userMapper.selectCtUserByAccount(account)
    }

    override fun selectCtUserByWxOpenId(wxOpenId: String): CtUser? {
        return userMapper.selectCtUserByWxOpenId(wxOpenId)
    }

    /**
     * 查询用户列表
     *
     * @param ctUser 用户
     * @return 用户
     */
    override fun selectCtUserList(ctUser: CtUser): List<CtUser> {
        return userMapper.selectCtUserList(ctUser)
    }

    override fun selectCtUserListPlus(ctUser: CtUser): List<CtUserDto> {
        return userMapper.selectCtUserListPlus(ctUser)
    }

    /**
     * 新增用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    @DataSource(DataSourceType.SHARDING)
    override fun insertCtUser(ctUser: CtUser): Int {
        ctUser.createTime = DateUtils.getNowDate()
        return userMapper.insertCtUser(ctUser)
    }

    /**
     * 修改用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    override fun updateCtUser(ctUser: CtUser): Int {
        ctUser.updateTime = DateUtils.getNowDate()
        return userMapper.updateCtUser(ctUser)
    }
}