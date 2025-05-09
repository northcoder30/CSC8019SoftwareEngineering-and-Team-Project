package com.ebooksystem.ebookmanagementsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * Class for BorrowRecord object. Annotates the fields with correct @Column annotation, to properly map the object
 * to the database tables.
 */
@Getter
@Setter
@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
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
    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @NotNull
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;


    @Column(name = "return_date")
    private LocalDate returnDate;

    @NotNull
    @Column(name = "price",precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

}