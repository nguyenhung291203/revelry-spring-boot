package com.develop.revelryspringboot.mapper;

import org.mapstruct.Mapper;

import com.develop.revelryspringboot.dto.request.ProductCreationRequest;
import com.develop.revelryspringboot.dto.request.ProductUpdateRequest;
import com.develop.revelryspringboot.dto.response.ProductResponse;
import com.develop.revelryspringboot.entity.Product;

@Mapper(config = DefaultConfigMapper.class)
public interface ProductMapper
        extends BaseMapper<ProductCreationRequest, ProductUpdateRequest, Product, ProductResponse> {}
