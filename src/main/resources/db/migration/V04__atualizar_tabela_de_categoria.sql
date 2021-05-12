ALTER TABLE lancamento DROP FOREIGN KEY `lancamento_ibfk_1`;

ALTER TABLE categoria CHANGE COLUMN codigo id BIGINT(20) AUTO_INCREMENT;

ALTER TABLE lancamento
    ADD CONSTRAINT FOREIGN KEY (codigo_categoria) REFERENCES categoria(id);