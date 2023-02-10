package com.cloudtimes.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 地区信息对象 ct_region
 *
 * @author tank
 * @date 2023-01-17
 */
public class CtRegion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    private String id;

    /**
     * 地区名称
     */
    @Excel(name = "地区名称")
    private String regionName;

    /**
     * 地区缩写
     */
    @Excel(name = "地区缩写")
    private String regionShortName;

    /**
     * 地区编码
     */
    @Excel(name = "地区编码")
    private String regionCode;

    /**
     * 父地区编码
     */
    @Excel(name = "父地区编码")
    private String regionParentId;

    /**
     * 地区级别
     */
    @Excel(name = "地区级别")
    private String regionLevel;

    /**
     * 是否删除
     */
    private Long delFlag;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionShortName(String regionShortName) {
        this.regionShortName = regionShortName;
    }

    public String getRegionShortName() {
        return regionShortName;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionLevel(String regionLevel) {
        this.regionLevel = regionLevel;
    }

    public String getRegionLevel() {
        return regionLevel;
    }

    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() {
        return delFlag;
    }

    public String getRegionParentId() {
        return regionParentId;
    }

    public void setRegionParentId(String regionParentId) {
        this.regionParentId = regionParentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("regionName", getRegionName())
                .append("regionShortName", getRegionShortName())
                .append("regionCode", getRegionCode())
                .append("regionLevel", getRegionLevel())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
