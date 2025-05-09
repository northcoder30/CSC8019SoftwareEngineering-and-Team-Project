package com.ebooksystem.ebookmanagementsystem.controllers;

import com.ebooksystem.ebookmanagementsystem.dtos.AddEbookToWishlistRequest;
import com.ebooksystem.ebookmanagementsystem.dtos.AssignWishlistToUserRequest;
import com.ebooksystem.ebookmanagementsystem.dtos.WishlistDTO;
import com.ebooksystem.ebookmanagementsystem.dtos.WishlistItemDto;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookAlreadyInWishlistException;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.UserNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.WishlistNotFoundException;
import com.ebooksystem.ebookmanagementsystem.services.WishlistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;
/**
 * Controller for wishlists. Allows the frontend to send requests regarding wishlists by exposing the nodes
 * and performs operations in the backend and database layers based on those requests. Sends appropriate responses.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/wishlists")
@Tag(name = "Wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    /**
     * Creates a wishlist
     * @param uriBuilder
     * @return
     */
    @PostMapping
    public ResponseEntity<WishlistDTO> createWishlist(
            UriComponentsBuilder uriBuilder
    ) {
        var wishlistDto = wishlistService.createWishlist();
        var uri = uriBuilder.path("/wishlists/{id}").buildAndExpand(wishlistDto.getId()).toUri();

        return ResponseEntity.created(uri).body(wishlistDto);
    }

    /**
     * Adds eBook to wishlist
     * @param wishlistId
     * @param request
     * @return
     */
    @PostMapping("/{wishlistId}/ebooks")
    public ResponseEntity<WishlistItemDto> addToWishlist(
            @PathVariable UUID wishlistId,
            @RequestBody AddEbookToWishlistRequest request){

        var wishlistItemDto = wishlistService.addToWishlist(wishlistId, request.getEbookId());

        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistItemDto);
    }

    /**
     * Gets a wishlist based on the wishlit id
     * @param wishlistId
     * @return
     */
    @GetMapping("/{wishlistId}")
    public ResponseEntity<WishlistDTO> getWishlist(@PathVariable UUID wishlistId){
        var wishlistDto = wishlistService.getWishlist(wishlistId);

        return ResponseEntity.ok(wishlistDto);
    }

    /**
     * Deletes the ebook from a wishlist
     * @param wishlistId
     * @param ebookId
     * @return
     */
    @DeleteMapping("/{wishlistId}/ebooks/{ebookId}")
    public ResponseEntity<?> removeEbook(
            @PathVariable("wishlistId") UUID wishlistId,
            @PathVariable("ebookId") Long ebookId){

        wishlistService.removeEbook(wishlistId, ebookId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Clears the entire wishlist
     * @param wishlistId
     * @return
     */
    @DeleteMapping("/{wishlistId}/ebooks")
    public ResponseEntity<Void> clearWishlist(@PathVariable UUID wishlistId){
        wishlistService.clearWishlist(wishlistId);

        return ResponseEntity.noContent().build();
    }

    /**
     * Assigns wishlist to specific user
     * @param request
     * @return
     */
    @PutMapping("/assign-wishlist")
    public ResponseEntity<Void> assignWishlistToUser(@RequestBody AssignWishlistToUserRequest request){
        wishlistService.assignToUser(request.getWishlistId(), request.getUserId());

        return ResponseEntity.noContent().build();
    }

    /**
     * Gets a user's wishlist based on their Id
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<WishlistDTO> getUserWishlist(@PathVariable Long userId){
        var wishlistDto = wishlistService.getUserWishlist(userId);

        return ResponseEntity.ok(wishlistDto);

    }


    @ExceptionHandler(WishlistNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleWishlistNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Wishlist not found."));
    }

    @ExceptionHandler(EbookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEbookNotFound(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "EBook not found."));
    }

    @ExceptionHandler(EbookAlreadyInWishlistException.class)
    public ResponseEntity<Map<String, String>> handleEbookAlreadyExists(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "EBook is already in the wishlist"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found."));
    }

}
