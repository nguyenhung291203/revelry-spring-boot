package com.develop.revelryspringboot.entity;

import jakarta.persistence.*;

import org.hibernate.annotations.UuidGenerator;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(length = 50, nullable = false, unique = true)
    String name;

    @Column(columnDefinition = "text")
    String description;

    @Column(columnDefinition = "text")
    String image;

    @Column(nullable = false)
    Double price;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;
}
