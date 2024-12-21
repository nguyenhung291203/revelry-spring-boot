package com.develop.revelryspringboot.dto.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class PagingFilterRequest<T> {
    int page = 1;
    int size = 10;
    Map<String, String> orders = new HashMap<>();
    boolean isPagingEnabled = true;

    public abstract Specification<T> specification();

    public Pageable pageable() {
        if (!isPagingEnabled) {
            return Pageable.unpaged();
        }
        if (CollectionUtils.isEmpty(orders)) {
            return PageRequest.of(page - 1, size);
        }
        Sort sortable = sortable(orders);
        return PageRequest.of(page - 1, size, sortable);
    }

    public Sort sortable(Map<String, String> orders) {
        List<Sort.Order> sortableList = new ArrayList<>();
        orders.forEach((key, value) -> {
            Sort.Direction direction =
                    Sort.Direction.DESC.name().equalsIgnoreCase(value) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, key);
            sortableList.add(order);
        });
        return Sort.by(sortableList);
    }
}
