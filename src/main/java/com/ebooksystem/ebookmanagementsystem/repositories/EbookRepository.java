package com.ebooksystem.ebookmanagementsystem.repositories;

import com.ebooksystem.ebookmanagementsystem.entities.Ebook;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for borrow records. Extends JpaRepository to access its classes, as well as adds new ones.
 */
public interface EbookRepository extends JpaRepository<Ebook, Long> {
    /**
     * Finds a List of all eBooks by category ID
     * @param categoryId
     * @return
     */
    @EntityGraph(attributePaths = "category")
    List<Ebook> findByCategoryId(Byte categoryId);

    /**
     * Returns all eBooks with their categories
     * @return
     */
    @EntityGraph(attributePaths = "category")
    @Query("select b from Ebook b")
    List<Ebook> findAllWithCategory();
}
