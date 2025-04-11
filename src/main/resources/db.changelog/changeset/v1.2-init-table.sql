 --liquibase formatted sql

 --changeset Ivan Iashkevich:v1.2
 --comment: Initial changeset tables

CREATE TABLE if NOT EXISTS usr_t (
     id uuid PRIMARY KEY,
     email   varchar(60),
     number  varchar(11)
);

CREATE TABLE if NOT EXISTS token_t (
     id uuid PRIMARY KEY,
     usr_id uuid,
     CONSTRAINT "fk_usr_id" FOREIGN KEY (usr_id) REFERENCES usr_t(id),
     code varchar(6),
     created_at TIMESTAMP
);

CREATE TABLE if NOT EXISTS attempt_t (
     id uuid PRIMARY KEY,
     token_id uuid,
     CONSTRAINT "fk_token_id" FOREIGN KEY (token_id) REFERENCES token_t(id),
     status status,
     created_at TIMESTAMP
);