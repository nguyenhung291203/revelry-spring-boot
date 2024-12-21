package com.develop.revelryspringboot.controller.category;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.develop.revelryspringboot.dto.request.CategoryCreationRequest;
import com.develop.revelryspringboot.dto.request.CategoryFilterRequest;
import com.develop.revelryspringboot.dto.request.CategoryUpdateRequest;
import com.develop.revelryspringboot.dto.response.ApiResponse;
import com.develop.revelryspringboot.dto.response.CategoryResponse;
import com.develop.revelryspringboot.dto.response.PagingFilterResponse;
import com.develop.revelryspringboot.service.category.CategoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryControllerImpl implements CategoryController {
    CategoryService categoryService;

    @Override
    public ApiResponse<CategoryResponse> create(final CategoryCreationRequest request) {
        return ApiResponse.created(categoryService.create(request));
    }

    @Override
    public ApiResponse<PagingFilterResponse<CategoryResponse>> filter(final CategoryFilterRequest request) {
        final Page<CategoryResponse> categories = categoryService.findAll(request);
        return ApiResponse.ok(PagingFilterResponse.<CategoryResponse>builder()
                .contents(categories.getContent())
                .page(request.getPage())
                .size(categories.getContent().size())
                .totalElements(categories.getTotalElements())
                .totalPages(categories.getTotalPages())
                .build());
    }

    @Override
    public ApiResponse<CategoryResponse> update(final String id, final CategoryUpdateRequest request) {
        return ApiResponse.updated(categoryService.update(id, request));
    }

    @Override
    public ApiResponse<?> delete(String id) {
        categoryService.delete(id);
        return ApiResponse.deleted();
    }
}
