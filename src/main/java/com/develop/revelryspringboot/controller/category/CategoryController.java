package com.develop.revelryspringboot.controller.category;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.develop.revelryspringboot.dto.request.CategoryCreationRequest;
import com.develop.revelryspringboot.dto.request.CategoryFilterRequest;
import com.develop.revelryspringboot.dto.request.CategoryUpdateRequest;
import com.develop.revelryspringboot.dto.response.ApiResponse;
import com.develop.revelryspringboot.dto.response.CategoryResponse;
import com.develop.revelryspringboot.dto.response.PagingFilterResponse;

@RequestMapping("/api/v1/categories")
public interface CategoryController {
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<CategoryResponse> create(@Valid @ModelAttribute final CategoryCreationRequest request);

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PagingFilterResponse<CategoryResponse>> filter(@Valid @RequestBody final CategoryFilterRequest request);

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<CategoryResponse> update(
            final @PathVariable String id, @Valid @ModelAttribute final CategoryUpdateRequest request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<?> delete(@PathVariable String id);
}
