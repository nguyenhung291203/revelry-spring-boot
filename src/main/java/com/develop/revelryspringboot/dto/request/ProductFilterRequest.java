package com.develop.revelryspringboot.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.develop.revelryspringboot.entity.Product;
import com.develop.revelryspringboot.repository.specification.ProductSpecification;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilterRequest extends PagingFilterRequest<Product> {
    String name;
    boolean isDeleted = false;
    Double minPrice;
    Double maxPrice;
    List<String> categoryIds = new ArrayList<>();

    @Override
    public Specification<Product> specification() {
        return ProductSpecification.builder()
                .withName(this.name)
                .withIsDeleted(this.isDeleted)
                .withCategoryIds(this.categoryIds)
                .withMinPrice(this.minPrice)
                .withMaxPrice(this.maxPrice)
                .build();
    }
}
