package com.cloudtimes.resources.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 媒体信息对象 ct_media
 * 
 * @author tank
 * @date 2023-01-17
 */
public class CtMedia extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序列号 */
    private Long id;

    /** 媒体编码 */
    @Excel(name = "媒体编码")
    private String mediaCode;

    /** 关连编码 */
    @Excel(name = "关连编码")
    private String linkCode;

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
    private Long enabled;

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
    public void setMediaCode(String mediaCode) 
    {
        this.mediaCode = mediaCode;
    }

    public String getMediaCode() 
    {
        return mediaCode;
    }
    public void setLinkCode(String linkCode) 
    {
        this.linkCode = linkCode;
    }

    public String getLinkCode() 
    {
        return linkCode;
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
    public void setEnabled(Long enabled) 
    {
        this.enabled = enabled;
    }

    public Long getEnabled() 
    {
        return enabled;
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
            .append("mediaCode", getMediaCode())
            .append("linkCode", getLinkCode())
            .append("mediaType", getMediaType())
            .append("fileName", getFileName())
            .append("fileSize", getFileSize())
            .append("saveType", getSaveType())
            .append("ossLink", getOssLink())
            .append("enabled", getEnabled())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
