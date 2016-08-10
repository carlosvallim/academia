# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table usuario (
  id_lcto                   bigint auto_increment not null,
  username                  varchar(255),
  email                     varchar(255),
  passwd                    varchar(255),
  fg_ativo                  varchar(255),
  constraint pk_usuario primary key (id_lcto))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

