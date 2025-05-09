package com.ebooksystem.ebookmanagementsystem.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WishlistEbookDto {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private BigDecimal price;
}
