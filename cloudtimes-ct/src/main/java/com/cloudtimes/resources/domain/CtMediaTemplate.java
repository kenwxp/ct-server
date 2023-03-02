package com.cloudtimes.resources.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 媒体资源模板对象 ct_media_template
 *
 * @author tank
 * @date 2023-01-17
 */
@Data
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

}
