package com.cloudtimes.app.manager;

import com.cloudtimes.common.constant.RocketMQConstants;
import com.cloudtimes.common.mq.CtRocketMqProducer;
import com.cloudtimes.common.mq.DoorMessageMqData;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.common.utils.spring.SpringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Iterator;

@Slf4j
public class CtWebSocketClient extends WebSocketClient {
    private CtRocketMqProducer producer;


    public CtWebSocketClient(URI serverUri) throws InterruptedException {
        super(serverUri);
        producer = SpringUtils.getBean(CtRocketMqProducer.class);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("webscoket连接通道已打通...");
        for (Iterator<String> it = serverHandshake.iterateHttpFields(); it.hasNext(); ) {
            String key = it.next();
            log.info(key + ":" + serverHandshake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String s) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode nodes = mapper.readTree(s);
            Iterator<JsonNode> elements = nodes.elements();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                if (node.get("SN") != null) {
                    int serial = node.get("SN").asInt();
                    if (node.get("刷新时间") != null) {
                        //发送消息
                        log.debug("====>发送mq消息：" + node.toString());
                        producer.send(RocketMQConstants.CT_DOOR_MESSAGE, new DoorMessageMqData(0, serial, node.get("刷新时间").asText()));
                        return;
                    }
                    if (node.get("描述") != null && StringUtils.equals(node.get("描述").asText(), "按钮开门")) {
                        log.debug("====>发送mq消息：" + node.toString());
                        producer.send(RocketMQConstants.CT_DOOR_MESSAGE, new DoorMessageMqData(1, serial, node.get("时间").asText()));
                        return;
                    }
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("连接已关闭");
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        log.error("发生异常", e.getMessage());
    }


}
