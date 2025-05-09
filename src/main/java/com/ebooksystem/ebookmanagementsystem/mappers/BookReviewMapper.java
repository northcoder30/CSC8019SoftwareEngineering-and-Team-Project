package com.ebooksystem.ebookmanagementsystem.mappers;

import com.ebooksystem.ebookmanagementsystem.dtos.BookReviewDto;
import com.ebooksystem.ebookmanagementsystem.entities.BookReview;
import org.mapstruct.Mapper;

/**
 * Mapper for the BookReview class. Translates a BookReview into its DTO Object.
 */
@Mapper(componentModel = "spring")
public interface BookReviewMapper {
    public BookReviewDto toDto(BookReview bookReview);
}
