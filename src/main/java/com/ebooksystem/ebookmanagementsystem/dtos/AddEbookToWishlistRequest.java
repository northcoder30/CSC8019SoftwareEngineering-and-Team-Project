package com.ebooksystem.ebookmanagementsystem.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Structure of the request body for adding the eBook to a wishlist
 */
@Data
public class AddEbookToWishlistRequest {
    @NotNull(message = "Should be a valid eBook")
    private Long ebookId;
}
