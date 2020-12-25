DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         BIGINT PRIMARY KEY,
    email      VARCHAR(20) UNIQUE NOT NULL,
    username   VARCHAR(20) UNIQUE NOT NULL,
    password   VARCHAR(32)        NOT NULL,
    first_name VARCHAR(20)        NOT NULL,
    last_name  VARCHAR(20)        NOT NULL,
    role       VARCHAR(20)        NOT NULL,
    status     VARCHAR(20)        NOT NULL
);

-- CREATE TABLE roles
-- (
--     id   INT PRIMARY KEY,
--     role VARCHAR(20) NOT NULL
-- );
--
-- CREATE TABLE users_roles
-- (
--     user_id BIGINT NOT NULL,
--     role_id INT    NOT NULL,
--     PRIMARY KEY (user_id, role_id),
--     FOREIGN KEY (user_id) REFERENCES users (id),
--     FOREIGN KEY (role_id) REFERENCES roles (id)
-- );