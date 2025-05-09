package com.ebooksystem.ebookmanagementsystem.services;

import com.ebooksystem.ebookmanagementsystem.dtos.BookReviewDto;
import com.ebooksystem.ebookmanagementsystem.dtos.CreateReviewRequest;
import com.ebooksystem.ebookmanagementsystem.entities.BookReview;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.ReviewNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.UserNotFoundException;
import com.ebooksystem.ebookmanagementsystem.mappers.BookReviewMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.BookReviewRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.EbookRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * BookReviewService class implements the logic required to process the frontend requests received by the controller
 */
@Service
@AllArgsConstructor
public class BookReviewService {

    private final UserRepository userRepository;
    private final EbookRepository ebookRepository;
    private final BookReviewRepository bookReviewRepository;
    private final BookReviewMapper bookReviewMapper;

    /**
     * Handles the logic for creating a new book review. Handles appropriate errors. If every check passes,
     * create new review object, assign values and save in the database. Return its Dto
     * @param request
     * @return
     */
    public BookReviewDto addBookReview(CreateReviewRequest request) {
        var user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        var ebook = ebookRepository.findById(request.getEbookId()).orElse(null);
        if (ebook == null) {
            throw new EbookNotFoundException("Ebook not found");
        }
        var bookReview = new BookReview();
        bookReview.setEbook(ebook);
        bookReview.setUser(user);
        bookReview.setReview(request.getReview());
        bookReview.setRating(request.getRating());
        bookReview.setDateCreated(LocalDateTime.now());
        bookReviewRepository.save(bookReview);

        return bookReviewMapper.toDto(bookReview);
    }

    /**
     * Deletes the review from the database
     * @param reviewId
     * @param userId
     */
    public void deleteBookReview(Long reviewId, Long userId) {
        var user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        var review = bookReviewRepository.findReviewById(reviewId);
        if (review == null) {
            throw new ReviewNotFoundException("Review not found");
        }
        bookReviewRepository.delete(review);

    }

    /**
     * Gets all off the reviews for an eBook
     * @param ebookId
     * @return
     */
    public List<BookReview> getAllBookReviews(Long ebookId) {
        var ebook = ebookRepository.findById(ebookId).orElse(null);
        if (ebook == null) {
            throw new EbookNotFoundException("Ebook not found");
        }
        return bookReviewRepository.findReviewByEBookId(ebook.getId());
    }

    /**
     * Gets all off the reviews made by the user
     * @param userID
     * @return
     */
    public List<BookReview> getAllUserReviews(Long userID) {
        var user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return bookReviewRepository.findReviewByUser(user);
    }
}
