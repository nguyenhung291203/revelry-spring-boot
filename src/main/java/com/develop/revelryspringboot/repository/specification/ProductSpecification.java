package com.develop.revelryspringboot.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.develop.revelryspringboot.entity.Product;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductSpecification {
    private final List<Specification<Product>> specifications = new ArrayList<>();

    private static final String FIELD_NAME = "name";
    private static final String FIELD_IS_DELETED = "isDeleted";
    private static final String FIELD_ID = "id";

    private static final String FIELD_PRICE = "price";
    private static final String TABLE_CATEGORIES = "categories";

    public static ProductSpecification builder() {
        return new ProductSpecification();
    }

    public ProductSpecification withName(final String name) {
        if (!ObjectUtils.isEmpty(name)) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(FIELD_NAME)), like(name)));
        }
        return this;
    }

    public ProductSpecification withIsDeleted(final Boolean isDeleted) {
        if (isDeleted != null) {
            specifications.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_IS_DELETED), isDeleted));
        }
        return this;
    }

    public ProductSpecification withCategoryIds(final List<String> categoryIds) {
        if (categoryIds != null && !categoryIds.isEmpty()) {
            specifications.add((root, query, criteriaBuilder) -> {
                var categoryJoin = root.join(TABLE_CATEGORIES);
                return criteriaBuilder.and(
                        categoryJoin.get(FIELD_ID).in(categoryIds),
                        criteriaBuilder.equal(categoryJoin.get(FIELD_IS_DELETED), false));
            });
        }
        return this;
    }

    public ProductSpecification withMinPrice(final Double minPrice) {
        if (minPrice != null) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get(FIELD_PRICE), minPrice));
        }
        return this;
    }

    public ProductSpecification withMaxPrice(final Double maxPrice) {
        if (maxPrice != null) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get(FIELD_PRICE), maxPrice));
        }
        return this;
    }

    private String like(final String value) {
        return "%" + value.toUpperCase() + "%";
    }

    public Specification<Product> build() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(specifications.stream()
                .filter(Objects::nonNull)
                .map(s -> s.toPredicate(root, query, criteriaBuilder))
                .toArray(Predicate[]::new));
    }
}
