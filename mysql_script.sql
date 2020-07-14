drop schema cetriolo;
create schema cetriolo;
 
use cetriolo;

-- create user 'user' identified by 'pass123';

-- grant select, insert, delete, update on avaliacao.* to user;

create table usu_usuario (
	id bigint unsigned primary key auto_increment,
    usu_nome varchar(50) not null,
    usu_email varchar(50) not null,
    usu_telefone varchar(20)
);

create table ori_orientador (
	ori_id bigint unsigned primary key,
	constraint ori_usu_id foreign key (ori_id) references usu_usuario (id)
);

create table alu_aluno (
	alu_id bigint unsigned primary key,
	alu_curso varchar(30) not null,
	ori_usu_id bigint unsigned,
	constraint alu_usu_id foreign key (alu_id) references usu_usuario (id),
	constraint ori_usu_id_fk foreign key (ori_usu_id) references ori_orientador (ori_id)
);

create table mat_materia (
	mat_id bigint unsigned primary key auto_increment,
	mat_nome varchar(50) not null
);

create table ama_aluno_materia (
	alu_id bigint unsigned,
	mat_id bigint unsigned,
	primary key (alu_id, mat_id),
	constraint ama_alu_fk foreign key (alu_id) references alu_aluno (alu_id),
	constraint ama_mat_fk foreign key (mat_id) references mat_materia (mat_id)
);


select * from usu_usuario;
select * from  ori_orientador;
select * from  alu_aluno;
select * from  mat_materia;
select * from  ama_aluno_materia;

-- select id from usu_usuario;

-- drop table usu_usuario;
-- drop table ori_orientador;
-- drop table alu_aluno;
-- drop table mat_materia;
-- drop table ama_aluno_materia;