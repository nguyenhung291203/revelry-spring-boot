package com.develop.revelryspringboot.service.product;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.develop.revelryspringboot.dto.request.ProductCreationRequest;
import com.develop.revelryspringboot.dto.request.ProductFilterRequest;
import com.develop.revelryspringboot.dto.request.ProductUpdateRequest;
import com.develop.revelryspringboot.dto.response.ProductResponse;
import com.develop.revelryspringboot.mapper.ProductMapper;
import com.develop.revelryspringboot.repository.ProductRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @Override
    public Page<ProductResponse> findAll(ProductFilterRequest request) {
        return productRepository
                .findAll(request.specification(), request.pageable())
                .map(productMapper::toResponse);
    }

    @Override
    public ProductResponse create(ProductCreationRequest request) {

        return null;
    }

    @Override
    public ProductResponse findById(String id) {
        return null;
    }

    @Override
    public ProductResponse update(String id, ProductUpdateRequest request) {
        return null;
    }

    @Override
    public void delete(String id) {}
}
