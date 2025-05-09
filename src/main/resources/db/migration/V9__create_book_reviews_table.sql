create table book_reviews
(
    id           bigint auto_increment
        primary key,
    user_id      bigint                             not null,
    ebook_id     bigint                             not null,
    rating       int                                not null,
    review       varchar(1000)                      not null,
    date_created datetime default current_timestamp null,
    constraint book_reviews_ebooks_id_fk
        foreign key (ebook_id) references ebooks (id),
    constraint book_reviews_users_id_fk
        foreign key (user_id) references users (id)
);

