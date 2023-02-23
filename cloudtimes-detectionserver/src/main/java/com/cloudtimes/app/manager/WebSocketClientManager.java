package com.cloudtimes.app.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class WebSocketClientManager {

    @Value("${wiegand.server_url}")
    private String serverUrl;

    private URI serverUri;
    private int reConnMaxCount = 5;

    private boolean isMonitor = false;

    private CtWebSocketClient webSocketClient;

    private static WebSocketClientManager instance;

    public Thread montiorThread = null;

    public static WebSocketClientManager getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() throws URISyntaxException {
        WebSocketClientManager.instance = this;
        this.isMonitor = true;
        serverUri = new URI(serverUrl);
        montiorThread = new Thread(new MonitorTreadWork());
        montiorThread.setDaemon(true);
        montiorThread.start();
    }

    public void reConnect() throws InterruptedException {
        try {
            log.info("正在建立连接...");
            if (this.webSocketClient == null || !this.webSocketClient.isOpen()) {
                this.webSocketClient = new CtWebSocketClient(serverUri);
                this.webSocketClient.connectBlocking();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }


    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    class MonitorTreadWork implements Runnable {
        @Override
        public void run() {
            while (instance.isMonitor) {
                try {
                    if (instance.webSocketClient == null || !instance.webSocketClient.isOpen()) {
                        instance.reConnect();
                    }
                    Thread.sleep(1000L);
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                }
            }
        }
    }
}
