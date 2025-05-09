create table wishlists
(
    id           binary(16) default (uuid_to_bin(uuid())) not null
        primary key,
    date_created date       default (current_date)        not null
);

create table wishlist_items
(
    id          bigint auto_increment
        primary key,
    wishlist_id binary(16) not null,
    ebook_id    bigint     not null,
    constraint wishlist_items_wishlist_ebook_unique
        unique (wishlist_id, ebook_id),
    constraint wishlist_items_ebooks_id_fk
        foreign key (ebook_id) references ebooks (id)
            on delete cascade,
    constraint wishlist_items_wishlists_id_fk
        foreign key (wishlist_id) references wishlists (id)
            on delete cascade
);
