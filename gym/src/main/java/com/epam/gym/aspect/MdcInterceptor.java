package com.epam.gym.aspect;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class MdcInterceptor implements HandlerInterceptor {

    private static final String TRANSACTION_ID = "transactionId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put(TRANSACTION_ID, getRandomId());
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    MDC.clear();
    }

    private String getRandomId() {
        return UUID.randomUUID().toString();
    }
}
