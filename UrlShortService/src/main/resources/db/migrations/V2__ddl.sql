CREATE TABLE logCreate(
    user_id     BIGINT,
    url_id      BIGINT,
    timeCreate  TIMESTAMP NOT NULL
);
ALTER TABLE logCreate
    ADD CONSTRAINT pk_logCreate             PRIMARY KEY (user_id, url_id),
    ADD CONSTRAINT fk_logCreate_userId      FOREIGN KEY (user_id)            REFERENCES users(id),
    ADD CONSTRAINT fk_logCreate_urlId       FOREIGN KEY (url_id)             REFERENCES url(id);