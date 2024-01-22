package com.epam.gym.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
@Component
public class MdcFilter extends OncePerRequestFilter {
    private static final String TRANSACTION_ID = "transactionId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var transactionId = getRandomId();
        request.setAttribute(TRANSACTION_ID,transactionId);
        MDC.put(TRANSACTION_ID, transactionId);
        filterChain.doFilter(request, response);
    }

    private String getRandomId() {
        return UUID.randomUUID().toString();
    }

}
