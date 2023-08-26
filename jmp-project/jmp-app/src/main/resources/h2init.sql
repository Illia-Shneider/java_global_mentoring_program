CREATE SCHEMA IF NOT EXISTS jmp;
SET SCHEMA jmp;
CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT,
    name     VARCHAR(100) NOT NULL,
    surname  VARCHAR(100) NOT NULL,
    birthday DATE         NOT NULL
);
CREATE TABLE subscriptions
(
    id       BIGINT AUTO_INCREMENT,
    bankcard VARCHAR(100) NOT NULL UNIQUE,
    date     DATE         NOT NULL
);

INSERT INTO users (name, surname, birthday)
VALUES ('John', 'Doe', '1990-05-15'),
       ('Jane', 'Smith', '1985-09-22'),
       ('Michael', 'Johnson', '1978-11-03'),
       ('Emily', 'Williams', '1995-02-18'),
       ('William', 'Brown', '1982-07-29'),
       ('Olivia', 'Jones', '1998-04-12'),
       ('James', 'Davis', '1974-08-07'),
       ('Sophia', 'Miller', '1989-01-26'),
       ('Robert', 'Wilson', '2000-03-09'),
       ('Ava', 'Moore', '1992-06-21'),
       ('David', 'Taylor', '1987-10-14'),
       ('Emma', 'Anderson', '1993-12-30');

INSERT INTO subscriptions (bankcard, date)
VALUES ('1234567890123456', '2023-08-01'),
       ('9876543210987654', '2023-07-15'),
       ('5555666677778888', '2023-09-05'),
       ('4444333322221111', '2023-06-10'),
       ('1111222233334444', '2023-08-20'),
       ('6666777788889999', '2023-07-03'),
       ('8888777766665555', '2023-09-12'),
       ('2222333344445555', '2023-06-25'),
       ('7777666655554444', '2023-08-08'),
       ('9999888877776666', '2023-07-18'),
       ('4444555566667777', '2023-09-02'),
       ('1111999922223333', '2023-06-15');
