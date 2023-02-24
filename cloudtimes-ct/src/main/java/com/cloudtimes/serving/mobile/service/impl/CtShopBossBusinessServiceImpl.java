package com.cloudtimes.serving.mobile.service.impl;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.ChannelType;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.enums.OpenDoorOption;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.OpenDoorMqData;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.domain.CtSuperviseLogs;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.hardwaredevice.mapper.CtSuperviseLogsMapper;
import com.cloudtimes.mq.service.CtCashMqSenderService;
import com.cloudtimes.serving.mobile.service.ICtShopBossBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 小程序用户登录Service业务层处理
 *
 * @author wangxp
 * @date 2023-02-02
 */
@DataSource(DataSourceType.CT)
@Service
public class CtShopBossBusinessServiceImpl implements ICtShopBossBusinessService {

    @Autowired
    private CtUserMapper userMapper;
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtSuperviseLogsMapper superviseLogsMapper;
    @Autowired
    private CtCashMqSenderService cashMqSender;
    @Autowired
    private CtRocketMqProducer producer;

    @Transactional
    @Override
    public boolean applySupervise(String userId, String storeId, String opFlag) {
        CtUser dbUser = userMapper.selectCtUserById(userId);
        if (dbUser == null) {
            throw new ServiceException("无法获取用户信息");
        }
        if (!StringUtils.equals(dbUser.getIsShopBoss(), "1")) {
            throw new ServiceException("当前用户不是店老板");
        }
        CtStore dbStore = storeMapper.selectCtStoreById(storeId);
        if (!StringUtils.equals(dbStore.getBossId(), userId)) {
            throw new ServiceException("当前门店不属于该用户");
        }
        if (StringUtils.equals(opFlag, "1") && StringUtils.equals(dbStore.getIsSupervise(), "1")) {
            throw new ServiceException("当前门店已在值守,请勿重复申请");
        }
        if (StringUtils.equals(opFlag, "0") && StringUtils.equals(dbStore.getIsSupervise(), "0")) {
            throw new ServiceException("当前门店不在值守中，无法取消");
        }
        if (StringUtils.equals(opFlag, "1")) {
            //申请云值守
            //生产值守日志
            CtSuperviseLogs newLog = new CtSuperviseLogs();
            newLog.setUserId(userId);
            newLog.setStoreId(storeId);
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
            //todo 发送app客户端消息
            // 解锁门禁
            producer.send(RocketMQConstants.CT_OPEN_DOOR, new OpenDoorMqData(OpenDoorOption.UNLOCK_DOOR, dbStore.getId(), userId, ChannelType.MOBILE));
        } else if (StringUtils.equals(opFlag, "0")) {
            //取消云值守
            //获取并更新值守日志
            CtSuperviseLogs updateLog = new CtSuperviseLogs();
            updateLog.setId(dbStore.getSuperviseLogId());
            updateLog.setEndTime(new Date());
            updateLog.setUpdateTime(new Date());
            if (superviseLogsMapper.updateCtSuperviseLogs(updateLog) < 1) {
                throw new ServiceException("新增值守日志异常");
            }
            //更新门店值守状态
            String isSupervise = "0";
            dbStore.setIsSupervise(isSupervise);
            dbStore.setUpdateTime(new Date());
            if (storeMapper.updateCtStore(dbStore) < 1) {
                throw new ServiceException("更新门店值守状态异常");
            }
            //通知收银机进行状态转换
            cashMqSender.notifyCashDutyStatus(dbStore.getId(), isSupervise);
            //todo 发送值守端结束任务通知
            //强锁门禁
            producer.send(RocketMQConstants.CT_OPEN_DOOR, new OpenDoorMqData(OpenDoorOption.FORCE_LOCK_DOOR, dbStore.getId(), userId, ChannelType.MOBILE));
        }
        return true;
    }
}

