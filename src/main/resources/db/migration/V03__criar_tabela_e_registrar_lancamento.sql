CREATE TABLE lancamento(
codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
descricao VARCHAR(50) NOT NULL,
data_vencimento DATE NOT NULL,
data_pagamento DATE,
valor DECIMAL(10,2) NOT NULL,
observacao VARCHAR(100),
tipo VARCHAR(20) NULL,
codigo_categoria BIGINT(20) NOT NULL,
codigo_pessoa BIGINT(20) NOT NULL,
FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO lancamento(codigo,descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values
(1,'Salario mensal','2017-06-10', null,6500.00,'Distribuicao de lucros','RECEITA',1,1);

INSERT INTO lancamento(codigo,descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values
(2,'Cupom','2017-06-10', null,6500.00,'Distribuicao de lucros','RECEITA',1,2);


INSERT INTO lancamento(codigo,descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values
(3,'Nota','2017-07-10', null,6500.00,'Distribuicao de lucros','RECEITA',1,3);


INSERT INTO lancamento(codigo,descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values
(4,'Cartao','2017-08-10', null,6500.00,'Distribuicao de lucros','RECEITA',2,2);




