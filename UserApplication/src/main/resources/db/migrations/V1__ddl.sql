CREATE TABLE users(
    id                  BIGINT          PRIMARY KEY,
    email               varchar(255)    NOT NULL,
    links_per_day       serial          NOT NULL,
    links_left          serial          NOT NULL,
    day_create         TIMESTAMP        NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT  un_email     UNIQUE(email);
