package com.ebooksystem.ebookmanagementsystem.controllers;

import com.ebooksystem.ebookmanagementsystem.dtos.BookReviewDto;
import com.ebooksystem.ebookmanagementsystem.dtos.CreateReviewRequest;
import com.ebooksystem.ebookmanagementsystem.entities.BookReview;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.ReviewNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.UserNotFoundException;
import com.ebooksystem.ebookmanagementsystem.mappers.BookReviewMapper;
import com.ebooksystem.ebookmanagementsystem.services.BookReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for book reviews. Allows the frontend to send requests regarding book reviews and
 * performs operations in the backend and database layers based on those requests. Sends appropriate responses.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/book-reviews")
public class BookReviewController {

    private final BookReviewService bookReviewService;
    private final BookReviewMapper bookReviewMapper;

    /**
     * Creates a book review and saves it in the database
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<BookReviewDto> createReview(@RequestBody CreateReviewRequest request) {
        var reviewDto = bookReviewService.addBookReview(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto);
    }

    /**
     * Deletes a book review based on its id and the id of the user who wrote it
     * @param reviewId
     * @param userId
     * @return
     */
    @DeleteMapping("/{reviewId}/{userId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable("reviewId") Long reviewId,
            @PathVariable("userId") Long userId) {
        bookReviewService.deleteBookReview(reviewId, userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Gets all reviews for an ebook.
     * @param ebookId
     * @return
     */
    @GetMapping("/book/{ebookId}")
    public List<BookReviewDto> getAllEbookReviews(@PathVariable Long ebookId) {
        List<BookReview> reviews = bookReviewService.getAllBookReviews(ebookId);
        return reviews.stream().map(bookReviewMapper::toDto).toList();
    }

    /**
     * Gets all reviews made by user
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public List<BookReviewDto> getAllUserReviews(@PathVariable Long userId) {
        List<BookReview> reviews = bookReviewService.getAllUserReviews(userId);
        return reviews.stream().map(bookReviewMapper::toDto).toList();
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found."));
    }

    @ExceptionHandler(EbookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEbookNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "EBook not found."));
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleReviewNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Review not found."));
    }
}

