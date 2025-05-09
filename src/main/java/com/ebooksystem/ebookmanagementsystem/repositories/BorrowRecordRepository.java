package com.ebooksystem.ebookmanagementsystem.repositories;

import com.ebooksystem.ebookmanagementsystem.entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository for borrow records. Extends JpaRepository to access its classes, as well as adds new ones.
 */
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
    /**
     * Finds a borrow record by userId, returns such record if it exists.
     * @param userId
     * @return
     */
    Optional<BorrowRecord> findByUserId(Long userId);

    /**
     * Finds all off the borrow requests made by the user, returns a list of borrow records
     * @param userId
     * @return
     */
    List<BorrowRecord> findAllByUserId(Long userId);

    /**
     * Finds a borrow record by its ID. Implements a custom query. Looks up the record in the database by its ID,
     * returns a borrow record if it exists.
     * @param id
     * @return
     */
    @Query("select r from BorrowRecord r where r.id = :Id")
    Optional<BorrowRecord> findById(@Param("Id")Long id);
}
