package com.ebooksystem.ebookmanagementsystem.dtos;

import com.ebooksystem.ebookmanagementsystem.entities.Ebook;
import com.ebooksystem.ebookmanagementsystem.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Strucutre of the BookReviewDto
 */
@Data
public class BookReviewDto {
    private Long id;
    private User user;
    private Ebook ebook;
    private Integer rating;
    private String review;
    private LocalDateTime reviewDate;
}
