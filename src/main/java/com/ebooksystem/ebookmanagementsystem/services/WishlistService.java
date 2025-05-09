package com.ebooksystem.ebookmanagementsystem.services;

import com.ebooksystem.ebookmanagementsystem.dtos.WishlistDTO;
import com.ebooksystem.ebookmanagementsystem.dtos.WishlistItemDto;
import com.ebooksystem.ebookmanagementsystem.entities.Wishlist;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookAlreadyInWishlistException;
import com.ebooksystem.ebookmanagementsystem.exceptions.EbookNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.UserNotFoundException;
import com.ebooksystem.ebookmanagementsystem.exceptions.WishlistNotFoundException;
import com.ebooksystem.ebookmanagementsystem.mappers.WishlistMapper;
import com.ebooksystem.ebookmanagementsystem.repositories.EbookRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.UserRepository;
import com.ebooksystem.ebookmanagementsystem.repositories.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * WishlistService class implements the logic required to process the frontend requests received by the controller
 */
@Service
@AllArgsConstructor
public class WishlistService {
    private final UserRepository userRepository;
    private WishlistRepository wishlistRepository;
    private WishlistMapper wishlistMapper;
    private EbookRepository ebookRepository;

    /**
     * Handles the creation of a Wishlist Object. Returns a WishlistDTO
     * @return
     */
    public WishlistDTO createWishlist(){
        var wishlist = new Wishlist();
        wishlistRepository.save(wishlist);

        return wishlistMapper.toDTO(wishlist);
    }

    /**
     * Handles the logic of adding an eBook to the wishlist. Handles appropriate errors
     * @param wishlistId
     * @param ebookId
     * @return
     */
    public WishlistItemDto addToWishlist(UUID wishlistId, Long ebookId){
        // Checks if this wishlist exists
        var wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if(wishlist == null){
            throw new WishlistNotFoundException("Wishlist not found");
        }
        // checks if the ebook to be added to wishlist exists
        var ebook = ebookRepository.findById(ebookId).orElse(null);
        if(ebook == null){
            throw new EbookNotFoundException("eBook not found");
        }

        // checks if this ebook already exists in the wishlist
        var wishlistItem = wishlist.getEbook(ebook.getId());
        // if the ebook is already in the wishlist, send an error
        // else add the ebook to the wishlist
        if(wishlistItem != null){
            throw new EbookAlreadyInWishlistException("eBook already in wishlist");
        }
        else {
            wishlistItem = wishlist.addEbook(ebook);
        }

        wishlistRepository.save(wishlist);

        return wishlistMapper.toDTO(wishlistItem);

    }

    /**
     * Gets a wishlist based on its id. Handles appropriate errors
     * @param wishlistId
     * @return
     */
    public WishlistDTO getWishlist(UUID wishlistId){
        var wishlist = wishlistRepository.getWishlistWithItems(wishlistId).orElse(null);
        if(wishlist == null){
            throw new WishlistNotFoundException("Wishlist not found");
        }
        return wishlistMapper.toDTO(wishlist);
    }

    /**
     * Removes an eBook from the wishlist. Handles appropriate errors
     * @param wishlistId
     * @param ebookId
     */
    public void removeEbook(UUID wishlistId, Long ebookId){
        var wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if(wishlist == null){
           throw new WishlistNotFoundException("Wishlist not found");
        }

        wishlist.removeEbook(ebookId);

        wishlistRepository.save(wishlist);
    }

    /**
     * Clears the wishlist. Handles appropriate errors
     * @param wishlistId
     */
    public void clearWishlist(UUID wishlistId){
        var wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if(wishlist == null){
            throw new WishlistNotFoundException("Wishlist not found");
        }

        wishlist.clear();

        wishlistRepository.save(wishlist);
    }

    /**
     * Assigns a wishlist to a user. Handles appropriate errors
     * @param wishlistId
     * @param userId
     */
    public void assignToUser(UUID wishlistId, Long userId){
        var wishlist = wishlistRepository.findById(wishlistId).orElse(null);
        if(wishlist == null){
            throw new WishlistNotFoundException("Wishlist not found");
        }
        var user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        wishlist.setUser(user);

        wishlistRepository.save(wishlist);
    }

    /**
     * Gets the wishlist by user id. Handles appropriate errors
     * @param userId
     * @return
     */
    public WishlistDTO getUserWishlist(Long userId){
        var wishlist = wishlistRepository.findWishlistByUser(userId).orElse(null);
        if(wishlist == null){
            throw new WishlistNotFoundException("Wishlist not found");
        }
        return wishlistMapper.toDTO(wishlist);
    }


}
