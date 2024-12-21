package com.develop.revelryspringboot.constant;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Endpoints {
    private static final String apiPrefix = "/api/v1";

    public static String[] PUBLIC_ENDPOINTS;
    public static String[] CUSTOMER_ENDPOINTS;
    public static String[] MANAGER_ENDPOINTS;

    @PostConstruct
    private void init() {
        PUBLIC_ENDPOINTS = new String[] {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/webjars/swagger-ui/**",
            String.format("%s/view/**", apiPrefix),
            String.format("%s/categories/**", apiPrefix),
            String.format("%s/dishes/**", apiPrefix),
            String.format("%s/restaurants/**", apiPrefix),
            String.format("%s/restaurants/filter", apiPrefix),
            String.format("%s/auth/register", apiPrefix),
            String.format("%s/auth/login", apiPrefix)
        };

        CUSTOMER_ENDPOINTS = new String[] {
            String.format("%s/accounts/me", apiPrefix),
            String.format("%s/orders/**", apiPrefix),
            String.format("%s/cart/me", apiPrefix),
            String.format("%s/favorite/**", apiPrefix)
        };

        MANAGER_ENDPOINTS = new String[] {
            String.format("%s/dishes", apiPrefix),
            String.format("%s/restaurants", apiPrefix),
            String.format("%s/categories", apiPrefix)
        };
    }
}
