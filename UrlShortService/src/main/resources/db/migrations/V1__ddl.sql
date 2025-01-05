CREATE TABLE users (
    id  BIGINT
);
ALTER TABLE users
    ADD CONSTRAINT pk_user PRIMARY KEY (id);

CREATE TABLE url (
    id              BIGSERIAL,
    short_url       varchar(7)      NOT NULL,
    full_url        varchar(255)    NOT NULL,
    iterations      INT             NOT NULL,
    valid_until     TIMESTAMP       NOT NULL,
    user_id         BIGINT          NOT NULL
);

ALTER TABLE url
    ADD CONSTRAINT pk_url               PRIMARY KEY (id),
    ADD CONSTRAINT fk_url_user_id       FOREIGN KEY (user_id) REFERENCES users(id);

