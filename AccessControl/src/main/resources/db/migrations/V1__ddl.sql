CREATE TABLE role(
    id      BIGSERIAL,
    name    varchar(255)    NOT NULL
);

ALTER TABLE role
    ADD CONSTRAINT  pk_role  PRIMARY KEY(id);

CREATE TABLE users(
    id          BIGINT,
    role_id     BIGINT      NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT pk_user_role     PRIMARY KEY(id),
    ADD CONSTRAINT fk_users_role    FOREIGN KEY(role_id) REFERENCES role(id);


CREATE TABLE path(
    id      BIGSERIAL,
    name    varchar(1000)       NOT NULL
);

ALTER TABLE path
    ADD CONSTRAINT pk_path PRIMARY KEY(id);

CREATE TABLE path_role(
    path_id BIGINT,
    role_id BIGINT
);

ALTER TABLE path_role
    ADD CONSTRAINT  pk_path_role    PRIMARY KEY(path_id, role_id),
    ADD CONSTRAINT  fk_path         FOREIGN KEY(path_id)                REFERENCES path(id),
    ADD CONSTRAINT  fk_role         FOREIGN KEY(role_id)                REFERENCES role(id);


