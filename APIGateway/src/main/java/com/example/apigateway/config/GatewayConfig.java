package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // /chat/*, /*/chat/**
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("chat-service", r -> r
                        .path("/chat/*", "/*/chat/**", "/ws/**") // WebSocket 경로
                        .filters(f -> f.addRequestHeader("Upgrade", "websocket"))
                        .uri("lb://CHAT-SERVICE")) // Eureka에 등록된 서비스 이름
                .build();
    }
}