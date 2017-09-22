CREATE TABLE pessoa(
   codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,   
   logradouro VARCHAR(50) NULL,   
   numero VARCHAR(50) NULL,   
   complemento VARCHAR(50) NULL,   
   bairro VARCHAR(50) NULL,   
   cep VARCHAR(50) NULL,   
   cidade VARCHAR(50) NULL,   
   estado VARCHAR(50) NULL,   
   ativo  tinyint(1) NOT NULL    
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO pessoa (codigo,nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values (1,'Joao','rua x','1','casa','Laranjeiras','38410-230','Uberlandia','MG','1');
INSERT INTO pessoa (codigo,nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values (2,'Roberto','rua z','33','casa','Santa Monica','38420-240','Uberlandia','MG','1');
INSERT INTO pessoa (codigo,nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values (3,'Eliza','rua w','44','casa','Tyberi','38310-245','Uberlandia','MG','1');
INSERT INTO pessoa (codigo,nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values (4,'Vagner','rua k','5','casa','Brasil','38210-255','Uberlandia','MG','1');