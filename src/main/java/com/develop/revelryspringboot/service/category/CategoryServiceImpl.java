package com.develop.revelryspringboot.service.category;

import static com.develop.revelryspringboot.constant.error.CategoryErrorCode.CATEGORY_NOT_FOUND;
import static com.develop.revelryspringboot.constant.message.CategoryErrorMessage.CATEGORY_ALREADY_EXISTS;

import java.util.Map;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.develop.revelryspringboot.dto.request.CategoryCreationRequest;
import com.develop.revelryspringboot.dto.request.CategoryFilterRequest;
import com.develop.revelryspringboot.dto.request.CategoryUpdateRequest;
import com.develop.revelryspringboot.dto.response.CategoryResponse;
import com.develop.revelryspringboot.entity.Category;
import com.develop.revelryspringboot.exception.ApiException;
import com.develop.revelryspringboot.exception.ValidateException;
import com.develop.revelryspringboot.mapper.CategoryMapper;
import com.develop.revelryspringboot.repository.CategoryRepository;
import com.develop.revelryspringboot.service.minio.MinioService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    MinioService minioService;
    static final String FIELD_NAME = "name";

    Category getById(String id) {
        return categoryRepository.findByIdNotDeleted(id).orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND));
    }

    @Override
    @Transactional
    public CategoryResponse create(final CategoryCreationRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new ValidateException(Map.of(FIELD_NAME, CATEGORY_ALREADY_EXISTS));
        }
        Category category = categoryMapper.toEntity(request);
        if (!request.getImage().isEmpty()) {
            category.setImage(minioService.upload(request.getImage()));
        }
        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse update(String id, CategoryUpdateRequest request) {
        Category category = getById(id);
        if (categoryRepository.existsByNameAndNotId(request.getName(), id)) {
            throw new ValidateException(Map.of(FIELD_NAME, CATEGORY_ALREADY_EXISTS));
        }
        categoryMapper.update(request, category);
        if (!request.getImage().isEmpty()) {
            category.setImage(minioService.upload(request.getImage()));
        }
        category = categoryRepository.save(category);
        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Category category = getById(id);
        category.setIsDeleted(true);
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<CategoryResponse> findAll(CategoryFilterRequest request) {
        return categoryRepository
                .findAll(request.specification(), request.pageable())
                .map(categoryMapper::toResponse);
    }
}
