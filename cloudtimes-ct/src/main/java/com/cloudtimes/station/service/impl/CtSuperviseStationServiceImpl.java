package com.cloudtimes.station.service.impl;

import com.cloudtimes.cache.CacheVideoData;
import com.cloudtimes.cache.CtCustomerServiceCache;
import com.cloudtimes.cache.CtStoreVideoCache;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.*;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.enums.PayState;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.mq.service.CtCashMqSenderService;
import com.cloudtimes.mq.service.CtOpenDoorService;
import com.cloudtimes.partner.agora.service.CtAgoraApiService;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.hik.domain.VideoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.resources.domain.CtMediaTemplate;
import com.cloudtimes.resources.mapper.CtMediaTemplateMapper;
import com.cloudtimes.station.domain.*;
import com.cloudtimes.station.service.ICtSuperviseStationService;
import com.cloudtimes.supervise.domain.*;
import com.cloudtimes.supervise.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CtSuperviseStationServiceImpl implements ICtSuperviseStationService {
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtStoreVideoCache videoCache;
    @Autowired
    private CtDeviceMapper deviceMapper;

    /**
     * 门店区域视频树查询 用户编号
     *
     * @param userId
     * @return
     */
    @Override
    public List<VideoTreeNode> getVideoTree(Long userId) {
        Map<String, CtTask> taskMap = taskCache.getAllTasksOfStaff(String.valueOf(userId));
        List<VideoTreeNode> topList = new ArrayList<>();
        for (CtTask task :
                taskMap.values()) {
            VideoTreeNode shopNode = new VideoTreeNode();
            shopNode.setId(task.getStoreId());
            shopNode.setLabel(task.getStoreName());
            List<VideoTreeNode> areaList = new ArrayList<>();
            VideoTreeNode areaNode = new VideoTreeNode();
            areaNode.setId("0");
            areaNode.setLabel("默认区");
//            Map<String, CacheVideoData> videoMap = videoCache.getCacheVideosOfStore(task.getStoreId());
            CtDevice query = new CtDevice();
            query.setStoreId(task.getStoreId());
            query.setDeviceType(DeviceType.CAMERA.getCode());
            query.setDelFlag("0");
            List<CtDevice> cameraList = deviceMapper.selectCtDeviceList(query);
            query.setDeviceType(DeviceType.NVR_CAMERA.getCode());

            List<CtDevice> nvrCameraList = deviceMapper.selectCtDeviceList(query);
            cameraList.addAll(nvrCameraList);

            List<VideoTreeNode> videoList = new ArrayList<>();
            for (CtDevice videoData :
                    cameraList) {
                VideoTreeNode videoNode = new VideoTreeNode();
                videoNode.setId(videoData.getId());
                videoNode.setLabel(videoData.getName());
                CacheVideoData cacheVideo = videoCache.getCacheVideo(task.getStoreId(), videoData.getId());
                videoNode.setVideoUrl(cacheVideo.getUrl());
                videoNode.setToken(cacheVideo.getToken());
                videoList.add(videoNode);
            }
            areaNode.setChildren(videoList);
            areaList.add(areaNode);
            shopNode.setChildren(areaList);
            topList.add(shopNode);
        }
        return topList;
    }

    @Autowired
    private CtMediaTemplateMapper templateMapper;

    /**
     * 获取语音模版列表
     *
     * @return
     */
    @Override
    public List<GetAudioTemplateResp> getAudioTemplate() {
        List<CtMediaTemplate> templateList = templateMapper.selectCtMediaTemplateList(new CtMediaTemplate());
        List<GetAudioTemplateResp> respList = new ArrayList<>();
        for (CtMediaTemplate template :
                templateList) {
            GetAudioTemplateResp temp = new GetAudioTemplateResp();
            temp.setTemplateId(template.getId());
            temp.setTemplateName(template.getTemplateName());
            temp.setMediaUuid(template.getMediaId());
            temp.setOssLink(template.getOssLink());
            temp.setCreateTime(DateUtils.formatDateTime(template.getCreateTime()));
            temp.setUpdateTime(DateUtils.formatDateTime(template.getUpdateTime()));
            respList.add(temp);
        }
        return respList;
    }

    @Autowired
    private CtAgoraApiService agoraApiService;
    @Autowired
    private PartnerConfig config;
    @Autowired
    private CtCashMqSenderService cashMqSender;


    /**
     * 语音接入
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public JoinAudioResp joinAudio(Long userId, JoinAudioReq param) {
        JoinAudioResp joinAudioResp = new JoinAudioResp();
        if (StringUtils.equals(param.getIsJoin(), "1")) {
            int uid = 0;
            String channel = param.getShopId();
            try {
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                uid = random.nextInt(999999);
            } catch (NoSuchAlgorithmException e) {
                throw new ServiceException("产生随机用户失败：" + e.getMessage());
            }
            String agoraToken = agoraApiService.getAgoraToken(uid, channel);
            joinAudioResp.setUid(uid);
            joinAudioResp.setChannelName(channel);
            joinAudioResp.setVoiceToken(agoraToken);
            joinAudioResp.setAppId(config.getAgoraAppId());
            // 通知收银机
            cashMqSender.notifyCashDoCall(channel, "1");
        } else if (StringUtils.equals(param.getIsJoin(), "0")) {
            // 通知收银机
            cashMqSender.notifyCashDoCall(param.getShopId(), "0");
        }
        return joinAudioResp;
    }

    @Autowired
    private CtOpenDoorService openDoorService;
    @Autowired
    private CtRocketMqProducer producer;

    /**
     * 应急开门
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public void openDoor(Long userId, OpenDoorReq param) {
        OpenDoorMqData mqData = new OpenDoorMqData();
        mqData.setOption(OpenDoorOption.EMERGENCY_OPEN_DOOR);
        mqData.setStoreId(param.getShopId());
        mqData.setUserId(String.valueOf(userId));
        mqData.setChannelType(ChannelType.WEB);
        producer.send(RocketMQConstants.CT_OPEN_DOOR, mqData);
    }

    /**
     * 锁门，解锁
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public void lockDoor(Long userId, LockDoorReq param) {
        CtTask cacheTask = taskCache.getCacheTask(param.getTaskId());
        if (cacheTask == null) {
            throw new ServiceException("无法获取任务信息");
        }
        cacheTask.setOpenLock(StringUtils.equals(param.getOption(), "1"));
        taskCache.setCacheTask(cacheTask);
    }

    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtShoppingMapper shoppingMapper;
    @Autowired
    private CtEventsMapper eventsMapper;

    /**
     * 订单审核
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    @Transactional
    public void approveOrder(Long userId, String userName, ApproveOrderReq param) {
        CtOrder dbOrder = orderMapper.selectCtOrderById(param.getOrderId());
        if (dbOrder == null) {
            throw new ServiceException("无法获取任务信息");
        }
        if (StringUtils.isEmpty(dbOrder.getShoppingId())) {
            throw new ServiceException("当前订单未绑定购物");
        }
        CtShopping shopping = shoppingMapper.selectCtShoppingById(dbOrder.getShoppingId());
        if (shopping == null) {
            throw new ServiceException("无法获取购物信息");
        }
        if (StringUtils.equals(dbOrder.getState(), PayState.PAY_FAIL.getCode())) {
            // 失败订单更新结束时间和状态
            shopping.setEndTime(DateUtils.getNowDate());
            shopping.setState("1");
        }
        if (StringUtils.equals(param.getOption(), "1")) {
            if (!StringUtils.equals(dbOrder.getState(), PayState.PAY_SUCCESS.getCode()) &&
                    !StringUtils.equals(dbOrder.getState(), PayState.PAY_FAIL.getCode())) {
                throw new ServiceException("当前订单状态非最终态无法审核");
            }
            // 审核通过
            shopping.setExceptionalState("0");
            shopping.setIsApprove("0");
            shopping.setUpdateTime(DateUtils.getNowDate());
            shoppingMapper.updateCtShopping(shopping);
        } else if (StringUtils.equals(param.getOption(), "2")) {
            if (!StringUtils.equals(dbOrder.getState(), PayState.PAY_SUCCESS.getCode()) &&
                    !StringUtils.equals(dbOrder.getState(), PayState.PAY_FAIL.getCode())) {
                throw new ServiceException("当前订单状态非最终态无法审核");
            }
            // 审核拒绝
            dbOrder.setIsExceptional("1");
            orderMapper.updateCtOrder(dbOrder);
            shopping.setExceptionalState("1");
            shopping.setIsApprove("1");
            shopping.setRemark(param.getRemark());
            shopping.setUpdateTime(DateUtils.getNowDate());
            shoppingMapper.updateCtShopping(shopping);
            //新增事件
            CtEvents ctEvents = new CtEvents();
            ctEvents.setEventType(EventType.StealingMessage.getCode());
            ctEvents.setEventName("购物订单异常审核");
            ctEvents.setContent(param.getRemark());
            ctEvents.setSender(String.valueOf(userId));
            ctEvents.setSenderName(userName);
            ctEvents.setTaskId(dbOrder.getTaskId());
            ctEvents.setShoppingId(dbOrder.getShoppingId());
            ctEvents.setUserType(UserType.Service.getCode());
            ctEvents.setSourceType(ChannelType.WEB.getCode());
            ctEvents.setStopped("0");
            ctEvents.setCreateDate(DateUtils.getNowDate());
            ctEvents.setDelFlag("0");
            ctEvents.setCreateTime(DateUtils.getNowDate());
            ctEvents.setUpdateTime(DateUtils.getNowDate());
            eventsMapper.insertCtEvents(ctEvents);
        } else if (StringUtils.equals(param.getOption(), "3")) {
            if (!StringUtils.equals(dbOrder.getState(), PayState.READY_TO_PAY.getCode())) {
                throw new ServiceException("当前订单不可取消");
            }
            orderMapper.deleteCtOrderById(dbOrder.getId());
            shoppingMapper.deleteCtShoppingById(shopping.getId());
        }
        //内存删除订单和购物数据
        taskCache.deleteCacheShopping(shopping.getId());
        taskCache.deleteCacheOrder(dbOrder.getId());
    }

    /**
     * 新建事件（异常）
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public void createEvent(Long userId, String userName, CreateEventReq param) {
        //新增事件
        CtEvents ctEvents = new CtEvents();
        ctEvents.setEventType(EventType.StealingMessage.getCode());
        ctEvents.setEventName("购物订单异常审核");
        ctEvents.setContent(param.getRemark());
        ctEvents.setSender(String.valueOf(userId));
        ctEvents.setSenderName(userName);
        ctEvents.setTaskId(param.getTaskId());
        ctEvents.setUserType(UserType.Service.getCode());
        ctEvents.setSourceType(ChannelType.WEB.getCode());
        ctEvents.setStopped("0");
        ctEvents.setCreateDate(DateUtils.getNowDate());
        ctEvents.setDelFlag("0");
        ctEvents.setCreateTime(DateUtils.getNowDate());
        ctEvents.setUpdateTime(DateUtils.getNowDate());
        eventsMapper.insertCtEvents(ctEvents);
    }

    @Autowired
    private CtCustomerServiceCache customerServiceCache;

    @Autowired
    private CtCustomerServiceMapper customerServiceMapper;

    /**
     * 接单开关维护
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public void acceptTask(Long userId, AcceptTaskReq param) {
        if (StringUtils.equals(param.getOption(), AcceptTaskType.COMPLETE.getCode())) {
            //结束任务
            Map<String, CtTask> taskMap = taskCache.getAllTasksOfStaff(String.valueOf(userId));
            if (taskMap.size() > 0) {
                throw new ServiceException("当前有未完成的任务,请确认");
            }
        }
        CtCustomerService ctCustomerService = customerServiceMapper.selectCtCustomerServiceByServiceId(userId);
        if (ctCustomerService == null) {
            throw new ServiceException("无法获取客服信息");
        }
        ctCustomerService.setAcceptState(param.getOption());
        customerServiceMapper.updateCtCustomerService(ctCustomerService);
        customerServiceCache.setAcceptState(String.valueOf(userId), param.getOption());
    }

    @Autowired
    private CtTaskMapper taskMapper;

    /**
     * 结束任务
     *
     * @param userId
     * @param param
     * @return
     */
    @Transactional
    @Override
    public void finishTask(Long userId, FinishTaskReq param) {
        Map<String, CtOrder> orderMap = taskCache.getOrdersByTask(param.getTaskId());
        if (orderMap.size() > 0) {
            throw new ServiceException("订单未处理完成");
        }
        Map<String, CtShopping> shoppingMap = taskCache.getShoppingsByTask(param.getTaskId());
        if (shoppingMap.size() > 0) {
            throw new ServiceException("购物未处理完成");
        }
        CtTask cacheTask = taskCache.getCacheTask(param.getTaskId());
        cacheTask.setState("1");
        cacheTask.setEndTime(DateUtils.getNowDate());
        cacheTask.setRemark(param.getRemark());
        taskMapper.updateCtTask(cacheTask);
        taskCache.deleteCacheTask(param.getTaskId());
    }

    @Autowired
    private ICtHikApiService hikApiService;

    /**
     * 获取播放链接
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public GetLocalVideoResp getLocalVideo(Long userId, GetLocalVideoReq param) {
        String deviceId = param.getDeviceId();
        String beginTime = param.getBeginTime();
        String endTime = param.getEndTime();
        CtDevice device = deviceMapper.selectCtDeviceById(deviceId);
        if (device == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (!StringUtils.equals(device.getDeviceType(), DeviceType.CAMERA.getCode()) && !StringUtils.equals(device.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
            throw new ServiceException("设备类型错误");
        }
        int channelNo = 1;
        if (StringUtils.equals(device.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
            channelNo = device.getDeviceChannel();
        }
        VideoData playbackAddress = hikApiService.getPlaybackAddress(device.getDeviceSerial(), channelNo, "1", beginTime, endTime);
        if (playbackAddress == null) {
            throw new ServiceException("获取回放连接失败");
        }
        GetLocalVideoResp getLocalVideoResp = new GetLocalVideoResp();
        getLocalVideoResp.setUrl(playbackAddress.getUrl());
        getLocalVideoResp.setToken(playbackAddress.getToken());
        return getLocalVideoResp;
    }
}
