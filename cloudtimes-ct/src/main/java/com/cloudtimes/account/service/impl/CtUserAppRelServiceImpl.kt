package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtUserAppRel
import com.cloudtimes.account.mapper.CtUserAppRelMapper
import com.cloudtimes.account.service.ICtUserAppRelService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 用户与应用关系Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@DataSource(DataSourceType.CT)
@Service
class CtUserAppRelServiceImpl : ICtUserAppRelService {
    @Autowired
    private lateinit var ctUserAppRelMapper: CtUserAppRelMapper

    /**
     * 查询用户与应用关系
     *
     * @param userId 用户与应用关系主键
     * @return 用户与应用关系
     */
    override fun selectCtUserAppRelByUserId(userId: String): CtUserAppRel? {
        return ctUserAppRelMapper.selectCtUserAppRelByUserId(userId)
    }

    /**
     * 查询用户与应用关系列表
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 用户与应用关系
     */
    override fun selectCtUserAppRelList(ctUserAppRel: CtUserAppRel): List<CtUserAppRel> {
        return ctUserAppRelMapper.selectCtUserAppRelList(ctUserAppRel)
    }

    /**
     * 新增用户与应用关系
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 结果
     */
    override fun insertCtUserAppRel(ctUserAppRel: CtUserAppRel): Int {
        ctUserAppRel.createTime = DateUtils.getNowDate()
        return ctUserAppRelMapper.insertCtUserAppRel(ctUserAppRel)
    }

    /**
     * 修改用户与应用关系
     *
     * @param ctUserAppRel 用户与应用关系
     * @return 结果
     */
    override fun updateCtUserAppRel(ctUserAppRel: CtUserAppRel): Int {
        ctUserAppRel.updateTime = DateUtils.getNowDate()
        return ctUserAppRelMapper.updateCtUserAppRel(ctUserAppRel)
    }

    /**
     * 批量删除用户与应用关系
     *
     * @param userIds 需要删除的用户与应用关系主键
     * @return 结果
     */
    override fun deleteCtUserAppRelByUserIds(userIds: Array<String>): Int {
        return ctUserAppRelMapper.deleteCtUserAppRelByUserIds(userIds)
    }

    /**
     * 删除用户与应用关系信息
     *
     * @param userId 用户与应用关系主键
     * @return 结果
     */
    override fun deleteCtUserAppRelByUserId(userId: String): Int {
        return ctUserAppRelMapper.deleteCtUserAppRelByUserId(userId)
    }
}