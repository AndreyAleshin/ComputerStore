DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         INT PRIMARY KEY,
    email      VARCHAR(20) UNIQUE NOT NULL,
    username   VARCHAR(20) UNIQUE NOT NULL,
    password   VARCHAR(32)        NOT NULL,
    first_name VARCHAR(20)        NOT NULL,
    last_name  VARCHAR(20)        NOT NULL,
    role       VARCHAR(20)        NOT NULL,
    status     VARCHAR(20)        NOT NULL
);