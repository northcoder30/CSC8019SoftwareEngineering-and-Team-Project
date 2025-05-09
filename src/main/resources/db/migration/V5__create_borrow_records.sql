create table borrow_records
(
    id          bigint auto_increment
        primary key,
    user_id     bigint                   not null,
    ebook_id    bigint                   not null,
    borrow_date date default (curdate()) not null,
    return_date date default (curdate()) not null,
    price       double                   not null,
    constraint borrow_records_ebooks_id_fk
        foreign key (ebook_id) references ebooks (id),
    constraint borrow_records_users_id_fk
        foreign key (user_id) references users (id)
);
