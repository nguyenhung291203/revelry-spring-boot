package com.develop.revelryspringboot.mapper;

import java.util.List;

import org.mapstruct.*;

public interface BaseMapper<S, U, E, R> {
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "image", ignore = true)
    E toEntity(S createRequest);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "image", ignore = true)
    void update(U updateRequest, @MappingTarget E entity);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    R toResponse(E entity);

    List<R> toResponse(List<E> entities);
}
