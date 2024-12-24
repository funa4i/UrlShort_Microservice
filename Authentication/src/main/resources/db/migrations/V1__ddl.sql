CREATE TABLE userAuth(
    id              BIGSERIAL,
    login           varchar(255),
    password        varchar(255)    NOT NULL,
    registerDate    timestamp       NOT NULL
);

ALTER TABLE userAuth
    ADD CONSTRAINT  pk_user         PRIMARY KEY(id),
    ADD CONSTRAINT  unique_login    UNIQUE(login);