package com.ebooksystem.ebookmanagementsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Class for BookReview object. Annotates the fields with correct @Column annotation, to properly map the object
 * to the database tables.
 */
@Getter
@Setter
@Entity
@Table(name = "book_reviews")
public class BookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ebook_id", nullable = false)
    private Ebook ebook;

    @NotNull
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Size(max = 1000)
    @NotNull
    @Column(name = "review", nullable = false, length = 1000)
    private String review;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

}