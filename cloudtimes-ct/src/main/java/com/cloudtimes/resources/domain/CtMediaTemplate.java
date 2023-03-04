package com.cloudtimes.resources.domain;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 媒体资源模板对象 ct_media_template
 *
 * @author tank
 * @date 2023-01-17
 */
public class CtMediaTemplate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 序列号
     */
    private String id;


    /**
     * 模板名称
     */
    @Excel(name = "模板名称")
    private String templateName;

    /**
     * 媒体编码
     */
    @Excel(name = "媒体编码")
    private String mediaId;
    /**
     * 播放链接
     */
    @Excel(name = "播放链接")
    private String ossLink;

    /**
     * 是否删除
     */
    private Long delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getOssLink() {
        return ossLink;
    }

    public void setOssLink(String ossLink) {
        this.ossLink = ossLink;
    }

    public Long getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }
}
