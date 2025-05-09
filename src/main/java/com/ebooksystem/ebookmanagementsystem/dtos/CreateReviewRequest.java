package com.ebooksystem.ebookmanagementsystem.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Strucutre of the body of the create review request. Annotated so that it can only take certain values, restricts others
 */
@Data
public class CreateReviewRequest {
    @NotNull(message = "UserId can not be empty")
    private Long userId;
    @NotNull(message = "eBookId can not be empty")
    private Long ebookId;
    @NotNull(message = "Rating can not be empty")
    @Min(value = 1, message = "Minimal value is 1 star")
    @Max(value = 5, message = "Maximal value is 5 stars")
    private Integer rating;
    @Max(value = 1000)
    private String review;
    private LocalDateTime reviewDate;

}
