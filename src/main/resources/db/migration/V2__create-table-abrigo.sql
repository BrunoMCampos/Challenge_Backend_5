create table if not exists abrigo (
    id bigint not null primary key auto_increment,
    nome varchar(200) not null,
    email varchar(255) not null,
    estado varchar(2) not null,
    cidade varchar(255) not null,
    bairro varchar(255) not null,
    rua varchar (255) not null,
    numero varchar(10),
    telefone varchar(15),
    celular varchar(15)
)