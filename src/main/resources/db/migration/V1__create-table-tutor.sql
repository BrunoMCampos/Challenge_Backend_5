create table if not exists tutor (
    id bigint not null primary key auto_increment,
    nome varchar(200) not null,
    email varchar(254) not null,
    senha varchar(255) not null,
    urlFoto varchar(255),
    telefone varchar(15),
    cidade varchar(255),
    sobre varchar (500)
)