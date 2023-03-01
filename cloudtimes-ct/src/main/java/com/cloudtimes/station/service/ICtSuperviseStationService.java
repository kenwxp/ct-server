package com.cloudtimes.station.service;

public interface ICtSuperviseStationService {
    // 门店区域视频树查询
    public void getVideoTree() ;

    // 统计当前值守员当日工作量及任务量
    public void getStaffStatData() ;

    // 获取语音模版列表
    public void getAudioTemplate() ;

    //语音接入
    public void joinAudio() ;

    // 应急开门
    public void openDoor() ;

    // 锁门，解锁
    public void lockDoor() ;

    // 订单审核
    public void approveOrder() ;

    // 新建事件（异常）
    public void createEvent() ;

    // 接单开关维护
    public void acceptTask() ;

    // 结束任务
    public void finishTask() ;
}
