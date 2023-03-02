package com.cloudtimes.station.service;

import com.cloudtimes.station.domain.*;

import java.util.List;

public interface ICtSuperviseStationService {

    /**
     * 门店区域视频树查询 用户编号
     *
     * @param userId
     * @return
     */
    public List<VideoTreeNode> getVideoTree(Long userId);

    /**
     * 获取语音模版列表
     *
     * @return
     */
    public List<GetAudioTemplateResp> getAudioTemplate();

    /**
     * 语音接入
     *
     * @param userId
     * @param param
     * @return
     */
    public JoinAudioResp joinAudio(Long userId, JoinAudioReq param);

    /**
     * 应急开门
     *
     * @param userId
     * @param param
     * @return
     */
    public void openDoor(Long userId, OpenDoorReq param);

    /**
     * 锁门，解锁
     *
     * @param userId
     * @param param
     * @return
     */
    public void lockDoor(Long userId, LockDoorReq param);

    /**
     * 订单审核
     *
     * @param userId
     * @param param
     * @return
     */
    public void approveOrder(Long userId, String userName, ApproveOrderReq param);

    /**
     * 新建事件（异常）
     *
     * @param userId
     * @param param
     * @return
     */
    public void createEvent(Long userId, String userName, CreateEventReq param);

    /**
     * 接单开关维护
     *
     * @param userId
     * @param param
     * @return
     */
    public void acceptTask(Long userId, AcceptTaskReq param);

    /**
     * 结束任务
     *
     * @param userId
     * @param param
     * @return
     */
    public void finishTask(Long userId, FinishTaskReq param);
}
