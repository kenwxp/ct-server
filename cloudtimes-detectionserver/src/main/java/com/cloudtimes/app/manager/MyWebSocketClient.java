package com.cloudtimes.app.manager;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Iterator;

@Slf4j
public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri) throws InterruptedException {
        super(serverUri);
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
        log.info("接收到消息：" + s);
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
