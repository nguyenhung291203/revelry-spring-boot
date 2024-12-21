package com.develop.revelryspringboot.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagingFilterResponse<T> {
    List<T> contents;
    int page;
    int size;
    long totalElements;
    int totalPages;
}
