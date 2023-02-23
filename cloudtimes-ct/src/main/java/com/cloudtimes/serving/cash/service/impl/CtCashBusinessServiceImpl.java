package com.cloudtimes.serving.cash.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.cloudtimes.account.domain.CtUser;
import com.cloudtimes.account.mapper.CtUserMapper;
import com.cloudtimes.cache.CtDeviceCache;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.OrderUtil;
import com.cloudtimes.common.PayOrderUtils;
import com.cloudtimes.common.exception.ServiceException;
import com.cloudtimes.common.mq.RocketMqProducer;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.JacksonUtils;
import com.cloudtimes.common.utils.NumberUtils;
import com.cloudtimes.common.utils.Threads;
import com.cloudtimes.enums.PayState;
import com.cloudtimes.enums.PayWay;
import com.cloudtimes.enums.PayeeType;
import com.cloudtimes.hardwaredevice.domain.CtDevice;
import com.cloudtimes.serving.cash.service.domain.ShouqianbaParam;
import com.cloudtimes.hardwaredevice.domain.CtPayment;
import com.cloudtimes.hardwaredevice.domain.CtStore;
import com.cloudtimes.hardwaredevice.mapper.CtDeviceMapper;
import com.cloudtimes.hardwaredevice.mapper.CtStoreMapper;
import com.cloudtimes.mq.domain.CtMQConstants;
import com.cloudtimes.mq.domain.PayOrderMsgData;
import com.cloudtimes.partner.agora.service.CtAgoraApiService;
import com.cloudtimes.partner.config.PartnerConfig;
import com.cloudtimes.partner.pay.shouqianba.domain.*;
import com.cloudtimes.partner.pay.shouqianba.service.ICtShouqianbaApiService;
import com.cloudtimes.partner.weixin.ICtWeixinFaceApiService;
import com.cloudtimes.partner.weixin.domain.WxpayfaceAuthInfoResp;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.product.mapper.CtShopProductMapper;
import com.cloudtimes.serving.cash.service.ICtCashBusinessService;
import com.cloudtimes.serving.cash.service.domain.VoiceTokenData;
import com.cloudtimes.serving.common.CtTaskInnerService;
import com.cloudtimes.supervise.domain.*;
import com.cloudtimes.supervise.mapper.CtOrderDetailMapper;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import com.cloudtimes.hardwaredevice.mapper.CtPaymentMapper;
import com.cloudtimes.supervise.mapper.CtShoppingMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class CtCashBusinessServiceImpl implements ICtCashBusinessService {
    static Logger log = LoggerFactory.getLogger(CtCashBusinessServiceImpl.class);
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
    private CtPaymentMapper paymentMapper;
    @Autowired
    private CtShopProductMapper shopProductMapper;
    @Autowired
    private CtTaskInnerService taskInnerService;
    @Autowired
    private CtShoppingMapper shoppingMapper;
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtOrderDetailMapper orderDetailMapper;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtAgoraApiService agoraApiService;
    @Autowired
    private CtDeviceCache deviceCache;
    @Autowired
    private PayOrderUtils payOrderUtils;
    @Autowired
    private RocketMqProducer mqProducer;

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
            // 获取设备收钱吧对接参数
            CtPayment paymentParam = paymentMapper.selectCtPaymentByUniqueKey(deviceId, PayeeType.CASH.getCode(), PayWay.SHOU_QIAN_BA.getCode());
            if (paymentParam == null) {
                throw new ServiceException("无法获取设备支付渠道信息");
            }
            if (StringUtils.isEmpty(paymentParam.getPayParams())) {
                throw new ServiceException("设备参数未设置");
            }
            ShouqianbaParam shouqianbaParam = JacksonUtils.parseObject(paymentParam.getPayParams(), ShouqianbaParam.class);
            AuthInfoData wxPayFaceAuthInfo = shouqianbaApiService.getWxPayFaceAuthInfo(rawdata, shouqianbaParam.getTerminalSn(), shouqianbaParam.getTerminalKey());
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
    @Transactional
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

        CtShopping newShopping = OrderUtil.getInitCtShopping();
        //获取任务
        CtTask task = taskInnerService.distributeTask(dbStore.getId());
        if (task != null) {
            newShopping.setTaskId(task.getId());
            newShopping.setStaffCode(task.getStaffCode());
            newShopping.setStartTime(task.getStartTime());
        }
        newShopping.setUserId(ctUser.getId());
        newShopping.setStoreId(dbStore.getId());
        newShopping.setDescText("刷脸购物");
        if (shoppingMapper.insertCtShopping(newShopping) < 1) {
            throw new ServiceException("新增购物失败");
        }
        //加入内存
        taskCache.setCacheShopping(newShopping);
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
        newOrder.setUserId(ctUser.getId());
        newOrder.setDeviceCashId(deviceId);
        newOrder.setDescText("刷脸订单");
        //新增订单，并推送单号，顾客信息，新动态随机数到收银机
        if (orderMapper.insertCtOrder(newOrder) < 1) {
            throw new ServiceException("新增订单失败");
        }
        taskCache.setCacheOrder(newOrder);
        retMap.put("orderId", newOrder.getId());
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
    @Transactional
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
            CtOrder newOrder = OrderUtil.getInitCtOrder();
//            newOrder.setTaskId(null);
            newOrder.setStoreId(dbStore.getId());
            newOrder.setStoreName(dbStore.getName());
            newOrder.setStoreProvince(dbStore.getRegionCode());
            newOrder.setStoreCity(dbStore.getRegionCode());
            newOrder.setAgentId(dbStore.getAgentId());
            newOrder.setBossUserId(dbStore.getBossId());
//            newOrder.setShoppingId(null);
            newOrder.setStaffCode("");
//            newOrder.setUserId(null);
            newOrder.setDeviceCashId(deviceId);
            newOrder.setDescText("直接扫商品");

            //新增订单，并推送单号，顾客信息，新动态随机数到收银机
            if (orderMapper.insertCtOrder(newOrder) < 1) {
                throw new ServiceException("新增订单失败");
            }
            taskCache.setCacheOrder(newOrder);
            orderId = newOrder.getId();
            System.out.println("产生新订单号：" + orderId);
        }
        CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
        if (cacheOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        if (!StringUtils.equals(cacheOrder.getDeviceCashId(), deviceId)) {
            throw new ServiceException("该订单不属于当前收银机");
        }
        int increase = num * sellPrice;
        //更新订单中的价格参数
        cacheOrder.setTotalAmount(cacheOrder.getTotalAmount().add(BigDecimal.valueOf(increase)));
        cacheOrder.setItemCount(cacheOrder.getItemCount() + num);
        taskCache.setCacheOrder(cacheOrder);

        CtOrderDetail od = taskCache.getCacheOrderDetail(orderId, goodId);
        if (od == null) {
            //生产订单物品
            od = new CtOrderDetail();
            od.setOrderId(orderId);
            od.setItemId(goodId);
            od.setItemName(goodName);
            od.setStoreId(cacheOrder.getStoreId());
            od.setItemTypeId(categoryId);
            od.setItemTypeName(categoryName);
            od.setItemCount(BigDecimal.valueOf(num));
            od.setItemPrice(BigDecimal.valueOf(sellPrice));
            od.setItemPrimePrice(BigDecimal.valueOf(buyPrice));
            od.setItemSum(BigDecimal.valueOf(num * sellPrice));
            od.setDelFlag("0");
            od.setYearMonth(Long.valueOf(DateUtils.getYearMonth()));
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
        if (!StringUtils.equals(cacheOrder.getDeviceCashId(), deviceId)) {
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
    @Transactional
    public void cancelOrder(String deviceId, String orderId) {
        CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
        if (cacheOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        if (!StringUtils.equals(cacheOrder.getDeviceCashId(), deviceId)) {
            throw new ServiceException("该订单不属于当前收银机");
        }
        if (StringUtils.equals(cacheOrder.getState(), PayState.READY_TO_PAY.getCode())) {
            if (taskCache.deleteCacheOrder(orderId)) {
                orderMapper.deleteCtOrderById(orderId);
            }
        }
    }

    @Override
    @Transactional
    public String payOrder(String deviceId, String orderId, int payType, String payCode, int totalAmount, int totalNum) {
        CtOrder cacheOrder = taskCache.getCacheOrder(orderId);
        if (cacheOrder == null) {
            throw new ServiceException("无法获取订单信息");
        }
        if (!StringUtils.equals(cacheOrder.getDeviceCashId(), deviceId)) {
            throw new ServiceException("该订单不属于当前收银机");
        }
        if (StringUtils.equals(cacheOrder.getState(), PayState.PAY_SUCCESS.getCode())
                || StringUtils.equals(cacheOrder.getState(), PayState.PAID_THEN_CONFIRM.getCode())) {
            throw new ServiceException("改订单正在支付中或完成支付，请勿重复提交");
        }

        CtPayment paymentParam = paymentMapper.selectCtPaymentByUniqueKey(deviceId, PayeeType.CASH.getCode(), PayWay.SHOU_QIAN_BA.getCode());
        if (paymentParam == null || StringUtils.isEmpty(paymentParam.getPayParams())) {
            throw new ServiceException("收银机未对接");
        }
        ShouqianbaParam shouqianbaParam = JacksonUtils.parseObject(paymentParam.getPayParams(), ShouqianbaParam.class);

        if (StringUtils.isEmpty(shouqianbaParam.getTerminalSn()) || StringUtils.isEmpty(shouqianbaParam.getTerminalKey())) {
            throw new ServiceException("支付未对接，无法付款");
        }
        if (cacheOrder.getTotalAmount().intValue() != totalAmount || cacheOrder.getItemCount().intValue() != totalNum) {
            throw new ServiceException("订单总额不一致");
        }
        // 持久化订单
        cacheOrder.setIsCompose("0");//非组合支付
        cacheOrder.setPaymentCode(paymentParam.getId());//支付渠道编号
        cacheOrder.setState(PayState.PAID_THEN_CONFIRM.getCode());
        cacheOrder.setPaymentAction(String.valueOf(payType));
        cacheOrder.setUpdateTime(DateUtils.getNowDate());
        orderMapper.updateCtOrder(cacheOrder);
        Map<String, CtOrderDetail> cacheOrderDetails = taskCache.getCacheOrderDetails(orderId);
        for (CtOrderDetail item :
                cacheOrderDetails.values()) {
            orderDetailMapper.insertCtOrderDetail(item);
        }
        // 准备支付参数
        CtStore ctStore = storeMapper.selectCtStoreById(cacheOrder.getStoreId());
        if (ctStore == null) {
            throw new ServiceException("无法获取门店信息");
        }
        String clientSN = NumberUtils.getRandomString(32);
        B2CPayReq b2CPayReq = new B2CPayReq();
        b2CPayReq.setTerminalSN(shouqianbaParam.getTerminalSn());
        b2CPayReq.setClientSN(clientSN);
        b2CPayReq.setTotalAmount(String.valueOf(totalAmount));
        b2CPayReq.setDynamicId(payCode);
        b2CPayReq.setSubject("生活用品");
        b2CPayReq.setOperator(cacheOrder.getStoreName());
        b2CPayReq.setReflect(orderId);
        if (ctStore.getDividendRate() != null && ctStore.getDividendRate().intValue() > 0) {
            String dividendRate = ctStore.getDividendRate().intValue() + "%";
            // 分账参数
            B2CProfitSharingData b2CProfitSharingData = new B2CProfitSharingData();
            b2CProfitSharingData.setSharingFlag("1");//分账
            b2CProfitSharingData.setSharingType("1");//按比例
            b2CProfitSharingData.setModelId(cacheOrder.getStoreId());
//        b2CProfitSharingData.setSharingNotifyUrl("");
            ArrayList<B2CProfitSharingReceiver> receivers = new ArrayList<>();
            B2CProfitSharingReceiver receiver = new B2CProfitSharingReceiver();
            receiver.setId("1680005823147");// 给雅安
            receiver.setRatio(dividendRate);
            receivers.add(receiver);
            b2CProfitSharingData.setReceivers(receivers);
            b2CPayReq.setProfitSharing(b2CProfitSharingData);
        }
        CommonResp commonResp = shouqianbaApiService.b2cPay(b2CPayReq, shouqianbaParam.getTerminalKey());
        if (commonResp == null) {
            throw new ServiceException("支付失败");
        }
        if (StringUtils.equals(commonResp.getResultCode(), ShouqianbaConstant.response200)) {
            BuzResponse bizResponse = commonResp.getBizResponse();
            if (bizResponse != null) {
                if (StringUtils.equals(bizResponse.getResultCode(), ShouqianbaConstant.busiPayInProgress)) {
                    //发起订单查询轮询
                    String payOrderSerial = "";
                    if (bizResponse.getData() != null) {
                        PayOrderData payOrderData = JacksonUtils.convertObject(bizResponse.getData(), PayOrderData.class);
                        payOrderSerial = payOrderData.getSn();
                    }
                    PayOrderMsgData payOrderMsgData = new PayOrderMsgData();
                    payOrderMsgData.setTerminalSN(shouqianbaParam.getTerminalSn());
                    payOrderMsgData.setTerminalKey(shouqianbaParam.getTerminalKey());
                    payOrderMsgData.setPaySn(payOrderSerial);
                    payOrderMsgData.setOrderId(clientSN);
                    payOrderMsgData.setCreateTime(DateUtils.getNowDate());
                    payOrderMsgData.setCancelFlag(false);
                    mqProducer.send(CtMQConstants.QUERY_PAY_ORDER, payOrderMsgData);
                } else if (StringUtils.equals(bizResponse.getErrorCodeStandard(), "EP104")) {
                    //输入密码超时
                    return "输入密码确认超时";
                } else {
                    if (!StringUtils.isEmpty(bizResponse.getErrorMessage())) {
                        return bizResponse.getErrorMessage();
                    }
                    if (bizResponse.getData() != null) {
                        PayOrderData payOrderData = JacksonUtils.convertObject(bizResponse.getData(), PayOrderData.class);
                        int confirm = payOrderUtils.handlePayOrder(payOrderData);
                        if (confirm == 1) {
                            //  发送库存维护
                            PayOrderMsgData payOrderMsgData = new PayOrderMsgData();
                            payOrderMsgData.setOrderId(orderId);
                            mqProducer.send(CtMQConstants.MAINTAIN_STOCK, payOrderMsgData);
                        }
                    }
                }
            }
        } else if (StringUtils.equals(commonResp.getResultCode(), ShouqianbaConstant.response400)) {
            //一般为二维码错误
            return commonResp.getErrorMessage();
        } else {
            throw new ServiceException(commonResp.getResultCode() + ": " + commonResp.getErrorMessage());
        }
        return "";
    }

    /**
     * 查询订单状态
     * 0-超时未确认 1-确认成功 2-确认失败 3-订单异常
     *
     * @param orderId
     */
    @Override
    public int payOrderStatus(String deviceId, String orderId) {
        CtOrder cacheOrder;
        Date start = DateUtils.getNowDate();
        while (true) {
            Date now = DateUtils.getNowDate();
            if (DateUtil.between(now, start, DateUnit.SECOND) > 10) {
                return 0;
            }
            cacheOrder = taskCache.getCacheOrder(orderId);
            if (cacheOrder != null) {
                if (StringUtils.equals(cacheOrder.getState(), PayState.PAID_THEN_CONFIRM.getCode())) {
                    Threads.sleep(1000);
                    continue;
                } else if (StringUtils.equals(cacheOrder.getState(), PayState.PAY_SUCCESS.getCode())) {
                    return 1;
                } else if (StringUtils.equals(cacheOrder.getState(), PayState.PAY_FAIL.getCode())) {
                    return 2;
                }
            }
            return 3;
        }
    }

    @Override
    public String genDynamicQrCodeUrl(String deviceId, String storeNo) {
        String dynamicStr = NumberUtils.getRandomENString(8);
        deviceCache.put(deviceId, dynamicStr);
        String dynamicQrCodeUrl =
                "https://api.htcloud2020.com/mapp/redirect?shopId=" + storeNo + "&dynamicCode=" + dynamicStr + "&did=" + deviceId;
        log.info("生成新动态码:" + dynamicQrCodeUrl);
        return dynamicQrCodeUrl;
    }


}