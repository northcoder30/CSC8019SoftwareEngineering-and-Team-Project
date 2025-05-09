package com.ebooksystem.ebookmanagementsystem.repositories;

import com.ebooksystem.ebookmanagementsystem.entities.Wishlist;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.Optional;
import java.util.UUID;
/**
 * Repository for wishlists. Extends JpaRepository to access its classes, as well as adds new ones.
 */
public interface WishlistRepository extends JpaRepository<Wishlist, UUID> {
  /**
   * Returns a wishlist with all off the ebBooks in it. Implements a custom query, looks up a wishlist by Id, if it
   * exists, return it with all off the eBooks assigned to it.
   * @param wishlistId
   * @return
   */
  @EntityGraph("wishlistItems.ebook")
  @Query("select w from Wishlist w where w.id = :wishlistId")
  Optional<Wishlist> getWishlistWithItems(@Param("wishlistId") UUID wishlistId);

  /**
   * Finds a wishlist by user Id. Custom query to look up a wishlist by user id in the database.
   * @param userId
   * @return
   */
  @Query("select w from Wishlist w where w.user.id = :userId")
  Optional<Wishlist> findWishlistByUser(@Param("userId") Long userId);
}
