create table if not exists adocao (
    id bigint not null primary key auto_increment,
    id_pet bigint not null,
    id_tutor bigint not null,
    data date
);

alter table adocao add constraint fk_adocao_pet foreign key (id_pet) references pet (id);
alter table adocao add constraint fk_adocao_tutor foreign key (id_tutor) references tutor (id);