package com.develop.revelryspringboot.service.category;

import org.springframework.data.domain.Page;

import com.develop.revelryspringboot.dto.request.CategoryCreationRequest;
import com.develop.revelryspringboot.dto.request.CategoryFilterRequest;
import com.develop.revelryspringboot.dto.request.CategoryUpdateRequest;
import com.develop.revelryspringboot.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse create(final CategoryCreationRequest request);

    CategoryResponse update(final String id, final CategoryUpdateRequest request);

    void delete(final String id);

    Page<CategoryResponse> findAll(final CategoryFilterRequest request);
}
