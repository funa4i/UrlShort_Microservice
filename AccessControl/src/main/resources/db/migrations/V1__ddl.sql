CREATE TABLE role(
    id      BIGSERIAL,
    name    varchar(255)    NOT NULL
);
ALTER TABLE role
    ADD CONSTRAINT  pk_role         PRIMARY KEY(id),
    ADD CONSTRAINT  un_name_role    UNIQUE(name);



CREATE TABLE users(
    id          BIGINT
);
ALTER TABLE users
    ADD CONSTRAINT pk_user     PRIMARY KEY(id);



CREATE TABLE user_role(
    user_id      BIGINT,
    role_id      BIGINT
);

ALTER TABLE user_role
    ADD CONSTRAINT  pk_user_role    PRIMARY KEY(user_id),
    ADD CONSTRAINT  fk_user_role    FOREIGN KEY(role_id)    REFERENCES role(id),
    ADD CONSTRAINT  fk_user_user    FOREIGN KEY(user_id)    REFERENCES users(id) ON DELETE CASCADE;



CREATE TABLE path(
    id      BIGSERIAL,
    name    varchar(1000)       NOT NULL
);
ALTER TABLE path
    ADD CONSTRAINT pk_path          PRIMARY KEY(id),
    ADD CONSTRAINT un_name_path     UNIQUE(name);



CREATE TABLE path_role(
    path_id BIGINT,
    role_id BIGINT
);
ALTER TABLE path_role
    ADD CONSTRAINT  pk_path_role    PRIMARY KEY(path_id, role_id),
    ADD CONSTRAINT  fk_path         FOREIGN KEY(path_id)                REFERENCES path(id),
    ADD CONSTRAINT  fk_role         FOREIGN KEY(role_id)                REFERENCES role(id);


