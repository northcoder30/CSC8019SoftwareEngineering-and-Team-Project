create table users
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create table categories
(
    id   tinyint auto_increment
        primary key,
    name varchar(255) not null
);

create table ebooks
(
    id          bigint auto_increment
        primary key,
    title        varchar(255)   not null,
    author       varchar(255)  not null,
    publisher    varchar(255) not null,
    total_quantity  int not null ,
    available_quantity int not null,
    price       decimal(10, 2) not null,
    description longtext       not null,
    category_id tinyint        null,
    constraint fk_category
        foreign key (category_id) references categories (id)
);

