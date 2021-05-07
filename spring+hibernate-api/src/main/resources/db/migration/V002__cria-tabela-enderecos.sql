create table enderecos (
	id bigint not null auto_increment,
	logradouro varchar(255) not null,
	numero varchar(10) not null,
	complemento varchar(255) not null,
	bairro varchar (30) not null,
	cidade varchar(30) not null,
	estado varchar(30) not null,
	CEP varchar(8) not null ,
	user varchar(30) not null,

	
	primary key (id)
)

