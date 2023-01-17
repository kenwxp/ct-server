package com.cloudtimes.hardwaredevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 门店对象 ct_store
 * 
 * @author tank
 * @date 2023-01-17
 */
public class CtStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String name;

    /** 门店编码 */
    @Excel(name = "门店编码")
    private String storeCode;

    /** 商户ID */
    @Excel(name = "商户ID")
    private String merchantCode;

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
    private Long longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private Long latitude;

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

    /** 是否值守 */
    @Excel(name = "是否值守")
    private Long isSupervise;

    /** 最大购物人数 */
    @Excel(name = "最大购物人数")
    private Long maxBuyPerson;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    /** 是否删除 */
    private Long delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
    }
    public void setMerchantCode(String merchantCode) 
    {
        this.merchantCode = merchantCode;
    }

    public String getMerchantCode() 
    {
        return merchantCode;
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
    public void setLongitude(Long longitude) 
    {
        this.longitude = longitude;
    }

    public Long getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(Long latitude) 
    {
        this.latitude = latitude;
    }

    public Long getLatitude() 
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
    public void setIsSupervise(Long isSupervise) 
    {
        this.isSupervise = isSupervise;
    }

    public Long getIsSupervise() 
    {
        return isSupervise;
    }
    public void setMaxBuyPerson(Long maxBuyPerson) 
    {
        this.maxBuyPerson = maxBuyPerson;
    }

    public Long getMaxBuyPerson() 
    {
        return maxBuyPerson;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("storeCode", getStoreCode())
            .append("merchantCode", getMerchantCode())
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
            .append("isSupervise", getIsSupervise())
            .append("maxBuyPerson", getMaxBuyPerson())
            .append("state", getState())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
