package com.cloudtimes.hardwaredevice.mapper

import com.cloudtimes.hardwaredevice.domain.CtStore
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

/**
 * 门店Mapper接口
 *
 * @author tank
 * @date 2023-01-17
 */
@Mapper
interface CtStoreMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<CtStore?>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CtStoreResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.OTHER, id=true),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="merchant_id", property="merchantId", jdbcType=JdbcType.VARCHAR),
        Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        Result(column="short_address", property="shortAddress", jdbcType=JdbcType.VARCHAR),
        Result(column="store_no", property="storeNo", jdbcType=JdbcType.VARCHAR),
        Result(column="region_id", property="regionId", jdbcType=JdbcType.OTHER),
        Result(column="longitude", property="longitude", jdbcType=JdbcType.DECIMAL),
        Result(column="latitude", property="latitude", jdbcType=JdbcType.DECIMAL),
        Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        Result(column="contact_name", property="contactName", jdbcType=JdbcType.VARCHAR),
        Result(column="contact_phone", property="contactPhone", jdbcType=JdbcType.VARCHAR),
        Result(column="sale_amount", property="saleAmount", jdbcType=JdbcType.DECIMAL),
        Result(column="dividend_rate", property="dividendRate", jdbcType=JdbcType.DECIMAL),
        Result(column="valid_distance", property="validDistance", jdbcType=JdbcType.DECIMAL),
        Result(column="max_buy_person", property="maxBuyPerson", jdbcType=JdbcType.INTEGER),
        Result(column="is_supervise", property="isSupervise", jdbcType=JdbcType.CHAR),
        Result(column="build_state", property="buildState", jdbcType=JdbcType.CHAR),
        Result(column="state", property="state", jdbcType=JdbcType.CHAR),
        Result(column="agent_id", property="agentId", jdbcType=JdbcType.OTHER),
        Result(column="store_online_date", property="storeOnlineDate", jdbcType=JdbcType.DATE),
        Result(column="store_online_time", property="storeOnlineTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="del_flag", property="delFlag", jdbcType=JdbcType.CHAR),
        Result(column="create_date", property="createDate", jdbcType=JdbcType.DATE),
        Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        Result(column="photo_url", property="photoUrl", jdbcType=JdbcType.LONGVARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<CtStore>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CtStoreResult")
    fun selectOne(selectStatement: SelectStatementProvider): CtStore?

    /**
     * 查询门店
     *
     * @param id 门店主键
     * @return 门店
     */
    fun selectCtStoreById(id: String): CtStore?

    /**
     * 查询门店
     *
     * @param storeNo 门店号
     * @return 门店
     */
    fun selectCtStoreByStoreNo(storeNo: String): CtStore?

    /**
     * 查询门店列表
     *
     * @param ctStore 门店
     * @return 门店集合
     */
    fun selectCtStoreList(ctStore: CtStore): List<CtStore>

    /**
     * 新增门店
     *
     * @param ctStore 门店
     * @return 结果
     */
    fun insertCtStore(ctStore: CtStore?): Int

    /**
     * 修改门店
     *
     * @param ctStore 门店
     * @return 结果
     */
    fun updateCtStore(ctStore: CtStore): Int

    /**
     * 删除门店
     *
     * @param id 门店主键
     * @return 结果
     */
    fun deleteCtStoreById(id: String): Int

    /**
     * 批量删除门店
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    fun deleteCtStoreByIds(ids: Array<String>): Int
}