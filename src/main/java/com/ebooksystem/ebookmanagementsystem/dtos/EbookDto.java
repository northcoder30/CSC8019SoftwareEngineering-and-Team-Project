package com.ebooksystem.ebookmanagementsystem.dtos;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Strucutre of the EBookDto
 */
@Data
public class EbookDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Integer totalQuantity;
    private int availableQuantity;
    private BigDecimal price;
    private String description;
    private Byte categoryId;
}
