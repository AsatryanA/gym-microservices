package com.epam.gym.config;

import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;

public class ReportClientConfiguration {
    @Bean
    public RequestInterceptor mdcRequestInterceptor() {
        return requestTemplate -> {
            var transactionId = MDC.get("transactionId");
            requestTemplate.header("transactionId", transactionId);
        };
    }
}
