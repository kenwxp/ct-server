package com.cloudtimes.app.config;

import com.cloudtimes.app.manager.CtWebSocketClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class WebSocketClientConfig {


    @Value("${wiegand.server_url}")
    private String serverUrl;

    @Bean
    public CtWebSocketClient createMyWebSocketClient() throws URISyntaxException, InterruptedException {
        URI uri = new URI(serverUrl);
        CtWebSocketClient client = new CtWebSocketClient(uri);
        return client;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
