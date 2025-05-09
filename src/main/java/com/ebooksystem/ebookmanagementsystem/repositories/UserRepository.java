package com.ebooksystem.ebookmanagementsystem.repositories;

import com.ebooksystem.ebookmanagementsystem.dtos.WishlistDTO;
import com.ebooksystem.ebookmanagementsystem.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
/**
 * Repository for Users. Extends JpaRepository to access its classes, as well as adds new ones.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Checks if a User with this email exists, returns true if yes, false otherwise
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    /**
     * Finds a user by their email. Returns the User if they exist.
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

}
