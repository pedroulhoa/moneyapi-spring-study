CREATE TABLE people
(
    id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(50) NOT NULL,
    active       BIT         NOT NULL,
    public_place VARCHAR(80),
    number       VARCHAR(10),
    complement   VARCHAR(50),
    district     VARCHAR(80),
    zip_code      VARCHAR(20),
    city         VARCHAR(80),
    country      VARCHAR(50)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO people
(name, active, public_place, number, complement, district, zip_code, city, country)
values ('Pedro', 1, 'teste', '300', 'apt', 'taguatinga', '72150455', 'Brasília', 'DF');

INSERT INTO people
(name, active, public_place, number, complement, district, zip_code, city, country)
values ('João', 1, 'teste', '800', 'apt', 'aguas claras', '72150111', 'Brasília', 'DF');
