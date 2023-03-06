package com.cloudtimes.hardwaredevice.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;

/**
 * 电子设备对象 ct_device
 * 
 * @author tank
 * @date 2023-02-07
 */
@Slf4j
@Data
public class CtDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 门店编号 */
    @Excel(name = "门店编号")
    private String storeId;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceCode;

    /** 设备类型
     * 摄像头	0
     * 收银机套件	1
     * 门禁	    2
     * 门禁刷脸   3
     * */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String deviceModel;

    /** 品牌名称 */
    @Excel(name = "品牌名称")
    private String brandName;

    /** 设备序列号 */
    @Excel(name = "设备序列号")
    private String deviceSerial;

    /** 设备位置
     * 门店1区	0
     * 门店2区	1
     * 主监控区	2
     * 监控区1	3
     * 监控区2	4
     * 监控区3	5
     * 监控区4	6
     * 监控区5	7
     * 监控区6	8
     * */
    @Excel(name = "设备位置")
    private String deviceArea;

    /** 设备验证码 */
    @Excel(name = "设备验证码")
    private String validateCode;

    /** 设备验证码 */
    @Excel(name = "设备验证码")
    private int deviceChannel;

    /** 设备归属
     * 平台	0
     * 商户	1
     * 租用	2
     * */
    @Excel(name = "设备归属")
    private String deviceAscription;

    /** 状态
     * 在线	0
     * 异常	1
     * 维护	2
     * 下线	3
     * 停用	4
     * */
    @Excel(name = "状态")
    private String state;

    /** 最近上云时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近上云时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOnlineTime;

    /** 最近下云时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近下云时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOfflineTime;

    /** 是否删除 */
    private String delFlag;

}
