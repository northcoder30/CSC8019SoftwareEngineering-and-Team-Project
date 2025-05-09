package com.ebooksystem.ebookmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for Category object. Annotates the fields with correct @Column annotation, to properly map the object
 * to the database tables.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    private Byte id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "category")
    private Set<Ebook> products = new HashSet<>();
}