DROP TABLE IF EXISTS users, products;

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

CREATE TABLE products
(
    id          INT PRIMARY KEY,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    image_url   VARCHAR(50) NOT NULL, --!!!
    price       money       NOT NULL  -- money format???
);