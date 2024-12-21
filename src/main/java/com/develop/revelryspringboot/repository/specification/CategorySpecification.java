package com.develop.revelryspringboot.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import com.develop.revelryspringboot.entity.Category;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CategorySpecification {
    private final List<Specification<Category>> specifications = new ArrayList<>();
    private static final String FIELD_NAME = "name";
    private static final String FIELD_IS_DELETED = "isDeleted";

    public static CategorySpecification builder() {
        return new CategorySpecification();
    }

    public CategorySpecification withName(final String name) {
        if (!ObjectUtils.isEmpty(name)) {
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.upper(root.get(FIELD_NAME)), like(name)));
        }
        return this;
    }

    public CategorySpecification withIsDeleted(final Boolean isDeleted) {
        if (isDeleted != null) {
            specifications.add(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FIELD_IS_DELETED), isDeleted));
        }
        return this;
    }

    private String like(final String value) {
        return "%" + value.toUpperCase() + "%";
    }

    public Specification<Category> build() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(specifications.stream()
                .filter(Objects::nonNull)
                .map(s -> s.toPredicate(root, query, criteriaBuilder))
                .toArray(Predicate[]::new));
    }
}
