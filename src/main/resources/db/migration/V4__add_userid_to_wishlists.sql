alter table wishlists
    add user_id BIGINT null;

alter table wishlists
    add constraint wishlists_users_id_fk
        foreign key (user_id) references users (id)
            on delete cascade;