--liquibase formatted sql

--changeset yuliezai:1
create table if not exists scheduler_info
(
    id             uuid        not null
        constraint pk_scheduler_info primary key,
    scheduler_name varchar(40) not null,
    created_at     timestamp   not null default now()
);
