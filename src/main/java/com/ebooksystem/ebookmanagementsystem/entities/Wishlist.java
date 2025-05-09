package com.ebooksystem.ebookmanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Class for Wishlists object. Annotates the fields with correct @Column annotation, to properly map the object
 * to the database tables.
 */
@Getter
@Setter
@Entity
@Table(name = "wishlists")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Column(name = "date_created", insertable = false, updatable = false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.MERGE, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<WishlistItem> wishlistItems = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (WishlistItem item : wishlistItems) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        return totalPrice;
    }

    public WishlistItem getEbook(Long ebookId) {
        return wishlistItems.stream().
                filter(wishlistItem -> wishlistItem.getEbook().getId().equals(ebookId))
                .findFirst()
                .orElse(null);
    }

    public WishlistItem addEbook(Ebook ebook) {

        var wishlistItem = getEbook(ebook.getId());

        wishlistItem = new WishlistItem();
        wishlistItem.setEbook(ebook);
        wishlistItem.setWishlist(this);
        wishlistItems.add(wishlistItem);

        return wishlistItem;
    }

    public void removeEbook(Long ebookId) {
        var wishlistItem = getEbook(ebookId);
        if(wishlistItem != null){
            wishlistItems.remove(wishlistItem);
            wishlistItem.setWishlist(null);
        }
    }

    public void clear(){
        wishlistItems.clear();
    }


}
