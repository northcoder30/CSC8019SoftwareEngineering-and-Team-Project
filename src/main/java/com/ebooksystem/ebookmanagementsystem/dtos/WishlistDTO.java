package com.ebooksystem.ebookmanagementsystem.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Strucuter of the WishlistDto
 */
@Data
public class WishlistDTO {
    private UUID id;
    private List<WishlistItemDto> ebooks = new ArrayList<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
