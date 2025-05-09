package com.ebooksystem.ebookmanagementsystem.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

/**
 * Structure of the request body for assigning wishlist to user
 */
@Data
public class AssignWishlistToUserRequest {
    @NotNull
    private UUID wishlistId;
    @NotNull
    private Long userId;
}
