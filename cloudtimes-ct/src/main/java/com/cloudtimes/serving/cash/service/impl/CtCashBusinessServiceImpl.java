package com.cloudtimes.serving.cash.service.impl;

import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.hardwaredevice.domain.CtDeviceCash;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceCashMapper;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.partner.agora.service.CtAgoraApiService;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.pay.shouqianba.domain.AuthInfoData;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.partner.weixin.ICtWeixinFaceApiService;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoResp;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.product.mapper.CtShopProductMapper;
import com.cloudtimes.serving.cash.service.ICtCashBusinessService;
import com.cloudtimes.serving.cash.service.domain.VoiceTokenData;
import com.cloudtimes.serving.common.CtTaskInnerService;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.domain.CtShopping;
import com.cloudtimes.supervise.domain.CtTask;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class CtCashBusinessServiceImpl implements ICtCashBusinessService {
    @Autowired
    private ICtWeixinFaceApiService weixinFaceApiService;
    @Autowired
    private CtUserMapper userMapper;
    @Autowired
    private ICtShouqianbaApiService shouqianbaApiService;
    @Autowired
    private CtDeviceMapper deviceMapper;
    @Autowired
    private CtStoreMapper storeMapper;
    @Autowired
    private CtDeviceCashMapper deviceCashMapper;
    @Autowired
    private CtShopProductMapper shopProductMapper;
    @Autowired
    private CtTaskInnerService taskInnerService;
    @Autowired
    private CtShoppingMapper shoppingMapper;
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtAgoraApiService agoraApiService;

    /**
     * 获取刷脸凭证
     *
     * @param deviceId 设备id
     * @param rawdata  rawdata
     * @param authType 凭证类型 1-微信 2-收钱吧
     * @return
     */
    @Override
    public AuthInfoData getFaceAuthInfo(String deviceId, String rawdata, String authType) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (dbDevice == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (StringUtils.isEmpty(dbDevice.getStoreId())) {
            throw new ServiceException("当前设备未绑定门店");
        }
        CtStore dbStore = storeMapper.selectCtStoreById(dbDevice.getStoreId());
        if (dbDevice == null) {
            throw new ServiceException("无法获取门店信息");
        }
        if (StringUtils.equals(authType, "1")) {
            WxpayfaceAuthInfoResp resp = weixinFaceApiService.getWxpayfaceAuthInfo(dbStore.getStoreNo(), dbStore.getName(), dbDevice.getDeviceCode(), rawdata);
            if (resp == null) {
                throw new ServiceException("获取刷脸信息失败");
            }
            if (StringUtils.equals("SUCCESS", resp.getReturnCode())) {
                AuthInfoData authInfoData = new AuthInfoData();
                authInfoData.setAppid(resp.getAppid());
                authInfoData.setSubAppid(resp.getSubAppid());
                authInfoData.setMchId(resp.getMchId());
                authInfoData.setSubMchId(resp.getSubMchId());
                authInfoData.setExpiresIn(resp.getExpiresIn());
                authInfoData.setAuthinfo(resp.getAuthinfo());
                return authInfoData;
            } else {
                throw new ServiceException(resp.getReturnMsg());
            }
        } else {
            CtDeviceCash dbDeviceCash = deviceCashMapper.selectCtDeviceCashById(deviceId);
            if (dbDeviceCash == null) {
                throw new ServiceException("无法获取设备收钱吧参数");
            }
            AuthInfoData wxPayFaceAuthInfo = shouqianbaApiService.getWxPayFaceAuthInfo(rawdata, dbDeviceCash.getTerminalSn(), dbDeviceCash.getTerminalKey());
            return wxPayFaceAuthInfo;
        }
    }

    /**
     * 获取单号和顾客信息
     *
     * @param deviceId
     * @param token
     * @return map
     * orderId
     * phone
     */
    @Override
    public Map<String, String> getOrderId(String deviceId, String token) {
        //通过token获取用户union_id
        String unionId = weixinFaceApiService.getWxpayfaceUserUnionId(token);
        if (StringUtils.isEmpty(unionId)) {
            throw new ServiceException("无法获取用户unionId");
        }
        //查询出相应的顾客，若无，则提示异常
        CtUser ctUser = userMapper.selectCtUserByWxUnionId(unionId);
        if (ctUser == null) {
            throw new ServiceException("无法获取用户信息");
        }
        //根据设备获取门店信息
        CtDevice dbDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (dbDevice == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (StringUtils.isEmpty(dbDevice.getStoreId())) {
            throw new ServiceException("当前设备未绑定门店");
        }
        CtStore dbStore = storeMapper.selectCtStoreById(dbDevice.getStoreId());
        if (dbDevice == null) {
            throw new ServiceException("无法获取门店信息");
        }
        Map<String, String> retMap = new HashMap<>();
        retMap.put("phone", NumberUtils.getHiddenPhone(ctUser.getMobile()));
        //获取任务
        CtTask task = taskInnerService.distributeTask(dbStore.getId());
        String taskId = "";
        if (taskId != null) {
            taskId = task.getId();
        }
        CtShopping newShopping = new CtShopping();
        newShopping.setUserId(ctUser.getId());
        newShopping.setTaskId(taskId);
        newShopping.setStoreId(dbStore.getId());
        newShopping.setStaffCode(task.getStaffCode());
        newShopping.setShoppingType("0");
        newShopping.setDescText("刷脸购物");
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
            taskCache.setCacheShopping(newShopping);
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
        newOrder.setUserId(ctUser.getId());
        newOrder.setDeviceCashId(deviceId);
        newOrder.setDescText("刷脸订单");
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
            taskCache.setCacheOrder(newOrder);
        }
        return retMap;
    }

    @Override
    public List<CtShopProduct> getProductList(String deviceId) {
        CtDevice dbDevice = deviceMapper.selectCtDeviceById(deviceId);
        if (dbDevice == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (StringUtils.isEmpty(dbDevice.getStoreId())) {
            throw new ServiceException("当前设备未绑定门店");
        }
        CtStore ctStore = storeMapper.selectCtStoreById(dbDevice.getStoreId());
        if (ctStore == null) {
            throw new ServiceException("无法获取门店信息");
        }
        return shopProductMapper.selectCtShopProductListByStore(ctStore.getStoreNo());
    }

    @Autowired
    private PartnerConfig config;

    @Override
    public VoiceTokenData getVoiceToken(String deviceId) {
        CtDevice device = deviceMapper.selectCtDeviceById(deviceId);
        if (device == null) {
            throw new ServiceException("无法获取设备信息");
        }
        if (StringUtils.isEmpty(device.getStoreId())) {
            throw new ServiceException("当前设备未绑定门店");
        }
        int uid = 0;
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            uid = random.nextInt(999999);
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("产生随机用户失败：" + e.getMessage());
        }
        String channel = device.getStoreId();
        String agoraToken = agoraApiService.getAgoraToken(uid, channel);
        VoiceTokenData voiceTokenData = new VoiceTokenData();
        voiceTokenData.setAppId(config.getAgoraAppId());
        voiceTokenData.setVoiceToken(agoraToken);
        voiceTokenData.setChannelName(channel);
        voiceTokenData.setUid(uid);
        return voiceTokenData;
    }

    @Override
    public String addOrderItem(String deviceId, String orderId, String isSupervise, String goodId, String goodName, String categoryId, String categoryName, int num, int buyPrice, int sellPrice) {
        if (StringUtils.isEmpty(orderId)) {
            //只有在非值守状态才能扫商品产生新订单
            if (StringUtils.equals(isSupervise, "1")) {
                throw new ServiceException("收银机在值守模式下，不能直接扫商品");
            }
            //根据设备获取门店信息
            CtDevice dbDevice = deviceMapper.selectCtDeviceById(deviceId);
            if (dbDevice == null) {
                throw new ServiceException("无法获取设备信息");
            }
            if (StringUtils.isEmpty(dbDevice.getStoreId())) {
                throw new ServiceException("当前设备未绑定门店");
            }
            CtStore dbStore = storeMapper.selectCtStoreById(dbDevice.getStoreId());
            if (dbDevice == null) {
                throw new ServiceException("无法获取门店信息");
            }
            //有人模式下直接扫码，则产生新订单
            CtOrder newOrder = new CtOrder();
            newOrder.setTaskId("");
            newOrder.setStoreId(dbStore.getId());
            newOrder.setStoreName(dbStore.getName());
            newOrder.setStoreProvince(dbStore.getRegionCode());
            newOrder.setStoreCity(dbStore.getRegionCode());
            newOrder.setAgentId(dbStore.getAgentId());
            newOrder.setBossUserId(dbStore.getBossId());
            newOrder.setShoppingId("");
            newOrder.setStaffCode("");
            newOrder.setUserId("");
            newOrder.setDeviceCashId(deviceId);
            newOrder.setDescText("直接扫商品");
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
            taskCache.setCacheOrder(newOrder);
            orderId = newOrder.getId();
        }
        CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
        if (cacheOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        if (StringUtils.equals(cacheOrder.getDeviceCashId(), deviceId)) {
            throw new ServiceException("该订单不属于当前收银机");
        }
        int increase = num * buyPrice;
        //更新订单中的价格参数
        cacheOrder.setTotalAmount(cacheOrder.getTotalAmount().add(BigDecimal.valueOf(increase)));
        cacheOrder.setItemCount(cacheOrder.getItemCount() + num);
        taskCache.setCacheOrder(cacheOrder);

        CtOrderDetail od = taskCache.getCacheOrderDetail(goodId);
        if (od == null) {
            //生产订单物品
            od = new CtOrderDetail();
            od.setOrderId(orderId);
            od.setItemId(goodId);
            od.setItemName(goodName);
            od.setItemTypeId(categoryId);
            od.setItemTypeName(categoryName);
            od.setItemCount(BigDecimal.valueOf(num));
            od.setItemPrice(BigDecimal.valueOf(buyPrice));
            od.setItemSum(BigDecimal.valueOf(num * buyPrice));
        } else {
            od.setItemCount(od.getItemCount().add(BigDecimal.valueOf(num)));
            od.setItemSum(od.getItemSum().add(BigDecimal.valueOf(increase)));
        }
        taskCache.setCacheOrderDetail(od);
        return orderId;
    }

    @Override
    public void deleteOrderItem(String deviceId, String orderId, String goodId, int num) {
        CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
        if (cacheOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        if (StringUtils.equals(cacheOrder.getDeviceCashId(), deviceId)) {
            throw new ServiceException("该订单不属于当前收银机");
        }
        CtOrderDetail od = taskCache.getCacheOrderDetail(goodId);
        if (od == null) {
            throw new ServiceException("清单已无此商品");
        }
        BigDecimal itemPrice = od.getItemPrice();
        BigDecimal decrease = itemPrice.multiply(BigDecimal.valueOf(num));
        BigDecimal itemCount = od.getItemCount();
        BigDecimal remain = itemCount.subtract(BigDecimal.valueOf(num));
        if (remain.intValue() < 0) {
            throw new ServiceException("扣减数量过大");
        }
        if (remain.intValue() == 0) {
            taskCache.deleteCacheOrderDetail(goodId);
        } else {
            od.setItemCount(remain);
            od.setItemSum(od.getItemSum().subtract(decrease));
            taskCache.setCacheOrderDetail(od);
        }
        //更新订单中的价格参数
        cacheOrder.setTotalAmount(cacheOrder.getTotalAmount().subtract(decrease));
        cacheOrder.setItemCount(cacheOrder.getItemCount() - num);
        taskCache.setCacheOrder(cacheOrder);
    }

    @Override
    public void cancelOrder(String deviceId, String orderId) {
        CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
        if (cacheOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        if (StringUtils.equals(cacheOrder.getDeviceCashId(), deviceId)) {
            throw new ServiceException("该订单不属于当前收银机");
        }
        if (StringUtils.equals(cacheOrder.getState(), "0")) {
            if (taskCache.deleteCacheOrder(orderId)) {
                orderMapper.deleteCtOrderById(orderId);
            }
        }
    }

}
