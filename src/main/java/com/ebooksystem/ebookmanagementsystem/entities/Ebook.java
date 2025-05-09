package com.ebooksystem.ebookmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Class for eBook object. Annotates the fields with correct @Column annotation, to properly map the object
 * to the database tables.
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ebooks")
public class Ebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Lob
    @Column(name = "description")
    private String description;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;



}