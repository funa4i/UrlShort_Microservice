CREATE TABLE role(
    id      BIGSERIAL       PRIMARY KEY,
    name    varchar(255)    NOT NULL
);
ALTER TABLE role
    ADD CONSTRAINT  un_name_role    UNIQUE(name);


CREATE TABLE users(
    id      BIGINT  PRIMARY KEY
);


CREATE TABLE user_role(
    user_id      BIGINT PRIMARY KEY,
    role_id      BIGINT
);
ALTER TABLE user_role
    ADD CONSTRAINT  fk_user_role    FOREIGN KEY(role_id)    REFERENCES role(id),
    ADD CONSTRAINT  fk_user_user    FOREIGN KEY(user_id)    REFERENCES users(id) ON DELETE CASCADE;


CREATE TABLE path(
    id      BIGSERIAL           PRIMARY KEY,
    method  varchar(25)         NOT NULL,
    name    varchar(255)        NOT NULL     UNIQUE
);
ALTER TABLE path
    ADD CONSTRAINT un_name_path     UNIQUE(name);


CREATE TABLE path_role(
    path_id BIGINT,
    role_id BIGINT
);
ALTER TABLE path_role
    ADD CONSTRAINT  pk_path_role    PRIMARY KEY(path_id, role_id),
    ADD CONSTRAINT  fk_path         FOREIGN KEY(path_id)                REFERENCES path(id),
    ADD CONSTRAINT  fk_role         FOREIGN KEY(role_id)                REFERENCES role(id);
