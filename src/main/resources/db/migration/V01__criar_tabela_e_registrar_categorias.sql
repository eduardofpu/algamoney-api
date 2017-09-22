CREATE TABLE categoria(
   codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL 
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO categoria (codigo,nome) values (1,'Lazer');
INSERT INTO categoria (codigo,nome) values (2,'Alimentacao');
INSERT INTO categoria (codigo,nome) values (3,'Supermecado');
INSERT INTO categoria (codigo,nome) values (4,'Farmacia');
INSERT INTO categoria (codigo,nome) values (5,'Outros');
