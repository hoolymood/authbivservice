 --liquibase formatted sql

 --changeset Ivan Iashkevich:v1.1
 --comment  Initial enums

CREATE TYPE status AS ENUM ('SUCCESS', 'FAILED');