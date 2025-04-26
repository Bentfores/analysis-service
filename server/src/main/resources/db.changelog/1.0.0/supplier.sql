--liquibase formatted sql

--changeset yuliezai:1
create table if not exists supplier
(
    supplier_id          uuid         not null
        constraint pk_supplier primary key,
    article              varchar(15)  not null,
    profit_place         decimal      not null,
    supplier_name        varchar(255) not null,
    product_url          varchar(255) not null,
    price                decimal      not null,
    quantity             decimal      not null,
    rating               decimal      not null,
    years                decimal      not null,
    image_url            varchar(255) not null,
    supplier_image_url   varchar(255) not null,
    supplier_product_url varchar(255) not null,
    created_at           timestamp    not null default now(),
    updated_at           timestamp    null
);
