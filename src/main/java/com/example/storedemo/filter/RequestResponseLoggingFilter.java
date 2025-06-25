package com.example.storedemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger("HttpLogger");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {

        logger.info("REQUEST  [{}] {}", request.getMethod(), request.getRequestURI());

        filterChain.doFilter(request, response);

        logger.info("RESPONSE [{}] {} => {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }
}
