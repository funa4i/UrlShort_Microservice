CREATE TABLE user_authentication(
    id              BIGSERIAL       PRIMARY KEY,
    login           varchar(255),
    password        varchar(255)    NOT NULL,
    registerDate    timestamp       NOT NULL
);

ALTER TABLE user_authentication
    ADD CONSTRAINT  unique_login    UNIQUE(login);