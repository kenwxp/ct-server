package com.cloudtimes.hardwaredevice.service.impl

import com.cloudtimes.account.domain.CtUser
import com.cloudtimes.account.mapper.CtUserMapper
import com.cloudtimes.account.mapper.provider.CtUserProvider
import com.cloudtimes.common.annotation.DataSource
import com.cloudtimes.common.enums.DataSourceType
import com.cloudtimes.common.enums.ShopBuildState
import com.cloudtimes.common.enums.YesNoState
import com.cloudtimes.common.exception.ServiceException
import com.cloudtimes.common.utils.DateUtils
import com.cloudtimes.hardwaredevice.domain.CtStore
import com.cloudtimes.hardwaredevice.dto.request.RegisterStoreRequest
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper
import com.cloudtimes.hardwaredevice.service.ICtStoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 * 门店Service业务层处理
 *
 * @author tank
 * @date 2023-01-17
 */
@DataSource(DataSourceType.CT)
@Service
class CtStoreServiceImpl : ICtStoreService {
    @Autowired
    private lateinit var storeMapper: CtStoreMapper

    @Autowired
    private lateinit var userMapper: CtUserMapper

    /** 获取下一个店铺编号 */
    fun nextStoreNo(): String {
        val now = LocalDate.now()
        val nextStoreNo = storeMapper.selectNextStoreNo();
        return String.format("CT%d%02d%05d", now.year, now.monthValue, nextStoreNo)
    }

    /**
     * 查询门店
     *
     * @param id 门店主键
     * @return 门店
     */
    override fun selectCtStoreById(id: String): CtStore? {
        return storeMapper.selectCtStoreById(id)
    }

    /**
     * 查询门店列表
     *
     * @param ctStore 门店
     * @return 门店
     */
    override fun selectCtStoreList(ctStore: CtStore): List<CtStore> {
        return storeMapper.selectCtStoreList(ctStore)
    }

    override fun registerStore(request: RegisterStoreRequest): CtStore? {
        // Step 1. 查询用户信息
        val existUser = userMapper.selectOne(CtUserProvider.selectUserByUnionId(request.wxUnionId!!)) ?:
            throw ServiceException("数据库异常，查询微信用户失败")

        // Step 2. 首次登记为店主时更新店主状态
        if ( existUser.mobile == null || existUser.isShopBoss == null || existUser.isShopBoss == YesNoState.No.code ) {
            existUser.mobile = request.mobile
            existUser.realName = request.shopKeeper
            existUser.isShopBoss = YesNoState.Yes.code
            userMapper.updateCtUser(existUser)
        }

        // Step 3. 获取代理用户
        var agent: CtUser? = null;
        if (request.inviteCode != null) {
            agent = userMapper.selectOne(CtUserProvider.selectAgentByInviteCode(request.inviteCode!!));
        }

        // Step 3. 增加店铺信息
        val store = CtStore().apply {
            name = request.shopName
            storeNo = nextStoreNo()
            address = request.address
            regionCode = request.regionCode
            latitude = request.latitude
            longitude = request.longitude
            contactName = request.shopKeeper
            contactPhone = request.mobile
            buildState = ShopBuildState.Applying.code
            bossId = existUser.id
            agentId = agent?.id
        }
        insertCtStore(store)

        return null
    }

    /**
     * 新增门店
     *
     * @param ctStore 门店
     * @return 结果
     */
    override fun insertCtStore(ctStore: CtStore): Int {
        // 逻辑赋值字段
        ctStore.apply {
            storeNo = nextStoreNo()
            isSupervise = "0"
            buildState = "0"
            state = "0"
            delFlag = "0"
            createDate = DateUtils.getNowDate()
            createTime = DateUtils.getNowDate()
            updateTime = DateUtils.getNowDate()
        }
        return storeMapper.insertCtStore(ctStore)
    }

    /**
     * 修改门店
     *
     * @param ctStore 门店
     * @return 结果
     */
    override fun updateCtStore(ctStore: CtStore): Int {
        ctStore.updateTime = DateUtils.getNowDate()
        return storeMapper.updateCtStore(ctStore)
    }

    /**
     * 批量删除门店
     *
     * @param ids 需要删除的门店主键
     * @return 结果
     */
    override fun deleteCtStoreByIds(ids: Array<String>): Int {
        return storeMapper.deleteCtStoreByIds(ids)
    }

    /**
     * 删除门店信息
     *
     * @param id 门店主键
     * @return 结果
     */
    override fun deleteCtStoreById(id: String): Int {
        return storeMapper.deleteCtStoreById(id)
    }
}