package com.develop.revelryspringboot.constant;

import java.util.List;

public class Endpoints {
    public static final List<String> PUBLIC_APIS = List.of(
            "/api/v1/authentication/**",
            "/api/v1/categories/filter",
            "/api/v1/products/filter",
            "/api/v1/tables/filter",
            "/api/v1/products/{id}",
            "/api/v1/products",
            "/swagger-ui/**",
            "/v3/api-docs/**");
}
