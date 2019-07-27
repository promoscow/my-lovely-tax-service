create table users
(
    id       bigint auto_increment
        primary key,
    active   int          null,
    created  datetime     not null,
    updated  datetime     null,
    username varchar(255) not null,
    password varchar(255) not null,
    name     varchar(255) not null,
    email    varchar(255) not null,
    inn      bigint       not null,
    ogrn     bigint       not null
);