package com.cloudtimes.hardwaredevice.domain

import com.cloudtimes.common.annotation.Excel
import com.cloudtimes.common.core.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import java.math.BigDecimal
import java.util.*

/**
 * 门店对象 ct_store
 *
 * @author tank
 * @date 2023-02-09
 */
@ApiModel(value = "CtStore", description = "店铺")
class CtStore : BaseEntity() {
    @ApiModelProperty(value = "编号")
    var id: String? = null

    @ApiModelProperty(value = "店铺名称")
    @Excel(name = "店铺名称")
    var name: String? = null

    @ApiModelProperty(value = "详细地址")
    @Excel(name = "详细地址")
    var address: String? = null

    @ApiModelProperty(value = "短地址")
    @Excel(name = "短地址")
    var shortAddress: String? = null

    @ApiModelProperty(value = "门店号")
    @Excel(name = "门店号")
    var storeNo: String? = null

    @ApiModelProperty(value = "地区号")
    @Excel(name = "地区号")
    var regionCode: String? = null

    @ApiModelProperty(value = "经度")
    @Excel(name = "经度")
    var longitude: BigDecimal? = null

    @ApiModelProperty(value = "纬度")
    @Excel(name = "纬度")
    var latitude: BigDecimal? = null

    @ApiModelProperty(value = "门头照片")
    @Excel(name = "门头照片")
    var photoUrl: String? = null

    @ApiModelProperty(value = "面积")
    @Excel(name = "面积")
    var area: String? = null

    @ApiModelProperty(value = "联系人姓名")
    @Excel(name = "联系人姓名")
    var contactName: String? = null

    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    var contactPhone: String? = null

    @ApiModelProperty(value = "开店费用")
    @Excel(name = "开店费用")
    var saleAmount: BigDecimal? = null

    @ApiModelProperty(value = "扣减比例(单位%)')")
    @Excel(name = "扣减比例")
    var dividendRate: BigDecimal? = null

    @ApiModelProperty(value = "扫码有效距离(单位米)")
    @Excel(name = "扫码有效距离(单位米)")
    var validDistance: BigDecimal? = null

    @ApiModelProperty(value = "最大购物人数")
    @Excel(name = "最大购物人数")
    var maxBuyPerson: Long? = null

    @ApiModelProperty(value = "是否值守")
    @Excel(name = "是否值守")
    @get:JvmName("getIsSupervise")
    @set:JvmName("setIsSupervise")
    var isSupervise: String? = null

    @ApiModelProperty(value = "值守日志编号")
    @Excel(name = "值守日志编号")
    var superviseLogId: String? = null

    @ApiModelProperty(value = "开设状态")
    @Excel(name = "开设状态")
    var buildState: String? = null

    @ApiModelProperty(value = "门店状态")
    @Excel(name = "门店状态")
    var state: String? = null

    @ApiModelProperty(value = "店老板用户编号")
    @Excel(name = "店老板用户编号")
    var bossId: String? = null

    @ApiModelProperty(value = "代理用户编号")
    @Excel(name = "代理用户编号")
    var agentId: String? = null

    @ApiModelProperty(value = "门店上线日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门店上线日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var storeOnlineDate: Date? = null

    @ApiModelProperty(value = "门店上线时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门店上线时间", width = 30.0, dateFormat = "yyyy-MM-dd")
    var storeOnlineTime: Date? = null

    @ApiModelProperty(value = "三方商户编号")
    @Excel(name = "三方商户编号")
    var merchantSn: String? = null

    @ApiModelProperty(value = "三方门店编号")
    @Excel(name = "三方门店编号")
    var storeSn: String? = null

    @ApiModelProperty(value = "蓉城易购门店id")
    @Excel(name = "蓉城易购门店id")
    var rcygStoreId: String? = null

    @ApiModelProperty(value = "蓉城易购门店手机号")
    @Excel(name = "蓉城易购门店手机号")
    var rcygStorePhone: String? = null

    @ApiModelProperty(value = "是否删除")
    var delFlag: String? = null

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30.0, dateFormat = "yyyy-MM-dd")
    var createDate: Date? = null

    override fun toString(): String {
        return ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", id)
            .append("name", name)
            .append("address", address)
            .append("shortAddress", shortAddress)
            .append("storeNo", storeNo)
            .append("regionCode", regionCode)
            .append("longitude", longitude)
            .append("latitude", latitude)
            .append("photoUrl", photoUrl)
            .append("area", area)
            .append("contactName", contactName)
            .append("contactPhone", contactPhone)
            .append("saleAmount", saleAmount)
            .append("maxBuyPerson", maxBuyPerson)
            .append("isSupervise", isSupervise)
            .append("superviseLogId", superviseLogId)
            .append("buildState", buildState)
            .append("state", state)
            .append("bossId", bossId)
            .append("agentId", agentId)
            .append("storeOnlineDate", storeOnlineDate)
            .append("storeOnlineTime", storeOnlineTime)
            .append("merchantSn", merchantSn)
            .append("storeSn", storeSn)
            .append("delFlag", delFlag)
            .append("createDate", createDate)
            .append("createTime", createTime)
            .append("updateTime", updateTime)
            .toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
