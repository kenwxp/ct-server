package com.cloudtimes.hardwaredevice.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 门店对象 ct_store
 *
 * @author tank
 * @date 2023-02-09
 */
public class CtStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String name;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 短地址 */
    @Excel(name = "短地址")
    private String shortAddress;

    /** 门店号 */
    @Excel(name = "门店号")
    private String storeNo;

    /** 地区号 */
    @Excel(name = "地区号")
    private String regionCode;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal latitude;

    /** 门头照片 */
    @Excel(name = "门头照片")
    private String photoUrl;

    /** 面积 */
    @Excel(name = "面积")
    private String area;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 开店费用 */
    @Excel(name = "开店费用")
    private BigDecimal saleAmount;

    /** 最大购物人数 */
    @Excel(name = "最大购物人数")
    private Long maxBuyPerson;

    /** 是否值守 */
    @Excel(name = "是否值守")
    private String isSupervise;

    /** 值守日志编号 */
    @Excel(name = "值守日志编号")
    private String superviseLogId;

    /** 开设状态 */
    @Excel(name = "开设状态")
    private String buildState;

    /** 门店状态 */
    @Excel(name = "门店状态")
    private String state;

    /** 店老板用户编号 */
    @Excel(name = "店老板用户编号")
    private String bossId;

    /** 代理用户编号 */
    @Excel(name = "代理用户编号")
    private String agentId;

    /** 门店上线日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门店上线日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storeOnlineDate;

    /** 门店上线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门店上线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storeOnlineTime;

    /** 三方商户编号 */
    @Excel(name = "三方商户编号")
    private String merchantSn;

    /** 三方门店编号 */
    @Excel(name = "三方门店编号")
    private String storeSn;

    /** 是否删除 */
    private String delFlag;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setShortAddress(String shortAddress)
    {
        this.shortAddress = shortAddress;
    }

    public String getShortAddress()
    {
        return shortAddress;
    }
    public void setStoreNo(String storeNo)
    {
        this.storeNo = storeNo;
    }

    public String getStoreNo()
    {
        return storeNo;
    }
    public void setRegionCode(String regionCode)
    {
        this.regionCode = regionCode;
    }

    public String getRegionCode()
    {
        return regionCode;
    }
    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude()
    {
        return longitude;
    }
    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude()
    {
        return latitude;
    }
    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }
    public void setArea(String area)
    {
        this.area = area;
    }

    public String getArea()
    {
        return area;
    }
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactName()
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }
    public void setSaleAmount(BigDecimal saleAmount)
    {
        this.saleAmount = saleAmount;
    }

    public BigDecimal getSaleAmount()
    {
        return saleAmount;
    }
    public void setMaxBuyPerson(Long maxBuyPerson)
    {
        this.maxBuyPerson = maxBuyPerson;
    }

    public Long getMaxBuyPerson()
    {
        return maxBuyPerson;
    }
    public void setIsSupervise(String isSupervise)
    {
        this.isSupervise = isSupervise;
    }

    public String getIsSupervise()
    {
        return isSupervise;
    }
    public void setSuperviseLogId(String superviseLogId)
    {
        this.superviseLogId = superviseLogId;
    }

    public String getSuperviseLogId()
    {
        return superviseLogId;
    }
    public void setBuildState(String buildState)
    {
        this.buildState = buildState;
    }

    public String getBuildState()
    {
        return buildState;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }
    public void setBossId(String bossId)
    {
        this.bossId = bossId;
    }

    public String getBossId()
    {
        return bossId;
    }
    public void setAgentId(String agentId)
    {
        this.agentId = agentId;
    }

    public String getAgentId()
    {
        return agentId;
    }
    public void setStoreOnlineDate(Date storeOnlineDate)
    {
        this.storeOnlineDate = storeOnlineDate;
    }

    public Date getStoreOnlineDate()
    {
        return storeOnlineDate;
    }
    public void setStoreOnlineTime(Date storeOnlineTime)
    {
        this.storeOnlineTime = storeOnlineTime;
    }

    public Date getStoreOnlineTime()
    {
        return storeOnlineTime;
    }
    public void setMerchantSn(String merchantSn)
    {
        this.merchantSn = merchantSn;
    }

    public String getMerchantSn()
    {
        return merchantSn;
    }
    public void setStoreSn(String storeSn)
    {
        this.storeSn = storeSn;
    }

    public String getStoreSn()
    {
        return storeSn;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("address", getAddress())
                .append("shortAddress", getShortAddress())
                .append("storeNo", getStoreNo())
                .append("regionCode", getRegionCode())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("photoUrl", getPhotoUrl())
                .append("area", getArea())
                .append("contactName", getContactName())
                .append("contactPhone", getContactPhone())
                .append("saleAmount", getSaleAmount())
                .append("maxBuyPerson", getMaxBuyPerson())
                .append("isSupervise", getIsSupervise())
                .append("superviseLogId", getSuperviseLogId())
                .append("buildState", getBuildState())
                .append("state", getState())
                .append("bossId", getBossId())
                .append("agentId", getAgentId())
                .append("storeOnlineDate", getStoreOnlineDate())
                .append("storeOnlineTime", getStoreOnlineTime())
                .append("merchantSn", getMerchantSn())
                .append("storeSn", getStoreSn())
                .append("delFlag", getDelFlag())
                .append("createDate", getCreateDate())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
