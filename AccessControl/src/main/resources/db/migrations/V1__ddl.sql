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


CREATE TABLE paths(
    id      BIGSERIAL,
    name    varchar(1000)       NOT NULL,
);

ALTER TABLE paths
    ADD CONSTRAINT pk_paths PRIMARY KEY(id);

CREATE TABLE paths_role(
    path_id BIGINT,
    role_id BIGINT
);

ALTER TABLE paths_role
    ADD CONSTRAINT pk_paths_role PRIMARY KEY (paths_role, role_id);


