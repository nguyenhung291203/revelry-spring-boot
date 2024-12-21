package com.develop.revelryspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.develop.revelryspringboot.entity.Category;

public interface CategoryRepository extends BaseRepository<Category, String> {
    boolean existsByName(String name);

    @Query("SELECT c FROM Category c WHERE c.isDeleted = false")
    List<Category> findAllNotDeleted();

    @Query("SELECT c FROM Category c WHERE c.isDeleted = false AND c.id = :id")
    Optional<Category> findByIdNotDeleted(@Param("id") String id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END "
            + "FROM Category c WHERE c.name = :name AND c.id <> :id")
    boolean existsByNameAndNotId(@Param("name") String name, @Param("id") String id);
}
