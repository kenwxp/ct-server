package com.cloudtimes.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 媒体对象 ct_media
 * 
 * @author tank
 * @date 2023-02-10
 */
public class CtMedia extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private String id;

    /** 关连编号 */
    @Excel(name = "关连编号")
    private String linkId;

    /** 媒体类型 */
    @Excel(name = "媒体类型")
    private String mediaType;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long fileSize;

    /** 存储介质类型 */
    @Excel(name = "存储介质类型")
    private String saveType;

    /** 访问链接 */
    @Excel(name = "访问链接")
    private String ossLink;

    /** 启用标志 */
    @Excel(name = "启用标志")
    private String isEnabled;

    /** 是否删除 */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setLinkId(String linkId) 
    {
        this.linkId = linkId;
    }

    public String getLinkId() 
    {
        return linkId;
    }
    public void setMediaType(String mediaType) 
    {
        this.mediaType = mediaType;
    }

    public String getMediaType() 
    {
        return mediaType;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }
    public void setSaveType(String saveType) 
    {
        this.saveType = saveType;
    }

    public String getSaveType() 
    {
        return saveType;
    }
    public void setOssLink(String ossLink) 
    {
        this.ossLink = ossLink;
    }

    public String getOssLink() 
    {
        return ossLink;
    }
    public void setIsEnabled(String isEnabled) 
    {
        this.isEnabled = isEnabled;
    }

    public String getIsEnabled() 
    {
        return isEnabled;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("linkId", getLinkId())
            .append("mediaType", getMediaType())
            .append("fileName", getFileName())
            .append("fileSize", getFileSize())
            .append("saveType", getSaveType())
            .append("ossLink", getOssLink())
            .append("isEnabled", getIsEnabled())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
