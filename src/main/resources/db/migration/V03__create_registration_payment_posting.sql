CREATE TABLE payment_posting (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    expiration_date DATE NOT NULL,
    payment_date DATE,
    amount DECIMAL(10,2) NOT NULL,
    note VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    category_code BIGINT(20) NOT NULL,
    people_code BIGINT(20) NOT NULL,
    FOREIGN KEY (category_code) REFERENCES category(id),
    FOREIGN KEY (people_code) REFERENCES people(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'INCOME', 1, 1);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Top Club', '2017-06-10', null, 120, null, 'INCOME', 3, 1);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'INCOME', 3, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, 1);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'INCOME', 4, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Bahamas', '2017-06-10', null, 500, null, 'INCOME', 1, 1);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENSE', 4, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'EXPENSE', 3, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'INCOME', 5, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Café', '2017-06-10', null, 8.32, null, 'EXPENSE', 1, 1);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENSE', 5, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'EXPENSE', 4, 1);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENSE', 4, 2);
INSERT INTO payment_posting (description, expiration_date, payment_date, amount, note, type, category_code, people_code) values ('Lanche', '2017-06-10', null, 10.20, null, 'EXPENSE', 4, 1);