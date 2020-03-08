package com.thundersdata.backend.facade.client.config;

import com.thundersdata.backend.facade.client.BasicClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Value("${basic.host}")
    private String host;
    @Value("${basic.port}")
    private int port;

    @Bean("basicClient")
    public BasicClient serviceClient() {
        return new BasicClient(host, port);
    }
}
