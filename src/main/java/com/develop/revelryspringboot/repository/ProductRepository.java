package com.develop.revelryspringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.develop.revelryspringboot.entity.Product;

@Repository
public interface ProductRepository extends BaseRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE p.isDeleted = false AND p.id = :id")
    Optional<Product> findByIdNotDeleted(@Param("id") String id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END "
            + "FROM Product p WHERE p.name = :name AND p.id <> :id")
    boolean existsByNameAndNotId(@Param("name") String name, @Param("id") String id);
}
