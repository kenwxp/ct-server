package com.cloudtimes.serving.wechat.service.impl;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.cache.TaskOrderCache;
import com.cloudtimes.cache.TaskShoppingCache;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.serving.common.CtTaskInnerService;
import com.cloudtimes.serving.wechat.service.ICtCustomerBusinessService;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@DataSource(DataSourceType.CT)
@Service
public class CtCustomerBusinessServiceImpl implements ICtCustomerBusinessService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private CtUserMapper userMapper;
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtShoppingMapper shoppingMapper;
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtTaskInnerService taskInnerService;
    @Autowired
    private TaskShoppingCache taskShoppingCache;
    @Autowired
    private TaskOrderCache taskOrderCache;

    @Override
    @Transactional
    public Map<String, String> scanCode(String userId, String storeNo, String dynamicCode, String deviceId) {
        // 获取门店信息
        CtStore dbStore = storeMapper.selectCtStoreByStoreNo(storeNo);
        if (dbStore == null) {
            throw new ServiceException("无法获取门店信息");
        }
        // 校验门店值守状态，只处理在值守状态下的业务。
        if ("0".equals(dbStore.getIsSupervise())) {
            throw new ServiceException("当前门店不在营业中");
        }
        CtUser dbUser = userMapper.selectCtUserById(userId);
        if (dbUser == null) {
            throw new ServiceException("无法获取用户信息");
        }
        // 获取动态随机数，校验是否扫码场景：动态码，开门物料二维码
        if (StringUtils.isEmpty(dynamicCode)) {
            // 若为开门码，则查30分钟内的购物记录，直接返回购物流水
        }
        if (!StringUtils.isEmpty(dynamicCode)) {
            // 若为动态码，则校验内存中动态码是否一致，不一致则产生新的二维码，推送收银机，一致流程继续
        }
        //获取任务
        CtTask task = taskInnerService.distributeTask(dbStore.getId());
        String taskId = "";
        if (taskId != null) {
            taskId = task.getId();
        }
        CtShopping newShopping = new CtShopping();
        newShopping.setUserId(userId);
        newShopping.setTaskId(taskId);
        newShopping.setStoreId(dbStore.getId());
        newShopping.setStaffCode(task.getStaffCode());
        newShopping.setShoppingType("0");
        newShopping.setDescText("扫码购物");
        newShopping.setStartTime(task.getStartTime());
        newShopping.setEndTime(new Date());
        newShopping.setExceptionalState("0");
        newShopping.setIsApprove("0");
        newShopping.setIsLeadApprove("0");
        newShopping.setIsBossApprove("0");
        newShopping.setDelFlag("0");
        newShopping.setCreateTime(new Date());
        newShopping.setUpdateTime(new Date());
        if (shoppingMapper.insertCtShopping(newShopping) < 1) {
            throw new ServiceException("新增购物失败");
        }
        if (StringUtils.isNotEmpty(taskId)) {
            //加入内存
            taskShoppingCache.setCacheShopping(task.getId(), newShopping.getId(), newShopping);
        }
        //新增购物记录，开始时间设置成任务开始时间
        CtOrder newOrder = new CtOrder();
        newOrder.setTaskId(task.getId());
        newOrder.setStoreId(dbStore.getId());
        newOrder.setStoreName(dbStore.getName());
        newOrder.setStoreProvince(dbStore.getRegionCode());
        newOrder.setStoreCity(dbStore.getRegionCode());
        newOrder.setAgentId(dbStore.getAgentId());
        newOrder.setBossUserId(dbStore.getBossId());
        newOrder.setShoppingId(newShopping.getId());
        newOrder.setStaffCode(task.getStaffCode());
        newOrder.setUserId(userId);
        newOrder.setDeviceCashId(deviceId);
        newOrder.setDescText("扫码订单");
        newOrder.setIsExceptional("0");
        newOrder.setState("0");
        newOrder.setDelFlag("0");
        Date now = new Date();
        newOrder.setYearMonth(now.getYear() * 100 + now.getMonth());
        newOrder.setCreateDate(now);
        newOrder.setCreateTime(now);
        newOrder.setUpdateTime(now);
        //新增订单，并推送单号，顾客信息，新动态随机数到收银机
        if (orderMapper.insertCtOrder(newOrder) < 1) {
            throw new ServiceException("新增订单失败");
        }
        if (StringUtils.isNotEmpty(taskId)) {
            taskOrderCache.setCacheOrder(task.getId(), newOrder.getId(), newOrder);
        }
        return null;
    }
}
