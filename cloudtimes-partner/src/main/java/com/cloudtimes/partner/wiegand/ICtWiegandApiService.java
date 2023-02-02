package com.cloudtimes.partner.wiegand;

public interface ICtWiegandApiService {
    /**
     * 远程开门
     *
     * @param deviceSerial 门控序列号
     * @return boolean 是否成功
     */
    public boolean remoteOpenDoor(int deviceSerial);

    /**
     * 设置门禁密码
     *
     * @param deviceSerial 设备序列号
     * @param password     密码
     * @param once         单次有效
     * @param beginTime    生效时间 格式 yyyy-MM-dd hh:mm:ss
     * @param endTime      失效时间 格式 yyyy-MM-dd hh:mm:ss
     * @return boolean 是否成功
     */
    public boolean settingAccess(int deviceSerial, int password, boolean once, String beginTime, String endTime);

    /**
     * 删除门禁密码
     *
     * @param deviceSerial 设备序列号
     * @param password     密码
     * @return boolean 是否成功
     */
    public boolean deleteAccess(int deviceSerial, int password);

    /**
     * 设置门禁参数
     *
     * @param deviceSerial 设备序列号
     * @param mode         控制方式 0-在线 1-常开 2-常闭
     * @param delaySec     开门延时(秒) 默认3秒, 可设置25秒
     * @return boolean 是否成功
     */
    public boolean settingParams(int deviceSerial, int mode, int delaySec);

    /**
     * 启用6位临时密码功能
     *
     * @param deviceSerial 设备序列号
     * @param enable       是否开启
     * @return
     */
    public boolean enablePassword(int deviceSerial, boolean enable);
}