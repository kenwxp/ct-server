package com.cloudtimes.account.service.impl

import com.cloudtimes.account.domain.CtStoreUser
import com.cloudtimes.account.mapper.CtStoreUserMapper
import com.cloudtimes.account.service.ICtStoreUserService
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.utils.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 门店会员Service业务层处理
 *
 * @author 沈兵
 * @date 2023-03-08
 */
@DataSource(DataSourceType.CT)
@Service
class CtStoreUserServiceImpl : ICtStoreUserService {
    @Autowired
    private lateinit var ctStoreUserMapper: CtStoreUserMapper

    /**
     * 查询门店会员
     *
     * @param storeId 门店会员主键
     * @return 门店会员
     */
    override fun selectCtStoreUserByStoreId(storeId: String): CtStoreUser? {
        return ctStoreUserMapper.selectCtStoreUserByStoreId(storeId)
    }

    /**
     * 查询门店会员列表
     *
     * @param ctStoreUser 门店会员
     * @return 门店会员
     */
    override fun selectCtStoreUserList(ctStoreUser: CtStoreUser): List<CtStoreUser> {
        return ctStoreUserMapper.selectCtStoreUserList(ctStoreUser)
    }

    /**
     * 新增门店会员
     *
     * @param ctStoreUser 门店会员
     * @return 结果
     */
    override fun insertCtStoreUser(ctStoreUser: CtStoreUser): Int {
        ctStoreUser.createTime = DateUtils.getNowDate()
        return ctStoreUserMapper.insertCtStoreUser(ctStoreUser)
    }

    /**
     * 修改门店会员
     *
     * @param ctStoreUser 门店会员
     * @return 结果
     */
    override fun updateCtStoreUser(ctStoreUser: CtStoreUser): Int {
        ctStoreUser.updateTime = DateUtils.getNowDate()
        return ctStoreUserMapper.updateCtStoreUser(ctStoreUser)
    }

    /**
     * 批量删除门店会员
     *
     * @param storeIds 需要删除的门店会员主键
     * @return 结果
     */
    override fun deleteCtStoreUserByStoreIds(storeIds: Array<String>): Int {
        return ctStoreUserMapper.deleteCtStoreUserByStoreIds(storeIds)
    }

    /**
     * 删除门店会员信息
     *
     * @param storeId 门店会员主键
     * @return 结果
     */
    override fun deleteCtStoreUserByStoreId(storeId: String): Int {
        return ctStoreUserMapper.deleteCtStoreUserByStoreId(storeId)
    }
}