package com.ebooksystem.ebookmanagementsystem.mappers;

import com.ebooksystem.ebookmanagementsystem.dtos.WishlistDTO;
import com.ebooksystem.ebookmanagementsystem.dtos.WishlistItemDto;
import com.ebooksystem.ebookmanagementsystem.entities.Wishlist;
import com.ebooksystem.ebookmanagementsystem.entities.WishlistItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * Mapper for the Wishlist class.
 */
@Mapper(componentModel = "spring")
public interface WishlistMapper {
    /**
     * Translates a Wishlist object into its DTO object
     * @param wishlist
     * @return
     */
    @Mapping(target = "ebooks", source = "wishlistItems")
    @Mapping(target = "totalPrice", expression = "java(wishlist.getTotalPrice())")
    WishlistDTO toDTO(Wishlist wishlist);

    /**
     * Translates a WishlisItem object into its DTO object
     * @param wishlistItem
     * @return
     */
    WishlistItemDto toDTO(WishlistItem wishlistItem);
}
