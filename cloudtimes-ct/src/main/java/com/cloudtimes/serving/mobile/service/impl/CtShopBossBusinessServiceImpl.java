package com.cloudtimes.serving.mobile.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.cache.CacheVideoData;
import com.cloudtimes.cache.CtStoreVideoCache;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.common.utils.SecurityUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.DeviceType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceNum;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.domain.CtSuperviseLogs;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.hardwaredevice.mapper.CtSuperviseLogsMapper;
import com.cloudtimes.mq.service.CtCashMqSenderService;
import com.cloudtimes.mq.service.CtWebMqSenderService;
import com.cloudtimes.partner.hik.domain.VideoData;
import com.cloudtimes.partner.hik.service.ICtHikApiService;
import com.cloudtimes.resources.service.ICtRegionService;
import com.cloudtimes.serving.common.CtTaskInnerService;
import com.cloudtimes.serving.mobile.domain.*;
import com.cloudtimes.serving.mobile.service.ICtShopBossBusinessService;
import com.cloudtimes.supervise.domain.*;
import com.cloudtimes.supervise.mapper.CtOrderDetailMapper;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import com.cloudtimes.supervise.mapper.CtTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
@DataSource(DataSourceType.CT)
@Service
@Slf4j
public class CtShopBossBusinessServiceImpl implements ICtShopBossBusinessService {

    @Autowired
    private CtUserMapper userMapper;
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtSuperviseLogsMapper superviseLogsMapper;
    @Autowired
    private CtCashMqSenderService cashMqSender;
    @Autowired
    private CtRocketMqProducer producer;

    @Autowired
    private CtTaskInnerService taskInnerService;

    @Autowired
    private CtTaskMapper taskMapper;

    @Autowired
    private CtWebMqSenderService webMqSenderService;

    @Autowired
    private ICtRegionService regionService;

    @Override
    public void changePassword(String userId, ChangePasswordReq param) {
        CtUser dbUser = userMapper.selectCtUserById(userId);
        if (dbUser == null) {
            throw new ServiceException("无法获取用户信息");
        }
        String oldEncrypt = SecurityUtils.encryptPassword(param.getPasswordOld());
        String newEncrypt = SecurityUtils.encryptPassword(param.getPasswordNew());
        if (!StringUtils.equals(oldEncrypt, dbUser.getPassword())) {
            throw new ServiceException("旧密码错误，请确认");
        }
        dbUser.setPassword(newEncrypt);
        if (userMapper.updateCtUser(dbUser) < 1) {
            throw new ServiceException("更新密码失败");
        }
    }

    @Transactional
    @Override
    public void applySupervise(String userId, ApplySuperviseReq param) {
        CtUser dbUser = userMapper.selectCtUserById(userId);
        if (dbUser == null) {
            throw new ServiceException("无法获取用户信息");
        }
        if (!StringUtils.equals(dbUser.getIsShopBoss(), "1")) {
            throw new ServiceException("当前用户不是店老板");
        }
        CtStore dbStore = storeMapper.selectCtStoreById(param.getStoreId());
        if (!StringUtils.equals(dbStore.getBossId(), userId)) {
            throw new ServiceException("当前门店不属于该用户");
        }
        if (StringUtils.equals(param.getOptFlag(), "1") && StringUtils.equals(dbStore.getIsSupervise(), "1")) {
            throw new ServiceException("当前门店已在值守,请勿重复申请");
        }
        if (StringUtils.equals(param.getOptFlag(), "0") && StringUtils.equals(dbStore.getIsSupervise(), "0")) {
            throw new ServiceException("当前门店不在值守中，无法取消");
        }
        if (StringUtils.equals(param.getOptFlag(), "1")) {
            //申请云值守
            //生产值守日志
            CtSuperviseLogs newLog = new CtSuperviseLogs();
            newLog.setUserId(userId);
            newLog.setStoreId(dbStore.getId());
            newLog.setStartTime(new Date());
            newLog.setCreateTime(new Date());
            newLog.setUpdateTime(new Date());
            newLog.setDelFlag("0");
            if (superviseLogsMapper.insertCtSuperviseLogs(newLog) < 1) {
                throw new ServiceException("新增值守日志异常");
            }
            //更新门店值守状态
            String isSupervise = "1";
            dbStore.setIsSupervise(isSupervise);
            dbStore.setSuperviseLogId(newLog.getId());
            dbStore.setUpdateTime(new Date());
            if (storeMapper.updateCtStore(dbStore) < 1) {
                throw new ServiceException("更新门店值守状态异常");
            }
            //通知收银机进行状态转换
            cashMqSender.notifyCashDutyStatus(dbStore.getId(), isSupervise);
            // 发送app客户端消息
            CtEvents newEvent = new CtEvents();
            newEvent.setEventName("云值守上线通知");
            newEvent.setContent("您的店铺+" + dbStore.getName() + "已提交云值守申请，由客服人员看店，如需取消云值守，请在当前app上申请下线");
            newEvent.setReceiver(userId);
            newEvent.setReceiverName(dbUser.getNickName());
            webMqSenderService.sendShopkeeperMessage(newEvent);
            // 解锁门禁
            producer.send(RocketMQConstants.CT_OPEN_DOOR, new OpenDoorMqData(OpenDoorOption.UNLOCK_DOOR, dbStore.getId(), userId, ChannelType.MOBILE));
        } else if (StringUtils.equals(param.getOptFlag(), "0")) {
            //取消云值守
            //获取并更新值守日志
            CtSuperviseLogs updateLog = new CtSuperviseLogs();
            updateLog.setId(dbStore.getSuperviseLogId());
            updateLog.setEndTime(new Date());
            updateLog.setUpdateTime(new Date());
            if (superviseLogsMapper.updateCtSuperviseLogs(updateLog) < 1) {
                throw new ServiceException("更新值守日志异常");
            }
            //更新门店值守状态
            String isSupervise = "0";
            dbStore.setIsSupervise(isSupervise);
            dbStore.setSuperviseLogId("");
            if (storeMapper.updateCtStore(dbStore) < 1) {
                throw new ServiceException("更新门店值守状态异常");
            }
            //通知收银机进行状态转换
            cashMqSender.notifyCashDutyStatus(dbStore.getId(), isSupervise);
            // 发送值守端结束任务通知
            CtTask query = new CtTask();
            query.setStoreId(dbStore.getId());
            query.setState("0");
            List<CtTask> dbTasks = taskMapper.selectCtTaskList(query);
            if (dbTasks != null && dbTasks.size() > 0) {
                // 有进行中的任务，则通知值守员结束任务
                // 发送进客提醒给客服,及时
                for (CtTask task :
                        dbTasks) {
                    CtEvents newEvent = new CtEvents();
                    newEvent.setEventName("值守下线通知");
                    newEvent.setContent(dbStore.getName() + "已提交下线申请，请及时处理并结束任务");
                    newEvent.setReceiver(task.getStaffCode());
                    newEvent.setTaskId(task.getId());
                    webMqSenderService.sendCustomerServiceMessage(newEvent);
                }
            }
            // 发送app客户端消息
            CtEvents newEvent = new CtEvents();
            newEvent.setEventName("云值守下线通知");
            newEvent.setContent("您的店铺+" + dbStore.getName() + "已提交下线申请，门店将处在关停状态，请及时接管门店。请如需重新上线，请在当前app上申请云值守");
            newEvent.setReceiver(userId);
            newEvent.setReceiverName(dbUser.getNickName());
            webMqSenderService.sendShopkeeperMessage(newEvent);
            //强锁门禁
            producer.send(RocketMQConstants.CT_OPEN_DOOR, new OpenDoorMqData(OpenDoorOption.FORCE_LOCK_DOOR, dbStore.getId(), userId, ChannelType.MOBILE));
        }
    }


    /**
     * 查询门店列表
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public List<GetShopListResp> getShopList(String userId, GetShopListReq param) {
        CtStore query = new CtStore();
        if (StringUtils.isNotEmpty(param.getShopName())) {
            query.setName(param.getShopName());
        }
        if (StringUtils.isNotEmpty(param.getIsSupervise())) {
            query.setIsSupervise(param.getIsSupervise());
        }
        query.setBossId(userId);
        query.setDelFlag("0");
        List<CtStore> ctStores = storeMapper.selectCtStoreList(query);
        List<CtDeviceNum> ctDeviceNumList = deviceMapper.selectDeviceNumGroupByStore();
        ArrayList<GetShopListResp> retList = new ArrayList<>();
        for (CtStore ctStore :
                ctStores) {
            GetShopListResp getShopListResp = new GetShopListResp();
            getShopListResp.setShopId(ctStore.getId());
            getShopListResp.setShopNo(ctStore.getStoreNo());
            getShopListResp.setShopName(ctStore.getName());
            getShopListResp.setShortAddress(ctStore.getShortAddress());
            getShopListResp.setRegionName(regionService.getRegionName(ctStore.getRegionCode()));
            getShopListResp.setIsSupervise(ctStore.getIsSupervise());
            for (CtDeviceNum deviceNum :
                    ctDeviceNumList) {
                if (StringUtils.equals(deviceNum.getStoreId(), ctStore.getId())) {
                    getShopListResp.setOnLineDeviceNum(String.valueOf(deviceNum.getOnline()));
                    getShopListResp.setOffLineDeviceNum(String.valueOf(deviceNum.getOffline()));
                }
            }
            retList.add(getShopListResp);
        }
        return retList;
    }

    /**
     * 查询门店详情
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public GetShopDetailResp getShopDetail(String userId, GetShopDetailReq param) {
        CtStore ctStore = storeMapper.selectCtStoreById(param.getShopId());
        GetShopDetailResp getShopDetailResp = new GetShopDetailResp();
        if (ctStore != null) {
            getShopDetailResp.setShopNo(ctStore.getStoreNo());
            getShopDetailResp.setShopName(ctStore.getName());
            getShopDetailResp.setAddress(ctStore.getAddress());
            getShopDetailResp.setContactName(ctStore.getContactName());
            getShopDetailResp.setContactPhone(ctStore.getContactPhone());
            getShopDetailResp.setState(ctStore.getState());
            getShopDetailResp.setIsSupervise(ctStore.getIsSupervise());
        }
        return getShopDetailResp;
    }

    @Autowired
    private CtShoppingMapper shoppingMapper;

    /**
     * 查询门店访问人数排行
     *
     * @return
     */
    @Override
    public List<GetShopRankResp> getShopRank(String userId) {
        List<CtShoppingNum> numList = shoppingMapper.selectShoppingNumGroupByStore(userId);
        List<GetShopRankResp> retList = new ArrayList<>();
        for (CtShoppingNum shoppingNum :
                numList) {
            GetShopRankResp getShopRankResp = new GetShopRankResp();
            getShopRankResp.setShopName(shoppingNum.getStoreName());
            getShopRankResp.setVisitNum(String.valueOf(shoppingNum.getTotalVisit()));
            retList.add(getShopRankResp);
        }
        return retList;
    }

    /**
     * 店家开门
     *
     * @param userId
     * @param param
     */
    @Override
    public void getShopOpenDoor(String userId, GetShopDetailReq param) {
        OpenDoorMqData mqData = new OpenDoorMqData();
        mqData.setOption(OpenDoorOption.OWNER_OPEN_DOOR);
        mqData.setStoreId(param.getShopId());
        mqData.setUserId(String.valueOf(userId));
        mqData.setChannelType(ChannelType.MOBILE);

        producer.send(RocketMQConstants.CT_OPEN_DOOR, mqData);
    }

    @Autowired
    private CtStoreVideoCache videoCache;

    /**
     * 查询门店设备列表
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public List<GetDeviceListResp> getDeviceList(String userId, GetDeviceListReq param) {
        CtDevice query = new CtDevice();
        query.setStoreId(param.getShopId());
        query.setDeviceType(DeviceType.CAMERA.getCode());
        query.setDelFlag("0");
        List<CtDevice> cameraList = deviceMapper.selectCtDeviceList(query);
        query.setDeviceType(DeviceType.NVR_CAMERA.getCode());
        List<CtDevice> nvrCameraList = deviceMapper.selectCtDeviceList(query);
        cameraList.addAll(nvrCameraList);
        List<GetDeviceListResp> retList = new ArrayList<>();
        if (param.getQueryType() == 1) {
            for (CtDevice camera :
                    cameraList) {
                GetDeviceListResp temp = new GetDeviceListResp();
                temp.setDeviceName(camera.getName());
                temp.setDeviceSerial(camera.getDeviceSerial());
                temp.setDevicePosition(camera.getDeviceArea());
                temp.setDeviceStatus(camera.getState());
                temp.setUpdateTime(DateUtil.formatDateTime(camera.getUpdateTime()));
                CacheVideoData cacheVideo = videoCache.getCacheVideo(camera.getStoreId(), camera.getId());
                temp.setToken(cacheVideo.getToken());
                temp.setVideoUrl(cacheVideo.getUrl());
//                temp.setImageUrl();
            }
        } else {
            for (CtDevice camera :
                    cameraList) {
                GetDeviceListResp temp = new GetDeviceListResp();
                temp.setDeviceName(camera.getName());
                temp.setDeviceSerial(camera.getDeviceSerial());
                temp.setDevicePosition(camera.getDeviceArea());
                temp.setDeviceStatus(camera.getState());
                temp.setUpdateTime(DateUtil.formatDateTime(camera.getUpdateTime()));
            }
        }
        return retList;
    }

    /**
     * 查询门店值守日志列表
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public List<GetSuperviseListResp> getSuperviseList(String userId, GetSuperviseListReq param) {
        CtSuperviseLogs query = new CtSuperviseLogs();
        query.setUserId(userId);
        query.setDelFlag("0");
        List<CtSuperviseLogs> logList = superviseLogsMapper.selectCtSuperviseLogsList(query);
        List<GetSuperviseListResp> retList = new ArrayList<>();
        for (CtSuperviseLogs superviseLog :
                logList) {
            GetSuperviseListResp temp = new GetSuperviseListResp();
            temp.setSuperviseId(superviseLog.getId());
            temp.setShopId(superviseLog.getStoreId());
            temp.setShopName(superviseLog.getStoreName());
            temp.setShopAddress(superviseLog.getStoreAddress());
            temp.setBeginTime(DateUtil.formatTime(superviseLog.getStartTime()));
            if (superviseLog.getEndTime() != null) {
                temp.setEndTime(DateUtil.formatTime(superviseLog.getEndTime()));
                temp.setState("1");
            } else {
                temp.setState("0");
            }
            retList.add(temp);
        }
        return retList;
    }

    /**
     * 门店访问量汇总统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public ShopVisitSumResp getStatVisitSum(String userId, ShopStatSumReq param) {
        if (StringUtils.equals(param.getPeriodType(), SearchType.ONE.getCode()) && StringUtils.isEmpty(param.getShopId())) {
            throw new ServiceException("查询单店时，门店编号不能为空");
        }
        Date beginTime = null;
        Date endTime = null;


        return null;
    }

    /**
     * 门店访问量图表统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public List<ShopStatChartResp> getStatVisitChart(String userId, ShopStatChartReq param) {
        return null;
    }

    /**
     * 门店营收汇总统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public ShopIncomeSumResp getStatIncomeSum(String userId, ShopStatSumReq param) {
        return null;
    }

    /**
     * 门店营收图表统计数据
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public List<ShopStatChartResp> getStatIncomeChart(String userId, ShopStatChartReq param) {
        return null;
    }

    /**
     * 门店24小时访问量及销售额统计
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public ShopStat24hSumResp getStatIncomeSum(String userId, GetShopDetailReq param) {
        return null;
    }

    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtOrderDetailMapper orderDetailMapper;

    /**
     * 查询订单列表
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public List<GetOrderListResp> getOrderList(String userId, GetOrderListReq param) {
        Date beginTime = null;
        Date endTime = null;
        if (StringUtils.equals(param.getPeriodType(), PeriodType.SELF_DEFINE.getCode())) {
            if (StringUtils.isEmpty(param.getBeginTime()) || StringUtils.isEmpty(param.getEndTime())) {
                throw new ServiceException("自定义时间不能为空");
            }
            beginTime = DateUtil.parseDate(param.getBeginTime());
            endTime = DateUtil.parseDate(param.getEndTime());
        } else if (StringUtils.equals(param.getPeriodType(), PeriodType.TODAY.getCode())) {

        } else if (StringUtils.equals(param.getPeriodType(), PeriodType.YESTERDAY.getCode())) {

        } else if (StringUtils.equals(param.getPeriodType(), PeriodType.THIS_WEEK.getCode())) {

        } else if (StringUtils.equals(param.getPeriodType(), PeriodType.THIS_MONTH.getCode())) {

        } else {
            throw new ServiceException("时间查询类型不合法");
        }


        return null;
    }

    /**
     * 查询订单详情
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public GetOrderDetailResp getOrderDetail(String userId, GetOrderDetailReq param) {
        GetOrderDetailResp getOrderDetailResp = new GetOrderDetailResp();
        CtOrder order = orderMapper.selectCtOrderById(param.getOrderId());
        if (order != null) {
            getOrderDetailResp.setOrderId(order.getId());
            getOrderDetailResp.setTaskId(order.getTaskId());
            getOrderDetailResp.setStoreId(order.getStoreId());
            getOrderDetailResp.setStoreName(order.getStoreName());
            getOrderDetailResp.setShoppingId(order.getShoppingId());
            getOrderDetailResp.setUserId(order.getUserId());
            getOrderDetailResp.setUserPhone(order.getUserPhone());
            getOrderDetailResp.setActualAmount(NumberUtils.centToYuan(order.getActualAmount()));
            getOrderDetailResp.setTotalCount(String.valueOf(order.getItemCount()));
            getOrderDetailResp.setPaymentMode(order.getPaymentMode());
            getOrderDetailResp.setPaymentId(order.getPaymentId());
            getOrderDetailResp.setState(order.getState());
            getOrderDetailResp.setCreateTime(DateUtil.formatDateTime(order.getCreateTime()));
            getOrderDetailResp.setUpdateTime(DateUtil.formatDateTime(order.getUpdateTime()));
            List<OrderDetailData> detailList = new ArrayList<>();
            CtOrderDetail query = new CtOrderDetail();
            query.setOrderId(order.getId());
            List<CtOrderDetail> dbDetailList = orderDetailMapper.selectCtOrderDetailList(query);
            for (CtOrderDetail detail :
                    dbDetailList) {
                OrderDetailData temp = new OrderDetailData();
                temp.setItemId(detail.getItemId());
                temp.setItemName(detail.getItemName());
                temp.setItemTypeId(detail.getItemTypeId());
                temp.setItemTypeName(detail.getItemName());
                temp.setItemCount(NumberUtils.formatIntValue(detail.getItemCount()));
                temp.setItemPrice(NumberUtils.centToYuan(detail.getItemPrice()));
                temp.setItemPrimePrice(NumberUtils.centToYuan(detail.getItemPrimePrice()));
                temp.setItemSum(NumberUtils.centToYuan(detail.getItemSum()));
                BigDecimal profit = detail.getItemPrice().subtract(detail.getItemPrimePrice()).multiply(new BigDecimal(100)).divide(detail.getItemPrice());
                temp.setProfit(profit.toPlainString() + "%");
                detailList.add(temp);
            }
            getOrderDetailResp.setDetail(detailList);
        }

        return getOrderDetailResp;
    }

    @Autowired
    private ICtHikApiService hikApiService;

    /**
     * 获取订单本地回放
     *
     * @param userId
     * @param param
     * @return
     */
    @Override
    public GetOrderLocalVideoResp getOrderLocalVideo(String userId, GetOrderLocalVideoReq param) {
        CtOrder order = orderMapper.selectCtOrderById(param.getOrderId());
        if (order == null) {
            throw new ServiceException("无法获取订单信息");
        }
        Date beginTime = new Date();
        Date endTime = new Date();
        if (StringUtils.isEmpty(order.getShoppingId())) {
            //非云值守订单
            beginTime = order.getCreateDate();
            endTime = order.getUpdateTime();
        } else {
            // 值守订单，取购物起始时间
            CtShopping shopping = shoppingMapper.selectCtShoppingById(order.getShoppingId());
            if (shopping == null) {
                throw new ServiceException("无法获取购物信息");
            }
            beginTime = DateUtil.offset(shopping.getStartTime(), DateField.SECOND, -10);
            endTime = DateUtil.offset(shopping.getEndTime(), DateField.SECOND, 10);
        }

        CtDevice device = deviceMapper.selectCtDeviceByDeviceSerial(param.getDeviceSerial());
        if (device == null) {
            throw new ServiceException("无法获取设备信息");
        }
        VideoData playbackAddress = null;
        if (StringUtils.equals(device.getDeviceType(), DeviceType.CAMERA.getCode())) {
            playbackAddress = hikApiService.getPlaybackAddress(device.getDeviceSerial(), "1", DateUtil.formatDateTime(beginTime), DateUtil.formatDateTime(endTime));
        } else if (StringUtils.equals(device.getDeviceType(), DeviceType.NVR_CAMERA.getCode())) {
            playbackAddress = hikApiService.getPlaybackAddress(device.getDeviceSerial(), device.getDeviceChannel(), "1", DateUtil.formatDateTime(beginTime), DateUtil.formatDateTime(endTime));
        }
        if (playbackAddress == null) {
            throw new ServiceException("获取回放连接失败");
        }
        GetOrderLocalVideoResp resp = new GetOrderLocalVideoResp();
        resp.setUrl(playbackAddress.getUrl());
        resp.setToken(playbackAddress.getToken());
        return resp;
    }

    @Autowired
    private CtCashMqSenderService mqSenderService;

    /**
     * 更新商品
     *
     * @param userId
     * @param param
     */
    @Override
    public void syncProduct(String userId, SyncProductReq param) {
        mqSenderService.sendSyncProduct(param.getShopId());
    }
}

