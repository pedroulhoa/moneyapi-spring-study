CREATE TABLE user
(
    id       BIGINT(20) PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(150) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE permission
(
    id          BIGINT(20) PRIMARY KEY,
    description VARCHAR(50) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE permission_user
(
    user_code       BIGINT(20) NOT NULL,
    permission_code BIGINT(20) NOT NULL,
    PRIMARY KEY (user_code, permission_code),
    FOREIGN KEY (user_code) REFERENCES user (id),
    FOREIGN KEY (permission_code) REFERENCES permission (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user (id, name, email, password)
values (1, 'Administrador', 'admin@moneyapi.com', '$2a$10$4UuHWMyxj3WdKh7DcvYOP.MVK/pgl4tfNwqAXG/tbPD8z9KF/ScLi');
INSERT INTO user (id, name, email, password)
values (2, 'Pedro', 'pedro@moneyapi.com', '$2a$10$4UuHWMyxj3WdKh7DcvYOP.MVK/pgl4tfNwqAXG/tbPD8z9KF/ScLi');

INSERT INTO permission (id, description)
values (1, 'ROLE_CREATE_CATEGORY');
INSERT INTO permission (id, description)
values (2, 'ROLE_SEARCH_CATEGORY');

INSERT INTO permission (id, description)
values (3, 'ROLE_CREATE_PEOPLE');
INSERT INTO permission (id, description)
values (4, 'ROLE_REMOVE_PEOPLE');
INSERT INTO permission (id, description)
values (5, 'ROLE_SEARCH_PEOPLE');

INSERT INTO permission (id, description)
values (6, 'ROLE_CREATE_PAYMENTPOSTING');
INSERT INTO permission (id, description)
values (7, 'ROLE_REMOVE_PAYMENTPOSTING');
INSERT INTO permission (id, description)
values (8, 'ROLE_SEARCH_PAYMENTPOSTING');

-- admin
INSERT INTO permission_user (user_code, permission_code)
values (1, 1);
INSERT INTO permission_user (user_code, permission_code)
values (1, 2);
INSERT INTO permission_user (user_code, permission_code)
values (1, 3);
INSERT INTO permission_user (user_code, permission_code)
values (1, 4);
INSERT INTO permission_user (user_code, permission_code)
values (1, 5);
INSERT INTO permission_user (user_code, permission_code)
values (1, 6);
INSERT INTO permission_user (user_code, permission_code)
values (1, 7);
INSERT INTO permission_user (user_code, permission_code)
values (1, 8);

-- pedro
INSERT INTO permission_user (user_code, permission_code)
values (2, 2);
INSERT INTO permission_user (user_code, permission_code)
values (2, 5);
INSERT INTO permission_user (user_code, permission_code)
values (2, 8);