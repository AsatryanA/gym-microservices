package com.epam.reportservice.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerLoggingAspect {

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerBean() {
    }

    @Pointcut("execution(* com.epam.reportservice.controller.*.*(..))")
    public void controllerMethods() {
    }

    @Around("controllerMethods() && controllerBean()")
    public Object logControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logRequestDetails();
        try {
            var result = joinPoint.proceed();
            //logResponseDetails(result);
            return result;
        } catch (Exception e) {
            logTransactionError(e);
            throw e;
        }
    }

    private void logRequestDetails() {
        log.info("Request URL: {}, Method: {}", request.getRequestURI(), request.getMethod());
    }

    private void logResponseDetails(Object result) {
        log.info("Response Status: {}, Response Body: {}", response.getStatus(), result.toString());
    }


    private void logTransactionError(Exception e) {
        log.error("Failed with error: {}", e.getMessage());
    }
}
