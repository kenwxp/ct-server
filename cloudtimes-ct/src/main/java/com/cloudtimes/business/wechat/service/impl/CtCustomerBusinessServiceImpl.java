package com.cloudtimes.business.wechat.service.impl;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.cache.CtCashDynamicCodeCache;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.util.OrderUtil;
import com.cloudtimes.common.annotation.DataSource;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.DataSourceType;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.mq.CtCashMqSenderService;
import com.cloudtimes.business.cash.service.ICtCashBusinessService;
import com.cloudtimes.business.common.CtTaskDistributionService;
import com.cloudtimes.business.wechat.domain.ScanCodeReq;
import com.cloudtimes.business.wechat.domain.ScanCodeResp;
import com.cloudtimes.business.wechat.service.ICtCustomerBusinessService;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

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
    private CtTaskDistributionService taskInnerService;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtCashDynamicCodeCache deviceCache;
    @Autowired
    private CtCashMqSenderService cashMqSender;
    @Autowired
    private ICtCashBusinessService cashBusinessService;

    @Override
    @Transactional
    public ScanCodeResp scanCode(String userId, ScanCodeReq param) {
        String storeNo = param.getShopId();
        String dynamicCode = param.getDynamicCode();
        String deviceId = param.getDid();
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
            CtShopping query = new CtShopping();
            query.setStoreId(dbStore.getId());
            query.setUserId(userId);
            query.setState("0");
            List<CtShopping> ctShoppings = shoppingMapper.selectCtShoppingList(query);
            // 倒序开始时间
            ctShoppings.sort(new Comparator<CtShopping>() {
                @Override
                public int compare(CtShopping o1, CtShopping o2) {
                    return (int) (o2.getStartTime().getTime() - o1.getStartTime().getTime());
                }
            });
            if (ctShoppings != null && ctShoppings.size() > 0) {
                ScanCodeResp scanCodeResp = new ScanCodeResp();
                scanCodeResp.setIsSupervise(dbStore.getIsSupervise());
                scanCodeResp.setShoppingId(ctShoppings.get(0).getId());
                return scanCodeResp;
            }

        }
        if (!StringUtils.isEmpty(dynamicCode)) {
            // 若为动态码，则校验内存中动态码是否一致，不一致则产生新的二维码，推送收银机，一致流程继续
            if (!StringUtils.equals(deviceCache.get(deviceId), dynamicCode)) {
                String newUrl = cashBusinessService.genDynamicQrCodeUrl(deviceId, dbStore.getStoreNo());
                cashMqSender.sendBillSerial(dbStore.getId(), deviceId, "", newUrl, dbUser.getMobile());
                throw new ServiceException("二维码失效，请重试");
            }
        }
        CtShopping newShopping = OrderUtil.getInitCtShopping();
        //获取任务
        CtTask task = taskInnerService.distributeTask(dbStore.getId(), "");
        if (task != null) {
            newShopping.setTaskId(task.getId());
            newShopping.setStaffCode(task.getStaffCode());
            newShopping.setStartTime(task.getStartTime());
        }
        newShopping.setUserId(userId);
        newShopping.setStoreId(dbStore.getId());
        newShopping.setDescText("扫码购物");
        if (shoppingMapper.insertCtShopping(newShopping) < 1) {
            throw new ServiceException("新增购物失败");
        }
        if (!StringUtils.isEmpty(dynamicCode)) {
            //扫动态码流程，新增订单
            //新增购物记录，开始时间设置成任务开始时间
            CtOrder newOrder = OrderUtil.getInitCtOrder();
            if (task != null) {
                newOrder.setTaskId(task.getId());
                newOrder.setStaffCode(task.getStaffCode());
            }
            newOrder.setStoreId(dbStore.getId());
            newOrder.setStoreName(dbStore.getName());
            newOrder.setStoreProvince(dbStore.getRegionCode());
            newOrder.setStoreCity(dbStore.getRegionCode());
            newOrder.setAgentId(dbStore.getAgentId());
            newOrder.setBossUserId(dbStore.getBossId());
            newOrder.setShoppingId(newShopping.getId());
            newOrder.setUserId(userId);
            newOrder.setUserPhone(dbUser.getMobile());
            newOrder.setDeviceCashId(deviceId);
            newOrder.setDescText("扫码订单");
            //新增订单，并推送单号，顾客信息，新动态随机数到收银机
            if (orderMapper.insertCtOrder(newOrder) < 1) {
                throw new ServiceException("新增订单失败");
            }
            String newUrl = cashBusinessService.genDynamicQrCodeUrl(deviceId, dbStore.getStoreNo());
            // 推送收银机单号
            cashMqSender.sendBillSerial(dbStore.getId(), deviceId, newOrder.getId(), newUrl, dbUser.getMobile());
            // 订单加入内存
            taskCache.setCacheShopping(newShopping); // 与订单绑定的购物加入内存
            taskCache.setCacheOrder(newOrder);
        }
        ScanCodeResp scanCodeResp = new ScanCodeResp();
        scanCodeResp.setIsSupervise(dbStore.getIsSupervise());
        scanCodeResp.setShoppingId(newShopping.getId());
        return scanCodeResp;
    }
}
