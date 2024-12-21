package com.develop.revelryspringboot.dto.request;

import org.springframework.data.jpa.domain.Specification;

import com.develop.revelryspringboot.entity.Category;
import com.develop.revelryspringboot.repository.specification.CategorySpecification;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryFilterRequest extends PagingFilterRequest<Category> {
    String name;
    boolean isDeleted = false;

    @Override
    public Specification<Category> specification() {
        return CategorySpecification.builder()
                .withName(this.name)
                .withIsDeleted(isDeleted)
                .build();
    }
}
