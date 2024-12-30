package com.libraryhub.msusers.infrastructure.FeignClients.Config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor feignRequestInterceptor() {
        return requestTemplate -> {
            // Retrieve the current authentication
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Check if authentication is present and valid
            if (authentication != null && authentication instanceof JwtAuthenticationToken) {
                JwtAuthenticationToken jwtAuthToken = (JwtAuthenticationToken) authentication;
                Jwt jwt = (Jwt) jwtAuthToken.getToken();

                // Add the Authorization header with the Bearer token
                requestTemplate.header("Authorization", "Bearer " + jwt.getTokenValue());
            }
        };
    }
}