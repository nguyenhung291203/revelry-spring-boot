package com.develop.revelryspringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.UuidGenerator;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseEntity<String> {
    @Id
    @UuidGenerator
    String id;

    @Column(length = 50, nullable = false, unique = true)
    String name;

    @Column(columnDefinition = "text")
    String description;

    @Column(columnDefinition = "text")
    String image;
}
