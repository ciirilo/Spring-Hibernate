create table usuario (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	email varchar(255) not null,
	CPF varchar(11) not null ,
	dt_nscto date not null,
	
	primary key (id),
	constraint UNK_FIELDS unique(email, CPF)
)
