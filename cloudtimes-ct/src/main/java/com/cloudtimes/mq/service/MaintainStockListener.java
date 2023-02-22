package com.cloudtimes.mq.service;

import com.cloudtimes.cache.CtTaskCache;
import com.cloudtimes.common.NoUtils;
import com.cloudtimes.common.utils.DateUtils;
import com.cloudtimes.mq.domain.CtMQConstants;
import com.cloudtimes.mq.domain.PayOrderMsgData;
import com.cloudtimes.product.domain.CtShopProduct;
import com.cloudtimes.product.mapper.CtShopProductMapper;
import com.cloudtimes.supervise.domain.CtOrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = CtMQConstants.MAINTAIN_STOCK, messageModel = MessageModel.CLUSTERING)
public class MaintainStockListener implements RocketMQListener<PayOrderMsgData> {
    @Autowired
    private CtTaskCache taskCache;
    @Autowired
    private CtShopProductMapper shopProductMapper;

    @Override
    public void onMessage(PayOrderMsgData data) {
//        PayOrderMsgData data = JSON.parseObject(msgData.getBody(), PayOrderMsgData.class);
//        String orderId = NoUtils.parseOrderNo(data.getRawOrderId());
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
