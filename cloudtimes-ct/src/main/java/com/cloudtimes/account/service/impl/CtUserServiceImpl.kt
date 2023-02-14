package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.mapper.CtUserMapper
import com.cloudtimes.account.mapper.provider.CtUserProvider
import com.cloudtimes.account.service.ICtUserService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.mybatis.dynamic.sql.insert.render.DefaultInsertStatementProvider
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
    private lateinit var ctUserMapper: CtUserMapper

    override fun wxLoginOrCreateNewUser(loginUser: CtUser): CtUser {
        val existUser = ctUserMapper.selectOne(CtUserProvider.selectUserByUnionId(loginUser.wxUnionId!!))
        if ( existUser != null) {
            return existUser
        }

        ctUserMapper.insert(CtUserProvider.insertWxUser(loginUser))
        return loginUser
    }

    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    override fun selectCtUserById(id: String): CtUser? {
        return ctUserMapper.selectCtUserById(id)
    }

    override fun selectCtUserByAccount(account: String): CtUser? {
        return ctUserMapper.selectCtUserByAccount(account)
    }

    override fun selectCtUserByWxOpenId(wxOpenId: String): CtUser? {
        return ctUserMapper.selectCtUserByWxOpenId(wxOpenId)
    }

    /**
     * 查询用户列表
     *
     * @param ctUser 用户
     * @return 用户
     */
    override fun selectCtUserList(ctUser: CtUser): List<CtUser> {
        return ctUserMapper.selectCtUserList(ctUser)
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
        return ctUserMapper.insertCtUser(ctUser)
    }

    /**
     * 修改用户
     *
     * @param ctUser 用户
     * @return 结果
     */
    override fun updateCtUser(ctUser: CtUser): Int {
        ctUser.updateTime = DateUtils.getNowDate()
        return ctUserMapper.updateCtUser(ctUser)
    }
}