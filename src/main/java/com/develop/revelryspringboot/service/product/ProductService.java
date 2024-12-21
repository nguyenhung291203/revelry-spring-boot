package com.develop.revelryspringboot.service.product;

import org.springframework.data.domain.Page;

import com.develop.revelryspringboot.dto.request.ProductCreationRequest;
import com.develop.revelryspringboot.dto.request.ProductFilterRequest;
import com.develop.revelryspringboot.dto.request.ProductUpdateRequest;
import com.develop.revelryspringboot.dto.response.ProductResponse;

public interface ProductService {
    Page<ProductResponse> findAll(ProductFilterRequest request);

    ProductResponse create(ProductCreationRequest request);

    ProductResponse findById(String id);

    ProductResponse update(String id, ProductUpdateRequest request);

    void delete(String id);
}
