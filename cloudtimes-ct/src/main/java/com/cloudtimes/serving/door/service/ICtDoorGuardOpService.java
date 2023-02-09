package com.cloudtimes.serving.door.service;

import com.cloudtimes.common.enums.ChannelType;

public interface ICtDoorGuardOpService {
    /**
     * 交易开门
     * 小程序，刷脸，支付订单成功后调用
     *
     * @param storeId
     * @return
     */

    public boolean transOpen(String storeId, String userId, ChannelType channelType);

    /**
     * 应急开门
     * 管理端开门
     *
     * @param storeId
     * @param userId
     * @return
     */

    public boolean emergentOpen(String storeId, String userId);

    /**
     * 店家开门
     * 店家app端
     *
     * @param storeId
     * @param userId
     * @return
     */
    public boolean ownerOpen(String storeId, String userId);

    /**
     * 强锁
     * 设置后，门禁将无法远程开门，和触发开门
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    public boolean forceLock(String storeId, String userId, ChannelType channelType);

    /**
     * 解强锁
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    public boolean unlock(String storeId, String userId, ChannelType channelType);

    /**
     * 设置门禁密码
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    public boolean setDoorAccess(String storeId, String userId, ChannelType channelType, boolean once, String beginTime, String endTime);

    /**
     * 取消门禁密码
     *
     * @param storeId
     * @param userId
     * @param channelType
     * @return
     */
    public boolean disableDoorAccess(String storeId, String userId, ChannelType channelType);
}
