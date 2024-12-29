CREATE TABLE user_authentication(
    id              BIGSERIAL,
    login           varchar(255),
    password        varchar(255)    NOT NULL,
    registerDate    timestamp       NOT NULL
);

ALTER TABLE user_authentication
    ADD CONSTRAINT  pk_user         PRIMARY KEY(id),
    ADD CONSTRAINT  unique_login    UNIQUE(login);