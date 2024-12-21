package com.develop.revelryspringboot.mapper;

import org.mapstruct.Mapper;

import com.develop.revelryspringboot.dto.request.CategoryCreationRequest;
import com.develop.revelryspringboot.dto.request.CategoryUpdateRequest;
import com.develop.revelryspringboot.dto.response.CategoryResponse;
import com.develop.revelryspringboot.entity.Category;

@Mapper(config = DefaultConfigMapper.class)
public interface CategoryMapper
        extends BaseMapper<CategoryCreationRequest, CategoryUpdateRequest, Category, CategoryResponse> {}
