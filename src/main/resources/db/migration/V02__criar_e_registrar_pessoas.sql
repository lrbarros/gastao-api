CREATE TABLE pessoa(
	codigo bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	ativo boolean,
	logradouro varchar(255),
	numero varchar(10),
	complemento varchar(100),
	bairro varchar(100),
	cep varchar(50),
	cidade varchar(255),
	estado varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep , cidade, estado) 
	values('administrador',true,'Rua Teste', '1', null, 'Bairro Teste', '86200000' , 'Londrina', 'Paraná');
insert into pessoa (nome, ativo) values('Usuario1',true);
INSERT INTO pessoa(nome,logradouro,numero,complemento,bairro,cep,cidade,estado,ativo) values('Luiz','Rua 1','1','casa','centro','86200000','Ibiporã','PR',true);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('João','Rua norte','1','serraia','86200000','Ibiporã','PR',true);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('Jose dos Santos','Rua sul','1','saida para londrina','86200000','Ibiporã','PR',true);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('Maria Júlia','Rua Leste','1','Conjunto Henrique Alves Pereira','86200000','Ibiporã','PR',true);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('Ana Beatriz','Rua Oeste','1','JD. Paranoá','86200000','Ibiporã','PR',true);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('Oliveira','Rua das Maritacas','1','Centro','86200000','Ibiporã','PR',true);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('Ondina','Rua dos Pineiros','1','Vila Rosana','86200000','Ibiporã','PR',true);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('Tatiane','Rua das Camélias','1','Centro','86200000','Ibiporã','PR',false);
INSERT INTO pessoa(nome,logradouro,numero,bairro,cep,cidade,estado,ativo) values('Helio','Rua dos mercenários','1','Centro','86200000','Ibiporã','PR',false);