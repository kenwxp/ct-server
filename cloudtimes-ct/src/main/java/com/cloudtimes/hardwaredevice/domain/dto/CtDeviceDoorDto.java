package com.cloudtimes.hardwaredevice.domain.dto;

import com.cloudtimes.common.annotation.Excel;
import com.cloudtimes.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 门禁设备密码对象 ct_device_door
 *
 * @author tank
 * @date 2023-02-09
 */
@Data
@Slf4j
public class CtDeviceDoorDto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 门禁号
     */
    @Excel(name = "门禁号")
    private Long doorNo;

    /**
     * 门禁密码
     */
    @Excel(name = "门禁密码")
    private String accessPassword;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 设备序列号 冗余
     */
    private String deviceSerial;

    private String deviceId;

    private String doorDesc;

    private String deviceName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
