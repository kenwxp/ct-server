package com.cloudtimes.mq.service;

import com.alibaba.fastjson2.JSONObject;
import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.PayOrderUtils;
import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.enums.PayOrderOption;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.PayOrderMqData;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.enums.PayState;
import com.cloudtimes.partner.pay.shouqianba.domain.BuzResponse;
import com.cloudtimes.partner.pay.shouqianba.domain.PayOrderData;
import com.cloudtimes.partner.pay.shouqianba.domain.ShouqianbaConstant;
import com.cloudtimes.partner.pay.shouqianba.service.impl.CtShouqianbaApiServiceImpl;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.product.mapper.CtShopProductMapper;
import com.cloudtimes.supervise.domain.CtOrder;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import com.cloudtimes.supervise.mapper.CtOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;

@Service
@Slf4j
public class CtMqPayOrderService {
    @Autowired
    private CtShouqianbaApiServiceImpl shouqianbaApiService;
    @Autowired
    private PayOrderUtils payOrderUtils;
    @Autowired
    private CtRocketMqProducer mqProducer;
    private final int loopQueryPayOrderTimeOut = 50;
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtOrderMapper orderMapper;
    @Autowired
    private CtShopProductMapper shopProductMapper;

    public void queryPayOrderService(PayOrderMqData data) {
        PayOrderData payOrderData = shouqianbaApiService.queryPayOrder(data.getPaySn(), "", data.getTerminalSN(), data.getTerminalKey());
        if (payOrderData != null && payOrderUtils.handlePayOrder(payOrderData) == 1) {
            // 发起订单库存维护
            data.setOption(PayOrderOption.MAINTAIN_STOCK);
            mqProducer.send(RocketMQConstants.CT_PAY_ORDER, data);
            return;
        }
        // 未获取终态，则校验超时时间
        Calendar instance = Calendar.getInstance();
        instance.setTime(data.getCreateTime());
        instance.add(Calendar.SECOND, loopQueryPayOrderTimeOut);
        if (DateUtils.getNowDate().after(instance.getTime())) {
            //超时未获取最终态，则发起撤单
            if (!data.isCancelFlag()) {
                data.setCancelFlag(true);
                data.setOption(PayOrderOption.CANCEL_PAY_ORDER);
                mqProducer.send(RocketMQConstants.CT_PAY_ORDER, data);
            }
        } else {
            // 未超时则发起信息 延时5秒
            data.setOption(PayOrderOption.QUERY_PAY_ORDER);
            mqProducer.sendDelayMsg(RocketMQConstants.CT_PAY_ORDER, JSONObject.toJSONString(data), 2);
        }
    }

    public void cancelPayOrder(PayOrderMqData data) {
        BuzResponse buzResponse = shouqianbaApiService.cancelPayOrder(data.getPaySn(), "", data.getTerminalSN(), data.getTerminalKey());
        if (buzResponse != null) {
            if (StringUtils.equals(buzResponse.getResultCode(), ShouqianbaConstant.busiCancelInProgress)) {
                //发起订单查询
                data.setOption(PayOrderOption.QUERY_PAY_ORDER);
                mqProducer.send(RocketMQConstants.CT_PAY_ORDER, data);
            } else if (StringUtils.equals(buzResponse.getResultCode(), ShouqianbaConstant.busiCancelSuccess)) {
//                String orderId = NoUtils.parseOrderNo(data.getOrderId());
                // 更新订单状态
                CtOrder cacheOrder = taskCache.getCacheOrder(data.getOrderId());
                cacheOrder.setState(PayState.PAY_FAIL.getCode());
                cacheOrder.setUpdateTime(DateUtils.getNowDate());
                taskCache.setCacheOrder(cacheOrder);
                orderMapper.updateCtOrder(cacheOrder);
            }
        }
    }

    public void maintainStock(PayOrderMqData data) {
        Map<String, CtOrderDetail> cacheOrderDetails = taskCache.getCacheOrderDetails(data.getOrderId());
        for (CtOrderDetail item :
                cacheOrderDetails.values()) {
            CtShopProduct ctShopProduct = new CtShopProduct();
            ctShopProduct.setId(item.getItemId());
            ctShopProduct.setStock(item.getItemCount().longValue());
            ctShopProduct.setTotalSold(item.getItemCount().longValue());
            ctShopProduct.setUpdateTime(DateUtils.getNowDate());
            shopProductMapper.updateCtShopProduct(ctShopProduct);
        }
    }
}
