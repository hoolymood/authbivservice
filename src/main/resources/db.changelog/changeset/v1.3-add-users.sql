 --liquibase formatted sql

 --changeset Ivan Iashkevich:v1.3
 --comment: Initial changeset add user

INSERT INTO usr_t (id, email, number) VALUES
    (gen_random_uuid(), 'first@mail.ru', '89151654811'),
    (gen_random_uuid(), 'second@mail.ru', '89664854811'),
    (gen_random_uuid(), 'fhird@mail.ru', '89058524811');
