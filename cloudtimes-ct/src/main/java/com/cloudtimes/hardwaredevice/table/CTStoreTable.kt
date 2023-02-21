package com.cloudtimes.hardwaredevice.table

import java.math.BigDecimal
import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

class CtStoreTable : AliasableSqlTable<CtStoreTable>("ct_store", ::CtStoreTable) {
    /** 编号 */
    val id = column<String>(name = "id", jdbcType = JDBCType.OTHER)

    /** 店铺名称 */
    val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

    /** 商户编号 */
    val merchantId = column<String>(name = "merchant_id", jdbcType = JDBCType.VARCHAR)

    /** 详细地址 */
    val address = column<String>(name = "address", jdbcType = JDBCType.VARCHAR)

    /** 短地址 */
    val shortAddress = column<String>(name = "short_address", jdbcType = JDBCType.VARCHAR)

    /** 门店号 */
    val storeNo = column<String>(name = "store_no", jdbcType = JDBCType.VARCHAR)

    /** 地区编码 */
    val regionCode = column<String>(name = "region_code", jdbcType = JDBCType.OTHER)

    /** 经度 */
    val longitude = column<BigDecimal>(name = "longitude", jdbcType = JDBCType.DECIMAL)

    /** 纬度 */
    val latitude = column<BigDecimal>(name = "latitude", jdbcType = JDBCType.DECIMAL)

    /** 面积 */
    val area = column<String>(name = "area", jdbcType = JDBCType.VARCHAR)

    /** 联系人姓名 */
    val contactName = column<String>(name = "contact_name", jdbcType = JDBCType.VARCHAR)

    /** 联系电话 */
    val contactPhone = column<String>(name = "contact_phone", jdbcType = JDBCType.VARCHAR)

    /** 开店费用/合同价格 */
    val saleAmount = column<BigDecimal>(name = "sale_amount", jdbcType = JDBCType.DECIMAL)

    /** 最大购物人数 */
    val maxBuyPerson = column<Int>(name = "max_buy_person", jdbcType = JDBCType.INTEGER)

    /** 是否值守 */
    val isSupervise = column<String>(name = "is_supervise", jdbcType = JDBCType.CHAR)

    /** 开设状态 */
    val buildState = column<String>(name = "build_state", jdbcType = JDBCType.CHAR)

    /** 门店状态 */
    val state = column<String>(name = "state", jdbcType = JDBCType.CHAR)

    /** 代理用户编号 */
    val agentId = column<String>(name = "agent_id", jdbcType = JDBCType.OTHER)

    /** 门店上线日期 */
    val storeOnlineDate = column<Date>(name = "store_online_date", jdbcType = JDBCType.DATE)

    /** 门店上线时间 */
    val storeOnlineTime = column<Date>(name = "store_online_time", jdbcType = JDBCType.TIMESTAMP)

    /** 是否删除 */
    val delFlag = column<String>(name = "del_flag", jdbcType = JDBCType.CHAR)

    /** 创建日期 */
    val createDate = column<Date>(name = "create_date", jdbcType = JDBCType.DATE)

    /** 创建时间 */
    val createTime = column<Date>(name = "create_time", jdbcType = JDBCType.TIMESTAMP)

    /** 修改时间 */
    val updateTime = column<Date>(name = "update_time", jdbcType = JDBCType.TIMESTAMP)

    /** 门头照片 */
    val photoUrl = column<String>(name = "photo_url", jdbcType = JDBCType.LONGVARCHAR)
}

var storeTable = CtStoreTable()