package com.epam.gym.config;

import com.epam.gym.util.jwt.JwtProvider;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportClientConfiguration {
    private final JwtProvider jwtProvider;

    @Bean
    public RequestInterceptor mdcRequestInterceptor() {
        return requestTemplate -> {
            var transactionId = MDC.get("transactionId");
            requestTemplate.header("transactionId", transactionId);
            requestTemplate.header(HttpHeaders.AUTHORIZATION, jwtProvider.getToken());
        };
    }
}
