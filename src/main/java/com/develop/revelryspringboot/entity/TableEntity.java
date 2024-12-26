package com.develop.revelryspringboot.entity;


import jakarta.persistence.*;

import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tables")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Table extends BaseEntity<String>{
    String id;
}
