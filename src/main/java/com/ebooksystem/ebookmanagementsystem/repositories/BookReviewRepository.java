package com.ebooksystem.ebookmanagementsystem.repositories;

import com.ebooksystem.ebookmanagementsystem.entities.BookReview;
import com.ebooksystem.ebookmanagementsystem.entities.Ebook;
import com.ebooksystem.ebookmanagementsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

/**
 * Repository for book reviews. Extends JpaRepository to access its classes.
 */
public interface BookReviewRepository extends JpaRepository<BookReview, Integer> {
    BookReview findReviewById(Long id);
    @Query("select r from BookReview r where r.ebook.id = :ebookId")
    List<BookReview> findReviewByEBookId(@Param("ebookId")Long eBookId);
    List<BookReview> findReviewByUser(User user);
}
