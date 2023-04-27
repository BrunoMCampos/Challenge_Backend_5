create table if not exists pet (
    id bigint not null primary key auto_increment,
    nome varchar(255) not null,
    idade varchar(10) not null,
    porte varchar(20) not null,
    caracteristicas varchar(30) not null,
    id_abrigo bigint not null,
    url_foto varchar(255) not null,
    adotado boolean not null
);

alter table pet add constraint fk_pet_abrigo foreign key (id_abrigo) references abrigo (id);