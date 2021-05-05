CREATE TABLE pessoa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BIT NOT NULL,
    logradouro VARCHAR(80),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(80),
    cep VARCHAR(20),
    cidade VARCHAR(80),
    estado VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa
    (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
    values ('Pedro', 1, 'teste', '300', 'apt', 'taguatinga', '72150455', 'Brasília', 'DF');

INSERT INTO pessoa
(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
values ('João', 1, 'teste', '800', 'apt', 'aguas claras', '72150111', 'Brasília', 'DF');
