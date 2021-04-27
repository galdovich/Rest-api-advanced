create table certificates
(
    id               bigint auto_increment
        primary key,
    name             varchar(255) not null,
    description      varchar(500) not null,
    price            int          not null,
    duration         int          not null,
    create_date      datetime     not null,
    last_update_date datetime     not null
);

create table tags
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table certificate_has_tag
(
    certificate_id bigint not null,
    tag_id         bigint not null,
    primary key (certificate_id, tag_id),
    constraint FKexvl8mwv4y5c7o71jgycua3q3
        foreign key (certificate_id) references certificates (id),
    constraint FKs2tcgti00b9ov0tqfsr43y146
        foreign key (tag_id) references tags (id)
);

create table users
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table certificate_orders
(
    certificate_order_id    bigint auto_increment
        primary key,
    certificate_order_price decimal(8, 2) not null,
    certificate_order_date  datetime      not null,
    certificate_id          bigint null,
    user_id                 bigint null,
    constraint certificate_orders_certificates_id_fk
        foreign key (certificate_id) references certificates (id),
    constraint certificate_orders_users_id_fk
        foreign key (user_id) references users (id)
);